'use strict';

var host = 'http://localhost:8080/rest-tech-talk/rest';
var app = angular.module('restApp', ['ngRoute']);

app.config(function($routeProvider) {

  $routeProvider
    .when('/', {
      templateUrl: 'views/rest-demo.html'
    });
});