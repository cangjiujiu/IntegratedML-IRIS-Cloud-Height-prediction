<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang='scss'>
*{
  margin: 0;
  padding: 0;
}
ul li{
  list-style: none;
}
@font-face{
    font-family: 'DS-DIGI';
    src: url('../static/fontface/DS-DIGI.eot') format('eot'),
    url('../static/fontface/DS-DIGI.ttf') format('tff'),
    url('../static/fontface/DS-DIGI.woff2') format('woff2'),
}

</style>
