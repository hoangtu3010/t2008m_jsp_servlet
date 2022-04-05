<%@ page import="com.example.hellot2008m.entity.Product" %>
<%
    Product obj = (Product) request.getAttribute("obj");
%>

<%--<%@ page import="java.util.HashMap" %>--%>
<%--<%--%>
<%--    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");--%>

<%--    if (errors == null) {--%>
<%--        errors = new HashMap<>();--%>
<%--    }--%>
<%--%>--%>

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
    <title>Create new products</title>
</head>
<body>
    <div class="container">
        <h1 class="text-center"><p><%= obj.getId() == 0 ? "Create new product" : "Edit product" %>
        </p>
        </h1>
        <a href="/products/list" class="btn btn-secondary mb-5">Back to list</a>

        <form action="<%= obj.getId() == 0 ? "/products/create" : "/products/edit" %>" method="post">
            <div class="mb-3">
                <label class="form-label">Name</label>
                <input type="hidden" name="id" value="<%= obj.getId() %>">
                <input type="text" name="name" class="form-control"
                       value="<%= obj.getName() != null ? obj.getName() : "" %>">
            </div>
            <div class="mb-3">
                <label class="form-label">Thumbnail</label>
                <input type="text" name="thumbnail" class="form-control"
                       value="<%= obj.getThumbnail() != null ? obj.getThumbnail() : "" %>">
            </div>
            <div class="mb-3">
                <label class="form-label">Price</label>
                <input type="number" name="price" class="form-control" aria-describedby="emailHelp"
                       value="<%= obj.getPrice() != 0 ? obj.getPrice() : 0 %>">
            </div>
            <button type="submit" class="btn btn-primary"><%= obj.getId() == 0 ? "Submit" : "Save" %>
            </button>
            <button type="reset" class="btn btn-default">Reset</button>
        </form>
    </div>
</body>
</html>
