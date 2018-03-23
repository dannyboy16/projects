'use strict';
/* Controllers */

var testControllers = angular.module('testControllers', ['chart.js', 'ui.bootstrap']);
var sortingOrder = 'build'; //default sort


testControllers.controller('buildCtrl', ['$scope', '$filter', '$http', '$timeout', '$rootScope', '$translate',
	function ($scope, $filter, $http, $timeout,$rootScope,$translate) {				
		$scope.labels =[];
		$scope.series = [];
		$scope.data = [[],[],[],[]];
		$scope.sortingOrder = sortingOrder;
		$scope.pageSizes = [5,10,25,50];
		$scope.reverse = true;
		$scope.filteredItems = [];
		$scope.groupedItems = [];
		$scope.itemsPerPage = 10;
		$scope.pagedItems = [];
		$scope.currentPage = 0;
		$scope.items = {};  
		$http.get('tests/builds.json').success(function(data) {				
			$scope.items = data;
			$scope.pagedItems = data;			
			for(var i = 0; i<data.length; i++){
				$scope.labels[i] = 'Ejecucion ' + data[i].build; 
				$scope.series = ['Total Test', 'Passed Test', 'Failed Test', 'Blocked Test'];
				$scope.data [0][i] = data[i].passed +  data[i].failed + data[i].blocked;
				$scope.data [1][i] = data[i].passed;
				$scope.data [2][i] =  data[i].failed;
				$scope.data [3][i] =  data[i].blocked;
			}			
//		});
		
		
		
		// init    

		var searchMatch = function (haystack, needle) {	
			if (!needle) {
			return true;
			}			
			return (haystack + '').toLowerCase().indexOf(needle.toLowerCase()) !== -1;
		};
		
		// init the filtered items
		$scope.search = function () {				
			$scope.filteredItems = $filter('filter')($scope.items, function (item) {								
			for(var attr in item) {						
				if (searchMatch(item[attr], $scope.query))				
					return true;
			}
			return false;
			});
			// take care of the sorting order			
			if ($scope.sortingOrder !== '') {			
			$scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);			
			}
			$scope.currentPage = 0;
			// now group by pages
			$scope.groupToPages();
		};
		
		/*Nueva Funcion de fechas*/
		$scope.searchDate = function () {					
			$scope.filteredItems = $filter('filter')($scope.filteredItems, function (filteredItems) {									
			for(var attr in filteredItems) {		
				if (searchMatch(filteredItems['date'], $scope.query2))
					return true;
			}
			return false;
			});
			// take care of the sorting order			
			if ($scope.sortingOrder !== '') {			
			$scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);			
			}
			$scope.currentPage = 0;
			// now group by pages
			$scope.groupToPages();
		};
		
		$scope.searchDateFin = function () {				
			//$scope.filteredItems = $filter('filter')($scope.items, function (item) {
			$scope.filteredItems = $filter('filter')($scope.filteredItems, function (filteredItems) {
			//for(var attr in item) {
			for(var attr in filteredItems) {
				//if (searchMatch(item['dateFin'], $scope.query3))
				if (searchMatch(filteredItems['dateFin'], $scope.query3))
					return true;
			}
			return false;
			});
			// take care of the sorting order
			if ($scope.sortingOrder !== '') {
			$scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);
			}
			$scope.currentPage = 0;
			// now group by pages
			$scope.groupToPages();
		};
		
		$scope.searchPassed = function () {				
			$scope.filteredItems = $filter('filter')($scope.items, function (item) {									
			for(var attr in item) {						
				if (searchMatch(item['passed'], $scope.query4))
					return true;
			}
			return false;
			});
			// take care of the sorting order			
			if ($scope.sortingOrder !== '') {			
			$scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);			
			}
			$scope.currentPage = 0;
			// now group by pages
			$scope.groupToPages();
		};
		
		$scope.searchFailed = function () {				
			$scope.filteredItems = $filter('filter')($scope.items, function (item) {									
			for(var attr in item) {						
				if (searchMatch(item['failed'], $scope.query5))
					return true;
			}
			return false;
			});
			// take care of the sorting order			
			if ($scope.sortingOrder !== '') {			
			$scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);			
			}
			$scope.currentPage = 0;
			// now group by pages
			$scope.groupToPages();
		};
		
		$scope.searchBlocked = function () {				
			$scope.filteredItems = $filter('filter')($scope.items, function (item) {									
			for(var attr in item) {						
				if (searchMatch(item['blocked'], $scope.query6))
					return true;
			}
			return false;
			});
			// take care of the sorting order			
			if ($scope.sortingOrder !== '') {			
			$scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);			
			}
			$scope.currentPage = 0;
			// now group by pages
			$scope.groupToPages();
		};
		
		$scope.cleanfilter = function () {							
			$scope.query= '';
			$scope.query2= '';
			$scope.query3= '';
			$scope.query4= '';
			$scope.query5= '';
			$scope.query6= '';
			$scope.filteredItems = $scope.items;
		};
		/*Fin Nueva Funcion de fechas*/
		
		// show items per page		
		$scope.perPage = function () {
				$scope.groupToPages();		
		};		
		
		// calculate page in place
		$scope.groupToPages = function () {		
			$scope.pagedItems = [];				
			for (var i = 0; i < $scope.filteredItems.length; i++) {
			if (i % $scope.itemsPerPage === 0) {
				$scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [ $scope.filteredItems[i] ];
			} else {
				$scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
			}
			}			
		};
		
		$scope.deleteItem = function (idx) {
				var itemToDelete = $scope.pagedItems[$scope.currentPage][idx];
				var idxInItems = $scope.items.indexOf(itemToDelete);
				$scope.items.splice(idxInItems,1);
				$scope.search();
				
				return false;
			};
		
		$scope.range = function (start, end) {
			var ret = [];
			if (!end) {
			end = start;
			start = 0;
			}
			for (var i = start; i < end; i++) {
			ret.push(i);
			}
			return ret;
		};
		
		$scope.prevPage = function () {
			if ($scope.currentPage > 0) {
			$scope.currentPage--;
			}
		};
		
		$scope.nextPage = function () {
			if ($scope.currentPage < $scope.pagedItems.length - 1) {
			$scope.currentPage++;
			}
		};
		
		$scope.setPage = function () {
			$scope.currentPage = this.n;
		};
		
		// functions have been describe process the data for display
		$scope.search();
		
		
		// change sorting order
		$scope.sort_by = function(newSortingOrder) {
			if ($scope.sortingOrder == newSortingOrder)
			$scope.reverse = !$scope.reverse;
			
			$scope.sortingOrder = newSortingOrder;
		};
		
		
		
			});	
		
		
	}]);

testControllers.controller('TestListCtrl', ['$scope', '$routeParams', '$http', '$translate',
	function ($scope,$routeParams, $http,$translate) {

		 var getData = function (){
			$http.get('tests/'+ $scope.runId +'/sumary.json').success(function(data) {
				$scope.tests = data;
				for(var i = 0; i< data.length; i++){
					if(data[i].status == "passed"){
						$scope.results[0]++;
					}else if(data[i].status == "failed"){
						$scope.results[1]++;
					}else if(data[i].status == "blocked"){
						$scope.results[2]++;
					}
				}
			});
 		};


		$scope.tests;
		$scope.labels = ["passed", "failed", "blocked"];
 		$scope.results = [0, 0, 0];
 		$scope.runId = $routeParams.runid;
 		if(!$scope.runId){
 			$http.get('tests/builds.json').success(function(data) {
			 	$scope.runId=data.length;
			 	getData();
			});
 		}
 		else{
 			getData();
 		}
		
  }]);

  
testControllers.controller('TestDetailCtrl', ['$scope', '$routeParams', '$http', '$location', '$rootScope','$modal','$translate',
  function($scope, $routeParams, $http, $location, $rootScope, $modal, $translate) {
   
  	var urisImages = [];
    $http.get('tests/' + $routeParams.runid + '/'+ $routeParams.testId + '.json').success(function(data) {
	    $scope.test = data;
	    $scope.numPag = data.length;
		$scope.runId = $routeParams.runid;
		for (var i = 0; i< data.steps.length; i++){
			urisImages[i] = data.steps[i].uri;
		}

		console.log(urisImages);
    });
	$scope.notSorted = function(obj){
        if (!obj) {
            return [];
        }
        return Object.keys(obj);
    }

    $scope.openImage = function (uriIndex) {
    var modalInstance = $modal.open({
      templateUrl: 'myModalContent.html',
      controller: 'ModalInstanceCtrl',
      size: 'lg',
      resolve: {
        uriIndex: function () {
          return uriIndex;
        },
        uris: function () {
          return urisImages;
        }
      }
    });
 	};

	$scope.back = function(){
	 	$location.path("tests");
	}

}]);

testControllers.controller('ModalInstanceCtrl', function ($scope, $modalInstance, uriIndex, uris) {
  $scope.uri = uris[uriIndex];
  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };

  $scope.change = function (cantidad) {
  	console.log('entra');
  	uriIndex = (uriIndex + cantidad) % uris.length ;
  	uriIndex = (uriIndex  === -1) ? uris.length : uriIndex;

  	$scope.uri = uris[uriIndex];

  };
});


testControllers.controller('IdiomCtrl', function ($scope, $rootScope, $translate) {
  	$scope.changeLanguage = function (langKey) {
   	 	$translate.use(langKey);

   	 	$translate(['ALERT_OK', 'ALERT_FAIL']).then(function (translations) {
    	$rootScope.alert_ok = translations.ALERT_OK;
    	$rootScope.alert_fail = translations.ALERT_FAIL;
  	});
  	};


});
