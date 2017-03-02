import angularAnimate from 'angular-animate';
import angularMaterial from 'angular-material';
import angularCookies from 'angular-cookies';
import angularMessages from 'angular-messages';
import bootstrap from 'angular-ui-bootstrap';
import tabs from 'angular-ui-bootstrap/src/tabs';
import accordion from 'angular-ui-bootstrap/src/accordion';
import datepicker from 'angular-ui-bootstrap/src/datepicker';
import timepicker from 'angular-ui-bootstrap/src/timepicker';
import modal from 'angular-ui-bootstrap/src/modal';
import uiRouter from 'angular-ui-router';

const app = 
angular.module("Brinz", [
	bootstrap,
	angularAria,
	angularAnimate,
	angularMaterial,
	angularCookies,
	angularMessages,
	tabs,
	uiRouter,
	accordion,
	datepicker,
	timepicker,
	modal
]);

setTimeout(
	function asyncBootstrap() {
		angular.bootstrap(document, ["Brinz"]);
	}, (2 * 1000));