"use strict";
{

	function billingController($scope, utilsService, customerService, itemsService, billingService) {
		const me = this;
		me.bill = {};
		me.bill.items = [{ name: "" }];
		me.foundCustomers = [];
		me.foundItems = [];

		$scope.amountReadOnly = true;

		/**
		 * Seraches for given customer  in DB by name
		 */
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

		};

		/**
		 * Serches for given item in DB by name
		 */
		me.findItems = function (name) {
			if (name && name.length >= 2) {
				itemsService.findItemsByName(name).then(
					function (response) {
						console.log("returnung data: ", response.data);
						me.foundItems = response.data;
					},
					function (response) {
						console.log("error while searching user:", response);
						me.foundItems = [];
					});
			}

		};

		/**
		 * Add one item to bill
		 */
		me.addBillItem = function () {
			me.bill.items.push({ name: "" });
		}

		/**
		 * to save bill.
		 */
		me.saveBill = function () {
			billingService.saveBill(me.bill).then(
				function (response) {
					console.log("returnung data: ", response.data);

				},
				function (response) {
					console.log("error while saving bill:", response);

				});
		};

		/**
		 * This function will executes when customer is selected from Auto completed
		 * It used to update mobile number of customer automatically 
		 */
		me.customerSelected = function (item) {
			if (item.phone) {
				//auto update custoemr phone numner
				me.bill.customer.phone = item.phone;
				$scope.amountReadOnly = true;
				//item.creditAmoount;
			}

		};


	}

	app.controller('billingController', billingController);

};
