(function () {
    'use strict';

    angular.module('app.training')
        .controller('TrainingCenter', TrainingCenter);
    TrainingCenter.$inject = ['trainingcenterservice', '$scope', 'NgTableParams', 'logger'];

    function TrainingCenter(trainingcenterservice, $scope, NgTableParams, logger) {
        findAll();
        findAllParentTC();

        var self = this;

        var vm = this;
        $scope.tcmodel = {};

        $scope.trainingCenterDropboxitemselected = function (item) {

            $scope.selectedTrainingCenterItem = item;
        }
        $scope.initCreatePanel = function () {
            pushAndPopSELECTmenu();
            vm.riskyId = 0;
            $scope.tcmodel = {};
            vm.title = "Create Training Center Panel";
            vm.showCreatePanel = !vm.showCreatePanel;
            $scope.btnText = "Create ";
        }
        $scope.initEditPanel = function (pid) {
            pushAndPopSELECTmenu();
            $scope.btnText = "Update";
            vm.riskyId = pid;
            getSingleTrainingCenter(pid);

            vm.title = "Edit Training Center Panel";
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
            var x = {
                id: vm.riskyId,
                name: $scope.tcmodel.name,
                address: $scope.tcmodel.address,
                district: $scope.tcmodel.district,
                zone: $scope.tcmodel.zone,
                parentTrainingCenter: $scope.selectedTrainingCenterItem

            }

            createOrEditTrainingCenter(x);

        }
        $scope.hasChildren = function () {
            return true;
        }
        $scope.deleteTrainingCenter = function (tcId) {
            if (!$scope.hasChildren()) {
                trainingcenterservice.deleteTrainingCenterIfPossible({id: tcId}).$promise.then(function (data) {
                    findAll();
                });
            }
            else{
                alert("You must first delete the parent Training Center and all related trainings to delete this training center.");
            }


        }
        $scope.clearValidationMessages = function () {

            $scope.submitted = false;

        }
        function createOrEditTrainingCenter(tc) {
            trainingcenterservice.addTrainingCenter(tc).$promise.then(function (data) {
                findAll();
                $scope.closeThePanel();
            });

        }

        function getSingleTrainingCenter(pid) {
            trainingcenterservice.getTrainingCenter({id: pid}).$promise.then(function (data) {
                settcmodels(data);
            });


        }

        function settcmodels(data) {
            $scope.tcmodel.name = data.name;
            $scope.tcmodel.address = data.address;
            $scope.tcmodel.district = data.district;
            $scope.tcmodel.zone = data.zone
            if (data.parentTrainingCenter == undefined) {
                $scope.selectedTrainingCenterItem = "SELECT"
            }
            else {

                $scope.selectedTrainingCenterItem = data.parentTrainingCenter;
            }

            /* $scope.trainingCenters.forEach(function(tc){
             if(tc==data.name){
             $scope.trainingCenters.
             }
             if($scope.selectedTrainingCenterItem==tc){


             }
             });*/

        }


        function findAll() {
            trainingcenterservice.findAllTrainingCenters().$promise.then(function (data) {
                self.tableParams = new NgTableParams({}, {dataset: data});
            });

        }

        function pushAndPopSELECTmenu() {
            $scope.trainingCenters.push("SELECT");
            $scope.selectedTrainingCenterItem = $scope.trainingCenters[$scope.trainingCenters.length - 1];
            $scope.trainingCenters.pop();

        }

        function findAllParentTC() {
            trainingcenterservice.findAllParentTrainingCenters().$promise.then(function (data) {
                $scope.trainingCenters = [];
                data.forEach(function (tc) {
                    $scope.trainingCenters.push(tc.name);
                });
                $scope.trainingCenters.push("NONE");

            });

        }

    }

})();
