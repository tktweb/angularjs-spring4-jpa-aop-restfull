var appAng = angular.module('angularApp',
		[ 'ngRoute', 'ngAnimate' ].concat(config.modules),
		function($routeProvider, $locationProvider) {
			// adding route configurations
			console.log(config.subapps);
			for ( var i in config.subapps) {
				var subapp = config.subapps[i];
				for ( var j in subapp.routes) {
					var route = subapp.routes[j];
					console.log(route);
					$routeProvider.when(route.path, {
						templateUrl : route.templateUrl,
						controller : route.controller
					});
				}
			}

			$routeProvider.otherwise({
				redirectTo : '/user/list'
			});
		}).config(function($routeProvider, $httpProvider) {
	$httpProvider.defaults.withCredentials = true;
});


function GlobalCtrl($scope, $location, $http, $rootScope, $route, $window,$timeout, $compile) {
	$rootScope.dateformat =  'dd-MM-yyyy hh:mm:ss';
}
