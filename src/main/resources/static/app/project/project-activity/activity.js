(function () {
    'use strict';

    angular.module('app.project')
        .controller('Activity', Activity);
    Activity.$inject = ['activityservice', 'goalservice', '$scope', 'NgTableParams', 'logger', '$routeParams'];

    function Activity(activityservice, goalservice, $scope, NgTableParams, logger, $routeParams) {
        var pCode = $routeParams.code;

        $scope.activitymodel={};

        // alert($routeParams.code);
        $scope.initCreatePanel = function () {
            $scope.clearValidationMessages();
            // $scope.clearValidationMessages();
            vm.riskyId = 0;


            vm.title = "Create Project Activity Panel";
            vm.showCreatePanel = !vm.showCreatePanel;
            $scope.btnText = "Create ";
        }

        $scope.clearValidationMessages=function(){
            $scope.submitted=false;
        }





        $scope.initEditPanel = function (aid) {
            $scope.clearValidationMessages();
            $scope.btnText = "Update";
            vm.riskyId = aid;
            getSingleProjectActivity(aid);

            vm.title = "Edit Project Activity Panel";
            vm.showCreatePanel = true;
        }


        $scope.initializeGoalPanel = function (aid) {

            vm.showGoalPanel = true;
            vm.goalTitle = "Create Goal";
            $scope.q1btnText = "Create";
            $scope.q2btnText = "Create";
            $scope.q3btnText = "Create";
            $scope.activityId = aid;
            getGoalsByActivityIdAndSetModels(aid);


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
                    activityId: $scope.activityId
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
                    activityId: $scope.activityId
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
                    activityId: $scope.activityId
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

        $scope.closeThePanel = function () {
            vm.showCreatePanel = !vm.showCreatePanel;
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

        function createOrEditGoal(goal) {
            goalservice.addGoal(goal).$promise.then(function (data) {
                // findAll();
                $scope.closeTheGoalPanel();
            });

        }
        function getSingleProjectActivity(activityId){
            activityservice.getSingleActivity({id: activityId}).$promise.then(function (data) {
                setProjectModels(data);
            });


        }
        function setProjectModels(data) {
            $scope.activitymodel.activityHead = data.activityHead;
            $scope.activitymodel.expenseHead = data.expenseHead;
            $scope.activitymodel.budget = data.budget;
            $scope.activitymodel.unit = data.unit;
           // $scope.activitymodel.fiscalYear = data.fiscalYear;

        }



        function findAllActivities(pCode) {
            activityservice.findAllActivities({id: pCode}).$promise.then(function (data) {
                self.tableParams = new NgTableParams({}, {dataset: data});
            });

        }
        function getGoalsByActivityIdAndSetModels(aid) {
            goalservice.getGoalsByActivityId({id: aid}).$promise.then(function (goals) {
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
    }

})();
