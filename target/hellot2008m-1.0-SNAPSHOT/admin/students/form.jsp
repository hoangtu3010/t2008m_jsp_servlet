<%@ page import="com.example.hellot2008m.entity.Student" %>
<%
    Student obj = (Student) request.getAttribute("obj");
%>

<%@ page import="java.util.HashMap" %>
<%
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");

    if (errors == null) {
        errors = new HashMap<>();
    }
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title>Create new students</title>
</head>
<body>
    <div class="container">
        <h1 class="text-center"><p><%= obj.getId() == 0 ? "Create new student" : "Edit student" %>
        </p>
        </h1>
        <a href="/students/list" class="btn btn-secondary mb-5">Back to list</a>
        <% if (errors.size() > 0) { %>
        <div class="alert alert-danger" role="alert">
            Please enter information below.
            <ul>
                <% for (String message : errors.values()
                ) {
                %>
                <li><%=message%>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
        <% } %>
        <form action="<%= obj.getId() == 0 ? "/students/create" : "/students/edit" %>" method="post">
            <div class="mb-3">
                <label class="form-label">Roll Name</label>
                <input type="hidden" name="id" value="<%= obj.getId() %>">
                <input type="text" name="rollName" class="form-control"
                       value="<%= obj.getRollName() != null ? obj.getRollName() : "" %>">
                <% if (errors.containsKey("rollName")) { %>
                <p class="text-danger mt-2"><%=errors.get("rollName")%></p>
                <%
                    }
                %>
            </div>
            <div class="mb-3">
                <label class="form-label">Name</label>
                <input type="text" name="name" class="form-control"
                       value="<%= obj.getName() != null ? obj.getName() : "" %>">
                <% if (errors.containsKey("name")) { %>
                <p class="text-danger mt-2"><%=errors.get("name")%></p>
                <%
                    }
                %>
            </div>
            <div class="mb-3">
                <label class="form-label">Email address</label>
                <input type="email" name="email" class="form-control" aria-describedby="emailHelp"
                       value="<%= obj.getEmail() != null ? obj.getEmail() : "" %>">
                <% if (errors.containsKey("email")) { %>
                <p class="text-danger mt-2"><%=errors.get("email")%></p>
                <%
                    }
                %>
            </div>
            <button type="submit" class="btn btn-primary"><%= obj.getId() == 0 ? "Submit" : "Save" %>
            </button>
            <button type="reset" class="btn btn-default">Reset</button>
        </form>
    </div>
</body>
</html>
