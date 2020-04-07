const connection = require('./connection')

const tablename = 'user';

module.exports = {
    insert: function(con,vNama,vAsal_kota,vBatuk,vDemam,vMeler,vSakit_tenggorokan,
        vSesak_nafas,vSakit_kepala,vPegal,vBersin,vLelah,vDiare,vPersen_covid,vPersen_flu,vPersen_cold) {
        let posts = {
            nama: vNama,
            asal_kota: vAsal_kota,
            batuk: vBatuk,
            demam: vDemam,
            meler: vMeler,
            sakit_tenggorokan: vSakit_tenggorokan,
            sesak_nafas: vSesak_nafas,
            sakit_kepala: vSakit_kepala,
            pegal: vPegal,
            bersin: vBersin,
            lelah: vLelah,
            diare: vDiare,
            persen_covid: vPersen_covid,
            persen_flu: vPersen_flu,
            persen_cold: vPersen_cold,
        }
        let q = con.query('INSERT INTO '+ tablename +' SET ?', posts, function (error, results, fields) {
            // if (error) throw error;
        })
        console.log(q.sql)
    }
}

