<template>
	<view>
		<view class="navbox">
			<uni-nav-bar color="#ffffff" left-icon="left" title="我的订餐记录" :background-color="backgroundColor"
				:border="false" statusBar='true' fixed="true" @clickLeft='getBack'>
			</uni-nav-bar>
		</view>
		<image :src="navbg" class="navbg"></image>
		<view class="zxmain" :style="'margin-top:-'+marTop+'rpx;'">
			<view class="ytbox">
				<!-- <view class="ytop flexc">
					<view class="tit">{{visitName.charAt(0)}}</view>
					<view class="cent">
						<view class="ctit">{{visitName}}</view>
						<view class="ctime">最新打卡：2024-07-18</view>
					</view>
				</view> -->
				<!-- 日历 -->
				<view class="mb24">
					<lunc-calendar ref="calendar" :showLunar="false" :configWeek="configWeek" :configHoliday="configHoliday" :firstDayOfWeek="firstDayOfWeek" :showMonthBg="false" :showShrink="true" :signList="signList"
						@dayChange="dayChange" weekType="" @monthChange="monthChange" @getIsSelDayFn="getIsSelDayFn" @shrinkClick="shrinkClick">
					</lunc-calendar>
				</view>
				<!-- 步骤条 -->
				<view class="mb16 plr18">
					<view class="steps">
						<view class="cir"></view>
						<view class="stepsa">
							<view class="steptit">中餐预订</view>
							<view class="steptxt">
								<image :src="cirimg"></image>
								<view>{{datainfo.am}}</view>
							</view>
						</view>
					</view>
					<view class="steps">
						<view class="cir"></view>
						<view class="stepsa">
							<view class="steptit">晚餐预订</view>
							<view class="steptxt">
								<image :src="cirimg"></image>
								<view>{{datainfo.pm}}</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {checkPermi,checkRole} from "@/utils/permission"; // 权限判断函数
	import {getDictionaryFn} from "@/api/mine/register.js"
	import {getFoodRiLi} from "@/api/mine/work.js"
	import luncCalendar from "@/work/components/lunc-calendar/foodlunc-calendar.vue"
	export default {
		components: {luncCalendar},
		data() {
			return {
				cirimg:require("@/static/images/home/chose.png"),
				navbg:require("@/static/images/navbg.png"),
				backgroundColor: "transparent",
				datainfo:{},
				nvaHeight: 44,
				marTop: 0, //距离顶部的距离
				stubarHeight: 0, //
				visitName:'林涵宇',
				jclxlist:[],//就餐类型
				kaTime:'',
				
				signList: [],
				configWeek:[],
				configHoliday:'Y',
				firstDayOfWeek:'sunday',
				// dayflag:false,
				// month:'',
				// year:'',
				// kaTime:'',
				changetime:'',//改变的日期
				// timeday:'',//选择的日期
				// visitDate:'',
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
		onLoad: function() {
			// this.init()
			this.time()
			this.getCalendarList()
			uni.getSystemInfo({
				success: (e) => {
					this.stubarHeight = Number(e.statusBarHeight);
					this.nvaHeight = Number(e.statusBarHeight) + 44;
					this.tabscheight = Number(this.nvaHeight) * 2 + 100;
				}
			})
			// 3c8cadac11af4f1298a2882addc8788d
		},
		onShow() {},
		mounted() {
			this.getHeightFn()
		},
		methods: {
			checkPermi,
			checkRole,
			getBack() {
				uni.navigateBack({
					delta: 1
				})
			},
			time(){
				var date = new Date();
				var y = date.getFullYear();
				var m = date.getMonth() + 1;
				var d = date.getDate();
				// var h = date.getHours();
				// var min = date.getMinutes();
				// var s = date.getSeconds();
				// var week = date.getDay(); //获取当前星期X(0-6,0代表星期天)
				// var weeks = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
				var yearStr = y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d)
				// var timeStr = (h < 10 ? ('0' + h) : h) + ':' + (min < 10 ? ('0' + min) : min) + ':' + (s < 10 ? (
				// 	'0' + s) : s);
				this.year=y;
				this.month=m < 10 ? ('0' + m) : m;
				this.kaTime = yearStr
			},
			dayChange(e){
				if(e.daySign&&e.daySign.length){
					var daySign=e.daySign[0];
					this.datainfo=JSON.parse(JSON.stringify(daySign))
				}
			},
			monthChange(e){
				var y=e.year;
				var m=e.month;
				if(y==this.year&&m==this.month){
					this.getCalendarList()
					this.changetime='';
				}else{
					var time=y + '-' + m + '-' + '01'
					this.changetime=time
					this.getCalendarList(time)
				}
			},
			// 选中的日期
			getIsSelDayFn(e){
				if(e.sign&&e.sign.length){
					var date=e.sign[0].date;
					if(date==this.kaTime){
						var daySign=e.sign[0];
						this.datainfo=JSON.parse(JSON.stringify(daySign))
					}
				}	
			},
			shrinkClick(e){
				console.log(e,3)
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
				// 就餐类型
				getDictionaryFn('jiucan').then(res=>{
					if(res.code==200){
						this.jclxlist = res.data.map(v => {
							return {
								label: v.dictLabel,
								value: v.dictValue
							}
						})
					}
				})
			},
			getCalendarList(itime) {
				var time=this.kaTime
				if(itime){
					time=itime
				}
				var params = {
					orderFoodTime: time,
				}
				params.orderFoodUserId=this.userId;
				getFoodRiLi(params).then(res => {
					if (res.code == 200) {
						this.signList=res.data;
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
.zxmain{position: relative;padding: 20rpx 32rpx 40rpx;box-sizing: border-box;display: flex;}
.ytbox{width: 684rpx;margin: 0 auto;padding-top: 24rpx;position: relative;flex: 1;overflow: auto;background-color: #ffffff;padding-bottom: 12rpx;border-radius: 14rpx;
	.ytop{padding: 16rpx 0 24rpx 16rpx;
		.tit{width: 62rpx;height: 62rpx;background: #0391FD;border-radius: 6rpx;font-weight: 500;
	font-size: 34rpx;color: #FFFFFF;margin-right: 18rpx;text-align: center;line-height: 62rpx;flex: 0 0 auto;}
		.cent{
			flex: 1;
			.ctit{font-weight: bold;font-size: 28rpx;color: #161616;margin-bottom: 6rpx;}
			.ctime{font-weight: 500;font-size: 18rpx;color: #666666;}
		}
	}
	.tips{font-weight: 500;font-size: 26rpx;color: #888888;padding-left: 34rpx;margin-bottom: 24rpx;}
	// 步骤条
	.steps{position: relative;display: flex;padding-bottom:40rpx;
		&::after{
			content: '';position: absolute;left:6rpx;top: 34rpx;bottom: 4rpx;background-color: #DADADA;width: 2rpx;
		}
		.cir{width: 14rpx;height: 14rpx;background: #AFAFAF;border-radius: 50%;flex: 0 0 auto;margin-right: 38rpx;margin-top: 13rpx;}
		.stepsa{flex: 1;
			.steptit{font-weight: bold;font-size: 28rpx;color: #161616;margin-bottom: 20rpx;}
			.steptxt{display: flex;
				image{width: 20rpx;height: 20rpx;margin-right: 14rpx;flex: 0 0 auto;margin-top: 7rpx;}
				view{font-weight: bold;font-size: 26rpx;color: #888888;}
			}
		}
	}
}
</style>
