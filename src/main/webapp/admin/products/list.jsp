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
    <title>List Students</title>
</head>
<body>
<div class="container">
    <h1 class="text-center">List Product</h1>
    <a class="btn btn-primary mb-5" href="/products/create">New Product</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Thumbnail</th>
            <th scope="col">Price</th>
            <th>Action</th>
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
            <td><a href="/carts/add?productId=<%= p.getId()%>&quantity=1" class="btn btn-danger">Add to cart</a>
                <a href="/products/edit?id=<%= p.getId() %>" type="button" class="btn btn-info">Edit</a></td>
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