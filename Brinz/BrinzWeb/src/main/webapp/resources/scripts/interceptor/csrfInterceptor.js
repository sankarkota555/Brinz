"use strict";
{

    function csrfInterceptor($cookies, $injector) {

        return {

            request: function (config) {

                var token = $cookies.get('XSRF-TOKEN');
                console.log("all cookies: ");
                console.log($cookies.getAll());
                if (token) {
                    config.headers['X-CSRF-TOKEN'] = token;
                }
                return config;
            },

            response: function (responseConfig) {

                if (responseConfig.config.url.indexOf("/logout") == -1 && responseConfig.data.indexOf("<html>") != -1) {
                    let userActivityService = $injector.get("userActivityService");
                    let utilsService = $injector.get("utilsService");
                    utilsService.alertPopup("Session Expired!!", "Please Login...", userActivityService.navigateTologin);
                    return null;
                }
                return responseConfig;
            }
        }

    }


   app.factory('csrfInterceptor', csrfInterceptor);


    /**
     * COnfigure interceptor
     */
    app.config(['$httpProvider', function ($httpProvider) {

        $httpProvider.defaults.withCredentials = true;
        $httpProvider.interceptors.push('csrfInterceptor');

    }]);

}
