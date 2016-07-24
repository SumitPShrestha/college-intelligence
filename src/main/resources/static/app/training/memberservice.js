(function () {
    'use strict';

    angular.module('app.training')
        .controller('Member', Member);
    Training.$inject = ['trainingservice', '$scope', 'NgTableParams', 'logger','$routeParams'];

    function Training(trainingservice, $scope, NgTableParams, logger,$routeParams) {
        var tcId = $routeParams.id;
        findAll(tcId);

        var self = this;

        var vm = this;


        $scope.initCreatePanel = function () {

            $scope.tmodel = {};
            vm.title = "Create Training ";
            vm.showCreatePanel = !vm.showCreatePanel;
            $scope.btnText = "Create ";
        }
        $scope.initEditPanel = function (pid) {
            $scope.btnText = "Update";
            vm.riskyId = pid;
            getSingleProject(pid);

            vm.title = "Edit User";
            vm.showCreatePanel = true;
        }

        $scope.closeThePanel = function () {
            vm.showCreatePanel = !vm.showCreatePanel;
        }
        $scope.submitFormss = function (isValid) {
            if (isValid) {
                $scope.eitherCreateOrEdit();
            }
            else {
                showValidationErrors();
            }

        }
        function showValidationErrors() {
            $scope.submitted = true;
            // $scope.userForm.username.$error.required=true;
        }
        $scope.eitherCreateOrEdit = function () {
            var x = {id: vm.riskyId,
                name: $scope.tmodel.name,
                budget: $scope.tmodel.budget,
                start:$scope.tmodel.start,
                end:$scope.tmodel.end,
                target:$scope.tmodel.target


            }

            createOrEditTraining(x);

        }
        function createOrEditTraining(tc) {
            trainingservice.addTraining(tc).$promise.then(function (data) {
                 findAll();
                $scope.closeThePanel();
            });

        }

        function getSingleTraining(pid) {
            trainingservice.getTraining({id: pid}).$promise.then(function (data) {
                settmodels(data);
            });


        }
        function settmodels(data) {
            $scope.tmodel.name = data.name;
            $scope.tmodel.budget = data.budget;
            $scope.tmodel.start = data.budgetSubHeadNumber;
            $scope.tmodel.end = data.budget;

        }


        function findAll(tcName) {
            trainingservice.findAllTrainingsByTrainingCenterId({id:tcId}).$promise.then(function (data) {
                self.tableParams = new NgTableParams({}, {dataset: data});
            });

        }


    }

})();
