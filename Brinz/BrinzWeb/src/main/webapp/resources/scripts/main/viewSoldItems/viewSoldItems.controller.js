"use strict";
{

    function viewSoldItemsController($scope) {

        const me = this;

        me.soldItems = [];

        const customer = { customerName: 'name of cus' };
        for (let index = 0; index < 5; index++) {
            me.soldItems.push({ soldQuantity: index, soldPrice: 432, noOfBags: 3, soldDate: new Date(), paidDate: new Date(), customer: customer, item: 'cabbage' });
        }
    }

    app.controller('viewSoldItemsController', viewSoldItemsController);

};



