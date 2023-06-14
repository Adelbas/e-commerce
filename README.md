# e-commerce MVC
E-commerce shop wtih roles
## Description
### User options:
* Register
* Authorize (with Session Token)
* Add products to cart (in process)
* Remove products from cart (in process)
### Admin options:
* Create/update/remove categories
* Create/update/remove products
* Ban users (in process)
## Stack
* Spring (MVC, Web, Data JPA, Security, JDBC Session)
* Lombok
* HTML, CSS, Thymeleaf
* PostgreSQL
## Endpoints:
### User:
* GET /
* GET /products
* GET /products/id
* GET /account
* GET /login
* GET /registration
* GET /cart
* POST /login
* POST /registration
* POST /add/id
### Admin:
* GET /products/list
* GET /products/create
* GET /category
* GET /category/create
* POST /category/create
* POST /category/delete/id
* POST /products/create
* POST /products/delete/id
