(function (angular) {
    "use strict";
    var app= angular.module('javaLiteApp', []);

    app.service("studentService", function($http, $q){
        return {
            getAllStudents: function(){
                var deferred = $q.defer();
                $http.get("/allstudents")
                            .then(function(response){deferred.resolve(response.data);}, function(error) {deferred.reject(error);});
                return deferred.promise;
            },
             studentByRollNumber: function(rollNumber){
                 var deferred = $q.defer();
                 $http.get("/findbyrollnumber?rollNumber=" + rollNumber)
                            .then(function(response){deferred.resolve(response.data);}, function(error) {deferred.reject(error);});
                 return deferred.promise;
             }
        }
    });

    app.controller('javaLiteController', ['$scope', 'studentService', function($scope, studentService) {
        $scope.students = [];
        $scope.name = "Voldemort";
        studentService
            .getAllStudents()
            .then(function(data){ $scope.students = data });

        $scope.studentByRollNumber = {rollNumber : "", firstName: "", lastName:""};
        $scope.showClearButton = false;
        $scope.fetchStudentByRollNumber = function(form){
            var rollNumber = $scope.studentByRollNumber.roll_number;
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