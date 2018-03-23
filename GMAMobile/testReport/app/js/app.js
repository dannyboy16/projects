'use strict';
/* App Module */
var testApp = angular.module('testApp', [
  'ngRoute',
  'testControllers',
  'testFilters',
  'ui.bootstrap',
  'ngAnimate',
  'i18n'
]);

testApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/tests/:runid', {
        templateUrl: 'app/partials/test-list.html',
        controller: 'TestListCtrl'
      }).
      when('/', {
        templateUrl: 'app/partials/test-list.html',
        controller: 'TestListCtrl'
      }).
      when('/tests/:runid/:testId', {
        templateUrl: 'app/partials/test-detail.html',
        controller: 'TestDetailCtrl'
      }).
      when('/builds', {
        templateUrl: 'app/partials/runList.html',
        controller: 'buildCtrl'
      }).
      otherwise({
        redirectTo: '/builds'
      });
  }]);
