"use strict";
{

    function billingController($scope, utilsService) {
        const me = this;

        me.appStart = "RapidNetSports";


      //utilsService.processError(null,null,null);

      console.log("inside billing conreolller");

        /* $scope.$watch(function(){ return menuService.menuBackground;},function(newValue,oldValue) {
             me.menuBacground = newValue;
         });*/



    };

    //angular.module("payment").controller('viewPortController', viewPortController);
    app.controller('billingController', billingController);

};
