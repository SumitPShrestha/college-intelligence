(function () {
    'use strict';

    angular.module('app.trainingcenter')
        .controller('TrainingCenter', TrainingCenter);
    TrainingCenter.$inject = ['trainingcenterservice', '$scope', 'NgTableParams', 'logger'];

    function TrainingCenter(trainingcenterservice, $scope, NgTableParams, logger) {

        var self = this;

        var vm = this;

        $scope.initCreatePanel = function () {

            $scope.tcmodel = {};
            vm.title = "Create Training Center";
            vm.showCreatePanel = !vm.showCreatePanel;
            $scope.btnText = "Create ";
        }
        $scope.trainingCenter = ['Ramechhap', 'Rasuwa', 'Sindhupalchowk'];
        $scope.selectedTrainingCenterItem=$scope.trainingCenter[0];

        $scope.closeThePanel = function () {
            vm.showCreatePanel = !vm.showCreatePanel;
        }

        $scope.trainingCenterDropboxitemselected = function (item) {

            $scope.selectedTrainingCenterItem = item;
        }


    }

})();
