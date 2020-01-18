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
                    officeName: '全部',//默认值，让下拉出现的时候默认被选中
                    officeId: ''
                }
            }
        },
        methods: {
            //分页查询
            selectAll: function (pageNum, pageSize) {
                this.params.pageNum=pageNum;
                this.params.pageSize=pageSize;
                axios({
                    url: 'manager/admin/work/getPage',
                    data:this.params,
                    method:'post'
                }).then(response => {//箭头函数可以自动将上下文的this传递到当前函数中
                    this.pageInfo = response.data;
                }).catch(function (error) {
                    layer.msg(error);
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
                        "name": "所有机构",
                        open: true
                    }
                    this.treeObj =   $.fn.zTree.init($("#pullDownTreeone"),this.setting,this.nodes);
                    console.log(this.treeObj)  ;

                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            onClick: function(event, treeId, treeNode){
                this.params.officeId=treeNode.id;
                this.params.officeName=treeNode.name;
            },
            toDetail:function(id){
                axios({
                    url:'manager/admin/work/selectByOid',
                    params:{oid:id}

                }).then(response => {//箭头函数可以自动将上下文的this传递到当前函数中
                    //
                    layer.obj = response.data;
                    layer.open({
                        type:2,
                        content:'manager/admin/work/toDetail',
                        area:['80%','80%']
                    })
                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            toPrint:function(id){
                    layer.open({
                        type:2,
                        content:'manager/admin/work/toPrint',
                        area:['80%','80%']
                    })
            },
            allSelect: function(){
                this.params.officeId='';
                this.params.officeName='全部';
                this.params.status='';
                this.params.start='';
                this.params.end='';
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