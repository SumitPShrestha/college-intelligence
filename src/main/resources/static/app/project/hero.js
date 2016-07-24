(function () {
    'use strict';

    angular.module('app.project')
        .controller('Project', Project);
    Project.$inject = ['projectservice', '$scope', 'NgTableParams', 'logger'];

    function Project(projectservice, $scope, NgTableParams, logger) {
        //$scope.submitted = false;
        var self = this;
        var vm = this;
        vm.p={};
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


        function findAll( fiscalYear) {
            projectservice.getProjectsByFiscalYear(fiscalYear).$promise.then(function (data) {
                self.tableParams = new NgTableParams({}, {dataset: data});
            });

        }

    }

})();
