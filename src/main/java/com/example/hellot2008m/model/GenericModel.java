package com.example.hellot2008m.model;

import com.example.hellot2008m.annotation.Column;
import com.example.hellot2008m.annotation.Table;
import com.example.hellot2008m.util.ConnectionHelper;
import com.example.hellot2008m.util.SQLConfig;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenericModel<T> {
    private final Class<?> clazz;

    public static String tableName;

    private static Connection connection;

    static {
        try {
            connection = ConnectionHelper.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public GenericModel(Class<?> clazz) {
        this.clazz = clazz;

        if (this.clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getDeclaredAnnotation(Table.class);
            if (table.name().length() > 0) {
                tableName = table.name();
            } else {
                tableName = clazz.getSimpleName() + "s";
            }
        }
    }

    public boolean save(T obj) {
        StringBuilder sqlStringBuilder = new StringBuilder();
        StringBuilder fieldsStringBuilder = new StringBuilder();
        StringBuilder fieldsValueBuilder = new StringBuilder();

        sqlStringBuilder.append(SQLConfig.INSERT);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(tableName);

        Field[] fields = clazz.getDeclaredFields();
        sqlStringBuilder.append(SQLConfig.SPACE);
        fieldsStringBuilder.append(SQLConfig.OPEN_BRACKET);
        fieldsValueBuilder.append(SQLConfig.OPEN_BRACKET);

        for (Field f : fields
        ) {
            try {
                if (!f.isAnnotationPresent(Column.class)) {
                    continue;
                }
                Column column = f.getDeclaredAnnotation(Column.class);

                fieldsStringBuilder.append(column.name());
                fieldsStringBuilder.append(SQLConfig.COMMA);

                f.setAccessible(true);
                if (column.type().contains("VARCHAR") || column.type().contains("TEXT")) {
                    fieldsValueBuilder.append(SQLConfig.PARENTHESIS);
                    fieldsValueBuilder.append(f.get(obj));
                    fieldsValueBuilder.append(SQLConfig.PARENTHESIS);
                } else {
                    fieldsValueBuilder.append(f.get(obj));
                }
                fieldsValueBuilder.append(SQLConfig.COMMA);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        fieldsStringBuilder.setLength(fieldsStringBuilder.length() - 1);
        fieldsValueBuilder.setLength(fieldsValueBuilder.length() - 1);
        fieldsValueBuilder.append(SQLConfig.CLOSE_BRACKET);
        fieldsStringBuilder.append(SQLConfig.CLOSE_BRACKET);

        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(fieldsStringBuilder);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(SQLConfig.VALUES);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(fieldsValueBuilder);

        try {
            Statement stt = connection.createStatement();
            stt.execute(sqlStringBuilder.toString());
            System.out.println("Action success");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public List<T> getAll() {
        List<T> list = new ArrayList<>();

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append(SQLConfig.SELECT);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(SQLConfig.STAR);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(SQLConfig.FROM);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(tableName);

        System.out.println(sqlStringBuilder);

        try {
            Statement stt = connection.createStatement();
            ResultSet resultSet = stt.executeQuery(sqlStringBuilder.toString());
            while (resultSet.next()) {
                T obj = null;
                try {
                    obj = (T) clazz.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                loadResultSetIntoObject(resultSet, obj);
                list.add(obj);
            }
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Unable to get the records: " + e.getMessage(), e);
        }

        return list;
    }


    public static void loadResultSetIntoObject(ResultSet resultSet, Object obj) throws IllegalArgumentException, IllegalAccessException, SQLException {
        Class<?> clazz = obj.getClass();
        for (Field f : clazz.getDeclaredFields()
        ) {
            f.setAccessible(true);

            if (!f.isAnnotationPresent(Column.class)) {
                continue;
            }

            Column column = f.getDeclaredAnnotation(Column.class);
            Object value = resultSet.getObject(column.name());
            Class<?> type = f.getType();
            if (type.isPrimitive()) {
                Class<?> boxed = boxPrimitiveClass(type);
                value = boxed.cast(value);
            }
            f.set(obj, value);
        }
    }

    public static Class<?> boxPrimitiveClass(Class<?> clazz) {
        if (clazz == int.class) {
            return Integer.class;
        } else if (clazz == long.class) {
            return Long.class;
        } else if (clazz == double.class) {
            return Double.class;
        } else if (clazz == float.class) {
            return Float.class;
        } else if (clazz == byte.class) {
            return Byte.class;
        } else if (clazz == char.class) {
            return Character.class;
        } else if (clazz == short.class) {
            return Short.class;
        } else {
            String string = "class '" + clazz.getName() + "' is not a primitive";
            throw new IllegalArgumentException(string);
        }
    }

    public T findById(int id) {
        T obj = null;

        String sqlStringBuilder = SQLConfig.SELECT +
                SQLConfig.SPACE +
                SQLConfig.STAR +
                SQLConfig.SPACE +
                SQLConfig.FROM +
                SQLConfig.SPACE +
                tableName +
                SQLConfig.SPACE +
                SQLConfig.WHERE +
                SQLConfig.SPACE +
                "id" +
                SQLConfig.EQUAL_SIGN +
                id;
        System.out.println(sqlStringBuilder);

        try {
            Statement stt = connection.createStatement();
            ResultSet resultSet = stt.executeQuery(sqlStringBuilder);
            if (resultSet.next()) {
                try {
                    obj = (T) clazz.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                loadResultSetIntoObject(resultSet, obj);
                return obj;
            }
            System.out.println("Action success");
        } catch (SQLException | IllegalAccessException e) {
            System.err.println(e.getMessage());
        }

        return obj;
    }

    public boolean update(int id, T obj) {
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder sqlStringBuilder = new StringBuilder();

        sqlStringBuilder.append(SQLConfig.UPDATE);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(tableName);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(SQLConfig.SET);
        sqlStringBuilder.append(SQLConfig.SPACE);

        for (Field field : fields
        ) {
            try {
                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }
                Column column = field.getDeclaredAnnotation(Column.class);

                sqlStringBuilder.append(column.name());
                sqlStringBuilder.append(SQLConfig.EQUAL_SIGN);
                field.setAccessible(true);
                if (column.type().contains("VARCHAR") || column.type().contains("TEXT")) {
                    sqlStringBuilder.append(SQLConfig.PARENTHESIS);
                    sqlStringBuilder.append(field.get(obj));
                    sqlStringBuilder.append(SQLConfig.PARENTHESIS);
                } else {
                    sqlStringBuilder.append(field.get(obj));
                }
                sqlStringBuilder.append(SQLConfig.COMMA);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        sqlStringBuilder.setLength(sqlStringBuilder.length() - 1);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append(SQLConfig.WHERE);
        sqlStringBuilder.append(SQLConfig.SPACE);
        sqlStringBuilder.append("id");
        sqlStringBuilder.append(SQLConfig.EQUAL_SIGN);
        sqlStringBuilder.append(id);

        System.out.println(sqlStringBuilder.toString());

        try {
            Statement stt = connection.createStatement();
            stt.execute(sqlStringBuilder.toString());
            System.out.println("Action success");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

    public boolean delete(int id) {
        String sqlStringBuilder = SQLConfig.DELETE +
                SQLConfig.SPACE +
                tableName +
                SQLConfig.SPACE +
                SQLConfig.WHERE +
                SQLConfig.SPACE +
                "id" +
                SQLConfig.EQUAL_SIGN +
                id;
        System.out.println(sqlStringBuilder);

        try {
            Statement stt = connection.createStatement();
            stt.execute(sqlStringBuilder);
            System.out.println("Action success");
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }
}
