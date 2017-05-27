'use strict';

// Declare app level module which depends on others
angular.module('app', ['ngResource', 'ngRoute'])
  .constant('apiUrl', 'http://localhost:8080/api')
  .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
    $routeProvider
      .when('/', {templateUrl: '/html/main.html', controller: 'IndexCtrl'});
    $locationProvider.html5Mode(true).hashPrefix('!');
  }])

// index page
.controller('IndexCtrl', ['$scope', '$resource', 'apiUrl', function($scope, $resource, apiUrl) {
  $scope.search = function() {
    $scope.answers = $resource(apiUrl+'/answers/:question', {question: $scope.searchQuery}).query();
  }
}]);