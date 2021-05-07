# Spring Domain Driven Design Boilerplate
This project describes some particular product related operations such as create and get, also covered by Domain Driven Design on Spring Framework  

## Table of Contents

- [Modules](#domain-module)
    - [Domain Module](#domain-module)
    - [Application Module](#application-module)
    - [Infrastructure Module](#infrastructure-module)
- [Swagger and Postman](#swagger-and-postman)

<br>

<p align="center">
  <img src="/assets/DomainDrivenDesign.png" alt="Domain Driven Design" />
</p>

## Domain Module
The Domain module is a core module which contains main business logics. For example; Entities, Repositories, Services, Exceptions, Value Objects and Util Classes is in this module. Let's assume, Most of this module won't change when we decided to migrate any other framework or structure.

## Application Module
You could think Application module as a bridge in between the domain and other modules (infrastructure and scheduler) This module contains Managers, Converters and Models (Request and Response)

* Manager: It will validate and apply the particular process such as validate an existing product before get product operation.
* Converter: It will convert from request or entity models to Value Object, therefore domain module just knows value objects and entities not request and response.

## Infrastructure Module
The Infrastructure module is a door that opening to out of the world. 
<br>
Some important request and response data model as following such as create and get product operations. For more information you can take a look at swagger or postman collections.

## Swagger and Postman
