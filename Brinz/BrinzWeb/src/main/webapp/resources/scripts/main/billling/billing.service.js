"use strict";
{

    function menu() {
        return {
            template: require('./menu.template.html'),
            controller: 'menuController',
            controllerAs: 'menuController'
        }
    };

    //angular.module("Brinz").directive('menu', menu);

    app.directive('menu', menu);
};
