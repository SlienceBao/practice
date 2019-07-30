package com.jj0327.practice.constant;

/**
 * @author jinbao
 * @date 2019/7/30 16:29
 * @description: 搜房常量类
 */
public class SouFangConstants {
    /**
     * 登录相关的常量
     */
    public static class LoginFields {
        /**
         * 登录URL
         */
        public static final String LOGIN_URL = "https://2.fang.com/Default.aspx";
        /**
         * 默认页登录按钮ID
         */
        public static final String LOGIN_BUTTON_ID = "loginID";

        /**
         * 账号密码登录css
         */
        public static final String ACCOUNT_LOGIN_CSS = "[class='login-tab fr']";
        /**
         * 手机号登录css
         */
        public static final String MOBILE_LOGIN_CSS = "[class='login-tab fl']";
        /**
         * 用户文本框ID
         */
        public static final String USERNAME_ID = "txtusername";
        public static final String PASSWORD_ID = "password";
        /**
         * 登录按钮ID
         */
        public static final String LOGIN_SUBMIT_ID = "imgbt_login";
    }

    /**
     * 二手房录入
     */
    public static class SaleInputFields {
        public static final String SALE_INPUT_URL = "https://2.fang.com/magent/house/sale/saleinput.aspx";

        /**
         * 楼盘名称
         */
        public static final String BUILDING_ID = "input_PROJNAME";
        /**
         * 产权年限
         */
        public static final String PROPERTY_YEAR_ID = "options_input_PropertyYear";
        /**
         * 产权年限
         */
        public static final String PROPERTY_TYPE_ID = "options_input_y_str_PAYINFO0";
        /**
         * 产权年限
         */
        public static final String PROPERTY_SUB_YEAR_ID = "options_input_PropertySubType";
        /**
         * 卧室数
         */
        public static final String HOME_COUNT_ID = "input_ROOM";
        /**
         * 厅的数量
         */
        public static final String HALL_COUNT_ID = "input_HALL";
        /**
         * 卫生间数量
         */
        public static final String TOILET_COUNT_ID = "input_TOILET";
        /**
         * 厨房数量
         */
        public static final String KITCHEN_COUNT_ID = "input_KITCHEN";
        /**
         * 阳台数量
         */
        public static final String BALCONY_COUNT_ID = "input_BALCONY";
        /**
         * 房屋结构
         */
        public static final String HOUSE_STRUCTURE_ID = "options_input_HouseStructure";
        /**
         * 房屋装修
         */
        public static final String HOUSE_FITMENT_ID = "options_input_HouseFITMENT";
        /**
         * 房屋朝向
         */
        public static final String HOUSE_FORWORD_ID = "options_input_HouseFORWARD";
        /**
         * 建造类型
         */
        public static final String BUILDING_TYPE_ID = "options_input_BuildingType";
        /**
         * 建筑面积
         */
        public static final String BUILDING_AREA_ID = "BuildingArea";
        /**
         * 建筑面积
         */
        public static final String USED_AREA_ID = "input_LIVEAREA";
        /**
         * 楼层
         */
        public static final String FLOOR_ID = "input_FLOOR";
        /**
         * 总楼层
         */
        public static final String ALL_FLOOR_ID = "input_ALLFLOOR";
        /**
         * 总楼层
         */
        public static final String FLOOR_HEIGHT_ID = "input_n_num_floorHeight";
    }
    /**
     * 出租房录入
     */
    public static class LeaseInputFields {
        public static final String LEASE_INPUT_URL = "https://2.fang.com/magent/house/lease/leaseinput.aspx";

    }
}
