"use strict";
{

    function customerService($http) {
        const me = this;

        me.findByCustomerName = function (name) {
            const config = {
                params: { name: name }
            }
            return $http.get('findCustomerByName', config);
        };

    };

    //angular.module("Brinz").directive('menu', menu);

    app.service('customerService', customerService);
};
