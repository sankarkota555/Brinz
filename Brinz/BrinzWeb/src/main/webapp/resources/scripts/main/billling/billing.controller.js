"use strict";
{

	function billingController($scope, utilsService, customerService) {
		const
			me = this;
		me.bill = {};
		me.bill.items = [{}];
		me.foundCustomers = [];

		me.findCustomers = function (name) {
			if (name && name.length >= 2) {
				customerService.findByCustomerName(name).then(
					function (response) {
						console.log("returnung data: ", response.data);
						me.foundCustomers = response.data;
					},
					function (response) {
						console.log("error while searching user:", response);
						me.foundCustomers = [];
					});
			}

		}


	}

	app.controller('billingController', billingController);

};
