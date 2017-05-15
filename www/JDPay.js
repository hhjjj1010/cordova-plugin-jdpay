var exec = require('cordova/exec');


exports.payment = function (params, success, error) {
    exec(success, error, "JDPay", "payment", [params]);
};
