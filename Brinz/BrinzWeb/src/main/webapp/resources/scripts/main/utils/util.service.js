"use strict";
{

    function utilsService(ngDialog) {

        const me = this;

        // show error popup
        me.processError = function (errorMessage, errorMessageDetails) {
            ngDialog.open({
                template: '\
                <div class="Brinz-alert-title">'+ errorMessage + '</div>\
                <div class="Brinz-alert-message">'+ errorMessageDetails + '</div>\
                <p class="Brinz-alert-time">'+ new Date() + '</p>\
                <div class="ngdialog-buttons">\
                    <button type="button" class="ngdialog-button ngdialog-button-primary" autofocus="true" ng-click="closeThisDialog()">OK</button>\
                </div>',
                plain: true
            });
        }; // END - processError()

        // Confirmation popup
        me.confirmationPopup = function (confirmationMessage, successCallbackFunction, erroCallbackFunction, successParam, errorParam) {
            const confirmation = ngDialog.open({
                template: '\
                <div class="Brinz-confirmation-message">'+ confirmationMessage + '</div>\
                <div class="ngdialog-buttons">\
                    <button type="button" class="ngdialog-button ngdialog-button-secondary" ng-click="closeThisDialog(0)">No</button>\
                    <button type="button" class="ngdialog-button ngdialog-button-primary" autofocus="true" ng-click="closeThisDialog(1)">Yes</button>\
                </div>',
                plain: true,
                closeByEscape: false,
                closeByDocument: false,
                showClose: false
            });

            confirmation.closePromise.then(function (data) {
                if (data.value == 1) {
                    successCallbackFunction(successParam);
                } else if (erroCallbackFunction) {
                    erroCallbackFunction(errorParam);
                }
                console.log(data.id + ' has been dismissed.');
            });


        }; // END - confirmationPopup()

        // show alert popup
        me.alertPopup = function (alertTitle, alertDetails, successCallbackFunction) {
            const alertPopup = ngDialog.open({
                template: '\
                <div class="Brinz-alert-title">'+ alertTitle + '</div>\
                <div class="Brinz-alert-message">'+ alertDetails + '</div>\
                <div class="ngdialog-buttons">\
                    <button type="button" class="ngdialog-button ngdialog-button-primary" autofocus="true" ng-click="closeThisDialog(1)">OK</button>\
                </div>',
                plain: true,
                closeByEscape: false,
                closeByDocument: false,
                showClose: false
            });

            alertPopup.closePromise.then(function (data) {
                if (data.value == 1 && successCallbackFunction) {
                    successCallbackFunction();
                }
            });

        }; // END - alertPopup()

    };

    app.service('utilsService', utilsService);

};
