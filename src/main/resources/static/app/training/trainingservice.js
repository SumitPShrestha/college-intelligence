(function () {
    'use strict';

    angular.module('app.trainingcenter')
        .controller('TrainingCenter', TrainingCenter);
    TrainingCenter.$inject = ['trainingcenterservice', '$scope', 'NgTableParams', 'logger'];

    function TrainingCenter(trainingcenterservice, $scope, NgTableParams, logger) {
        findAll();

        var self = this;

        var vm = this;


        $scope.trainingCenter = ['Ramechhap', 'Rasuwa', 'Sindhupalchowk'];
        $scope.selectedTrainingCenterItem=$scope.trainingCenter[0];


        $scope.trainingCenterDropboxitemselected = function (item) {

            $scope.selectedTrainingCenterItem = item;
        }
        $scope.initCreatePanel = function () {

            $scope.tcmodel = {};
            vm.title = "Create Training Center";
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
                name: $scope.tcmodel.name,
                address: $scope.tcmodel.address,
                district:$scope.tcmodel.district,
                zone:$scope.tcmodel.zone,
                parentTrainingCenter:$scope.selectedTrainingCenterItem

            }

            createOrEditTrainingCenter(x);

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
            $scope.tcmodel.district = data.budgetSubHeadNumber;
            $scope.tcmodel.zone = data.budget;
            $scope.tcmodel.parentTrainingCenter = data.parentTrainingCenter;

        }


        function findAll() {
            trainingcenterservice.findAllTrainingCenters().$promise.then(function (data) {
                self.tableParams = new NgTableParams({}, {dataset: data});
            });

        }

    }

})();
