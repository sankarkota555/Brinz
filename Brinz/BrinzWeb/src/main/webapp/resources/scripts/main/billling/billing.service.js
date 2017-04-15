"use strict";
{

    function billingService($http) {
        const me = this;

        /**
         * Function to save bill
         */
        me.saveBill = function (bill) {
            console.log("bill received in service: ");
            console.log(bill);
            bill = prepareBillDetails(bill);
            console.log("bill Prepared ");
            console.log(bill);
            const config = { data: bill };
            return $http({ url: "saveBill", method: 'POST', data: bill });
        };

        /**
         * Function to prepare bill
         * @param {object} bill 
         * @returns prepared bill for backend call
         */
        function prepareBillDetails(bill) {
            const preparedBill = { customer: {} };
            preparedBill.soldItems = [];
            //check customer existence
            if (bill.customer.selected && bill.customer.selected.customerId) {
                // if exists add customer ID
                preparedBill.customer.customerId = bill.customer.selected.customerId;
            } else {
                // if not exists add customer details
                preparedBill.customer.customerName = bill.customer.selected;
                preparedBill.customer.phone = bill.customer.phone;
            }
            // prepare sold item details
            for (let index = 0; index < bill.items.length; index++) {
                const soldItem = { item: {} };
                soldItem.soldQuantity = bill.items[index].kgs;
                soldItem.soldPrice = bill.items[index].soldPrice;
                // check if item existence 
                if (bill.items[index].selectedItem && bill.items[index].selectedItem.itemId) {
                    // if exists add item ID
                    soldItem.item.itemId = bill.items[index].selectedItem.itemId;
                } else {
                    // if not exists add item Name
                    soldItem.item.itemName = bill.items[index].selectedItem;
                    soldItem.item.price = bill.items[index].soldPrice;
                }

                preparedBill.soldItems.push(soldItem);
                preparedBill.totalAmount = bill.totalAmount;
            }

            return preparedBill;
        }

        /**
         * Calculates total amount of bill
         */
        me.calculateTotal = function (items) {
            let total = 0;
            for (index in items) {
                if (items[index].kgs && items[index].soldPrice) {
                    total += items[index].kgs * items[index].soldPrice;
                }
            }
            return total;
        }

    };

    app.service('billingService', billingService);
};
