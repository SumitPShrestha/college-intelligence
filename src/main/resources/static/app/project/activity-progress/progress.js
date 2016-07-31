(function () {
    'use strict';

    angular.module('app.project')
        .controller('Progress', Progress);
    Progress.$inject = ['progressservice', 'trainingcenterservice', '$scope', 'NgTableParams', 'logger', '$routeParams'];

    function Progress(progressservice, trainingcenterservice, $scope, NgTableParams, logger, $routeParams) {
        var vm = this;
        var self = this;
        var activityId = $routeParams.activityId;
        // alert(pCode);
        $scope.timeFrame = ["FIRST_QUARTER", "SECOND_QUARTER", "THIRD_QUARTER", "SELECT"];
        $scope.selectedTimeFrameItem = $scope.timeFrame[3];
        $scope.timeFrame.pop();
        findAllTC();
        findAllActivitiesProgress();

        $scope.initCreatePanel = function () {
            // $scope.clearValidationMessages();
            vm.riskyId = 0;


            vm.title = "Create Progress Panel";
            vm.showCreatePanel = !vm.showCreatePanel;
            $scope.btnText = "Create ";
        }
        $scope.closeThePanel = function () {
            vm.showCreatePanel = !vm.showCreatePanel;
        }

        $scope.trainingCenterDropboxitemselected = function (item) {

            $scope.selectedTrainingCenterItem = item;

        }

        $scope.timeFrameDropboxitemselected = function (item) {

            $scope.selectedTimeFrameItem = item;

        }

        $scope.initEditPanel = function (pid) {
            $scope.btnText = "Update";
            vm.riskyId = pid;
            getSingleProgress(pid);

            vm.title = "Edit User";
            vm.showCreatePanel = true;
        }

        $scope.submitFormss = function (isValid) {
            if (isValid) {
                $scope.eitherCreateOrEdit();
            }
            else {
                showValidationErrors();
            }

        }
        $scope.deleteThisProgress=function(pid){
            progressservice.deleteProgress({id:pid}).$promise.then(function(data){
                findAllActivitiesProgress();
            })
        }
        function showValidationErrors() {
            $scope.submitted = true;
            // $scope.userForm.username.$error.required=true;
        }

        $scope.eitherCreateOrEdit = function () {


            var x = {
                id: vm.riskyId,
                timeFrame: $scope.selectedTimeFrameItem,
                description: $scope.progressmodel.description,
                progressQty: $scope.progressmodel.progressQty,
                goalQty: $scope.progressmodel.goalQty,
                trainingCenter: $scope.selectedTrainingCenterItem,
                activityId: activityId


            }

            createOrEditProgress(x);

        }

        function createOrEditProgress(progress) {
            progressservice.addProgress(progress).$promise.then(function (data) {
                findAllActivitiesProgress();
                $scope.closeThePanel();
            });

        }


        function findAllTC() {
            trainingcenterservice.findAllTrainingCenters().$promise.then(function (data) {
                $scope.trainingCenters = [];
                data.forEach(function (tc) {
                    $scope.trainingCenters.push(tc.name);
                });
                $scope.trainingCenters.push("SELECT");
                $scope.selectedTrainingCenterItem = $scope.trainingCenters[$scope.trainingCenters.length - 1];
                $scope.trainingCenters.pop();

            });

        }

        function findAllActivitiesProgress() {

            progressservice.findAllProgresses().$promise.then(function (data) {
                self.tableParams = new NgTableParams({}, {dataset: data});
            })

        }

        function getSingleProgress(pid) {
            progressservice.getProgress({id: pid}).$promise.then(function (data) {
                setProgressModel(data);
            });


        }

        function setProgressModel(progress) {
            $scope.progressmodel={};
            $scope.selectedTimeFrameItem = progress.timeFrame;
            $scope.progressmodel.description = progress.description;
            $scope.progressmodel.progressQty = progress.progressQty;
            $scope.progressmodel.goalQty = progress.goalQty;
            $scope.selectedTrainingCenterItem = progress.trainingCenter;

        }

    }

})();
