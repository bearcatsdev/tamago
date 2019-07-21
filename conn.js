var mysql = require('mysql');

var con = mysql.createConnection({
  host: "localhost",
  user: "tamago",
  password: "210d8b9a",
  database: "tamago"
});

con.connect(function (err){
    if(err) throw err;
});

module.exports = con;
