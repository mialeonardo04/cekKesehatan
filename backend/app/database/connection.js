const mysql = require('mysql');


const dbconfig = {
    connect: function(){
        if(process.env.DB_HOST == '' || process.env.DB_USER == '' || process.env.DB_PASSWORD == '' || process.env.DB_NAME == ''){
            console.log('DB Not found')
        }

        const connection = mysql.createConnection({
            host: 'localhost',
            user: 'root',
            password: '',
            database: 'cvd-19_projects'
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
exports.modules = dbconfig;