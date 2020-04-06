// main router file
const express = require('express');
const router = express.Router();

// import controller from another file
const controller_user = require('./user')
const controller_diagnosa = require('./diagnosa')

/* route init */
router.get('/', function(req, res, next) {
  res.send("Welcome to lapor covid-19");
});

/* example call controller */
router.use('/user', controller_user)
router.use('/diagnose', controller_diagnosa)

module.exports = router;
