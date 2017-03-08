"use strict";
{

    function utilsService(ngDialog) {

        const me = this;

        // show error popup
        me.processError = function (errorRequest, errorMessage, errorMessageDetails) {
            ngDialog.open({ template: 'res/scripts/main/menu/menu.template.html', className: 'ngdialog-theme-default', closeByDocument: false});

        }; // END - processError()

        // Confirmation popup
        me.confirmationPopup = function (confirmationMessage, successCallbackFunction, erroCallbackFunction, successParam, errorParam) {
            // preCloseCallback: 
            const confirmationDialog = $mdDialog.confirm()
                .title(confirmationMessage)
                .ariaLabel('confirmation prompt')
                .ok('YES')
                .cancel('NO')
                .openFrom({
                    top: -50,
                    width: 30,
                    height: 80
                })
                .closeTo({
                    right: 1500
                });

            $mdDialog.show(confirmationDialog).then(function () {
                successCallbackFunction(successParam);
            }, function () {
                erroCallbackFunction(errorParam);
            });

        }; // END - processError()

        // show alert popup
        me.alertPopup = function (alertTitle, alertDetails, sucessCallbackFunction) {
            $mdDialog.show(
                $mdDialog.alert()
                    .clickOutsideToClose(false)
                    .title(alertTitle)
                    .textContent(alertDetails)
                    .ariaLabel('Alert infromation message')
                    .ok('Close')
                    .openFrom({
                        top: -50,
                        width: 30,
                        height: 80
                    }).closeTo({
                        right: 1500
                    })
            ).then(function () {
                sucessCallbackFunction();
            }, null);

        }; // END - processError()

    };

    app.service('utilsService', utilsService);

};
