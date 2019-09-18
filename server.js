var express = require('express');
var https = require('https');
var fs = require('fs');
var app = express();
var port = process.env.PORT || 3000;
var portHttps = 3443;
var bodyParser = require('body-parser');
var controller = require('./controller');
var path = require('path');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use('/static', express.static(path.join(__dirname, 'public')));

var routes = require('./routes');
routes(app);

app.listen(port);
https.createServer(app).listen(portHttps);

console.log('Copyright (c) 2019 Stanley Ang.\nHello, Tamago! HTTP server started on port: ' + port + ' and HTTPS on port: ' + portHttps);
