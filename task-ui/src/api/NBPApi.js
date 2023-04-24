import axios from 'axios';

let url = "http://localhost:8080";

const getAverageExchangeRate = async (currnency, date) => {
    try{
        let result;
        await axios.get(url+`/exchanges/${currnency}/${date}`).then((res)=> {
            result = res.data;
        })
        return result;
    }
    catch(err) {
        console.error(err);
    }
}

const getMaxAndMinAverageValue = async (currnency, topCount) => {
    try{
        let result;
        await axios.get(url+`/exchanges/a/${currnency}/last/${topCount}`).then((res)=> {
            result = res.data;
        })
        return result;
    }
    catch(err) {
        console.error(err);
    }
}

const getMajorDifference = async (currnency, topCount) => {
    try{
        let result;
        await axios.get(url+`/exchanges/c/${currnency}/last/${topCount}`).then((res)=> {
            result = res.data;
        })
        return result;
    }
    catch(err) {
        console.error(err);
    }
}

export { getAverageExchangeRate, getMaxAndMinAverageValue, getMajorDifference };