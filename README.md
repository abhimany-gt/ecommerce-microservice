# Ecommerce Microservices

## 1.After cloning the repository run services in following sequence
##### 1.eureka-service:8761
##### 2.zipkin-service:9411
##### 3.turbine-service:8999
##### 4.user-service:7771
##### 5.zuul-service:8762
##### 6.product-service:7772
##### 7.cart-service:7773
##### 8.order-service:7774

## 2.View Eureka Registry at url http://localhost:8761 in Google Chrome or Firefox browser 


## 3.SignUp and Get jwt token from POST http://localhost:8762/users/signup Using Postman
  Request Body:--
  
  {
  "username": "rambo123",
  "email": "rmb-john@gmail.com",
  "password": "password",
  "roles": [
    "ADMIN"
  ]
  }
  
  Min username length = 4,Min password length = 8,email and username should be unique
  
  Respone Body:--
 eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbWl0MTIzIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiQURNSU4ifV0sImlhdCI6MTU5MTc4MTg1MywiZXhwIjoxNTkxODExODUzfQ.7rmSxkvONn-ANpSXpA7yTFUwYiHsb8ygbmtml_WXRmo
 
###  Also User can signin at url POST http://localhost:8762/users/signin with below Request Body to get above token 
  
  Request Body:-
       {"username":"rambo123",
         "password":"passwords"}
 
  
## 4.Get All Products GET http://localhost:8762/products/all Using Postman
  
  Authorisation scheme :Bearer token,
  copy jwt in token field
  
  Response Body:
  
  [
    {
        "id": 1,
        "code": "123A",
        "name": "BOOK-1",
        "description": "Cook-Book",
        "price": 100.0,
        "inStock": true
    }
  ]
  
## 5.Get Products by id GET http://localhost:8762/products/{id}  Using Postman
  
  Example URL:--
     GET http://localhost:8762/products/1
     
  Authorisation scheme :Bearer token,
  copy jwt in token field
  
  Response Body:
  
  [
    {
        "id": 1,
        "code": "123A",
        "name": "BOOK-1",
        "description": "Cook-Book",
        "price": 100.0,
        "inStock": true
    }
  ]
  
## 6.Add Products http://localhost:8762/products/add requiring role as ADMIN  Using Postman
 
  Authorisation scheme :Bearer token,
  copy jwt in token field
  
 Request Body:-
 
 {
    "code": "123A",
    "name": "BOOK-1",
    "description": "Cook-Book",
    "price": 100.0,
    "inStock": true
}

Response Body:--

{
    "id": 6,
    "code": "123A",
    "name": "BOOK-1",
    "description": "Cook-Book",
    "price": 100.0,
    "inStock": true
}

##### Try creating user with CLIENT role to get response as unauthorised.

## 7. Add Product in cart at url http://localhost:8762/cart/product using Postman Post httpmethod

  Authorisation scheme :Bearer token,
  copy jwt in token field
  
  Request Body:--
  
  {
  
   "productId" :"1"
   
  }
  
  Response Body:--
  
  {
    "cartId": 1,
    "productId": 1,
    "userName": "rambo123",
    "price": 110.0
}
  
  ## 8. Delete product in cart at url  http://localhost:8762/cart/product/{id} using Postman Delete httpmethod
  
  Example url:
  http://localhost:8762/cart/product/1  id is cartId
  
  Authorisation scheme :Bearer token,
  copy jwt in token field
  

  
 ## 9. Create order of product at url http://localhost:8762/order/product/ using Postman Post httpmethod
 
   Authorisation scheme :Bearer token,
   copy jwt in token field
   
   Request Body:--
   
   {
   
   "productId":"1"
   
   }
   
   
   Response Body:--
   
   {
    "orderId": 1,
    "cartId": 1,
    "userName": "rambo123",
    "total": 110.0
    }
  

## 10.View turbine stream at url http://localhost:8999/turbine.stream in Google Chrome or Firefox browser

## 11.View log tracing Zipkin Server at url http://localhost:9411/ in Google Chrome or Firefox browser

## 12.View swagger api of user service at http://localhost:7771/swagger-ui.html to get view of extra endpoints available
