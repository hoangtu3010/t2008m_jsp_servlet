<%@ page import="com.example.hellot2008m.entity.Student" %>
<%@ page import="java.util.List" %>
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
    <title>List Students</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">List Student</h1>
    <a class="btn btn-primary mb-5" href="/students/create">New Student</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Roll Number</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Student st : (List<Student>) request.getAttribute("listStudents")
            ) {
        %>
        <tr>
            <th scope="row"><%= st.getId() %>
            </th>
            <td><%= st.getRollName() %>
            </td>
            <td><%= st.getName() %>
            </td>
            <td><%= st.getEmail() %>
            </td>
            <td><a href="/students/edit?id=<%= st.getId() %>" type="button" class="btn btn-info">Edit</a> <a
                    href="/students/delete?id=<%= st.getId() %>" type="button" class="btn btn-danger">Delete</a></td>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>

</div>
</body>
</html>