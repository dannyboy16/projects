'use strict';
/* Controllers */

var testControllers = angular.module('testControllers', ['chart.js', 'ui.bootstrap']);


testControllers.controller('buildCtrl', ['$scope', '$http', '$timeout', '$rootScope', '$translate',
	function ($scope, $http, $timeout,$rootScope,$translate) {
		$scope.builds;
		$scope.labels =[];
		$scope.series = [];
		$scope.data = [[],[],[],[]];
		$scope.totalItems;
		$scope.page; 		
		$http.get('tests/builds.json').success(function(data) {
			$scope.builds = data.reverse();
			$scope.totalItems=data.length;
			$scope.page = $scope.builds.slice(0,10);		
			for(var i = 0; i<data.length; i++){
				$scope.labels[i] = 'Ejecucion ' + data[i].build; 
				$scope.series = ['Total Test', 'Passed Test', 'Failed Test', 'Blocked Test'];
				$scope.data [0][i] = data[i].passed +  data[i].failed + data[i].blocked;
				$scope.data [1][i] = data[i].passed;
				$scope.data [2][i] =  data[i].failed;
				$scope.data [3][i] =  data[i].blocked;
			}			
		});
		$scope.pageChanged = function(currentPage) {
			console.log('Page changed to: ' + currentPage);
			$scope.page = $scope.builds.slice((currentPage-1)*10,currentPage*10);					
		};
		
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
