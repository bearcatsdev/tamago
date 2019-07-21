var express = require('express'),
    https = require('https'),
    fs = require('fs'),
    app = express(),
    port = process.env.PORT || 3000,
    bodyParser = require('body-parser'),
    controller = require('./controller');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var routes = require('./routes');
routes(app);

app.listen(port);

https.createServer({
    key: fs.readFileSync('/etc/apache2/ssl/apache.key'),
    cert: fs.readFileSync('/etc/apache2/ssl/apache.crt')
}, app)
.listen(3443);

console.log('Copyright (c) 2019 Stanley Ang.\nHello, Tamago! Server started on port: ' + port);
