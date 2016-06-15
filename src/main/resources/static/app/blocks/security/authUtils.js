/**
 * Created by amit on 6/12/16.
 */
(function () {
    'use strict';

    angular.module("blocks.security")
        .run(['$rootScope', '$cookieStore', '$http', 'AUTH_EVENTS', 'ROLES', '$location', '$templateCache',
            function ($rootScope, $cookieStore, $http, AUTH_EVENTS, ROLES, $location, $templateCache) {

                //   $rootScope.uiState = $state;

                $rootScope.logout = function () {

                    $http.get("/auth/logout")

                    delete $rootScope.userAuth;
                    delete $http.defaults.headers.common['x-auth-token'];
                    $cookieStore.remove('user');
                    //$rootScope.loadMenus();


                };

                $rootScope.isLoggedIn = function () {
                    $rootScope.userAuth = $cookieStore.get('user');
                    var user = $rootScope.userAuth;
                    return angular.isDefined(user) &&
                        angular.isDefined(user.username) &&
                        angular.isDefined(user.token) &&
                        angular.isDefined(user.roles);
                };

                $rootScope.isAuthorized = function (role) {
                    var a = false;
                    var authorizedRoles;
                    if (angular.isArray(role)) {
                        authorizedRoles = role;
                    } else {
                        authorizedRoles = [role];
                    }
                    angular.forEach(authorizedRoles, function (r) {
                        if ($rootScope.userAuth.roles.indexOf(r) != -1) {
                            a = true;
                            return false;
                        } else {
                            return true;
                        }

                    })
                    return a;

                };

                $rootScope.userHasRole = function(role){
                    return $rootScope.userAuth.roles.indexOf(role) != -1
                }

                $rootScope.isAuthenticated = function () {
                    return $rootScope.isLoggedIn();
                };

                $rootScope.$on('$routeChangeStart', function (event, next) {
                    $rootScope.sideBar = next.sidebar||false;
                    $rootScope.clearGlobalMessages();
                    if (next.originalPath == '/logout') {
                        $rootScope.logout()
                        event.preventDefault();
                    } else {

                        if (next.templateUrl !== 'app/home/home.html') {
                            if (!$rootScope.isAuthenticated()) {
                                $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
                                event.preventDefault();
                            } else {
                                if (!$rootScope.isAuthorized(next.roles)) {
                                    $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
                                    event.preventDefault();
                                }
                            }
                        }
                    }
                });

                $rootScope.$on(AUTH_EVENTS.loginSuccess, function () {
                        $rootScope.success = AUTH_EVENTS.loginSuccess;
                        $location.url('/Dashboard');
                    }
                );

                $rootScope.$on(AUTH_EVENTS.loginFailed, function () {
                    $location.url('/home');
                });

                $rootScope.$on(AUTH_EVENTS.notAuthenticated, function () {
                    $rootScope.$broadcast("message", "you must be logged in to view this page")
                    $rootScope.logout();
                });

                $rootScope.$on(AUTH_EVENTS.notAuthorized, function (event) {
                    $rootScope.$broadcast("message", "you dont have authority to view this page")
                    event.preventDefault();
                });
                $rootScope.$on('message', function (event, data) {
                    $rootScope.message = data;
                });
                $rootScope.clearGlobalMessages = function () {
                    $rootScope.error = null;
                    $rootScope.success = null;
                    $rootScope.message = null;
                };
            }
        ])
})();