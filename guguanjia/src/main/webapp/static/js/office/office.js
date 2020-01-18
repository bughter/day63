let vm = new Vue({
        el: '#main-container',
        data: function() {
            return {
                setting: {
                    data: {
                        simpleData: {
                            enable: true,
                            pIdKey: 'parentId'//根据node节点中的parentId属性来作为pId属性值
                        }
                    },
                    callback: {
                        // beforeClick:this.beforeClick,
                        onClick: this.onClick,
                        beforeEditName:this.beforeEditName
                    },
                    edit:{
                        enable: true
                    },
                    view:{//自定义节点上的元素
                        addHoverDom:this.addHoverDom,
                        removeHoverDom:this.removeHoverDom
                    }
                },
                nodes: [],
                treeObj: {},
                pageInfo: {
                },
                params: {
                    officeName: '',//默认值，让下拉出现的时候默认被选中
                    aid: 0
                }
            }
        },
        methods: {
            //分页查询
            selectAll: function (pageNum, pageSize) {
                this.params.pageNum=pageNum;
                this.params.pageSize=pageSize;
                axios({
                    url: 'manager/office/getPage',
                    data:this.params,
                    method:'post'
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
                    url:'manager/office/toUpdate',
                    params: {id:id}
                }).then(response => {

                    layer.obj = response.data;//返回数据，绑定到layer上，传递给子窗口
                    console.log(layer)
                    let index = layer.open({
                        type:2,
                        title:'区域修改',
                        content:'manager/office/toUpdatePage',
                        area:['80%','80%'],
                        end: () => {//将then函数中的this传递到end的回调函数中
                            console.log(".....")
                            //刷新页面数据    1.直接查询selectAll实现    2.获取layer.appVersion更新当前pageInfo的该数据
                            this.selectAll(this.pageInfo.pageNum,this.pageInfo.pageSize);
                            //更新菜单树
                            this.initTree();
                        }
                    });
                }).catch(function (error) {
                    console.log(error);
                })
            },
            toDelete: function (id) {
                axios({
                    url: 'manager/office/delete',
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
            initTree:function(){//初始化ztree
                //获取nodes
                axios({
                    url:'manager/office/list'
                }).then(response => {
                    this.nodes = response.data;//   this.setNodes(.....)
                    this.nodes[this.nodes.length]={
                        "id": 0,
                        "name": "所有机构"
                    }
                    this.treeObj =   $.fn.zTree.init($("#treeMenu"),this.setting,this.nodes);
                    console.log(this.treeObj)  ;

                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            onClick: function(event, treeId, treeNode){
                this.params.aid=treeNode.id;
                this.params.officeName=treeNode.name;
                this.selectAll(1,5);
            },
            allSelect: function(){
                this.params.aid=0;
                this.params.officeName='';
                this.selectAll(1,5);
            },
            clear: function () {
            }
        },
        created: function () {
            this.selectAll(1, 5);
        },
        mounted: function (){
            this.initTree();
        }
    }
)