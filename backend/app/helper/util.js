const util = {

    sum: function(obj){
        var sum = 0
        for(var e in obj){
            if(obj.hasOwnProperty(e)){
                sum += parseFloat(obj[e])
            }
        }
        return sum;
    }
}
module.exports = util;