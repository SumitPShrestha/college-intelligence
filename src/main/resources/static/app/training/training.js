(function () {
    'use strict';

    angular.module('app.training')
        .controller('Training', Training);
    Training.$inject = ['trainingservice', '$scope', 'NgTableParams', 'logger', '$routeParams'];

    function Training(trainingservice, $scope, NgTableParams, logger, $routeParams) {
        var tcId = $routeParams.id;
        findAll(tcId);

        var self = this;

        var vm = this;

        vm.riskyId=0;

        $scope.tmodel = {};
        $scope.initCreatePanel = function () {
            vm.riskyId=0;
            vm.title = "Create Training Panel ";
            vm.showCreatePanel = !vm.showCreatePanel;
            $scope.btnText = "Create ";
        }
        $scope.initEditPanel = function (pid) {
            $scope.btnText = "Update";
            vm.riskyId = pid;
            getSingleTraining(pid);

            vm.title = "Edit Training Panel";
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
        }

        $scope.eitherCreateOrEdit = function () {
            var x = {
                id: vm.riskyId,
                name: $scope.tmodel.name,
                budget: $scope.tmodel.budget,
                start: $scope.tmodel.start,
                end: $scope.tmodel.end,
                target: $scope.tmodel.target,
                trainingCenterId:tcId,



            }

            createOrEditTraining(x);

        }
        $scope.deleteTheTraining = function (trainingId) {
            trainingservice.deleteTraining({id: trainingId}).$promise.then(function (data) {
                findAll(tcId);
            });
        }
        function createOrEditTraining(tc) {
            trainingservice.addTraining(tc).$promise.then(function (data) {
                findAll(tcId);
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
            $scope.tmodel.start = data.start;
            $scope.tmodel.end = data.end;
            $scope.tmodel.target = data.target;

        }


        function findAll(tcId) {
            trainingservice.findAllTrainingsByTrainingCenterId({id: tcId}).$promise.then(function (data) {
                self.tableParams = new NgTableParams({}, {dataset: data});
            });

        }


    }

})();
