<template>
  <h1>{{ msg }}</h1>
  <ApiForm />
</template>

<script>
import ApiForm from './ApiForm.vue';

export default {
    name: "NBPApi",
    props: {
        msg: String
    },
    components: { ApiForm },
    data(){
      return {
        result: ''
      }
    }
}
</script>
<style>
.vuejs3-datepicker__calendar-topbar{
  background-color: rgb(29, 45, 79) !important;
}
.vuejs3-datepicker__calendar .cell.selected{
  background: rgb(29, 45, 79) !important;
}
</style>
