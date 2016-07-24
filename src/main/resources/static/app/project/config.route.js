(function () {
    'use strict';

    angular
        .module('app.demo')
        .run(appRun);


    /* @ngInject */
    function appRun(routehelper) {
        routehelper.configureRoutes(getRoutes());
    }

    function getRoutes() {
        return [
            {
                url: '/demo',
                config: {
                    templateUrl: 'app/demo/demo.html',
                    controller: 'Demo',
                    controllerAs: 'vm',
                    title: 'demo',

                    settings: {
                        nav: 1,
                        content: '<i class="fa fa-shield fa-rotate-270"></i> Demo'
                    },
                    roles:['ROLE_ADMIN']
                }
            }
        ];
    }
})();
