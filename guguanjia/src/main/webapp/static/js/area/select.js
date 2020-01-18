var vm = new Vue({
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
                    beforeEditName: this.beforeEditName
                },
                edit: {
                    enable: true
                },
                view: {//自定义节点上的元素
                    addHoverDom: this.addHoverDom,
                    removeHoverDom: this.removeHoverDom
                }
            },
            nodes: [],
            treeObj: {},
            pageInfo: {},
            params: {
                areaName: '',//默认值，让下拉出现的时候默认被选中
                aid: 0,
                parentIds:''
            }
        }
    },
    methods: {
        initTree: function () {//初始化ztree
            //获取nodes
            axios({
                url: 'manager/area/selectAll'
            }).then(response => {
                this.nodes = response.data;//   this.setNodes(.....)

                this.treeObj = $.fn.zTree.init($("#select-tree"), this.setting, this.nodes);
                console.log(this.treeObj);

            }).catch(function (error) {
                layer.msg(error);
            })
        },
        onClick: function (event, treeId, treeNode) {
            this.params.aid = treeNode.id;
            this.params.areaName=treeNode.name;
            this.params.parentIds=treeNode.parentIds;
        },
        saveParent: function(){
            console.log(this.params.aid);
            console.log(this.params.areaName);
            parent.layer.parentName=this.params.areaName;
            parent.layer.parentId=this.params.aid;
            parent.layer.parentIds=this.params.parentIds;
            let index = parent.layer.getFrameIndex(window.name)//获取子窗口索引值
            parent.layer.close(index)//通过父窗口根据索引关闭子窗口
        }
    },
    created: function () {
    },
    mounted: function () {
        this.initTree();
    }

});