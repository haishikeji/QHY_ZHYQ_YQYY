<template>
	<view>
		<view class="navbox">
			<uni-nav-bar color="#ffffff" left-icon="left" title="会议预订" :background-color="backgroundColor"
				:border="false" statusBar='true' fixed="true" @clickLeft='getBack'>
			</uni-nav-bar>
			<view class="plr16" :style="'background:'+backgroundColor">
				<!-- <view class="search flexc mb4">
					<image :src="serimg"></image>
					<input placeholder="请输入人员姓名进行搜索" confirm-type="search" placeholder-style="color:#B9DCF7" v-model="title" @confirm="getConfirm"/>
				</view> -->
				<!-- 搜索 -->
				<view class="lbtab flexc">
					<image :src="licoimg" class="limg"  @click='getResetFn'></image>
					<view class="lbtabs flexc">
						<picker class="pickbox" range-key='conferenceRoomName' 	:range="adrlist" @change="bindTimeChangea">
							<view class="pickboxa">
								<view class="tit overtwo" >{{adrname||'会议室'}}</view>
								<image :src="wupimg" class="hupimg"></image>
							</view>
						</picker>
						<!-- <picker mode="date" class="pickbox"  :value="adridx"
							 @change="bindTimeChangeb">
							<view class="pickboxa">
								<view class="tit" >{{time||'2024年07月'}}</view>
								<image :src="wupimg" class="hupimg"></image>
							</view>
						</picker>
						<picker class="pickbox" range-key='dictLabel' 	:range="adrlist" @change="bindTimeChangec">
							<view class="pickboxa">
								<view class="tit overtwo" >{{zt||'已结束'}}</view>
								<image :src="wupimg" class="hupimg"></image>
							</view>
						</picker> -->
					</view>
					<!-- <view class="czbtns">重置</view> -->
				</view>
			</view>
			
		</view>
		<image :src="navbg" class="navbg"></image>
		<view class="zxmain" :style="'margin-top:-'+marTop+'rpx;'">
			<!-- 列表 -->
			<y-list type='meetlist' :datalist="list" :wtdt="wtdt" @getDetail='getDetail'  @getDelFn="getDelFn" ></y-list>
			<view class="yyfbtn flexcc" v-if="checkPermi(['system:order:add'])" @click="getAddFn">添加会议预约</view>
		</view>
	</view>
</template>

<script>
	import {checkPermi,checkRole} from "@/utils/permission"; // 权限判断函数
	import yList from "@/work/components/yuyue/list.vue"
	import {getRoomyyList,getRoomNoList,getRoomyyDel} from "@/api/mine/work.js"
	import {getDictionaryFn} from "@/api/mine/register.js"
	export default {
		components: {yList},
		data() {
			return {
				wupimg:require("@/work/static/record/wup.png"),
				serimg:require("@/work/static/record/search.png"),
				licoimg:require("@/work/static/record/lico.png"),
				navbg:require("@/static/images/navbg.png"),
				backgroundColor: "transparent",
				datainfo:{},
				nvaHeight: 44,
				marTop: 0, //距离顶部的距离
				stubarHeight: 0, //
				title:'',
				adrlist:[],
				list:[],
				pageSize: 10,
				pageNum: 1,
				reachflag: false,
				wtdt:'',
				adrname:'',
				adridx:[0],
				time:'',
				zt:'',
				userId:this.$store.state.user.userId,
			}
		},
		onPageScroll(e) {
			var scrollTop = Number(e.scrollTop);
			if (scrollTop > 0) {
				this.backgroundColor = '#0491FD'
			} else {
				this.backgroundColor = 'transparent'
			}
		},
		onUnload() {
			uni.$off('refreshmeetlist')
		},
		onLoad: function() {
			uni.$on('refreshmeetlist',(e) => {
				this.getConfirm()
			})
			this.init()
			this.getDataFn()
			uni.getSystemInfo({
				success: (e) => {
					this.stubarHeight = Number(e.statusBarHeight);
					this.nvaHeight = Number(e.statusBarHeight) + 44;
					this.tabscheight = Number(this.nvaHeight) * 2 + 100;
				}
			})
		},
		onShow() {},
		mounted() {
			this.getHeightFn()
		},
		// 上拉触底加载更多触发事件
		onReachBottom() {
			if (this.reachflag) {
				this.pageNum++
				this.getDataFn()
			}
		},
		methods: {
			checkPermi,
			checkRole,
			getConfirm(){
				this.pageNum=1;
				this.list=[];
				this.reachflag=true;
				this.getDataFn()
			},
			getResetFn(){
				// 重置
				this.adrname='';
				this.conferenceRoomId='';
				this.adridx=[0];
				this.getConfirm()
			},
			getBack() {
				uni.navigateBack({
					delta: 1
				})
			},
			getDetail(id){
				var obj={
					id:id,
					type:'edit'
				}
				this.$tab.navigateTo('/work/pages/yyorder/meetadd?data='+encodeURIComponent(JSON.stringify(obj)))
			},
			getAddFn(){
				this.$tab.navigateTo('/work/pages/yyorder/meetadd')
			},
			bindTimeChangea(e){
				var val=e.detail.value;
				this.adrname=this.adrlist[val].conferenceRoomName;
				this.conferenceRoomId=this.adrlist[val].conferenceRoomId;
				this.getConfirm()
			},
			bindTimeChangeb(e){
				var val=e.detail.value;
			},
			bindTimeChangec(e){
				var val=e.detail.value;
			},
			getHeightFn() {
				let query = uni.createSelectorQuery().in(this);
				//需要给黄色区域设置一个id标识，在这里是demo
				query.select('.navbox').boundingClientRect(data => {
					var top = data.top < 0 ? -data.top : data.top;
					var stubarHeight = Number(this.stubarHeight);
					this.marTop = stubarHeight > 0 ? 692 - Number(data.height) * 2 : 692 - Number(data.height) *
						2 + 40 //赋值，待会要用
					if (top <= this.nvaHeight) {
						const opacity = top / 100 // 计算透明度值
						const color = `rgba(4, 145, 253, ${opacity})`
						this.backgroundColor = color // 更新盒子背景颜色
					} else {
						this.backgroundColor = '#00A9F0'
					}
				}).exec();
			},
			init() {
				// 会议室
				getRoomNoList().then(res=>{
					if(res.code==200){
						this.adrlist = res.rows
					}
				})
			},
			getDelFn(id){
				var that=this;
				getRoomyyDel(id).then(res=>{
					if(res.code==200){
						this.$toast('删除成功')
						setTimeout(function(){
							that.getConfirm()
						},1200)
					}
				})
			},
			getDataFn() {
				var params = {
					pageSize: this.pageSize,
					pageNum: this.pageNum,
				}
				if(!checkRole(['administrators'])){
					params.orderUserId=this.userId
				}
				if(this.adrname){
					params.conferenceRoomId=this.conferenceRoomId
				}
				getRoomyyList(params).then(res => {
					if (res.code == 200) {
						if (res.rows.length < this.pageSize) {
							this.reachflag = false
							this.wtdt = '到底了~';
						} else {
							var num = parseInt(res.rows.length) + parseInt(this.pageSize) * parseInt(this.pageNum -
								1)
							if (num < res.total) {
								this.reachflag = true
								this.wtdt = '上拉加载更多'
							} else {
								this.reachflag = false
								this.wtdt = '到底了~';
							}
						}
						if (this.pageNum == 1) {
							this.list = res.rows;
						} else {
							this.list = this.list.concat(res.rows)
						}
					} else {
						this.$toast(res.msg)
					}
				})

			},
		},

	}
</script>
<style>
/* 	page {
		background: #ffffff;
	} */
</style>
<style lang="scss" scoped>
.navbox{position: fixed;left: 0;right: 0;top: 0;z-index: 4;	}
.navbg{width: 100%;height: 692rpx;}	
.zxmain{position: relative;padding: 8rpx 32rpx 100rpx;box-sizing: border-box;}
.search{padding: 0 52rpx;box-sizing: border-box;
	width: 100%;height: 76rpx;background: rgba(40, 139, 214, 0.5);border-radius: 38rpx;
	image{width: 24rpx;height: 24rpx;margin-right: 22rpx;flex: 0 0 auto;}
	input{flex: 1;font-weight: 500;font-size: 22rpx;color: #FFFFFF;}
}
.lbtab{padding-left:18rpx;
	.limg{width: 24rpx;height: 24rpx;margin-right: 16rpx;flex:0 0 auto;}
	.lbtabs{overflow: auto;flex-wrap: nowrap;flex:1;
		.pickbox{text-align: right;font-weight: 500;font-size: 28rpx;color: #161616;min-height:88rpx;margin: 0 10rpx;
			.pickboxa{justify-content: center;	
				min-height: 88rpx;display: flex;align-items: center;	
			}
			.tit{font-weight: 500;font-size: 26rpx;color: #FFFFFF;}
			.hupimg{width: 16rpx;height: 10rpx;margin-left: 26rpx;flex: 0 0 auto;}
		}
		
	}
}
</style>
