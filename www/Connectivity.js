module.exports = {
	getNetworkInfo: function(success, error) {
		var options = {}; // não usado
		cordova.exec(success, error, "Connectivity", "getNetworkInfo", [options]);
	}
};
