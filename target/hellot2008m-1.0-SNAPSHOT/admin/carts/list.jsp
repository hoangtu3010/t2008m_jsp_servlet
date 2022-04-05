<%@ page import="com.example.hellot2008m.entity.ShoppingCart" %>
<%@ page import="com.example.hellot2008m.entity.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    ShoppingCart shoppingCart = (ShoppingCart) request.getAttribute("shoppingCart");
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
    <title>List Carts</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">List Carts</h1>
    <a class="btn btn-primary mb-5" href="/products/list">Continue Shopping</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Thumbnail</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
<%--            <th>Action</th>--%>
        </tr>
        </thead>
        <tbody>
        <%
            ArrayList<CartItem> cartItems = shoppingCart.getListItems();
            for (int i = 0; i < shoppingCart.getListItems().size(); i++){
                CartItem obj = shoppingCart.getListItems().get(i);
        %>
        <tr>
            <th scope="row"><%= obj.getProductId() %>
            </th>
            <td><%= obj.getProductName() %>
            </td>
            <td><img src="<%= obj.getProductThumbnail() %>" width="80">
            </td>
            <td><%= obj.getUnitPrice() %>
            <td><%= obj.getQuantity() %>
            </td>

<%--            <td><a href="/students/edit?id=<%= p.getId() %>" type="button" class="btn btn-info">Edit</a> <a--%>
<%--                    href="/students/delete?id=<%= p.getId() %>" type="button" class="btn btn-danger">Delete</a></td>--%>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>
</div>
</body>
</html>