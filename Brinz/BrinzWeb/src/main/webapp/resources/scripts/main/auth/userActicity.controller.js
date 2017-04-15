"use strict";
{

    function userActivityController($scope, userActivityService, utilsService) {

        const me = this;
        me.logout = function () {
            userActivityService.logout().then(function (response) {
                console.log("successfully logged out");
                console.log(response);
                userActivityService.logoutSuccess();
                utilsService.alertPopup('Successfully logged out !!', "", userActivityService.navigateTologin);
            },
                function (response) {
                    console.log("failed to logout");
                    // show error opoup
                    processError(response);
                });;
        }

        function processError(response) {
            utilsService.processError(response.data.error, response.data.exception + ":" + response.data.message);
        }


    };

    app.controller('userActivityController', userActivityController);

};