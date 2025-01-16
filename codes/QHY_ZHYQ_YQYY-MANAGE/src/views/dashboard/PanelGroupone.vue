<template>
  <div :class="className" :style="{ height: height, width: width }" />
</template>

<script>
import * as echarts from 'echarts';
require('echarts/theme/macarons'); // echarts theme
import resize from './mixins/resize';

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '130px'
    },
    height: {
      type: String,
      default: '130px'
    },
    chartData: {
      type: Object,
      required: true
    }
  },
watch: {
     chartData: {
       deep: true,
       handler(val) {
         this.setOptions(val)
       }
     }
   },
   data() {
     return {
       chart: null
     }
   },
   mounted() {
     this.$nextTick(() => {
       this.initChart()
     })
   },
   beforeDestroy() {
     if (!this.chart) {
       return
     }
     this.chart.dispose()
     this.chart = null
   },
   methods: {
     initChart() {
       this.chart = echarts.init(this.$el, 'macarons');
       this.chart = echarts.init(this.$el, 'macarons')
       this.setOptions(this.chartData)
     },
       setOptions(row) {
         console.log(row,56)
        this.chart.setOption({
                color: [
                  new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                    {
                      offset: 0,
                      color: '#ABD8FC'
                    },
                    {
                      offset: 1,
                      color: '#ABD8FC'
                    }
                  ]),
                  new echarts.graphic.LinearGradient(1, 1, 0, 0, [
                    {
                      offset: 0,
                      color: '#4D73F8'
                    },
                    {
                      offset: 0.9,
                      color: '#4D73F8'
                    }
                  ])
                ],
                tooltip: {
                  trigger: 'item'
                },
                legend: {
                  top: '5%',
                  left: 'center',
                  data: []
                },
                // 设置环形中间的数据
                
				graphic: [{
				             type: 'text',
				             left:'center',
				             top: '53%',
				             z: 10,
				             style: {
				                 fill: '#3464EB',
				                 text:[
				                     row.dkzs
				                 ].join('\n\n'),//实现两文本上下换行的效果
				                 textAlign:'center',
				                 font: '14px Microsoft YaHei',
				             }
				          }],
				title: {
				          show: true,
				           text: "总人数",//要显示的文本
				           left: "center",
				           top: "35%",
				           textStyle: {
				               color: "#343434",
				               fontSize: "14",
				           },
				       },

                series: [
                  {
                    name: '',
                    type: 'pie',
                    radius: ['65%', '90%'],
                    center: ['50%', '50%'],
                    avoidLabelOverlap: false,
                    // label: {
                    //     show: false,
                    //     position: 'center'
                    // },
                    // emphasis: {
                    //     label: {
                    //         show: true,
                    //         fontSize: '17',
                    //         fontWeight: '400',
                    //         color:'#2497D5'
                    //     }
                    // },
                    // labelLine: {
                    //     show: true
                    // },
                    itemStyle: {
                      normal: {
                        label: {
                          show: false
                        },
                        labelLine: {
                          show: true
                        }
                      },
                      emphasis: {
                        label: {
                          show: false,
                          position: 'outer',
                          textStyle: {
                            fontSize: '15',
                            fontWeight: 'bold',
                            color: 'white'
                          }
                        }
                      }
                    },
                    data: [{ value: row.ydk, name: '已打卡人数' }, { value: row.wdk, name: '未打卡人数' }]
                  }
                ]
              });
       }
   }
};
</script>
