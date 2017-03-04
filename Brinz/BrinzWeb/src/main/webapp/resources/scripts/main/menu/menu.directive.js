"use strict";
{

    function menu() {
        return {
            template: require('./menu.template.html'),
            controller: 'menuController',
            controllerAs: 'menuController'
        }
    };

    app.directive('menu', menu);

};
