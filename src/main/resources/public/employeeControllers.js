var employeeControllers = angular.module('employeeControllers', []);

employeeControllers.controller('ListEmployeeCtrl', function ($scope, $http){
	$http.get('/employee').success(function(data) {
		$scope.employees = data;
	});
			
	$scope.sortField = 'personnelId';
	$scope.reverse = true;
	
	$scope.deleteEmployee = function(employeeToDelete) {
		$http({
        	method: 'DELETE',
        	url: '/employee',
        	data: employeeToDelete,
        		headers: {
        			'Content-Type': 'application/json'
      	}}).success(function(data) {
		});
		window.location.reload();
	};
});

employeeControllers.controller('CreateEmployeeCtrl', function($scope, $http) {
	$scope.personnelId = '';
	$scope.firstName = '';
	$scope.lastName = '';
	$scope.phoneNumber = '';
	$scope.email = '';
	$scope.startDate = '';
	$scope.login = '';
	
	var today = new Date();
	$scope.currentDate = new Date();
	$scope.createEmployee = function() {
		$http({
        	method: 'POST',
        	url: '/employee',
        	data: {
        		personnelId: $scope.personnelId,
        		firstName: $scope.firstName,
        		lastName: $scope.lastName,
        		phoneNumber: $scope.phoneNumber,
        		email: $scope.email,
        		startDate: $scope.startDate,
        		login: $scope.login
			}
      	}).success(function(data) {
		});
	};
	
	$scope.change = function() {
		var date = new Date($scope.startDate);
		if (date.getTime() > new Date().getTime()) {
			$scope.createEmployeeForm.startDate.$setValidity("beforeToday", false);
		}
		else {
			$scope.createEmployeeForm.startDate.$setValidity("beforeToday", true);
		}
	}
	
});

employeeControllers.controller('EditEmployeeCtrl', function($scope, $routeParams, $http) {
	$http({
        method: 'GET',
        url: '/employee/' + $routeParams.personnelId
      }).success(function(data) {
		$scope.employee = data;
	});
	
	$scope.updateEmployee = function() {
		$http({
        	method: 'PUT',
        	url: '/employee/' + $routeParams.personnelId,
        	data: {
        		personnelId: $scope.employee.personnelId,
        		firstName: $scope.employee.firstName,
        		lastName: $scope.employee.lastName,
        		phoneNumber: $scope.employee.phoneNumber,
        		email: $scope.employee.email,
        		startDate: $scope.employee.startDate,
        		login: $scope.employee.login
			}
      	}).success(function(data) {
		});
	};
	
	$scope.change = function() {
		var date = new Date($scope.employee.startDate);
		console.log(date);
		if (date.getTime() > new Date().getTime()) {
			$scope.updateEmployeeForm.startDate.$setValidity("beforeToday", false);
		}
		else {
			$scope.updateEmployeeForm.startDate.$setValidity("beforeToday", true);
		}
	}
});