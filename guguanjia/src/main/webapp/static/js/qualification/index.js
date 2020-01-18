let vm = new Vue({
    el: '#main-container',
    data: {
        pageInfo: '',
        params:{
        }
    },
    methods: {
        //分页查询
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum=pageNum;
            this.params.pageSize=pageSize;
            axios({
                url: 'manager/qualification/getPage',
                method:'post',
                data:this.params
            }).then(response => {//箭头函数可以自动将上下文的this传递到当前函数中
                this.pageInfo = response.data;

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        clear: function () {
            this.params = ''
        },
        allPage: function () {
            this.params.type = '';
            this.params.check='';
            this.params.startDate='';
            this.params.endDate='';
            this.selectAll(1,5);
        },
        toUpdate:function (id) {
            axios({
                url:'manager/qualification/toUpdate',
                params:{id:id}
            }).then(response =>{
                console.log(response.data)
                layer.obj = response.data;
                layer.open({
                    type:2,
                    content:'manager/qualification/toUpdatePage',
                    area:['80%','80%'],
                    end:() => {
                        this.selectAll(1,5);
                    }
                })

            }).catch( error => {

            })
        }
    },

    created: function () {
        this.selectAll(1, 5);
    }
})