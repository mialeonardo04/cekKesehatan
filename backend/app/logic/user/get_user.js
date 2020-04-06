const error = require('../../helper/errors/index')
const response = require('../../helper/response')
let lang = 'en'

exports.getUserById = async(req,res) => {
    try {
        // check if query id is numeric or not
        if(isNaN(req.params.id_user)) {
            res.json(error[lang].id_user_empty)
        }

        // check db layer if user id is exist or not
        

        res.json(response.createResp(200,{ok:"oke"}))
    }
    catch {
        res.json(error[lang].unexpected_error)
    }
}