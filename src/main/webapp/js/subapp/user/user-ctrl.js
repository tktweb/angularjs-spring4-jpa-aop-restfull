'use strict';
var config={};config.subapps = [];config.modules=[];

config.subapps.push({
	name : 'user',
	routes : [ {
		path : '/user/list',
		templateUrl : 'js/subapp/user/list.html',
		controller : 'UserListCtrl'
	} , {
		path : '/user/create',
		templateUrl : 'js/subapp/user/create.html',
		controller : 'UserCreateCtrl'
	}, {
		path : '/user/update/:key',
		templateUrl : 'js/subapp/user/create.html',
		controller : 'UserUpdateCtrl'
	}]
});


function UserUpdateCtrl($scope, $location, $http, $rootScope, $route, $window,$timeout, $compile,$routeParams) {
	$scope.init = function(){
		var url =  'rest/user/get/'+$routeParams.key;
		
		$http({method: 'GET', url: url}).
	    	success(function(data, status, headers, config) {
	    		$scope.user = data;
	      }).
	      error(function(data, status, headers, config) {
	    	  alert("Network ERROR");
	      });
	}
	
	$scope.init();
	
	$scope.submit = function(){
		var url =  'rest/user/update';
		$http({method: 'POST', url: url,data:$scope.user}).
	    	success(function(data, status, headers, config) {
	    		alert("User updated");
	    		$location.path("/user/list");
	      }).
	      error(function(data, status, headers, config) {
	    	  alert("Network ERROR");
	      });
	}
}

function UserListCtrl($scope, $location, $http, $rootScope, $route, $window,$timeout, $compile) {
	
		$scope.init = function(){
			var url =  'rest/user/list';
			
			$http({method: 'GET', url: url}).
		    	success(function(data, status, headers, config) {
		    		$scope.users = data;
		      }).
		      error(function(data, status, headers, config) {
		    	  alert("Network ERROR");
		      });
		}
		
		$scope.connect = function(user){
			$rootScope.userconnected = user.key;
			var url =  'rest/user/connect/'+user.key;
			
			$http({method: 'GET', url: url}).
		    	success(function(data, status, headers, config) {
		    		alert("user " + user.name + " is now connected");
		      }).
		      error(function(data, status, headers, config) {
		    	  alert("Network ERROR");
		      });
		}
		
		$scope.addtest = function(){
			var url =  'rest/user/init';
			
			$http({method: 'GET', url: url}).
		    	success(function(data, status, headers, config) {
		    		$scope.init();
		      }).
		      error(function(data, status, headers, config) {
		    	  alert("Network ERROR");
		      });
		}
		

		$scope.remove = function(user){
			var url =  'rest/user/delete';
			$http({method: 'POST', url: url,data:user}).
		    	success(function(data, status, headers, config) {
		    		alert("User removed");
		    		$scope.init();
		      }).
		      error(function(data, status, headers, config) {
		    	  alert("Network ERROR");
		      });
		}
		
		$scope.init();
		
}

function UserCreateCtrl($scope, $location, $http, $rootScope, $route, $window,$timeout, $compile) {
	
	$scope.submit = function(){
		var url =  'rest/user/create';
		$http({method: 'POST', url: url, data:$scope.user}).
	    	success(function(data, status, headers, config) {
	    		alert("User created");
	    		$location.path("/user/list");
	      }).
	      error(function(data, status, headers, config) {
	    	  alert("Network ERROR");
	      });
	}
}