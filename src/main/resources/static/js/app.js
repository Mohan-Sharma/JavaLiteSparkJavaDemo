(function (angular) {
    "use strict";
    var app= angular.module('javaLiteApp', ['ngRoute', 'ui.bootstrap', 'ngMessages']);

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
             },
             studentByFirstName: function(firstName){
                 var deferred = $q.defer();
                 $http.get("/student/getAllStudentHavingName?firstName=" + firstName)
                            .then(function(response){deferred.resolve(response.data);}, function(error) {deferred.reject(error);});
                 return deferred.promise;
             },
             studentsCountFirstsName: function(firstName){
                 var deferred = $q.defer();
                 $http.get("/student/countByFirstName?firstName=" + firstName)
                            .then(function(response){deferred.resolve(response.data);}, function(error) {deferred.reject(error);});
                 return deferred.promise;
             },
             studentsCount: function(){
                 var deferred = $q.defer();
                 $http.get("/student/count")
                            .then(function(response){deferred.resolve(response.data);}, function(error) {deferred.reject(error);});
                 return deferred.promise;
             },
             saveStudent: function(data){
                 var deferred = $q.defer();
                 $http.post("/student/save", data)
                            .then(function(response){deferred.resolve(response);}, function(error) {deferred.reject(error);});
                 return deferred.promise;
             },
             updateStudent: function(data){
                 var deferred = $q.defer();
                 $http.post("/student/update", data)
                            .then(function(response){deferred.resolve(response);}, function(error) {deferred.reject(error);});
                 return deferred.promise;
             }
        }
    });

    app.controller('javaLiteController', ['$scope', 'studentService', function($scope, studentService) {
        $scope.students = [];
        $scope.studentsbyfirstname = [];
        $scope.name = "Voldemort";
        $scope.showTable = false;
        $scope.getAllStudents = function(){
            studentService
                .getAllStudents()
                .then(function(data){
                    $scope.students = data;
                    if($scope.students.length > 0)
                         $scope.showTable = true;
                });
        };
        $scope.student = {rollNumber : "", firstName: "", lastName:""};
        $scope.studentByRollNumber = {rollNumber : "", firstName: "", lastName:""};
        $scope.studentByFirstName = {firstName: ""};
        $scope.studentscount = {count:""};
        $scope.studentsCountFirstsName = {firstName: "", count:""};
        $scope.showClearButtonOne = false;
        $scope.showClearButtonTwo = false;
        $scope.showClearButtonThree = false;
        $scope.showClearButtonFour = true;
        $scope.showClearButtonFive = false;
        $scope.fetchStudentByRollNumber = function(id){
            var rollNumber = $scope.studentByRollNumber.rollNumber;
            studentService
                        .studentByRollNumber(rollNumber)
                        .then(function(data){
                                if(data)
                                {
                                    $scope.studentByRollNumber = data;
                                    if(id === "first")
                                    {
                                        $scope.showClearButtonOne = true;
                                    }
                                    if(id === "fifth")
                                    {
                                        $scope.showClearButtonFive = true;
                                    }
                                }
                            });
        };

        $scope.clearForm = function(form, id) {
            form.$setPristine();
            form.$setUntouched();
            if(id === "first" || id === "fifth")
            {
                $scope.studentByRollNumber = {rollNumber : "", firstName: "", lastName:""};
                if(id === "first")
                    $scope.showClearButtonOne = false;
                if(id === "fifth")
                    $scope.showClearButtonFive = false;
            }
            if(id === "second")
            {
                $scope.studentByFirstName = {firstName: ""};
                $scope.showClearButtonTwo = false;
            }
            if(id === "third")
            {
                $scope.studentsCountFirstsName = {firstName: "", count:""};
                $scope.showClearButtonThree = false;
            }
            if(id === "fourth")
            {
                $scope.student = {rollNumber : "", firstName: "", lastName:""};
            }
        };

        $scope.fetchStudentByFirstName = function(){
            var firstName = $scope.studentByFirstName.firstName;
            studentService
                     .studentByFirstName(firstName)
                     .then(function(data){
                             if(data)
                             {
                                 $scope.studentsbyfirstname = data; $scope.showClearButtonTwo = true;
                             }
                         });
        };

        $scope.fetchStudentCountByFirstCount = function(){
            var firstName = $scope.studentsCountFirstsName.firstName;
            studentService
                     .studentsCountFirstsName(firstName)
                     .then(function(data){
                             if(data)
                             {
                             console.log(data);
                                 $scope.studentsCountFirstsName = data; $scope.showClearButtonThree = true;
                             }
                         });
         };

        $scope.fetchAllStudentCount = function(){
             studentService
                   .studentsCount()
                   .then(function(data){
                           if(data)
                           {
                               $scope.studentscount = data; $scope.showClearButtonTwo = true;
                           }
                       });
        };

        $scope.saveResult = {success:"", error:""};
        $scope.updateResult = {success:"", error:""};
        $scope.saveStudent = function(form){
             studentService
                   .saveStudent($scope.student)
                   .then(function(response){
                   if(response.data.status)
                    {
                        $scope.clearForm(form, "fourth");
                    }
                    $scope.saveResult = response.data;
                   })
                   .catch(function (error) {
                        $scope.saveResult = response.data;
                   });
        };

        $scope.updateStudent = function(){
             studentService
                   .updateStudent($scope.studentByRollNumber)
                   .then(function(response){
                    if(response.data.status)
                     {
                         $scope.clearForm(form, "fifth");
                     }
                     $scope.updateResult = response.data;
                    })
                    .catch(function (error) {
                         $scope.updateResult = response.data;
                    });
        };
    }])

})(angular);