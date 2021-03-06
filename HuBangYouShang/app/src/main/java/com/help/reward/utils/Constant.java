package com.help.reward.utils;

/**
 * Created by wuxiaojun on 2017/2/23.
 */

public class Constant {

    public static final String PLATFORM_CLIENT = "android"; // 平台是安卓

    // 210.72.13.135
    public static final String BASE_URL = "http://210.72.13.135/";
    // 登录
    public static final String URL_LOGIN = "mobile/index.php?act=login";
    // 注册获取验证码
    public static final String URL_GET_CODE = "mobile/index.php?act=shop&op=index";

    // 注册
    public static final String URL_REGISTER = "mobile/index.php?act=connect&op=sms_register";
    // 商城首页
    public static final String URL_SHOP_MALL_MAIN = "mobile/index.php?act=shop&op=index";

    // 商品详情信息
    public static final String URL_SHOP_MALL_INFO = "mobile/index.php";


    // 会员相关--余额明细  参数[post]：type=1/2 1-收入2-支出，不带参数为全部
    public static final String URL_MEMBER_BALANCE = "mobile/index.php";

    // 会员相关--帮赏分明细  参数[post]：type=1/2 1-收入2-支出，不带参数为全部
    public static final String URL_MEMBER_ACCOUNT_HELP_REWARD = "mobile/index.php";

    // 会员相关--通用卷明细  参数[post]：type=1/2 1-收入2-支出，不带参数为全部
    public static final String URL_MEMBER_ACCOUNT_GENERAL_VOLUME = "mobile/index.php";

    // 会员相关--我的优惠卷
    public static final String URL_MEMBER_ACCOUNT_MY_COUPON = "mobile/index.php?act=member_voucher&op=voucher_list";

    // 会员相关--优惠卷详情
    public static final String URL_MEMBER_ACCOUNT_MY_COUPON_INFO = "mobile/index.php?act=member_voucher&op=voucher_info";

    // 会员相关--优惠卷上架  参数[post]：price；voucher_id
    public static final String URL_MEMBER_ACCOUNT_MY_COUPON_PUT_ON_SALE = "mobile/index.php?act=member_voucher&op=put_on_sale";

    // 余额兑换帮赏分
    public static final String URL_MEMBER_YUE_DUIHUAN_BANGSHANGFEN = "mobile/index.php?act=member_points&op=deposit_exchange";

    // 余额兑换帮赏分--提交
    public static final String URL_MEMBER_COMMIT_YUE_DUIHUAN_BANGSHANGFEN = "mobile/index.php?act=member_points&op=deposit_exchange_post";

    // 帮赏分兑换通用卷
    public static final String URL_MEMBER_BANGSHANGFEN_DUIHUAN_TONGYONGJUAN = "mobile/index.php?act=member_points&op=exchange_general";

    // 帮赏分兑换通用卷--提交
    public static final String URL_MEMBER_COMMIT_BANGSHANGFEN_DUIHUAN_TONGYONGJUAN = "mobile/index.php?act=member_points&op=exchange_general_post";


    public static final String URL_MESSAGE = "mobile/index.php?act=member_message";

    public static final String URL_HELP = "mobile/index.php?act=index";

    public static final String URL_AREA = "mobile/index.php?act=area&op=post_area_list";
    public static final String URL_SUBREWARD = "mobile/index.php?act=get_reward&op=release";
    //发布求助帖
    public static final String URL_SUBSEEKHELP = "mobile/index.php?act=seek_help&op=release_post";
    public static final String URL_UPLOADIMAGE = "mobile/index.php?act=upload_file&op=upload_img";


}
