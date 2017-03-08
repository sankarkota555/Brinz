"use strict";
{

    function menu() {
        return {
            templateUrl: "res/scripts/main/menu/menu.template.html",
            controller: 'menuController',
            controllerAs: 'menuController'
        }
    };

    app.directive('menu', menu);

};
