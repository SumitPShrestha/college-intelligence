/**
 * Created by i82298 on 6/4/2016.
 */
(function () {
    'use strict';
    angular
        .module('app.project')
        .factory('progressservice', progressservice);

    function progressservice($resource) {
        return $resource("/privileged/progress",
            {Id: "@Id"},
            {
               // editUser: {method: "PUT", 'params': {id: '@id'}},
                editProject: {method: "PUT"},
                findAllActivities: {'url':'/privileged/activity/code/:id','method': 'GET', isArray: true,'params': {id: '@id'}},
                addProject: {'url':'/admin/projects','method': 'POST'},
                deleteProject: {'url':'/all/project/:id','method': 'DELETE','params': {id: '@id'}},
                getProject: {'url':'/all/project/pid/:id','method': 'GET','params': {id: '@id'}},
                getProjectsByFiscalYear: {'url':'/all/project/:fiscalYear','method': 'GET','params': {fiscalYear: '@fiscalYear'},isArray: true}


            }
        )


    }
})();