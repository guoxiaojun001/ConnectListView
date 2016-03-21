package com.maning.connectlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewFirst;
    private ListView listViewSecond;
    private ListView listViewThird;

    private List<String> firstList;
    private List<List> secondList;
    private List<String> thirdList;

    private SearchAreaFirstAdapter firstAdapter;
    private SearchAreaSecondAdapter secondAdapter;

    /**
     * 二级菜单名称数组
     **/
    String[][] secondNameArray = {
            {"不限", "东城", "西城", "朝阳", "海淀", "丰台", "石景山", "通州", "昌平", "大兴", "亦庄开发区", "顺义", "房山", "门头沟", "平谷", "怀柔", "密云", "延庆"},
            {"1号线", "2号线", "4号大兴线", "5号线", "6号线", "7号线", "8号线", "9号线", "10号线", "13号线", "14号线", "15号线", "八通线", "亦庄线", "昌平线", "房山线", "机场线"},
            {"不限", "1千米内", "2千米内", "3千米内"}
    };

    /**
     * 三级菜单名称数组：地铁站
     **/
    String[][] thirdNameArray = {
            {"不限",    //1号线
                    "苹果园",
                    "古城（石景山）",
                    "八角游乐园",
                    "八宝山",
                    "玉泉路",
                    "五棵松",
                    "万寿路",
                    "公主坟",
                    "军事博物馆",
                    "木樨地",
                    "南礼士路",
                    "复兴门",
                    "西单",
                    "天安门西",
                    "天安门东",
                    "王府井",
                    "东单",
                    "建国门",
                    "永安里",
                    "国贸",
                    "大望路",
                    "四惠",
                    "四惠东"
            },

            {"不限",  //2号线
                    "积水潭",
                    "鼓楼大街",
                    "安定门",
                    "雍和宫",
                    "东直门",
                    "东四十条",
                    "朝阳门",
                    "建国门",
                    "北京站",
                    "崇文门",
                    "前门",
                    "和平门",
                    "宣武门",
                    "长椿街",
                    "复兴门",
                    "阜成门",
                    "车公庄",
                    "西直门"},

            {"不限",   //4号线
                    "安河桥北",
                    "北宫门",
                    "西苑",
                    "圆明园",
                    "北京大学东门",
                    "中关村",
                    "海淀黄庄",
                    "人民大学",
                    "魏公村",
                    "国家图书馆",
                    "动物园",
                    "西直门",
                    "新街口",
                    "平安里",
                    "西四",
                    "灵境胡同",
                    "西单",
                    "宣武门",
                    "菜市口",
                    "陶然亭",
                    "北京南站",
                    "马家堡",
                    "角门西",
                    "公益西桥",
                    "新宫",
                    "西红门",
                    "高米店北",
                    "高米店南",
                    "枣园",
                    "清源路",
                    "黄村西大街",
                    "黄村火车站",
                    "义和庄",
                    "生物医药基地",
                    "天宫院",
            },

            {"不限",//5号线
                    "宋家庄",
                    "刘家窑",
                    "蒲黄榆",
                    "天坛东门",
                    "磁器口",
                    "崇文门",
                    "东单",
                    "灯市口",
                    "东四",
                    "张自忠路",
                    "北新桥",
                    "雍和宫",
                    "和平里北街",
                    "和平西桥",
                    "惠新西街南口",
                    "惠新西街北口",
                    "大屯路东",
                    "北苑路北",
                    "立水桥南",
                    "立水桥",
                    "天通苑南",
                    "天通苑",
                    "天通苑北",
            },

            {"不限",     //6号线
                    "海淀五路居",
                    "慈寿寺",
                    "花园桥",
                    "白石桥南",
                    "车公庄西",
                    "车公庄",
                    "平安里",
                    "北海北",
                    "南锣鼓巷",
                    "东四",
                    "朝阳门",
                    "东大桥",
                    "呼家楼",
                    "金台路",
                    "十里堡",
                    "青年路",
                    "褡裢坡",
                    "黄渠",
                    "常营",
                    "草房",
                    "物资学院路",
                    "通州北关",
                    "北运河西",
                    "郝家府",
                    "东夏园",
                    "潞城",
            },

            {"不限",    //7号线
                    "北京西站",
                    "湾子",
                    "达官营",
                    "广安门内",
                    "菜市口",
                    "虎坊桥",
                    "珠市口",
                    "桥湾",
                    "磁器口",
                    "广渠门内",
                    "广渠门外",
                    "双井",
                    "九龙山",
                    "大郊亭",
                    "百子湾",
                    "化工",
                    "南楼梓庄",
                    "欢乐谷景区",
                    "垡头",
                    "双合",
                    "焦化厂",
            },

            {"不限",     //8号线
                    "朱辛庄",
                    "育知路",
                    "平西府",
                    "回龙观东大街",
                    "霍营",
                    "育新",
                    "西小口",
                    "永泰庄",
                    "林萃桥",
                    "森林公园南门",
                    "奥林匹克公园",
                    "奥体中心",
                    "北土城",
                    "安华桥",
                    "鼓楼大街",
                    "什刹海",
                    "南锣鼓巷",
            },

            {"不限", //9号线
                    "郭公庄",
                    "丰台科技园",
                    "科怡路",
                    "丰台南路",
                    "丰台东大街",
                    "七里庄",
                    "六里桥",
                    "六里桥东",
                    "北京西站",
                    "军事博物馆",
                    "白堆子",
                    "白石桥南",
                    "国家图书馆",
            },
            {"不限",   //十号线
                    "巴沟",
                    "苏州街",
                    "海淀黄庄",
                    "知春里",
                    "知春路",
                    "西土城",
                    "牡丹园",
                    "健德门",
                    "北土城",
                    "安贞门",
                    "惠新西街南口",
                    "芍药居",
                    "太阳宫",
                    "三元桥",
                    "亮马桥",
                    "农业展览馆",
                    "团结湖",
                    "呼家楼",
                    "金台夕照",
                    "国贸",
                    "双井",
                    "劲松",
                    "潘家园",
                    "十里河",
                    "分钟寺",
                    "成寿寺",
                    "宋家庄",
                    "石榴庄",
                    "大红门",
                    "角门东",
                    "角门西",
                    "草桥",
                    "纪家庙",
                    "首经贸",
                    "丰台站",
                    "泥洼",
                    "西局",
                    "六里桥",
                    "莲花桥",
                    "公主坟",
                    "西钓鱼台",
                    "慈寿寺",
                    "车道沟",
                    "长春桥",
                    "火器营",
            },

            {"不限",    //13号线
                    "西直门",
                    "大钟寺",
                    "知春路",
                    "五道口",
                    "上地",
                    "西二旗",
                    "龙泽",
                    "回龙观",
                    "霍营",
                    "立水桥",
                    "北苑",
                    "望京西",
                    "芍药居",
                    "光熙门",
                    "柳芳",
                    "东直门",
            },

            {"不限",    //14号线
                    "北京南站",
                    "永定门外",
                    "景泰",
                    "蒲黄榆",
                    "方庄",
                    "十里河",
                    "北工大西门",
                    "九龙山",
                    "大望路",
                    "金台路",
                    "枣营",
                    "东风北桥",
                    "将台",
                    "高家园",
                    "望京南",
                    "阜通",
                    "望京",
                    "东湖渠",
                    "来广营",
                    "善各庄",
                    "张郭庄",
                    "园博园",
                    "大瓦窑",
                    "郭庄子",
                    "大井",
                    "七里庄",
                    "西局",
                    "善各庄",
            },

            {"不限",    //15号线
                    "俸伯",
                    "顺义",
                    "石门",
                    "南法信",
                    "后沙峪",
                    "花梨坎",
                    "国展",
                    "孙河",
                    "马泉营",
                    "崔各庄",
                    "望京",
                    "望京西",
                    "关庄",
                    "安立路",
                    "奥林匹克公园",
                    "北沙滩",
                    "六道口",
                    "清华东路西口",
            },

            {"不限",   //八通线
                    "四惠",
                    "四惠东",
                    "高碑店",
                    "传媒大学",
                    "双桥",
                    "管庄",
                    "八里桥",
                    "通州北苑",
                    "果园",
                    "九棵树",
                    "梨园",
                    "临河里",
                    "土桥",
            },

            {"不限",    //亦庄线
                    "宋家庄",
                    "肖村",
                    "小红门",
                    "旧宫",
                    "亦庄桥",
                    "亦庄文化园",
                    "万源街",
                    "荣京东街",
                    "荣昌东街",
                    "同济南路",
                    "经海路",
                    "次渠南",
                    "次渠",
            },

            {"不限",    //昌平线
                    "南邵",
                    "沙河高教园",
                    "沙河",
                    "巩华城",
                    "朱辛庄",
                    "生命科学园",
                    "西二旗",
            },


            {"不限",   //房山线
                    "苏庄",
                    "良乡南关",
                    "良乡大学城西",
                    "良乡大学城",
                    "良乡大学城北",
                    "广阳城",
                    "篱笆房",
                    "长阳",
                    "稻田",
                    "大葆台",
                    "郭公庄",
            },

            {"不限",    //机场线
                    "东直门",
                    "三元桥",
                    "T3航站楼",
                    "T2航站楼",
            },
    };
    private SearchAreaThirdAdapter thirdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initFirstListView();

    }

    private void initData() {
        firstList = new ArrayList<>();
        secondList = new ArrayList<>();
        thirdList = new ArrayList<>();

        firstList.add("商圈");
        firstList.add("地铁");
        firstList.add("附近");

        for (int i = 0; i < secondNameArray.length; i++) {
            List<String> list = new ArrayList<>();
            String[] strings = secondNameArray[i];
            for (int j = 0; j < strings.length; j++) {
                String name = secondNameArray[i][j];
                list.add(name);
            }
            secondList.add(list);
        }

    }


    private void initView() {

        listViewFirst = (ListView) findViewById(R.id.listViewFirst);
        listViewSecond = (ListView) findViewById(R.id.listViewSecond);
        listViewThird = (ListView) findViewById(R.id.listViewThird);

    }

    private void initFirstListView() {
        firstAdapter = new SearchAreaFirstAdapter(this, firstList);
        firstAdapter.setSelectPosition(0);
        listViewFirst.setAdapter(firstAdapter);
        initSecondListView(0);

        listViewFirst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updataListView(position);
            }
        });
    }


    private void initSecondListView(int position) {
        secondAdapter = new SearchAreaSecondAdapter(this, secondList.get(position));
        secondAdapter.setSelectPosition(position);
        listViewSecond.setAdapter(secondAdapter);

        listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updataListView2(position);
            }
        });

        initThirdListView(position);
    }

    private void initThirdListView(int position) {
        //更新第三ListView
        if ("地铁".equals(firstAdapter.getCurrentPositionItem().trim())) {
            String[] strings = thirdNameArray[position];
            if (strings != null && strings.length > 0) {
                thirdList.clear();
                for (int i = 0; i < strings.length; i++) {
                    thirdList.add(strings[i]);
                }
                listViewThird.setVisibility(View.VISIBLE);
                thirdAdapter = new SearchAreaThirdAdapter(this, thirdList);
                listViewThird.setAdapter(thirdAdapter);
                listViewThird.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        updataListView3(position);
                        //跳转Activity
                        MainActivity.this.startActivity(new Intent(MainActivity.this, TestActivity.class));
                    }
                });
            } else {
                listViewThird.setVisibility(View.GONE);
            }
        } else {
            listViewThird.setVisibility(View.GONE);
        }
    }

    private void updataListView(int position) {
        firstAdapter.setSelectPosition(position);
        firstAdapter.notifyDataSetChanged();
        //更新第二ListView
        secondAdapter.setDatas(secondList.get(position));
        updataListView2(0);
    }

    private void updataListView2(int position) {
        secondAdapter.setSelectPosition(position);
        secondAdapter.notifyDataSetChanged();
        //更新第三ListView
        if ("地铁".equals(firstAdapter.getCurrentPositionItem().trim())) {
            if (thirdAdapter != null) {
                String[] strings = thirdNameArray[position];
                if (strings != null && strings.length > 0) {
                    thirdList.clear();
                    for (int i = 0; i < strings.length; i++) {
                        thirdList.add(strings[i]);
                    }
                    listViewThird.setVisibility(View.VISIBLE);
                    thirdAdapter.setDatas(thirdList);
                    thirdAdapter.setSelectPosition(0);
                    thirdAdapter.notifyDataSetChanged();
                } else {
                    listViewThird.setVisibility(View.GONE);
                }
            } else {
                initThirdListView(position);
            }
        } else {
            listViewThird.setVisibility(View.GONE);
        }
    }

    private void updataListView3(int position) {
        thirdAdapter.setSelectPosition(position);
        thirdAdapter.notifyDataSetChanged();
    }

}
