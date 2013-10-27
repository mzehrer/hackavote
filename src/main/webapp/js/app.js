var app = angular.module('hackavote', ['ngRoute','ngResource', 'restangular']);

app.config(function($routeProvider, RestangularProvider) {
    RestangularProvider.setBaseUrl('/api/v1');
});

app.controller('adminctrl', function ($scope, Restangular) {

    Restangular.all('elections').getList().then(function(data) {
        $scope.elections = data;
    })

    $scope.appname = "hackavote";

});

