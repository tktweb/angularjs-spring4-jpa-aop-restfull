'use strict';

config.subapps.push({
	name : 'log',
	routes : [ {
		path : '/log/list',
		templateUrl : 'js/subapp/log/list.html',
		controller : 'LogListCtrl'
	} ]
});



function LogListCtrl($scope, $location, $http, $rootScope, $route, $window,$timeout, $compile) {
	$scope.init = function(){
		var url =  'rest/log/list';
		
		$http({method: 'GET', url: url}).
	    	success(function(data, status, headers, config) {
	    		$scope.logs = data;
	      }).
	      error(function(data, status, headers, config) {
	    	  alert("Network ERROR");
	      });
	}
	$scope.init();
}

