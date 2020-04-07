module.exports = {
    createResp: function(status, data) {
        let response = {
            status: status,
            data: data,
        }
        return response
    }
};