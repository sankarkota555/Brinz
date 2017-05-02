"use strict";
{

    function menuRoutes($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/billing");

        $stateProvider.state('home', {
            url: '/',
        }).state('billing', {
            url: '/billing',
            templateUrl: 'res/scripts/main/billling/billing.template.html',
            controller: 'billingController',
            controllerAs: 'billingController'
        }).state('viewSoldItems', {
            url: '/viewSoldItems',
            templateUrl: 'res/scripts/main/viewSoldItems/viewSoldItems.template.html',
            controller: 'viewSoldItemsController',
            controllerAs: 'viewSoldItemsController'
        }).state('manage', {
            url: '/manage',
            templateUrl: 'res/app/main/viewPort/content/manage/manage.template.html',
            controller: 'manageController',
            controllerAs: 'manageController'
        }).state('manage.items', {
            url: '/items',
            templateUrl: 'res/main/viewPort/content/viewBills/viewBills.template.html',
            controller: 'itemsController',
            controllerAs: 'itemsController'
        }).state('manage.companies', {
            url: '/companies',
            templateUrl: 'res/main/viewPort/content/viewBills/viewBills.template.html',
            controller: 'itemsController',
            controllerAs: 'itemsController'
        });

    };
    
    //angular.module("Brinz").config(menuRoutes);

    app.config(menuRoutes);

};
