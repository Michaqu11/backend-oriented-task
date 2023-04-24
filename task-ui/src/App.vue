<template>
  <img alt="Logo" src="./assets/logo.png">
  <NBPApi msg="Query data from NBP API"/>
</template>

<script>
import NBPApi from './components/NBPApi.vue'

export default {
  name: 'App',
  components: {
    NBPApi
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
