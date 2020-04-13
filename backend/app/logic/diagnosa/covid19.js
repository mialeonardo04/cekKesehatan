const error = require('../../helper/errors/index')
const criteria = require('../../helper/constant/disease')
const util = require('../../helper/util')
const data = require('../../database/user')
const response = require('../../helper/response')
const connection = require('../../database/connection')

let lang = 'en'

function arrNormalisasi(objek){

    let valBatuk = objek.batuk / util.sum(objek)
    let valDemam = objek.demam / util.sum(objek)
    let valMeler = objek.meler / util.sum(objek)
    let valTengg = objek.sakit_tenggorokan / util.sum(objek)
    let valSesak = objek.sesak_nafas / util.sum(objek)
    let valKepal = objek.sakit_kepala / util.sum(objek)
    let valPegal = objek.pegal / util.sum(objek)
    let valBersin = objek.bersin / util.sum(objek)
    let valLelah = objek.lelah / util.sum(objek)
    let valDiare = objek.diare / util.sum(objek)


    return {
        batuk: valBatuk,
        demam: valDemam,
        meler: valMeler,
        sakit_tenggorokan: valTengg,
        sesak_nafas: valSesak,
        sakit_kepala: valKepal,
        pegal: valPegal,
        bersin: valBersin,
        lelah: valLelah,
        diare: valDiare,
    }
}

exports.postDiagnoseCov = async(req,res) =>{
    try {
        if(isNaN(req.params.id_user)) {
            // res.json(error[lang].id_user_empty)
            // insert data
            if (req.body.nama != undefined && req.body.usia != undefined && 
                req.body.no_telp != undefined && req.body.koordinat != undefined && 
                req.body.asal_kota != undefined && req.body.kecamatan != undefined &&
                req.body.batuk != undefined && req.body.demam != undefined &&
                req.body.meler != undefined && req.body.sakit_tenggorokan != undefined &&
                req.body.sesak_nafas != undefined && req.body.sakit_kepala != undefined &&
                req.body.pegal != undefined && req.body.bersin != undefined &&
                req.body.lelah != undefined && req.body.diare != undefined) {
    
                let _nama = req.body.nama
                let _notelp = req.body.no_telp
                let _usia = req.body.usia
                let _asalkota = req.body.asal_kota
                let _kecamatan = req.body.kecamatan
                let _batuk = req.body.batuk
                let _demam = req.body.demam
                let _meler = req.body.meler
                let _sakit_tenggorokan = req.body.sakit_tenggorokan
                let _sesak_nafas = req.body.sesak_nafas
                let _sakit_kepala = req.body.sakit_kepala
                let _pegal = req.body.pegal
                let _bersin = req.body.bersin
                let _lelah = req.body.lelah
                let _diare = req.body.diare
                let _koordinat = req.body.koordinat
                
                //normalisasiValueMatrix
                let matrixCov = arrNormalisasi(criteria.covid19)
                let matrixFlu = arrNormalisasi(criteria.flu)
                let matrixCold= arrNormalisasi(criteria.cold)
    
                // SAW Algo
                let hitungCov = (((matrixCov.batuk * _batuk) + (matrixCov.demam * _demam) +
                                (matrixCov.meler * _meler) + (matrixCov.sakit_tenggorokan * _sakit_tenggorokan) +
                                (matrixCov.sesak_nafas * _sesak_nafas) + (matrixCov.sakit_kepala * _sakit_kepala) +
                                (matrixCov.pegal * _pegal) + (matrixCov.bersin * _bersin) +
                                (matrixCov.lelah * _lelah) + (matrixCov.diare * _diare))/4)*100
    
                let hitungFlu = (((matrixFlu.batuk * _batuk) + (matrixFlu.demam * _demam) +
                                (matrixFlu.meler * _meler) + (matrixFlu.sakit_tenggorokan * _sakit_tenggorokan) +
                                (matrixFlu.sesak_nafas * _sesak_nafas) + (matrixFlu.sakit_kepala * _sakit_kepala) +
                                (matrixFlu.pegal * _pegal) + (matrixFlu.bersin * _bersin) +
                                (matrixFlu.lelah * _lelah) + (matrixFlu.diare * _diare))/4)*100
    
                let hitungCold= (((matrixCold.batuk * _batuk) + (matrixCold.demam * _demam) +
                                (matrixCold.meler * _meler) + (matrixCold.sakit_tenggorokan * _sakit_tenggorokan) +
                                (matrixCold.sesak_nafas * _sesak_nafas) + (matrixCold.sakit_kepala * _sakit_kepala) +
                                (matrixCold.pegal * _pegal) + (matrixCold.bersin * _bersin) +
                                (matrixCold.lelah * _lelah) + (matrixCold.diare * _diare))/4)*100
                
                let persenCov = hitungCov.toFixed(2)
                let persenFlu = hitungFlu.toFixed(2)
                let persenCold= hitungCold.toFixed(2)              
                
                //proses input ke database
                let con = connection.connect()
                data.insert(con,
                    _nama,
                    _notelp,
                    _usia,
                    _asalkota,
                    _kecamatan,
                    _batuk,
                    _demam,
                    _meler,
                    _sakit_tenggorokan,
                    _sesak_nafas,
                    _sakit_kepala,
                    _pegal,
                    _bersin,
                    _lelah,
                    _diare,
                    persenCov,
                    persenFlu,
                    persenCold,
                    _koordinat,
                    function(error, result, fields){
                        if (error) throw error;
                        res.json(response.createResp(200,
                            [{  
                                id: result.insertId,
                                nama: _nama,
                                no_telp: _notelp,
                                usia: _usia,
                                asal_kota: _asalkota,
                                kecamatan: _kecamatan,
                                covid_19: persenCov,
                                flu: persenFlu,
                                cold: persenCold,
                                koordinat: _koordinat,
                            }]
                        ))
                        connection.close(con)
                    })
            } else {
                res.json(error[lang].body_request_empty)
            }    
        } else {
            // update data 
            if (req.body.nama != undefined && req.body.usia != undefined && 
                req.body.no_telp != undefined && req.body.koordinat != undefined && 
                req.body.asal_kota != undefined && req.body.kecamatan != undefined){
    
                let _id = req.params.id_user
                let _nama = req.body.nama
                let _notelp = req.body.no_telp
                let _usia = req.body.usia
                let _asalkota = req.body.asal_kota
                let _kecamatan = req.body.kecamatan
                let _koordinat = req.body.koordinat
                
                //update data db
                let con = connection.connect();
                data.update(con,   
    
                _id,
                _nama,
                _notelp,
                _usia,
                _asalkota,
                _kecamatan,
                _koordinat,
                function(error,result,fields){
                    if (error) throw error;
                    res.json(response.createResp(200,
                        [{
                            message: "Update Success",
                            id: _id,
                            nama: _nama,
                            no_telp: _notelp,
                            usia: _usia,
                            asal_kota: _asalkota,
                            kecamatan: _kecamatan,
                            koordinat: _koordinat,
                        }]
                    ))
                    connection.close(con)
                })
            } else {
                res.json(error[lang].body_request_empty)
            }
        }
        
    } catch (ex){
        res.json(error[lang].unexpected_error)
        console.log(ex)
    }
}


// exports.postUpdateUserInfo = async(req,res) =>{
//     try {
//         if(isNaN(req.params.id_user)) {
//             res.json(error[lang].id_user_empty)
//         }

//         if (req.body.nama != undefined && req.body.usia != undefined && 
//             req.body.no_telp != undefined && req.body.koordinat != undefined && 
//             req.body.asal_kota != undefined && req.body.kecamatan != undefined){

//             let _id = req.params.id_user
//             let _nama = req.body.nama
//             let _notelp = req.body.no_telp
//             let _usia = req.body.usia
//             let _asalkota = req.body.asal_kota
//             let _kecamatan = req.body.kecamatan
//             let _koordinat = req.body.koordinat
            
//             //update data db
//             let con = connection.connect();
//             data.update(con,   

//             _id,
//             _nama,
//             _notelp,
//             _usia,
//             _asalkota,
//             _kecamatan,
//             _koordinat,
//             function(error,result,fields){
//                 if (error) throw error;
//                 res.json(response.createResp(200,
//                     [{
//                         message: "Update Success",
//                         id: result.insertId,
//                         nama: _nama,
//                         no_telp: _notelp,
//                         usia: _usia,
//                         asal_kota: _asalkota,
//                         kecamatan: _kecamatan,
//                         koordinat: _koordinat,
//                     }]
//                 ))
//                 connection.close(con)
//             })
//         } else {
//             res.json(error[lang].body_request_empty)
//         }
//     } catch (ex){
//         res.json(error[lang].unexpected_error)
//         console.log(ex)
//     }
// }