<template>
    <div>
        <h2 class="h">{{message}}</h2>
        <button @click="btn">按钮</button>
    </div>
</template>

<script>
     export default {
        name:'App',
        data(){
            return {message : '方式三'}
        },
        methods:{
            btn(){

            }
        }
     }
</script>

<style>
    .h{
        color:red;
    }
</style>