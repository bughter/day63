let vm = new Vue({
    el: '#main-container',
    data: {
        /* appList:'',*/
        pageInfo: '',
        params: {
            type: ''
        },
        statute: {
            description: ''
        },
        ueditorConfig: {
            UEDITOR_HOME_URL: 'static/ueditor/',  //默认ueditor加载资源的前缀路径
            charset: "utf-8",
            serverUrl: 'doExec'  //  后端统一接口路径   /ueditor/doExce
        }
    },
    methods: {
        //分页查询
        selectAll: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: 'manager/statute/getPage',
                method: "post",
                data: this.params
            }).then(response => {//箭头函数可以自动将上下文的this传递到当前函数中
                // console.log(this);
                this.pageInfo = response.data;
                // this.appList=response.data.list;

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        toUpdate: function (id) {
            axios({
                url: 'manager/statute/toUpdate',
                params: {id: id}
            }).then(response => {

                layer.obj = response.data;//返回数据，绑定到layer上，传递给子窗口
                console.log(layer)
                let index = layer.open({
                    type: 2,
                    title: '法规修改',
                    content: 'manager/statute/toUpdatePage',
                    area: ['80%', '80%'],
                    end: () => {
                        this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
                    }
                });
            }).catch(function (error) {
                console.log(error);
            })
        },
        toDelete: function (id) {
            axios({
                url: 'manager/statute/delete',
                params: {id: id}
            }).then(response => {
                if (response.data.success) {
                    parent.layer.msg(response.data.msg);
                    this.allSelect();
                }
            }).catch(function (error) {
                console.log(error);
            })
        },
        insert: function () {
            //提交信息到后端
            axios({
                url: 'manager/statute/insert',
                method: 'post',
                data: this.statute
            }).then(response => {
                //返回结果如果是成功则显示提示、切换到列表页、清空新增表单信息
                if (response.data.success) {
                    this.clear();
                    this.selectAll(1, 5);
                    console.log($("#myTab").find("li[class='active']"));
                    $("#myTab").find("li[class='active']").attr("class", '').siblings().attr("class", 'active');//切换选项卡的激活状态
                    $("#home").addClass("active");
                    $("#profile").removeClass("active");
                }
                layer.msg(response.data.msg);
            }).catch(error => {

            })

        },
        clear: function () {
            this.params.type = ''
        },
        allSelect: function () {
            this.clear();
            this.selectAll(1, 5);
        }
    },
    created: function () {
        this.selectAll(1, 5);
    },
    components: {//引入vue的富文本编辑器组件
        VueUeditorWrap
    }
})