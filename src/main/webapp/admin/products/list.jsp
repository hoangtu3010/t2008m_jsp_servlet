<%@ page import="com.example.hellot2008m.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%
    HashSet<Product> products = (HashSet<Product>) session.getAttribute("recentViewStudent");
    if (products == null) {
        products = new HashSet<>();
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <title>List Products</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">List Product</h1>
    <div class="d-flex justify-content-end mb-5">
        <a class="btn btn-success" href="/products/create"><i class="bi bi-plus-circle"></i> Create</a>
        <a class="btn btn-primary ms-3" href="/carts/list"><i class="bi bi-cart2"></i> Cart</a>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Thumbnail</th>
            <th scope="col">Price</th>
            <th class="text-center" scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Product p : (List<Product>) request.getAttribute("listProducts")
            ) {
        %>
        <tr>
            <th scope="row"><%= p.getId() %>
            </th>
            <td><%= p.getName() %>
            </td>
            <td><img src="<%= p.getThumbnail() %>" width="80">
            </td>
            <td><%= p.getPrice() %>
            </td>
            <td class="text-center"><a href="/carts/add?productId=<%= p.getId()%>&quantity=1" class="btn btn-info"><i
                    class="bi bi-cart-plus"></i></a>
                <a href="/products/edit?id=<%= p.getId() %>" type="button" class="btn btn-secondary"><i
                        class="bi bi-pencil-square"></i></a> <a
                        href="/products/delete?id=<%= p.getId() %>" type="button" class="btn btn-danger btn-delete"><i
                        class="bi bi-trash3"></i></a>
            </td>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>
    <div class="d-flex">
        <% for (Product product : products) { %>
        <div class="card" style="width: 18rem;">
            <img src="<%=product.getThumbnail()%>" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title"><%=product.getName()%>
                </h5>
                <p class="card-text"><%=product.getPrice()%>
                </p>
                <a href="#" class="btn btn-primary">Detail</a>
            </div>
        </div>
        <%
            }
        %>
    </div>
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
                    xhr.open('GET', this.href, false);
                    xhr.send();
                }
            })
        }
    })
</script>