'use strict';
/* Services */
var testsServices = angular.module('testsServices', ['ngResource']);
testsServices.factory('Test', ['$resource',
function($resource){
return $resource('test/:testId.json', {}, {
query: {method:'GET', params:{testId:'tests'}, isArray:true}
});
}]);