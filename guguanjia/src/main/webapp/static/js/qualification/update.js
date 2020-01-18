let vm = new Vue({
    el:'#main-container',
    data:{
        qua:''
    },
    methods:{
        doUpdate:function () {
            axios({
                url:'manager/qualification/doUpdate',
                method:'post',
                data:this.qua
            }).then(response =>{
                // console.log(response);
                if (response.data.success){
                    //更新成功，关闭当前窗口    在父窗口上显示提示信息
                    let index = parent.layer.getFrameIndex(window.name)//获取子窗口索引值
                    parent.layer.close(index)//通过父窗口根据索引关闭子窗口
                    parent.layer.msg(response.data.msg);
                    return;
                }
                layer.msg(response.data.msg);//失败处理


            }).catch(error=>{
                layer.msg(error);
            })
        },
        doUpdate2:function () {
            axios({
                url:'manager/qualification/doUpdate2',
                method:'post',
                data:this.qua
            }).then(response =>{
                if (response.data.success){
                    //更新成功，关闭当前窗口    在父窗口上显示提示信息
                    let index = parent.layer.getFrameIndex(window.name)//获取子窗口索引值
                    parent.layer.close(index)//通过父窗口根据索引关闭子窗口
                    parent.layer.msg(response.data.msg);
                    return;
                }
                layer.msg(response.data.msg);//失败处理


            }).catch(error=>{
                layer.msg(error);
            })
        },
        closeLayer:function () {
            let index = parent.layer.getFrameIndex(window.name)
            parent.layer.close(index)//通过父窗口根据索引关闭子窗口
            return;
        }
    },
    created:function () {
        //在vue对象创建的时候获取父窗口layer对象绑定的数据，放入当前app对象中
        this.qua = parent.layer.obj;
    }

})