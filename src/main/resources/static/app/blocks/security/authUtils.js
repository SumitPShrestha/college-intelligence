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

                    $http.get("/api/logout")

                    delete $rootScope.userAuth;
                    delete $http.defaults.headers.common['x-auth-token'];
                    $cookieStore.remove('user');
                    $location.url('/');
                    $rootScope.loadMenus();


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
                    var authorizedRoles;
                    if (angular.isArray(role)) {
                        authorizedRoles = role;
                    } else {
                        authorizedRoles = [role];
                    }
                    if (authorizedRoles.indexOf(ROLES.any) !== -1) {
                        return true;
                    }
                    return $rootScope.isSuperAdmin() || $rootScope.hasRole(authorizedRoles);
                };

                $rootScope.isSuperAdmin = function () {
                    return $rootScope.hasRole(ROLES.superAdmin);
                };

                $rootScope.hasRole = function (role) {

                    if (!angular.isArray(role)) {
                        return $rootScope.userAuth.roles.indexOf(role) > -1;

                    } else {
                        var has = false;
                        angular.forEach(role, function (r, key) {
                            if ($rootScope.userAuth.roles.indexOf(r) > -1) {
                                has = true;
                                return;
                            }
                        });
                        return has;
                    }
                };

                $rootScope.isAuthenticated = function () {
                    return $rootScope.isLoggedIn();
                };

                $rootScope.loadMenus = function () {
                    $rootScope.menus = [];

                    function Menu(state, label, index) {
                        this.state = state;
                        this.label = label;
                        this.index = index;
                    }

                    var getMenu = function (role) {
                        if (role === ROLES.adminUser) {
                            return [new Menu('admin', 'Admins', 1)
                                /*new Menu('user', 'Groups', 2),
                                 new Menu('any', 'Any', 3),*/
                            ]
                        }

                        if (role === ROLES.user) {
                            return [
                                new Menu('user', 'Groups', 2),
                                /*new Menu('any', 'Any', 3)*/

                            ];
                        }

                        if (role === ROLES.any) {
                            new Menu('any', 'Any', 3)
                        }

                    };

                    var addMenu = function (menu) {
                        if (menu !== undefined) {
                            if (menu instanceof Array) {
                                angular.forEach(menu, function (m) {
                                    $rootScope.menus.push(m);
                                });
                            } else {
                                $rootScope.menus.push(menu);
                            }
                        }
                    };

                    var addMenuForRole = function (role) {
                        addMenu(getMenu(role));
                    };

                    var currentUserRoles = $cookieStore.get('user').roles;

                    if (currentUserRoles.indexOf(ROLES.adminUser) !== -1) {
                        currentUserRoles = ROLES;
                    }

                    angular.forEach(currentUserRoles, function (role) {
                        addMenuForRole(role);
                    });

                };

                if ($rootScope.isLoggedIn()) {
                    $rootScope.loadMenus();
                }

                $rootScope.$on('$routeChangeStart', function (event, next) {
                    $rootScope.clearGlobalMessages();
                    if (next.originalPath == '/logout') {
                        $rootScope.logout()

                    }

                    if (next.templateUrl !== 'app/home/home.html') {
                        if (next.roles) {

                            if (!$rootScope.isAuthenticated()) {

                                event.preventDefault();
                                $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);


                            } else {

                                var authorizedRole = next.roles;
                                if (!$rootScope.isAuthorized(authorizedRole)) {
                                    event.preventDefault();
                                    $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);

                                }
                            }
                        }
                    }

                });

                $rootScope.$on(AUTH_EVENTS.loginSuccess, function () {
                        $rootScope.success = AUTH_EVENTS.loginSuccess;
                        //$rootScope.loadMenus();
                        $location.url('/Dashboard');

                    }
                );

                $rootScope.$on(AUTH_EVENTS.loginFailed, function () {

                    $location.url('/login');

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