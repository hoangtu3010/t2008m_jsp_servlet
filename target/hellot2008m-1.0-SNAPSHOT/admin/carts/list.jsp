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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <title>List Carts</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">Shopping Carts</h1>
    <a class="btn btn-primary mb-5" href="/products/list">Continue Shopping</a>
    <%
        if (session.getAttribute("message") != null) {
    %>
    <div class="alert alert-success" role="alert">
        <%= session.getAttribute("message") %>
    </div>
    <%
            session.removeAttribute("message");
        }
    %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Thumbnail</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            ArrayList<CartItem> cartItems = shoppingCart.getListItems();
            for (CartItem item : cartItems
            ) {
        %>
        <tr>
            <th scope="row"><%= item.getProductId() %>
            </th>
            <td><%= item.getProductName() %>
            </td>
            <td><img src="<%= item.getProductThumbnail() %>" width="80">
            </td>
            <td><%= item.getUnitPrice() %>
            <td><%= item.getQuantity() %>
            </td>

            <td>
                <a href="/carts/delete?id=<%= item.getProductId() %>" type="button" class="btn btn-danger btn-delete"><i
                        class="bi bi-trash3"></i></a>
            </td>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>
</div>
</body>
</html>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        let listDeleteButton = document.querySelectorAll('.btn-delete');
        for (let i = 0; i < listDeleteButton.length; i++){
            listDeleteButton[i].addEventListener("click", function (event){
                event.preventDefault();
                if (confirm("Are you sure?")){
                    let xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function (){
                        if (xhr.readyState === 4 && xhr.status === 200){
                            alert('Delete Success!');
                            window.location.reload();
                        }
                    }
                    xhr.open('DELETE', this.href, false);
                    xhr.send();
                }
            })
        }
    })
</script>