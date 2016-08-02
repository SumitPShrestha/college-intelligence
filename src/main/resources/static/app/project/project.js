(function () {
    'use strict';

    angular.module('app.project')
        .controller('Project', Project);
    Project.$inject = ['projectservice', '$scope', 'NgTableParams', 'logger', '$http'];

    function Project(projectservice, $scope, NgTableParams, logger, $http) {
        //$scope.submitted = false;
        var self = this;
        var vm = this;
        //vm.p={};
        vm.title = 'Project Panel';
        $scope.projectmodel = {};
        $scope.fYear = ['2072-73', '2073-74', '2074-75', '2075-76', '2077-78'];
        $scope.selectedItem = $scope.fYear[0];
        findAll({fiscalYear: $scope.selectedItem});

        $scope.dropboxitemselected = function (item) {

            $scope.selectedItem = item;
            findAll({fiscalYear: $scope.selectedItem});
        }
        $scope.initCreatePanel = function () {
            $scope.clearValidationMessages();
            $scope.projectmodel = {};
             $scope.clearValidationMessages();
            vm.riskyId = 0;


            vm.title = "Create Project Panel";
            vm.showCreatePanel = !vm.showCreatePanel;
            $scope.btnText = "Create ";
        }
        $scope.clearValidationMessages = function () {
            $scope.submitted = false;

            //clear validation messages for username
            $scope.projectmodel.projectCode = "";
            $scope.projectForm.projectCode.$pristine = true;

            //clear validation messages for username
            $scope.projectmodel.budgetSubHeadNumber = "";
            $scope.projectForm.budgetSubHeadNumber.$pristine = true;
            //clear validation messages for username
            $scope.projectmodel.budget = "";
            $scope.projectForm.budget.$pristine = true;
            //clear validation messages for username
            $scope.projectmodel.aidOrganisation = "";
            $scope.projectForm.aidOrganisation.$pristine = true;


        }
        $scope.initEditPanel = function (pid) {
            $scope.clearValidationMessages();
            $scope.btnText = "Update";
            vm.riskyId = pid;
            getSingleProject(pid);

            vm.title = "Edit Project Panel";
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
                projectCode: $scope.projectmodel.projectCode,
                aidOrganisation: $scope.projectmodel.aidOrganisation,
                budget: $scope.projectmodel.budget,
                budgetSubHeadNumber: $scope.projectmodel.budgetSubHeadNumber,
                fiscalYear: $scope.selectedItem

            }

            createOrEditProject(x);

        }

        $scope.deleteTheProject = function (pid) {
            projectservice.deleteProject({id: pid}).$promise.then(function (data) {
                findAll({fiscalYear: $scope.selectedItem});
            });
        }
        function createOrEditProject(project) {
            projectservice.addProject(project).$promise.then(function (data) {
                findAll({fiscalYear: $scope.selectedItem});
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


        var heroes = [];

        function findAll(fYear) {

            $http.get(fYear.fiscalYear + '-nationalization.json').then(function (response) {
                //alert(response);
                heroes = response.data;
                projectservice.getProjectsByFiscalYear(fYear).$promise.then(function (data) {
                    data.forEach(function (val) {

                        heroes.forEach(function (loc) {
                            if (loc.key == val.projectCode) {
                                //  val.activityHead = loc.value;
                                val.projectName = loc.value;
                                //console.log("test")
                            }

                        });

                    });
                    self.tableParams = new NgTableParams({}, {dataset: data});
                });

            });


        }

    }

})();
