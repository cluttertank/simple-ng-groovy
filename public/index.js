var appsNgApp = angular.module('appsNgApp', [ 'ui.bootstrap', 'ui.select',
		'ngSanitize' ]);
appsNgApp.controller('appsCtrl', [
		'$scope',
		'$http',
		function($scope, $http) {

			$scope.data = {};
			$scope.result = {};

			$http.get('apps').success(function(data, status, header, config) {
				$scope.apps = data;
			}).error(function(data, status, header, config) {
				console.log(data);
			});

			$scope.changeProject = function(data) {
				$scope.proj = data;
				console.log($scope.proj);
				$http.get('apps/' + data + '/deployments').success(
						function(data, status, header, config) {
							$scope.result = data;
						}).error(function(data, status, header, config) {
					console.log(data);
				});
			};

			$scope.create = function(inputForm) {
				inputForm.$setSubmitted();
				if (inputForm.$valid) {
					$http.post("apps/" + $scope.proj + "/deployments",
							$scope.data).success(
							function(data, status, headers, config) {
								console.log(data);
								$scope.result = {};
								$scope.result[0] = data;

							}).error(function(data, status, headers, config) {
						console.log(data);
					});
				}
			}

		} ]);