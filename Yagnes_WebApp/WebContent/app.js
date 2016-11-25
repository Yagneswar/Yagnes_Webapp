var app = angular.module('myBlogApp',['ngRoute','ngResource']);



app.config(function($routeProvider){
  $routeProvider.when('/',{
    templateUrl: 'public/views/about.html',
    controller: 'BlogController'
  })
   .when('/admin',{
     templateUrl: 'public/views/admin_page.html',
      controller: 'AdminController',

 }).when('/login',{
     templateUrl: 'public/views/login.html',
      controller: 'LoginController',

 }).when('/signup',{
     templateUrl: 'public/views/signup.html',
      controller: 'UserController',

 }).when('/profile',{
     templateUrl: 'public/views/user_profile.html',
      controller: 'ProfileController',

 });
});
