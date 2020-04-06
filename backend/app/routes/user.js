const express = require('express');
const controller_user = express.Router();
const getUser = require('../logic/user/get_user')

/* example logic layer */
controller_user.get('/:id_user', getUser.getUserById);

module.exports = controller_user;