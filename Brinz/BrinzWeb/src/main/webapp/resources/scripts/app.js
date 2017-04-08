
const app =
	angular.module("Brinz", [
		'ui.router',
		'ngCookies',
		'ngDialog',
		'ui.bootstrap',
		'ngAnimate'
	]);

setTimeout(
	function asyncBootstrap() {
		angular.bootstrap(document, ["Brinz"]);
	}, (2 * 1000));