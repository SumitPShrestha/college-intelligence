(function () {
    'use strict';

    angular.module('app.demo')
        .controller('Demo', Demo);
    Demo.$inject = ['userservice', '$scope', 'NgTableParams', 'logger'];

    function Demo(userservice, $scope, NgTableParams, logger) {
        $scope.submitted=false;
        var self = this;
        findAll();

        /*        $http.get('/admin/users', {}, {
         isArray:true
         })                                Just an another way to solve the same thing..////
         .success(hero);*/


        /***********************VERY IMPORTANT****************************

         var x={username:"hero",password:"pass"}
         userservice.addUsers(x).$promise.then(function(data){
            alert(data);
        });
         var x={id:3,username:"heross",password:"passss"}
         userservice.editUser(x).$promise.then(function(data){
            alert(data);
        });
         var z=3;
         userservice.getUser({id:3}).$promise.then(function(data){
            alert(data);
        });
         /***********************END OF VERY IMPORTANT****************************
         */



        var vm = this;
        vm.user = {};
        $scope.usermodel = {};
        vm.showCreatePanel = false;
        $scope.clearValidationMessages=function(){
            $scope.submitted=false;
            $scope.usermodel.username="";
            $scope.userForm.username.$pristine=true;
            //$scope.userForm.username.$invalid=false;
            $scope.userForm.username.$error.required=true;
            // $scope.userForm.username.$error.required=true;
        }

        $scope.initCreatePanel = function () {
            $scope.clearValidationMessages();
            vm.riskyId = 0;
            $scope.usermodel = {};
            vm.title = "Create User";
            vm.showCreatePanel = !vm.showCreatePanel;
            $scope.btnText = "Create ";
        }

        $scope.eitherCreateOrEdit = function () {

            var x = {id: vm.riskyId, username: $scope.usermodel.username, password: $scope.usermodel.password}
            createOrEditUser(x);

        }
        $scope.closeThePanel = function () {
            vm.showCreatePanel = !vm.showCreatePanel;
        }
        $scope.initEditPanel = function (userId) {
            $scope.btnText = "Update";
            vm.riskyId = userId;
            getSingleUser(userId);

            vm.title = "Edit User";
            vm.showCreatePanel = true;
        }

        $scope.submitFormss=function(isValid){
            if(isValid){
            $scope.eitherCreateOrEdit();
            }
            else{
                showValidationErrors();
            }

        }

        $scope.deleteTheUser=function(userId){
            userservice.deleteUser({id: userId}).$promise.then(function (data) {
                findAll();
            });

        }

        $scope.roles = [{"id":"0", "name":"ROLE_ADMIN"},
            {"id":"1", "name":"ROLE_USER"},
            {"id":"2", "name":"ROLE_TRAINING CENTER"}
        ];

        vm.title = 'User Create Panel';


        activate();
        /*I am testing*/


        function activate() {
            logger.info('Activated Demo View');
        }

        function createOrEditUser(x) {
            userservice.addUsers(x).$promise.then(function (data) {
                findAll();
                $scope.closeThePanel();
            });

        }

        function getSingleUser(userId) {
            userservice.getUser({id: userId}).$promise.then(function (data) {
                setUserModels(data);
            });


        }
        function setUserModels(data){
            $scope.usermodel.username = data.username;
            $scope.usermodel.password = data.password;
            $scope.usermodel.firstName = data.firstName;
            $scope.usermodel.middleName = data.middleName;
            $scope.usermodel.lastName = data.lastName;
            $scope.usermodel.dob = data.dob;
            if(data.male){
                $scope.usermodel.sex = "male";
            }
            else{
                $scope.usermodel.sex = "female";
            }

            $scope.usermodel.mobileNumber = data.mobileNumber;
            $scope.usermodel.landlineNumber = data.landlineNumber;
            $scope.usermodel.streetAddress = data.streetAddress;
            $scope.usermodel.vdcOrMunicipality = data.vdcOrMunicipality;

            $scope.usermodel.zone = data.zone;

            $scope.usermodel.district = data.district;
            $scope.usermodel.country = data.country;
                var xyz=[];
            data.roles.forEach(function (val,idx) {
            if(val.role==="ROLE_ADMIN"){
                xyz["0"]=true;
            } if(val.role==="ROLE_USER"){
                    xyz["1"]=true;
                }
                if(val.role==="ROLE_TRAINING_CENTER"){
                    xyz["2"]=true;
                }

            });

                $scope.selection={
                    ids:xyz
                };



        }
        function showValidationErrors(){
            $scope.submitted=true;
           // $scope.userForm.username.$error.required=true;
        }


        function findAll() {
            userservice.findAllUsers().$promise.then(function (data) {
                self.tableParams = new NgTableParams({}, {dataset: data});
            });

        }

    }

})();
