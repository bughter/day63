var vm = new Vue({
    el: '#main-container',
    data: {
        area: {},
    },
    methods: {
        update: function () {
            // this.area.parentId = 6;//模拟修改了父id
            //
            // //根据parentId从后台获取id为6的sysArea的parentIds
            // this.area.parentIds = "0,1,6,";
            //更新
            axios({
                url: 'manager/area/doUpdate',
                method: 'post',
                data: this.area
            }).then(response => {
                // console.log(response);
                if (response.data.success) {
                    //更新成功，关闭当前窗口    在父窗口上显示提示信息
                    let index = parent.layer.getFrameIndex(window.name)//获取子窗口索引值
                    parent.layer.close(index)//通过父窗口根据索引关闭子窗口
                    parent.layer.msg(response.data.msg);
                    return;
                }
                layer.msg(response.data.msg);
            }).catch(error => {
                layer.msg(error.message);//失败处理
            })

        },
        selectIcon: function () {

            let index = layer.open({
                type: 2,
                title: '区域修改',
                content: 'manager/area/awesome',
                area: ['80%', '80%'],
                end: () => {//将then函数中的this传递到end的回调函数中

                    this.area.icon = layer.icon;
                    console.log(this.area.icon);

                }
            });
        },
        selectParent: function () {
            let index = layer.open({
                type: 2,
                title: '区域修改',
                content: 'manager/area/selectParent',
                area: ['80%', '80%'],
                end: () => {//将then函数中的this传递到end的回调函数中
                    this.area.parentName=layer.parentName;
                    this.area.parentId=layer.parentId;
                    this.area.parentIds=layer.parentIds;
                    console.log(this.area.parentIds);
                }
            });
        },
    },
    created: function () {
        this.area = parent.layer.obj;
        this.area.oldParentIds = this.area.parentIds;
    },
    mounted: function () {
        //choosen是一个下拉列表的插件:本质就是通过js将原来的下拉列表隐藏，并且额外添加div来实现
        $("#chosen-select").chosen({width: '100%', disable_search: true});//初始化chosen
    }

});