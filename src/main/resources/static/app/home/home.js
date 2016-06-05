(function () {
    'use strict';

    angular
        .module('app.home')
        .controller('home', home);

    /* @ngInject */
    home.$inject = ['$scope', '$rootScope', 'logger', 'LoginService'];

    function home($scope, $rootScope, logger, LoginService) {
        /*jshint validthis: true */
        var vm = this;
        vm.home = [];
        vm.title = 'home';
        this.submitLogin = Login;
        function Login(credentials) {
            LoginService.login(credentials).$promise.then(
                function (responseUser) {
                    logger.info("Login Success!!!!")
                }, function (response) {
                    logger.info("Login Failed!!!!")
                }
            )

        }


    }
})();
