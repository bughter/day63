let vm = new Vue({
    el:'#main-container',
    data:{
        app:''
    },
    methods:{

    },
    created:function () {
        this.app = parent.layer.obj;
    }

})