const tablename = 'user_info';

module.exports = {
    insert: function(con,vNama,vNoTelp,vUmur,vAsal_kota,vKecamatan,vBatuk,vDemam,vMeler,vSakit_tenggorokan,
        vSesak_nafas,vSakit_kepala,vPegal,vBersin,vLelah,vDiare,vPersen_covid,vPersen_flu,vPersen_cold, vKoordinat, callback) {
        let posts = {
            nama: vNama,
            no_telp:vNoTelp,
            usia: vUmur,
            asal_kota: vAsal_kota,
            kecamatan: vKecamatan,
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
            koordinat: vKoordinat,
        }
    con.query('INSERT INTO '+ tablename +' SET ?', posts, callback )
    },

    update: function name(con,id,vNama,vNoTelp,vUmur,vAsal_kota,vKecamatan,vKoordinat,callback) {
        let posts = {
            nama: vNama,
            no_telp:vNoTelp,
            usia: vUmur,
            asal_kota: vAsal_kota,
            kecamatan: vKecamatan,
            koordinat: vKoordinat,
        }
        con.query('UPDATE '+tablename+' SET ? WHERE id = ?', [posts,id], callback)
        
    },
}

