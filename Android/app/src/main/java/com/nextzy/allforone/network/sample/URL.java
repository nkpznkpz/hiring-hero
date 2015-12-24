package com.nextzy.allforone.network.sample;

/**
 * Created by Akexorcist on 7/30/15 AD.
 */
public class URL {
    public static final String URL_BASE_SERVER_1 = "http://128.199.146.190:4000/eServiceWS/";
    public static final String URL_BASE_SERVER_2 = "http://128.199.146.190:4001/";

    public static final String URL_LOGOUT = URL_BASE_SERVER_1 + "logout";
    public static final String URL_GET_OTP = URL_BASE_SERVER_1 + "login/getOTP";
    public static final String URL_SECONDARY_OTP = URL_BASE_SERVER_1 + "login/getSecondaryOTP";
    public static final String URL_CONFIRM_OTP = URL_BASE_SERVER_1 + "login/confirmOTP";
    public static final String URL_LOGIN_DATA_RECEIVE = URL_BASE_SERVER_2 + "logindataRecieve.json";
    public static final String URL_GET_PACKAGE_GROUP_LIST = URL_BASE_SERVER_2 + "service/mobile/:mobileNumber/packagelist/types/:typeCode/groups";
    //    public static final String URL_GET_PACKAGE_GROUP_LIST = URL_BASE_AKE + "service/mobile/:mobileNumber/packagelist/types/:typeCode/groups";
    public static final String URL_GET_PACKAGE_LIST = URL_BASE_SERVER_2 + "service/mobile/:mobileNumber/packagelist/types/:typeCode/groups/:groupCode/packages";
    //    public static final String URL_GET_PACKAGE_LIST = URL_BASE_AKE + "service/mobile/:mobileNumber/packagelist/types/:typeCode/groups/:groupCode/packages";
    public static final String URL_APPLY_PACKAGE = URL_BASE_SERVER_2 + "service/mobile/:mobileNumber/packagetypes/:typeCode/applyPackages";
    public static final String URL_CANCELABLE_PACKAGE = URL_BASE_SERVER_2 + "service/mobile/:mobileNumber/cancelablepackages";
    public static final String URL_GET_BOS_CDR = URL_BASE_SERVER_2 + "service/mobile/:mobileNumber/usages/billed/group/:groupCode/chargedetail/filter";
    public static final String URL_BILLING_CYCLE = URL_BASE_SERVER_2 + "service/mobile/:mobileNumber/preferencesettings/billingcycle";
    //    public static final String URL_BILLING_CYCLE = URL_BASE_AKE + "service/mobile/:mobileNumber/preferencesettings/billingcycle";
    public static final String URL_PACKAGE_REMARKS = URL_BASE_SERVER_2 + "service/mobile/:mobileNumber/pagesettings/remarks";
    //    public static final String URL_PACKAGE_REMARKS = URL_BASE_AKE + "service/mobile/:mobileNumber/pagesettings/remarks";
    public static final String URL_GET_BOS_CQS = URL_BASE_SERVER_2 + "service/mobile/:mobileNumber/usages/unbilled/chargesummary";
}
