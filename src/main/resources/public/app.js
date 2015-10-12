var employeeApp = angular.module('employeeApp', [
    'ngRoute',
    'employeeControllers' 
]);

employeeApp.config(function($routeProvider) {
	$routeProvider.
		when('/', {
			templateUrl: 'list-employees.html',
			controller: 'ListEmployeeCtrl'
		}).
		when('/create', {
			templateUrl: 'create-employee.html',
			controller: 'CreateEmployeeCtrl'
		}).
		when('/edit/:personnelId', {
			templateUrl: 'edit-employee.html',
			controller: 'EditEmployeeCtrl'
		}).
		otherwise({
			redirectTo: '/'
		});
});