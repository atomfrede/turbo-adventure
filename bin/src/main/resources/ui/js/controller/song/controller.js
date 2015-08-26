'use strict';

var turboAdventure = angular.module('TurboAdventure');

turboAdventure.controller('SongController', function ($scope, Restangular) {

    $scope.addNewSong = false;
    $scope.newSong = null;

    $scope.editMode = false;
    $scope.actualEditSong = null;

    $scope.songListLoaded = false;
    $scope.songList = {};

    $scope.initSongList = function(songs) {
        $scope.newSong = null;
        $scope.addNewSong = false;

        $scope.editMode = false;
        $scope.actualEditSong = null;

        $scope.songList = _.sortBy(songs, 'title');
        $scope.songListLoaded = true;
    };

    Restangular.one('song').get().then(function (data) {
        $scope.initSongList(data.songs);
    });

    $scope.addSong = function() {
        $scope.addNewSong = true;
    };

    $scope.cancelAddNewSong = function() {
        $scope.newSong = null;
        $scope.addNewSong = false;
    };

    $scope.saveNewSong = function() {
        $scope.songListLoaded = false;

        var songParameter = {
            "title": $scope.newSong.title,
            "interpreter": $scope.newSong.interpreter
        };
        var encodedFormData = $.param(songParameter);

        var headers = {
            "Content-Type": "application/x-www-form-urlencoded"
        };

        Restangular.one('song').customPOST(encodedFormData, null, null, headers).then(function (data) {
            $scope.initSongList(data.songs);
        });

    };

    $scope.editSong = function(song) {
        $scope.editMode = true;
        $scope.actualEditSong = song;
    };

    $scope.cancelEditSong = function() {
        $scope.editMode = false;
        $scope.songListLoaded = false;

        Restangular.one('song').get().then(function (data) {
            $scope.initSongList(data.songs);
        });
    };

    $scope.saveEditedSong = function(song) {
        $scope.songListLoaded = false;

        var songParameter = {
            "_id": song._id,
            "title": song.title,
            "interpreter": song.interpreter
        };
        var encodedFormData = $.param(songParameter);

        var headers = {
            "Content-Type": "application/x-www-form-urlencoded"
        };

        Restangular.one('song/' + song._id).patch(encodedFormData, null, headers).then(function (data) {
            $scope.initSongList(data.songs);
        });
    };

    $scope.deleteSong = function(song) {
        $scope.songListLoaded = false;

        Restangular.one('song/' + song._id).remove().then(function (data) {
            $scope.initSongList(data.songs);
        });
    };
});