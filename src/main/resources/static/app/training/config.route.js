(function() {
    'use strict';

    angular
        .module('app.training')
        .run(appRun);

    // appRun.$inject = ['routehelper'];

    /* @ngInject */
    function appRun(routehelper) {
        routehelper.configureRoutes(getRoutes());
    }

    function getRoutes() {
        return [
            {
                url: '/training',
                config: {
                    templateUrl: 'app/training/create.html',
                    controller: 'training',
                    controllerAs: 'vm',
                    title: 'training',
                    sidebar:true,

                    settings: {
                        nav: 1,
                        content: '<i class="fa fa-training"></i> Training'
                    },
                    roles:['ROLE_ADMIN']
                }
            }
        ];
    }
})();
