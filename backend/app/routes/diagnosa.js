const express = require('express');
const controller_diagnosa = express.Router();
const postDiagnosaCovid = require('../logic/diagnosa/covid19')

/* example logic layer */
// controller_user.get('/:id_user', getUser.getUserById);

controller_diagnosa.post('/covid/:id_user?',postDiagnosaCovid.postDiagnoseCov)

module.exports = controller_diagnosa;