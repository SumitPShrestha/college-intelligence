(function () {
    'use strict';

    angular.module('app.demo')
        .controller('Demo', Demo);
    Demo.$inject = ['userservice', '$scope', 'NgTableParams', 'logger'];

    function Demo(userservice, $scope, NgTableParams, logger) {
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

        $scope.initCreatePanel = function () {
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


        vm.title = 'User Create Panel';


        activate();
        /*I am testing*/


        function activate() {
            logger.info('Activated Demo View');
        }

        function createOrEditUser(x) {
            userservice.addUsers(x).$promise.then(function (data) {
                $scope.closeThePanel();
            });

        }

        function getSingleUser(userId) {
            userservice.getUser({id: userId}).$promise.then(function (data) {
                $scope.usermodel.username = data.username;
                $scope.usermodel.password = data.password;
            });


        }

        function findAll() {
            userservice.findAllUsers().$promise.then(function (data) {
                self.tableParams = new NgTableParams({}, {dataset: data});
            });

        }

    }

})();
