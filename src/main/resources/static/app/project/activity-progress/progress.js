(function () {
    'use strict';

    angular.module('app.project')
        .controller('Progress', Progress);
    Progress.$inject = ['progressservice', 'trainingcenterservice', '$scope', 'NgTableParams', 'logger', '$routeParams'];

    function Progress(progressservice, trainingcenterservice, $scope, NgTableParams, logger, $routeParams) {
        var vm = this;
        var pCode = $routeParams.activityId;
        // alert(pCode);
        $scope.timeFrame = ["FIRST_QUARTER", "SECOND_QUARTER", "THIRD_QUARTER", "SELECT"];
        $scope.selectedTimeFrameItem = $scope.timeFrame[3];
        $scope.timeFrame.pop();
        findAllTC();

        $scope.initCreatePanel = function () {
            // $scope.clearValidationMessages();
            vm.riskyId = 0;


            vm.title = "Create Project";
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
        /*
         $scope.initEditPanel = function (pid) {
         $scope.btnText = "Update";
         vm.riskyId = pid;
         getSingleProject(pid);

         vm.title = "Edit User";
         vm.showCreatePanel = true;
         }


         $scope.initializeGoalPanel = function (aid) {

         vm.showGoalPanel = true;
         vm.goalTitle = "Create Goal";
         $scope.q1btnText = "Create";
         $scope.q2btnText = "Create";
         $scope.q3btnText = "Create";
         $scope.ProgressId = aid;
         getGoalsByProgressIdAndSetModels(aid);


         }
         $scope.q1submitFormss = function (isValid) {
         alert(1);
         if (isValid) {
         var x = {
         goal: {
         id: vm.riskyId,
         qty: $scope.q1goalmodel.qty,
         weightage: $scope.q1goalmodel.weightage,
         budget: $scope.q1goalmodel.budget,
         timeFrame: "FIRST_QUARTER"
         },
         ProgressId: $scope.ProgressId
         }
         $scope.eitherCreateOrEditGoal(x);
         }
         else {
         //showValidationErrors();
         }

         }
         $scope.q2submitFormss = function (isValid) {
         alert(2);
         if (isValid) {
         var x = {
         goal: {
         id: vm.riskyId,
         qty: $scope.q2goalmodel.qty,
         weightage: $scope.q2goalmodel.weightage,
         budget: $scope.q2goalmodel.budget,
         timeFrame: "SECOND_QUARTER"

         },
         ProgressId: $scope.ProgressId
         }
         $scope.eitherCreateOrEditGoal(x);
         }
         else {
         // showValidationErrors();
         }

         }
         $scope.q3submitFormss = function (isValid) {
         alert(3);
         if (isValid) {
         var x = {
         goal: {

         id: vm.riskyId,
         qty: $scope.q3goalmodel.qty,
         weightage: $scope.q3goalmodel.weightage,
         budget: $scope.q3goalmodel.budget,
         timeFrame: "THIRD_QUARTER"

         },
         ProgressId: $scope.ProgressId
         }
         $scope.eitherCreateOrEditGoal(x);
         }
         else {
         // showValidationErrors();
         }

         }
         $scope.submitFormss = function (isValid) {
         if (isValid) {
         $scope.eitherCreateOrEdit();
         }
         else {
         // showValidationErrors();
         }

         }

         $scope.closeTheGoalPanel = function () {
         vm.showGoalPanel = !vm.showGoalPanel;
         }

         $scope.eitherCreateOrEditGoal = function (goal) {


         createOrEditGoal(goal);

         }

         findAllActivities(pCode);
         //$scope.submitted = false;
         var self = this;
         var vm = this;
         /!* vm.p={};
         vm.title = 'Project Panel';
         $scope.projectmodel = {};
         $scope.fYear = ['2072-73', '2073-74', '2074-75', '2075-76', '2077-78'];
         $scope.selectedItem=$scope.fYear[0];
         findAll({fiscalYear: $scope.selectedItem});

         $scope.dropboxitemselected = function (item) {

         $scope.selectedItem = item;
         findAll({fiscalYear: $scope.selectedItem});
         }
         $scope.initCreatePanel = function () {
         // $scope.clearValidationMessages();
         vm.riskyId = 0;


         vm.title = "Create Project";
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
         projectCode: $scope.projectmodel.projectCode,
         aidOrganisation: $scope.projectmodel.aidOrganisation,
         budget:$scope.projectmodel.budget,
         budgetSubHeadNumber:$scope.projectmodel.budgetSubHeadNumber,
         fiscalYear:$scope.projectmodel.fiscalYear

         }

         createOrEditProject(x);

         }
         function createOrEditProject(project) {
         projectservice.addProject(project).$promise.then(function (data) {
         // findAll();
         $scope.closeThePanel();
         });

         }

         function getSingleProject(pid) {
         projectservice.getProject({id: pid}).$promise.then(function (data) {
         setProjectModels(data);
         });


         }
         function setProjectModels(data) {
         $scope.projectmodel.projectCode = data.projectCode;
         $scope.projectmodel.aidOrganisation = data.aidOrganisation;
         $scope.projectmodel.budgetSubHeadNumber = data.budgetSubHeadNumber;
         $scope.projectmodel.budget = data.budget;
         $scope.projectmodel.fiscalYear = data.fiscalYear;

         }
         *!/
         function createOrEditGoal(goal) {
         goalservice.addGoal(goal).$promise.then(function (data) {
         // findAll();
         $scope.closeTheGoalPanel();
         });

         }

         function findAllActivities(pCode) {
         Progressservice.findAllActivities({id: pCode}).$promise.then(function (data) {
         self.tableParams = new NgTableParams({}, {dataset: data});
         });

         }
         function getGoalsByProgressIdAndSetModels(aid) {
         goalservice.getGoalsByProgressId({id: aid}).$promise.then(function (goals) {
         goals.forEach(function (goal) {
         if (goal.timeFrame == "FIRST_QUARTER") {
         $scope.q1goalmodel = {};
         $scope.q1goalmodel.qty = goal.qty;
         $scope.q1goalmodel.weightage = goal.weightage;
         $scope.q1goalmodel.budget = goal.budget;
         $scope.q1btnText = "Update";

         }

         if (goal.timeFrame == "SECOND_QUARTER") {
         $scope.q2goalmodel = {};
         $scope.q2goalmodel.qty = goal.qty;
         $scope.q2goalmodel.weightage = goal.weightage;
         $scope.q2goalmodel.budget = goal.budget;
         $scope.q2btnText = "Update";

         }

         if (goal.timeFrame == "THIRD_QUARTER") {
         $scope.q3goalmodel = {};
         $scope.q3goalmodel.qty = goal.qty;
         $scope.q3goalmodel.weightage = goal.weightage;
         $scope.q3goalmodel.budget = goal.budget;
         $scope.q3btnText = "Update";

         }

         });
         });
         }
         */


        function findAllTC() {
            trainingcenterservice.findAllTrainingCenters().$promise.then(function (data) {
                $scope.trainingCenters =[];
               data.forEach(function(tc){
                    $scope.trainingCenters.push(tc.name);
                });
                $scope.trainingCenters.push("SELECT");
                $scope.selectedTrainingCenterItem = $scope.trainingCenters[$scope.trainingCenters.length - 1];
                $scope.trainingCenters.pop();

            });

        }


    }

})();
