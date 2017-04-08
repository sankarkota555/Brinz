"use strict";
{

    function itemsController($scope, utilsService, itemsService) {
        const me = this;

        me.AllItems = [];


		/**
		 * Serches for given item in DB by name
		 */
        function findAllItems() {   
            if (name && name.length >= 2) {
                itemsService.findAllItems().then(
                    function (response) {
                        console.log("returnung data: ", response.data);
                        me.AllItems = response.data;
                    },
                    function (response) {
                        console.log("error while searching user:", response);
                        me.AllItems = [];
                    });
            }

        };


    }

    app.controller('billingController', billingController);

};
