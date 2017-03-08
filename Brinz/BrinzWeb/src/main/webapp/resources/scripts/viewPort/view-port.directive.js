"use strict";
{

    function viewPort() {
        return {
        	templateUrl : "res/scripts/viewPort/view-port.template.html",
            controller: 'viewPortController',
            controllerAs: 'viewPortController'
        }
    };

    app.directive('viewPort', viewPort);

};
