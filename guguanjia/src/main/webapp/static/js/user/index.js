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
                officeName: '全部',
                officeName2:'请选择归属机构',
                user: {
                    roleIds:''
                }
            }
        },
        methods: {
            //分页查询
            selectAll: function (pageNum, pageSize) {
                this.params.pageNum = pageNum;
                this.params.pageSize = pageSize;
                axios({
                    url: 'manager/user/getPage',
                    data: this.params,
                    method: 'post'
                }).then(response => {//箭头函数可以自动将上下文的this传递到当前函数中
                    this.pageInfo = response.data;
                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            todelete: function (id) {
                axios({
                    url: 'manager/user/delete',
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
            doInsert:function(){
                if(this.user.password==this.user.repassword){
                    axios({
                        url:'manager/user/insert',
                        method:'post',
                        data:this.user
                    }).then(response =>{
                        //返回结果如果是成功则显示提示、切换到列表页、清空新增表单信息
                        if(response.data.success){
                            this.selectAll(1,5);
                            this.clear();
                            console.log($("#myTab").find("li[class='active']"));
                            $("#myTab").find("li[class='active']").attr("class",'').siblings().attr("class",'active');//切换选项卡的激活状态
                            $("#home").addClass("active");
                            $("#profile").removeClass("active");
                        }
                        layer.msg(response.data.msg);
                    }).catch( error => {

                    })
                }else{
                    layer.msg('两次密码不相同！');
                }


            },
            toUpdate: function (id) {
                axios({
                    url: 'manager/user/toUpdate',
                    params: {id: id}
                }).then(response => {

                    layer.obj = response.data;//返回数据，绑定到layer上，传递给子窗口
                    console.log(layer)
                    let index = layer.open({
                        type: 2,
                        title: '法规修改',
                        content: 'manager/user/toUpdatePage',
                        area: ['80%', '80%'],
                        end: () => {
                            this.selectAll(this.pageInfo.pageNum, this.pageInfo.pageSize);
                        }
                    });
                }).catch(function (error) {
                    console.log(error);
                })
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
                    let treeObj2 = $.fn.zTree.init($("#pullDownTreetwo"), this.setting, nodes);
                    console.log(treeObj.getNodes())//复杂数组格式
                }).catch(error => {
                    layer.msg(error);
                })

            },
            clickNode: function (event, treeId, treeNode) {
                if(treeId=='pullDownTreeone') {
                    this.params.officeId = treeNode.id;
                    this.officeName = treeNode.name;
                }

                if(treeId=='pullDownTreetwo') {
                    this.officeName2 = treeNode.name;
                    this.user.officeId = treeNode.id;
                }
            },
            allSelect: function () {
                this.clear();
                this.selectAll(1, 5);
            },
            selectRole: function (e,param) {
                this.params.roleId=param.selected;
            },
            selectRole2: function (e,param) {
                if(param.deselected!=null){
                    let reg=new RegExp(param.deselected+',','g')//g代表全部
                   this.user.roleIds=JSON.stringify(this.user.roleIds).replace(reg,'');
                }else{
                    this.user.roleIds+=param.selected+",";
                }

                console.log(this.user.roleIds);
            },
            clear:function(){
                this.params.officeId = '';
                this.params.officeName = '全部';
                this.params.name = '';
                this.params.no = '';
                this.params.roleId = '';
                this.params.officeId = '';
            }

        },
        created: function () {
            this.selectAll(1, 5);
        },
        mounted: function () {
            this.initTree();
            $("#role-select").on("change",this.selectRole);
            $("#role-choose").on("change",this.selectRole2);
        }
    }
)