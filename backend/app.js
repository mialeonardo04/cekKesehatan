// import all needed package
const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');

// init express server and router
const app = express();
const mainRouter = require('./app/routes/index');

app.use(logger('dev')); 
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

// http router
app.use('/', mainRouter);

// static router
app.use('/static', express.static(path.join(__dirname, 'public')));

module.exports = app;
