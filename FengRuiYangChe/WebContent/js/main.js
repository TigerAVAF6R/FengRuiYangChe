angular.module('ngAdmin', ["ui.router", 'ui.bootstrap', 'ngAdmin.pageoneCtl'])
    .config(function ($stateProvider, $urlRouterProvider,$provide,$httpProvider) {
        $urlRouterProvider.otherwise("/pageone");
        $stateProvider.state('pageone', {
            url: "/pageone",
            templateUrl: "./js/customize/template/pageone.html",
            controller: "pageoneController"
        });

        $provide.factory('MyHttpInterceptor', function ($q) {
            return {
                request: function (config) {
                    console.log("request send out!");
                    document.getElementById("load").style.display="block";
                    return config || $q.when(config);
                },
                requestError: function (rejection) {
                    return $q.reject(rejection);
                },
                response: function (response) {
                    console.log("response is back!");
                    document.getElementById("load").style.display="none";
                    return response || $q.when(response);
                },
                responseError: function (rejection) {
                    return $q.reject(rejection);
                }
            };
        });

        $httpProvider.interceptors.push('MyHttpInterceptor');
    });






