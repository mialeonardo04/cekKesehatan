require('dotenv').config();
const mysql = require('mysql');

const dbconfig = {
    connect: function(){
        if(process.env.DB_HOST == undefined || process.env.DB_USER == undefined || process.env.DB_PASSWORD == undefined || process.env.DB_NAME == undefined){
            console.log('DB Not found')
        }

        const connection = mysql.createConnection({
            host: process.env.DB_HOST,
            user: process.env.DB_USER,
            password: process.env.DB_PASSWORD,
            database: process.env.DB_NAME
        });
        connection.connect();
        return connection;
    },

    close: function(connection){
        if(!Object.prototype.hasOwnProperty.call(connection, 'end')) {
            console.log("DB Not Found")
        }
        connection.end();
    }
}
module.exports = dbconfig;