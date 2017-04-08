"use strict";
{

    function itemsService($http) {

        const me = this;

        me.findItemsByName = function (name) {
            const config = {
                params: { name: name }
            }
            return $http.get('findItemByName', config);
        };


        me.findAllItems = function (name) {
            return $http.get('findAllItems');
        };

        me.saveItem = function (item) {
            return $http.post('findAllItems');
        }

    };

    app.service('itemsService', itemsService);
};
