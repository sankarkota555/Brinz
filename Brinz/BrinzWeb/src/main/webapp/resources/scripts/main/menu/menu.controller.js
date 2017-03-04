"use strict";
{

    function menuController($scope, $location) {
        const me = this;
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        };

    };

    app.controller('menuController', menuController);
};
