<template>
  <div class="form">
    <p>Send Request: </p>

    <br/>
    <select v-model="selected" class="input">
      <option>Average exchange rate</option>
      <option>Max and min average value</option>
      <option>Major difference between the buy and ask rate</option>
    </select>
    <br/>
    <br/>
    <datepicker v-model="date"></datepicker>
    <br/>
    <br/>
    <input v-model="currency" placeholder="currency" class="input">
    <br/>
    <input v-model="topCount" type="number" placeholder="topCount" class="input">

    <button v-if="result == ''" type="submit" class="button" @click="sendRequest()">SEND</button>

  </div>

  <div class="result" v-if="result != ''">
    <h1>Result:</h1>
    <h2>{{ result }}</h2>

    <button type="submit" class="button" @click="result = ''">Next request</button>
  </div>

</template>

<script>
import Datepicker from 'vuejs3-datepicker';
import { getAverageExchangeRate,getMaxAndMinAverageValue,  getMajorDifference} from '@/api/NBPApi';

export default {
  name: 'ApiForm',
  components: {
    Datepicker
  },
  data() {
    return {
      currency: '',
      topCount: null,
      selected: "Average exchange rate",
      date: null,
      result: ''
    }
  },
  methods: {

    async sendRequest(){
      let result;
      
      if(this.selected === "Average exchange rate" && this.currency && this.date){
        const temp_date = new Date(this.date);
        var year = temp_date.toLocaleString("default", { year: "numeric" });
        var month = temp_date.toLocaleString("default", { month: "2-digit" });
        var day = temp_date.toLocaleString("default", { day: "2-digit" });
        var formattedDate = year + "-" + month + "-" + day;
        result = await getAverageExchangeRate(this.currency, formattedDate)
      }
      else if(this.selected === "Max and min average value" && this.currency && this.topCount){
        if(this.topCount > 255 || this.topCount < 1){
          alert('topCount value should be in range <1,255>')
          return
        }
        const result_ = await getMaxAndMinAverageValue(this.currency, this.topCount)
        result = `Min: ${result_.min} Max: ${result_.max}`
      }
      else if(this.selected === "Major difference between the buy and ask rate" && this.currency && this.topCount){
        if(this.topCount > 255 || this.topCount < 1){
          alert('topCount value should be in range <1,255>')
          return
        }
        result= await getMajorDifference(this.currency, this.topCount)
      }
      else{
        alert("Error with data");
        return
      }

      if(!result){
        alert("Error with request");
      }
      else{
        this.result = result
      }

    }
  }
}
</script>
<style scoped>
.form{
  padding: 5px;
  margin-left: auto;
  margin-right: auto;
  max-width: 30%;
}

.input{
  width: 80%;
  margin:2px;
}

.button{
  margin: 20px;
  padding: 20px;
  background-color: rgb(29, 45, 79);
  color: rgb(198, 178, 123);
  border-radius: 10px;
  width: 50%;
}

</style>
