let vm = new Vue({
        el: '#main-container',
        data: function () {
            return {
                setting: {
                    data: {
                        simpleData: {
                            enable: true,//开启简单数据模式支持
                            pIdKey: "parentId"
                        }
                    },
                    callback: {
                        onClick: this.clickNode//如果设置this.xxx  methods对象还没有，不能绑定上
                    },
                    view: {
                        fontCss: this.fontCss//每次对元素节点进行创建或修改的时候都会自动调用该样式设置规则
                    }
                },
                nodes: [],
                treeObj: {},
                pageInfo: {},
                params: {},
                officeName: '全部'
            }
        },
        methods: {
            //分页查询
            selectAll: function (pageNum, pageSize) {
                this.params.pageNum = pageNum;
                this.params.pageSize = pageSize;
                axios({
                    url: 'manager/role/getPage',
                    data: this.params,
                    method: 'post'
                }).then(response => {//箭头函数可以自动将上下文的this传递到当前函数中
                    this.pageInfo = response.data;
                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            toUser: function (rid, name) {
                layer.rid = rid;//数据，绑定到layer上，传递给子窗口
                layer.name = name
                let index = layer.open({
                    type: 2,
                    title: '修改',
                    content: 'manager/role/toUserPage',
                    area: ['80%', '80%'],
                });
            },
            toUpdate: function (role) {
                layer.obj = role;
                let index = layer.open({
                    type: 2,
                    title: '修改',
                    content: 'manager/role/toUpdatePage',
                    area: ['80%', '80%'],
                });
            },
            //初始化菜单树
            initTree: function () {
                axios({
                    url: 'manager/office/list'
                }).then(response => {
                    //设置节点数据
                    let nodes = response.data;
                    nodes[nodes.length] = {id: 0, name: '所有机构', open: true};
                    let treeObj = $.fn.zTree.init($("#pullDownTreeone"), this.setting, nodes);
                    console.log(treeObj.getNodes())//复杂数组格式
                }).catch(error => {
                    layer.msg(error);
                })

            },
            toDelete:function(id){
                axios({
                    url: 'manager/role/delete',
                    params: {id: id}
                }).then(response => {//箭头函数可以自动将上下文的this传递到当前函数中
                    if (response.data.success) {
                        parent.layer.msg(response.data.msg);
                        this.allSelect();
                    }
                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            clickNode: function (event, treeId, treeNode) {
                this.params.officeId = treeNode.id;
                this.officeName = treeNode.name;
            },
            allSelect: function () {
                this.params.officeId = '';
                this.officeName = '全部';
            }
        },
        created: function () {
            this.selectAll(1, 5);
        },
        mounted: function () {
            this.initTree();
            $("#userDataScope").chosen({width: "80%",search_contains: true});
            $("#saveUserDataScope").chosen({width: "50%",search_contains: true});
            $("#userDataScope").on("change", (e,param) => {
                this.params.dataScope=param.selected;
            })
        }
    }
)