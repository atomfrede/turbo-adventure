'use strict';

var turboAdventure = angular.module('TurboAdventure', ['ngRoute', 'restangular']);

turboAdventure.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/songs', {
            templateUrl: 'song/songList.html'
        });
}]);

turboAdventure.config(function (RestangularProvider) {
    RestangularProvider.setBaseUrl('/');

    RestangularProvider.setRequestInterceptor(function(elem, operation) {
        if (operation === "remove") {
            return undefined;
        }
        return elem;
    });
});
