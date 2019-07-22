var express = require('express'),
    https = require('https'),
    fs = require('fs'),
    app = express(),
    port = process.env.PORT || 3000,
    portHttps = 3443,
    bodyParser = require('body-parser'),
    controller = require('./controller');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var routes = require('./routes');
routes(app);

app.listen(port);
https.createServer(app).listen(portHttps);

console.log('Copyright (c) 2019 Stanley Ang.\nHello, Tamago! HTTP server started on port: ' + port + ' and HTTPS on port: ' + portHttps);
