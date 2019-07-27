'use strict';

var response = require('./res');
var connection = require('./conn');
var request = require('request');

exports.index = function(req, res) {
    res.sendFile( __dirname + "/public/" + "index.html" );
};

exports.notFoundPage = function(req, res) {
    response.notFound(res);
};

exports.loginUser = function(req, res) {
    var sql = "SELECT user_id, user_name, user_tel, user_email, user_type FROM `users_list` WHERE `user_tel` = ?";
    var userTel = req.body.user_tel;
    var OTP_ACCOUNT = "numb_brianra4";
    var OTP_PASSWORD = "123456";
    
    function generateOtpUrl(numbers, content) {
        content = encodeURIComponent(content.trim());
        return "http://103.81.246.59:20003/sendsms?account=" + OTP_ACCOUNT + "&password=" + OTP_PASSWORD + "&numbers=" + numbers + "&content=" + content;
    }

    function generateOtpMessage(otp) {
        return "(TAMAGO) JANGAN BAGIKAN KODE INI KEPADA SIAPAPUN. Kode verifikasi OTP Tamago Anda adalah " + otp;
    }

    if (userTel == null) {
        response.error("Data supplied not sufficient", res);
    }

    connection.query(sql, [userTel], function (error, rows, fields){
        if (error){
            console.log(error);
            response.error(error, res);
        } else {
            if (rows.length == 1) {
                var userId = rows[0].user_id;

                // send OTP
                var otpError;
                var otp = Math.floor(100000 + Math.random() * 900000);
                //console.log("OTP for " + userTel + " is " + otp);

                request(generateOtpUrl(userTel, generateOtpMessage(otp)), function (error, response, body) {
                if (!error && response.statusCode == 200) {
                    //console.log(body);
                    if (body.status == 0 && body.success != 0) {
                        otpError = false;
                    } else if (body.fail != 0) {
                        otpError = true;
                    } else if (body.status == -1) {
                        otpError = true;
                    }
                }
                });

                if (!otpError) {
                    response.ok("OTP sent successfully", res);
                } else {
                    response.ok("OTP send failed", res);
                }

                // update OTP
                var sql = "UPDATE `users_list` SET `latest_otp` = '?' WHERE `users_list`.`user_id` = ?;"
                connection.query(sql, [otp, userId]);

            } else if (rows.length == 0) {
                // register user
                response.ok("User not found", res);
            }
        }
    });
};

exports.getUserProfile = function(req, res) {
    var sql = "SELECT user_id, user_name, user_tel, user_email, user_type FROM `users_list` WHERE user_id=?";
    var userId = req.body.user_id;

    if (userId == null) {
        response.error("Data supplied not sufficient", res);
    } else {
        connection.query(sql, [userId], function (error, rows, fields){
            if (error){
                console.log(error);
                response.error(error, res);
            } else {
                if (rows.length == 1) {
                    var userId = rows[0].user_id;
                    response.ok(rows[0], res);
                } else if (rows.length == 0) {
                    response.error("Invalid data supplied", res);
                }
            }
        });
    }
};

exports.loginChild = function(req, res) {
    var sql = "SELECT child_id, child_name, child_avatar, DATE_FORMAT(child_dob, \"%Y-%m-%d\") AS `child_dob`, child_gender, child_savings, child_wallet, child_daily_limit, child_eggs FROM `children_list` WHERE child_id=(SELECT pc_conn_child FROM `parent_child_connection` WHERE pc_conn_parent=? AND pc_conn_child=?)";
    var childId = req.body.child_id;
    var userId = req.body.user_id;

    if (childId == null || userId == null) {
        response.error("Data supplied not sufficient", res);
    } else {
        connection.query(sql, [userId, childId], function (error, rows, fields){
            if (error){
                console.log(error);
                response.error(error, res);
            } else {
                if (rows.length == 1) {
                    var userId = rows[0].user_id;
                    response.ok(rows[0], res);
                } else if (rows.length == 0) {
                    response.error("Invalid data supplied", res);
                }
            }
        });
    }
};

exports.getChildProfile = function(req, res) {
    var sql = "SELECT child_id, child_name, child_avatar, DATE_FORMAT(child_dob, \"%Y-%m-%d\") AS `child_dob`, child_gender, child_savings, child_wallet, child_daily_limit, child_eggs FROM `children_list` WHERE child_id=?";
    var childId = req.body.child_id;

    if (childId == null) {
        response.error("Data supplied not sufficient", res);
    } else {
        connection.query(sql, [childId], function (error, rows, fields){
            if (error){
                console.log(error);
                response.error(error, res);
            } else {
                if (rows.length == 1) {
                    var userId = rows[0].user_id;
                    response.ok(rows[0], res);
                } else if (rows.length == 0) {
                    response.error("Invalid data supplied", res);
                }
            }
        });
    }
};

exports.verifyOtp = function(req, res) {
    var sql = "SELECT `user_id`, `user_name`, `user_tel`, `user_email`, `user_type` FROM `users_list` WHERE `user_tel` = ? AND `latest_otp` = ?";
    var userTel = req.body.user_tel;
    var otp = req.body.otp;

    if (userTel == null || otp == null) {
        response.error("Data supplied not sufficient", res);
    } else {
        connection.query(sql, [userTel, otp], function (error, rows, fields){
            if (error){
                console.log(error);
                response.error(error, res);
            } else {
                if (rows.length == 1) {
                    var userId = rows[0].user_id;
                    response.ok(rows[0], res);
                } else if (rows.length == 0) {
                    response.error("Invalid OTP", res);
                }
            }
        });
    }
};

exports.newUser = function(req, res) {
    var name = req.body.user_name;
    var tel = req.body.user_tel;

    if (name == null || tel == null) {
        response.error("Data supplied not sufficient", res);
    } else {
        var sql = 'SELECT * FROM `users_list` WHERE `user_tel` = ?';
        connection.query(sql, [tel], function (error, rows){
            if(rows.length >= 1) {
                response.error("User already exist", res);
            } else {
                var sql = 'INSERT INTO `users_list` (`user_name`, `user_tel`, `user_type`) VALUES (?, ?, 1)';
                connection.query(sql, [name, tel], function (error, rows){
                    if(error){
                        console.log(error);
                        response.error(error, res);
                    } else{
                        response.ok("Registration completed.", res)
                    }
                });
            }
        });
    }
};

exports.newChild = function(req, res) {
    var parentId = req.body.parent_id;
    var name = req.body.child_name;
    var dob = req.body.child_dob; //YYYY-MM-DD
    var initialSaving = req.body.child_initial_saving;
    var dailyLimit = req.body.child_daily_limit;
    var avatar = req.body.child_avatar_type;
    var gender = req.body.child_gender; //1 cowo, 2 cewe
    var relation = req.body.parent_relation;

    if (name == null || dob == null || initialSaving == null || dailyLimit == null || avatar == null || gender == null) {
        response.error("Data supplied not sufficient", res);
    } else {
        var sql = 'INSERT INTO `children_list` (`child_name`, `child_dob`, `child_avatar`, `child_gender`, `child_savings`, `child_daily_limit`) VALUES (?, ?, ?, ?, ?, ?)';
        connection.query(sql, [name, dob, avatar, gender, initialSaving, dailyLimit], function (error, rows) {
            if(error){
                console.log(error);
                response.error(error);
            } else {
                var childId = rows.insertId;
                var sql = 'INSERT INTO `parent_child_connection` (`pc_conn_parent`, `pc_conn_child`, `parent_relation`) VALUES (?, ?, ?)';
                connection.query(sql, [parentId, childId, relation], function (error, rows) {
                    if(error){
                        console.log(error);
                        response.error(error, res);
                    } else{
                        response.ok("Registration completed.", res);
                    }
                });
            }
        });
    }
};

exports.newChildRelation = function(req, res) {
    var childId = req.body.child_id;
    var parentId = req.body.parent_id;
    var relation = req.body.parent_relation;

    if (childId == null || parentId == null || relation == null) {
        response.error("Data supplied not sufficient", res);
    } else {
        var sql = 'INSERT INTO `parent_child_connection` (`pc_conn_parent`, `pc_conn_child`, `parent_relation`) VALUES (?, ?, ?)';
        connection.query(sql, [parentId, childId, relation], function (error, rows) {
            if(error){
                console.log(error);
                response.error(error, res);
            } else{
                response.ok("Link completed.", res);
            }
        });
    }
};

exports.getChildrenList = function(req, res) {
    var parentId = req.body.parent_id;

    if (parentId == null) {
        response.error("Data not sufficient", res);
    } else {
        var sql = "SELECT `pc_conn_child` FROM `parent_child_connection` WHERE `pc_conn_parent` = ?";
        connection.query(sql, [parentId], function (error, rows, fields){
            if(error){
                console.log(error);
                response.error(error, res);
            } else{
                //response.ok(rows, res);
                var responseArray = [];
                rows.array.forEach(element => {
                    var sql = "SELECT `pc_conn_child` FROM `parent_child_connection` WHERE `pc_conn_parent` = ?";
                    connection.query(sql, [parentId], function (error, rows, fields){
                        if (error) {
                            console.log(error);
                            response.error(error, res);
                        } else {
                            responseArray.push(rows[0]);
                            if (element.length == responseArray.length) {
                                response.ok(rows, responseArray);
                            }
                        }
                    });
                });
            }
        });

    }
}

exports.getTaskList = function(req, res) {
    var childId = req.body.child_id;

    if (childId == null) {
        response.error("Data not sufficient", res);
    } else {
        var sql = "SELECT * FROM `child_task_list` WHERE `task_child_id` = ?";
        connection.query(sql, [childId], function (error, rows, fields){
            if(error){
                console.log(error);
                response.error(error, res);
            } else{
                response.ok(rows, res);
            }
        });

    }
}

exports.newTask = function(req, res) {
    var taskName = req.body.task_name;
    var taskDetail = req.body.task_detail;
    var childId = req.body.child_id;
    var parentId = req.body.parent_id;
    var rewardWallet = req.body.task_reward_wallet;
    var rewardEggs = req.body.task_reward_eggs;
    var taskExpiry = req.body.task_expiry;

    if (taskName == null || taskDetail == null || childId == null || parentId == null || rewardWallet == null || rewardEggs == null || taskExpiry == null) {
        response.error("Data supplied not sufficient", res);
    } else {
        var sql = 'INSERT INTO `child_task_list` (`task_name`, `task_child_id`, `task_detail`, `task_parent_id`, `task_reward_wallet`, `task_reward_eggs`, `task_expiry`) VALUES (?, ?, ?, ?, ?, ?, ?)';
        connection.query(sql, [taskName, childId, taskDetail, parentId, rewardWallet, rewardEggs, taskExpiry], function (error, rows){
            if(error){
                console.log(error)
                response.error(error, res)
            } else{
                response.ok("Add new task success.", res)
            }
        });
    }
};