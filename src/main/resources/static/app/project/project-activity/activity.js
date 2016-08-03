(function () {
    'use strict';

    angular.module('app.project')
        .controller('Activity', Activity);
    Activity.$inject = ['activityservice', 'goalservice', '$scope', 'NgTableParams', 'logger', '$routeParams', '$http'];
    function Activity(activityservice, goalservice, $scope, NgTableParams, logger, $routeParams, $http) {
        var pCode = $routeParams.code;
        var fYear = $routeParams.fiscalYear;
        findAllActivities(pCode, fYear);

        $scope.activitymodel = {};


        // alert($routeParams.code);
        $scope.initCreatePanel = function () {
            $scope.clearValidationMessages();
            // $scope.clearValidationMessages();
            vm.riskyId = 0;


            vm.title = "Create Project Activity Panel";
            vm.neep = "कुरा आजभन्दा ६ महिना अगाडीको हो । म माघको २७ गते रामेछाप गएको थिए, त्यहाँ मेरो मामाघर हो र मामाको ";
            vm.peep = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"

            vm.showCreatePanel = !vm.showCreatePanel;
            $scope.btnText = "Create ";
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


            var p = {projectCode: pCode};
            var x = {
                id: vm.riskyId,
                activityHead: $scope.activitymodel.activityHead
                ,
                expenseHead: $scope.activitymodel.expenseHead,
                budget: $scope.activitymodel.budget,
                unit: $scope.activitymodel.unit,
                projectCode: pCode


            }

            createOrEditActivity(x);

        }


        $scope.clearValidationMessages = function () {
            $scope.submitted = false;

            //clear validation messages for username
            $scope.activitymodel.activityHead= "";
            $scope.activityForm.activityHead.$pristine = true;

            //clear validation messages for username
            $scope.activitymodel.expenseHead = "";
            $scope.activityForm.expenseHead.$pristine = true;
            //clear validation messages for username
            $scope.activitymodel.unit = "";
            $scope.activityForm.unit.$pristine = true;
            //clear validation messages for username
            $scope.activitymodel.budget  = "";
            $scope.activityForm.budget.$pristine = true;

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
            if (isValid) {
                var x = {
                    goal: {
                        id: $scope.q1goalmodel.id,
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
            if (isValid) {
                var x = {
                    goal: {
                        id: $scope.q2goalmodel.id,
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
            if (isValid) {
                var x = {
                    goal: {

                        id: $scope.q3goalmodel.id,
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
        $scope.clearForm = function () {
            $scope.q1goalmodel.id = 0;
            $scope.q1goalmodel.qty = "";
            $scope.q1goalmodel.weightage = "";
            $scope.q1goalmodel.budget = "";
            $scope.q2goalmodel.id = 0;
            $scope.q2goalmodel.qty = "";
            $scope.q2goalmodel.weightage = "";
            $scope.q2goalmodel.budget = "";
            $scope.q3goalmodel.id = 0;
            $scope.q1goalmodel.qty = "";
            $scope.q1goalmodel.weightage = "";
            $scope.q1goalmodel.budget = "";


        }

        findAllActivities(pCode,fYear);
        //$scope.submitted = false;
        var self = this;
        var vm = this;

        function createOrEditGoal(goal) {
            goalservice.addGoal(goal).$promise.then(function (data) {
                // findAll();
                $scope.closeTheGoalPanel();
            });

        }

        function getSingleProjectActivity(activityId) {
            activityservice.getSingleActivity({id: activityId}).$promise.then(function (data) {
                setProjectModels(data);
            });


        }

        function setProjectModels(data) {
            $scope.activitymodel.activityHead = data.activityHead;
            $scope.activitymodel.expenseHead = data.expenseHead;
            $scope.activitymodel.budget = data.budget;
            $scope.activitymodel.unit = data.unit;
            //$scope.activitymodel.project = data.fiscalYear;

        }

        function createOrEditActivity(activity) {
            activityservice.addActivity(activity).$promise.then(function (data) {
                findAllActivities(pCode,fYear);
                $scope.closeThePanel();
            });

        }

        var heroes = [];

        function findAllActivities(pCode, fYear) {

            $http.get(fYear + '-nationalization.json').then(function (response) {
                heroes = response.data;
                activityservice.findAllActivities({id: pCode}).$promise.then(function (data) {
                    data.forEach(function (val) {

                        heroes.forEach(function (loc) {
                            if (loc.key == val.activityHead) {
                                //  val.activityHead = loc.value;
                                val.activityName = loc.value;
                                //console.log("test")
                            }

                        });

                    });
                    self.tableParams = new NgTableParams({}, {dataset: data});
                });

            });


        }

        function getGoalsByActivityIdAndSetModels(aid) {
            goalservice.getGoalsByActivityId({id: aid}).$promise.then(function (goals) {
                goals.forEach(function (goal) {
                    if (goal.timeFrame == "FIRST_QUARTER") {
                        $scope.q1goalmodel = {};
                        $scope.q1goalmodel.id = goal.id;
                        $scope.q1goalmodel.qty = goal.qty;
                        $scope.q1goalmodel.weightage = goal.weightage;
                        $scope.q1goalmodel.budget = goal.budget;
                        $scope.q1btnText = "Update";

                    }

                    if (goal.timeFrame == "SECOND_QUARTER") {
                        $scope.q2goalmodel = {};
                        $scope.q2goalmodel.id = goal.id;
                        $scope.q2goalmodel.qty = goal.qty;
                        $scope.q2goalmodel.weightage = goal.weightage;
                        $scope.q2goalmodel.budget = goal.budget;
                        $scope.q2btnText = "Update";

                    }

                    if (goal.timeFrame == "THIRD_QUARTER") {
                        $scope.q3goalmodel = {};
                        $scope.q3goalmodel.id = goal.id;
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
