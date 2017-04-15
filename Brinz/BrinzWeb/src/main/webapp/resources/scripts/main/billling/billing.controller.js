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
						if (response) {
							me.foundCustomers = response.data;
						} else {
							me.foundCustomers = [];
						}
					},
					function (response) {
						console.log("error while searching user:", response);
						me.foundCustomers = [];
						processError(response);
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
						if (response) {
							me.foundItems = response.data;
						} else {
							me.foundItems = [];
						}

					},
					function (response) {
						console.log("error while searching user:", response);
						me.foundItems = [];
						processError(response);
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
			utilsService.confirmationPopup('Are you want to save it', saveBillToDb);
		};

		function saveBillToDb() {
			billingService.saveBill(me.bill).then(
				function (response) {
					utilsService.confirmationPopup('Are you want to save it', saveBillToDb);
				},
				function (response) {
					processError(response);
				});
		}

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

		function processError(response) {
			utilsService.processError(response.data.error, response.data.exception + ":" + response.data.message);
		}


	}

	app.controller('billingController', billingController);

};
