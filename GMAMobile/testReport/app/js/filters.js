'use strict';


angular.module('testFilters',[]).filter('unique', function() {
   return function(collection, keyname) {
      var output = [], 
          keys = [];

      angular.forEach(collection, function(item) {
          var key = item[keyname];
          if(keys.indexOf(key) === -1) {
              keys.push(key);
              output.push(item);
          }
      });

      return output;
   };
}).filter("as", function($parse) {
  return function(value, context, path) {
    return $parse(path).assign(context, value);
  };
});


