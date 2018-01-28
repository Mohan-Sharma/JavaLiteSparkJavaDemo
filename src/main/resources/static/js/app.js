(function (angular) {
    "use strict";
    var app= angular.module('javaLiteApp', ['ngRoute', 'ui.bootstrap']);

    app.service("studentService", function($http, $q){
        return {
            getAllStudents: function(){
                var deferred = $q.defer();
                $http.get("/student/allstudents")
                            .then(function(response){deferred.resolve(response.data);}, function(error) {deferred.reject(error);});
                return deferred.promise;
            },
             studentByRollNumber: function(rollNumber){
                 var deferred = $q.defer();
                 $http.get("/student/getStudentByRoll?rollNumber=" + rollNumber)
                            .then(function(response){deferred.resolve(response.data);}, function(error) {deferred.reject(error);});
                 return deferred.promise;
             }
        }
    });

    app.controller('javaLiteController', ['$scope', 'studentService', function($scope, studentService) {
        $scope.students = [];
        $scope.name = "Voldemort";
        $scope.getAllStudents = function(){
            studentService
                .getAllStudents()
                .then(function(data){
                    console.log(data);
                    $scope.students = data;
                    $scope.name = "Something Else";
                });
        }

        $scope.studentByRollNumber = {rollNumber : "", firstName: "", lastName:""};
        $scope.showClearButton = false;
        $scope.fetchStudentByRollNumber = function(form){
            var rollNumber = $scope.studentByRollNumber.rollNumber;
            studentService
                        .studentByRollNumber(rollNumber)
                        .then(function(data){ $scope.studentByRollNumber = data; $scope.showClearButton = true;});
        };

        $scope.clearForm = function(form) {
            form.$setPristine();
            form.$setUntouched();
            $scope.studentByRollNumber = {rollNumber : "", firstName: "", lastName:""};
            $scope.showClearButton = false;
        }
    }])

})(angular);