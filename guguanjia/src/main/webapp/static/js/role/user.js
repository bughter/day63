let vm = new Vue({
        el: '#main-container',
        data: function () {
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
                    },
                    view: {//自定义节点上的元素
                        addHoverDom: this.addHoverDom,
                        removeHoverDom: this.removeHoverDom
                    }
                },
                nodes: [],
                treeObj: {},
                rid: '',//角色id
                name: '',//角色名
                yxUsers: [],//已选人员的列表
                showRemoveClass: 'hide',//控制移除已选人员的按钮样式
                uids: [],//移除人员的id列表
                companyUsers: [],//公司待选人员列表
                cids: [],//待选人员id列表
                companyShowClass: 'hide',//控制待选人员的按钮样式
                addUserIdstr:'',
                removeUserIdstr:''
            }
        },
        methods: {
            //分页查询
            initTree: function () {//初始化ztree
                //获取nodes
                axios({
                    url: 'manager/office/list'
                }).then(response => {
                    this.nodes = response.data;//   this.setNodes(.....)
                    this.nodes[this.nodes.length] = {
                        "id": 0,
                        "name": "所有机构"
                    }
                    this.treeObj = $.fn.zTree.init($("#treeOffice"), this.setting, this.nodes);
                    console.log(this.treeObj);

                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            dxUser:function(){
                //根据公司id，角色id  查询出当前选中公司的未给当前角色授权的用户
                axios({
                    url:'manager/user/selectNoRole',
                    params:{rid:this.rid,oid:this.treeNode.id}
                }).then(response => {
                    this.companyUsers=response.data;
                    //给每个用户绑定新属性show ,用于控制被选中与否
                    for (let i = 0; i <this.companyUsers.length ; i++) {
                        this.companyUsers[i].show=false;
                    }
                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            yxUser:function(){
                axios({
                    url:'manager/user/selectByRid',
                    params:{rid:this.rid}
                }).then(response => {
                    this.yxUsers=response.data;
                    // let str='';
                    // for(let i=0;i<this.yxUsers.length;i++){
                    //     str+=this.yxUsers[i].id+'@';
                    // }
                    // this.yxIdstr=str;
                    // console.log(this.yxIdstr);
                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            changedxUser:function(uid){
                for (let i = 0; i < this.companyUsers.length; i++) {
                    if(uid==this.companyUsers[i].id){
                        this.companyUsers[i].show = !this.companyUsers[i].show;
                        if(this.companyUsers[i].show){
                            this.addUserIdstr+=this.companyUsers[i].id+'@';
                        }else{//取消打钩
                            this.addUserIdstr=this.addUserIdstr.replace(uid+'@',"");
                        }
                    }
                }
                console.log(this.addUserIdstr);
                if($("#dxuser input:checked").length==0){//如果没有任何的input被选中
                    $("#addUserBtn").attr("class","hide");//隐藏提交按钮
                }else {
                    $("#addUserBtn").attr("class","");
                }
            },
            changeyxUser:function(uid){
                        if(this.removeUserIdstr.indexOf(uid+'@') >= 0){
                            this.removeUserIdstr=this.removeUserIdstr.replace(uid+'@',"");
                        }else{//取消打钩
                            this.removeUserIdstr+=uid+'@';
                        }
                if($("#yxuser input:checked").length==0){//如果没有任何的input被选中
                    $("#removeUserBtn").attr("class","hide");//隐藏提交按钮
                }else {
                    $("#removeUserBtn").attr("class","");
                }
            },
            addUser:function(){
                axios({
                    url: 'manager/role/insertUser',
                    params:{'rid':this.rid,'str': this.addUserIdstr}
                }).then(response => {
                    layer.msg(response.data.msg);
                    this.dxUser();
                    $("#addUserBtn").attr("class","hide");
                    $("#removeUserBtn").attr("class","hide");
                    $("#dxuser input:checked").prop("checked", function(index, oldValue){
                        return !oldValue;
                    });
                    this.yxUser();
                    this.addUserIdstr='';
                }).catch(function (error) {
                    layer.msg(error);
                })

            },
            deleteUser:function(){
                console.log(this.removeUserIdstr);
                axios({
                    url: 'manager/role/deleteUser',
                    params:{'rid':this.rid,'str': this.removeUserIdstr}
                }).then(response => {
                    layer.msg(response.data.msg);
                    this.dxUser();
                    $("#removeUserBtn").attr("class","hide");
                    $("#addUserBtn").attr("class","hide");
                    $("#yxuser input:checked").prop("checked", function(index, oldValue){
                        return !oldValue;
                    });
                    this.yxUser();
                    this.removeUserIdstr='';
                }).catch(function (error) {
                    layer.msg(error);
                })
            },
            onClick: function (event, treeId, treeNode) {
                this.treeNode = treeNode;
                let treeNodes = this.treeObj.transformToArray(this.treeObj.getNodes());
                //清除原高亮标记
                for (let index in treeNodes) {
                    if(treeNodes[index].id==treeNode.id){
                        treeNodes[index].higtLine = true;//设置高亮标记
                    }else{
                        treeNodes[index].higtLine=false;
                    }
                    this.treeObj.updateNode(treeNodes[index]);//更新节点，自动调用清除css
                }
                this.dxUser();
            }
        },
        created: function () {
            this.rid = parent.layer.rid;
            this.name = parent.layer.name;
        },
        mounted: function () {
            this.initTree();
            this.yxUser();
        }
    }
)