'use strict';

var turboAdventure = angular.module('TurboAdventure');

turboAdventure.controller('MatchController', function ($scope, Restangular) {

    $scope.left = "";
    $scope.right = "";
    $scope.song = "";
    $scope.clicked = false;
    $scope.createNewMatch = function() {

        Restangular.one('pairing').post().then(function (data) {
            $scope.setPairing(data);
            $scope.clicked = true;
            $scope.updateHistory();
        });

    }
    $scope.setPairing = function(triple){
        $scope.left = triple.left;
        $scope.right = triple.right;
        $scope.song = triple.songToSing;
    }
    $scope.updateHistory = function() {
        Restangular.one('pairing').get().then(function (data) {
            $scope.history = data.pairings;
        });
    }
    $scope.updateHistory();
});
