<p align="center">
 <img src="/assets/DomainDrivenDesign.png" align="center" alt="Spring Domain Driven Design" />
 <h2 align="center">Spring Domain Driven Design Boilerplate</h2>
 <p align="center">This project describes some product operations such as create and get, also covered by Domain Driven Design on Spring Framework</p>

## Table of Contents

- [Introduction](#introduction)
- [Tech Stacks](#tech-stacks)
- [Acknowledgment](#acknowledgment)

### Introduction

* `Domain` module is a core module which contains main business logics. For example; Entities, Repositories, Services, Exceptions, Value Objects and Util Classes is in this module.
* `Application` module could think as a bridge in between the domain and other modules (infrastructure and scheduler) This module contains Managers, Converters and Models (Request and Response)
* `Infrastructure` module is a door that opening to out of the world. Some important request and response data model as following such as create and get product operations. For more information you can take a look at swagger or postman collections.

### Tech Stacks
 * Java 17
 * Spring 3.0.x
 * Gradle 7.6.x
 * Flyway
 * Postgres
 * Testcontainer
 * jUnit5 and Mockito 5.2.x

### Acknowledgment
Thanks to my colleagues to share their know-how about domain driven design. Also, They've made a another boilerplate project about Quarkus, you can cast an eye [here](https://github.com/dolap-tech/quarkus-ddd-boilerplate) if you interested in this topic.

* [Bedirhan Atasoy](https://github.com/bedirhanatasoy)
* [Ozer Cevikaslan](https://github.com/ozer)
