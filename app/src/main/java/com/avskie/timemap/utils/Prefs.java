package com.avskie.timemap.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class Prefs {
    static final String PREF_SPLASH_SCREEN = "splash_screen";
	static final String PREF_USER_FIRST_NAME = "user_first_name";
    static final String PREF_USER_LAST_NAME = "user_last_name";
    static final String PREF_USER_NAME = "user_name";
    static final String PREF_USER_PWD = "user_pwd";
	static final String PREF_USER_EMAIL = "user_email";
	static final String PREF_USER_PIC_URL = "userPic_url";
    static final String PREF_USER_ID = "user_id";
    static final String PREF_ACC_EXISTS = "acc_exists";
    static final String PREF_GCM_TOKEN = "gcm_token";
    /* Android Info Generic Usecase*/
    static final String PREF_DEVICE_ID = "device_id";
    static final String PREF_ANDROID_ID = "android-id";
    static final String PREF_MANUFACTURER = "manufacturer";
    static final String PREF_IMEI_NUMBER = "imei-number";
    static final String PREF_DEVICE_DATA_BOOTSTRAP_DONE = " device-data-bootstrap";
    static final String PREF_FACEBOOK_ID = "facebook_id";
    static final String PREF_FACEBOOK_USER_GENDER = "facebook-gender";
    static final String PREF_FACEBOOK_USER_AGE = "facebook-age";
    static final String PREF_FACEBOOK_USER_UPDATED ="facebook-last-updated";

    static final String PREF_LAST_KNOWN_LOCATION = "location";
    static final String PREF_LAST_LOCATION_UPDATED_TIME = "location_time";
    static final String PREF_LOCATION_UPDATES = "location_update";
    static final String PREF_FRONT_CAMERA_RESOLUTION = "front_camera_resolution";
    static final String PREF_BACK_CAMERA_RESOLUTION = "back_camera_resolution";
    static final String PREF_LAST_KNOWN_LOCATION_LOCALITY = "location_locality";
    static final String PREF_LAST_KNOWN_LOCATION_SUBLOCALITY = "location_sub";
    static final String PREF_LAST_KNOWN_LOCATION_ZIPCODE = "location-zip";
    static final String PREF_DEVICE_MODEL_MATCH = "device-match";
    static final String PREF_DEVICE_COUNT_SEARCH = "device-count";
    static final String PREF_DEVICE_IMAGE_URL = "device-image-url";

    static final String PREF_RANDOM_NUMBER = "random-number";
    static final String PREF_LOCATION_DIALOG_SHOWN = "location-dialog-shown";

    static final String PREF_FEEDBACK_HAPPINESS = "feedback-happiness";
    static final String PREF_FEEDBACK_CAMERA_RATING = "feedback-camera";
    static final String PREF_FEEDBACK_BATTERY_RATING = "feedback-battery";
    static final String PREF_FEEDBACK_PERFORMANCE_RATING ="feedback-performance";
    static final String PREF_FEEDBACK_BRAND_SUPPORT = "feedback-brand";
    static final String PREF_FEEDBACK_EXTRA_COMMENTS = "feedback-comments";
    static final String PREF_FEEDBACK_EXTRA_PROS = "feedback-pros";
    static final String PREF_FEEDBACK_EXTRA_CONS = "feedback-cons";
    static final String PREF_DEVICE_OLDNESS_METRIC = "oldness-metric";
    static final String PREF_FEEDBACK_AVAILABLE = "feedback-available";

    static final String PREF_DISCOVER_MIN_PRICE = "discover_min_price";
    static final String PREF_DISCOVER_MAX_PRICE = "discover_max_price";

    static final String PREF_SEARCH_SYNC_IMMEDIATE = "search-sync-immediate";
    static final String PREF_USER_SETTING_PREFERENCE = "user-settings-preference";
    static final String PREF_PACKAGE_CHANGE_PREFERENCE = "package-change-preference";
    static final String PREF_APPSFLYER_UNIQUE_ID = "appsflyer-uniqueid";
    static final String PREF_REFERRAL_PARAM = "referral-param";
    static final String PREF_TINY_URL = "tiny-url";

    static final String PREF_ADVERTISING_ID = "advertising_id";

	static SharedPreferences getPrefs(Context ctx) {
		return PreferenceManager.getDefaultSharedPreferences(ctx);
	}

	public static void setUserFirstName(Context ctx, String s) {
		getPrefs(ctx).edit().putString(PREF_USER_FIRST_NAME, s).commit();
	}

    public static void setUserLastName(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_USER_LAST_NAME, s).commit();
    }

    public static void setUserName(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_USER_NAME, s).commit();
    }


	public static String getUserFirstName(Context ctx) {
		return getPrefs(ctx).getString(PREF_USER_FIRST_NAME, "");
	}

    public static String getUserLastName(Context ctx) {
        return getPrefs(ctx).getString(PREF_USER_LAST_NAME, "");
    }

    public static String getUserName(Context ctx) {
        return getPrefs(ctx).getString(PREF_USER_NAME, "");
    }

    public static void setUserEmail(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_USER_EMAIL, s).commit();
    }

    public static void setFacebookUserGender(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_FACEBOOK_USER_GENDER, s).commit();
    }

    public static void setFacebookUserAge(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_FACEBOOK_USER_AGE, s).commit();
    }

    public static void setFacebookUserLastUpdated(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_FACEBOOK_USER_UPDATED, s).commit();
    }


    public static String getUserEmail(Context ctx) {
        return getPrefs(ctx).getString(PREF_USER_EMAIL, "");
    }

    public static String getFacebookUserGender(Context ctx) {
        return getPrefs(ctx).getString(PREF_FACEBOOK_USER_GENDER, "");
    }

    public static String getFacebookUserAge(Context ctx) {
        return getPrefs(ctx).getString(PREF_FACEBOOK_USER_AGE, "");
    }

    public static String getFacebookUserLastUpdated(Context ctx) {
        return getPrefs(ctx).getString(PREF_FACEBOOK_USER_UPDATED, "");
    }


    public static void setUserProfileUrl(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_USER_PIC_URL, s).commit();
    }

    public static String getUserProfileUrl(Context ctx) {
        return getPrefs(ctx).getString(PREF_USER_PIC_URL, "");
    }

    public static void setUserPwd(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_USER_PWD, s).commit();
    }

    public static String getUserPwd(Context ctx) {
        return getPrefs(ctx).getString(PREF_USER_PWD, "");
    }
/* Global server user id */
    public static void setUserId(Context ctx, String s) {
        if (TextUtils.isEmpty(s)) return;
        /* Don't allow to write more than once Avoid accidential access */
        if (hasUserIdGenerated(ctx)) return;
        getPrefs(ctx).edit().putString(PREF_USER_ID, s).commit();
    }

    public static String getUserId(Context ctx) {
        return getPrefs(ctx).getString(PREF_USER_ID, null);
    }
    public static boolean hasUserIdGenerated(Context ctx){
        return !TextUtils.isEmpty(getUserId(ctx));
    }
/* Facebook User */
    public static void setFacebookId(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_FACEBOOK_ID, s).commit();
    }

    public static String getFacebookId(Context ctx) {
        return getPrefs(ctx).getString(PREF_FACEBOOK_ID, null);
    }
    public static boolean hasFacebookSignedup(Context ctx){
        return !TextUtils.isEmpty(getFacebookId(ctx));
    }
/* Device Id generation */
    public static boolean hasDeviceIdGenerated(Context ctx) {
        return !TextUtils.isEmpty(getDeviceId(ctx));
    }
    /* Android Info Generic Use case */
    public static void setDeviceId(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_DEVICE_ID, s).commit();
    }

    public static String getDeviceId(Context ctx) {
        return getPrefs(ctx).getString(PREF_DEVICE_ID, null);
    }

    /* Imei will also be used with the Agcmdevice */
    public static void setImeiId(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_IMEI_NUMBER, s).commit();
    }
    public static String getImeiId(Context ctx) {
        return getPrefs(ctx).getString(PREF_IMEI_NUMBER, null);
    }

    /* Imei will also be used with the Agcmdevice */
    public static void setAndroidId(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_ANDROID_ID, s).commit();
    }
    public static String getAndroidId(Context ctx) {
        return getPrefs(ctx).getString(PREF_ANDROID_ID, null);
    }

    /* Deciding on device capture */
    public static void markDeviceDataBootstrapDone(final Context context) {
        getPrefs(context).edit().putBoolean(PREF_DEVICE_DATA_BOOTSTRAP_DONE, true).commit();
    }

    public static boolean isDeviceDataBootstrapDone(final Context context) {
        return getPrefs(context).getBoolean(PREF_DEVICE_DATA_BOOTSTRAP_DONE, false);
    }


    public static void setManufacturer(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_MANUFACTURER, s).commit();
    }

    public static String getManufacturer(Context ctx) {
        return getPrefs(ctx).getString(PREF_MANUFACTURER, null);
    }
    /* Android Info Generic Use case */


    public static void setGCMToken(Context ctx, String s) {
        getPrefs(ctx).edit().putString(PREF_GCM_TOKEN, s).commit();
    }

    public static String getGCMToken(Context ctx) {
        return getPrefs(ctx).getString(PREF_GCM_TOKEN, null);
    }
    /* Last Known area of the device */
    public static void setLastKnownLocation(Context ctx, String s){
        getPrefs(ctx).edit().putString(PREF_LAST_KNOWN_LOCATION, s).commit();
    }
    public static String getLastKnownLocation(Context ctx){
        return getPrefs(ctx).getString(PREF_LAST_KNOWN_LOCATION, null);
    }
    public static void setLastUpdatedTime(Context ctx, String s){
        getPrefs(ctx).edit().putString(PREF_LAST_LOCATION_UPDATED_TIME, s).commit();
    }
    public static String getLastUpdatedTime(Context ctx){
        return getPrefs(ctx).getString(PREF_LAST_LOCATION_UPDATED_TIME, null);
    }
    public static void setLocationUpdate(Context ctx, boolean b){
        getPrefs(ctx).edit().putBoolean(PREF_LOCATION_UPDATES, b).commit();
    }
    public static boolean getLocationUpdate(Context ctx){
        return getPrefs(ctx).getBoolean(PREF_LOCATION_UPDATES, false);
    }
    public static void setLastKnownLocationLocality(Context ctx, String s){
        if (s==null) return;
        getPrefs(ctx).edit().putString(PREF_LAST_KNOWN_LOCATION_LOCALITY, s).commit();
    }
    public static String getLastKnownLocationLocality(Context ctx){
        return getPrefs(ctx).getString(PREF_LAST_KNOWN_LOCATION_LOCALITY, null);
    }
    public static void setLastKnownLocationSubLocality(Context ctx, String s){
        if (s==null) return;
        getPrefs(ctx).edit().putString(PREF_LAST_KNOWN_LOCATION_SUBLOCALITY, s).commit();
    }
    public static String getLastKnownLocationSubLocality(Context ctx){
        return getPrefs(ctx).getString(PREF_LAST_KNOWN_LOCATION_SUBLOCALITY, null);
    }
    public static void setLastKnownLocationZip(Context ctx, String s){
        if (s==null) return;
        getPrefs(ctx).edit().putString(PREF_LAST_KNOWN_LOCATION_ZIPCODE, s).commit();
    }
    public static String getLastKnownLocationZip(Context ctx){
        return getPrefs(ctx).getString(PREF_LAST_KNOWN_LOCATION_ZIPCODE, null);
    }
    /* Last Known area of the device */

    /* Required for device analysis */
    public static void setFrontCameraResolution(Context ctx, String s){
        getPrefs(ctx).edit().putString(PREF_FRONT_CAMERA_RESOLUTION, s).commit();
    }
    public static String getFrontCameraResolution(Context ctx){
        return getPrefs(ctx).getString(PREF_FRONT_CAMERA_RESOLUTION, null);
    }
    public static void setBackCameraResolution(Context ctx, String s){
        getPrefs(ctx).edit().putString(PREF_BACK_CAMERA_RESOLUTION, s).commit();
    }
    public static String getBackCameraResolution(Context ctx){
        return getPrefs(ctx).getString(PREF_BACK_CAMERA_RESOLUTION, null);
    }
    /* Device Id matches with database */
    /* To avoid descripency in model match */
    public static void setDeviceIdMatcheswithDatabase(Context ctx, boolean s){
        getPrefs(ctx).edit().putBoolean(PREF_DEVICE_MODEL_MATCH, s).commit();
    }

    public static Boolean IsDeviceModelMatch(Context ctx){
        return getPrefs(ctx).getBoolean(PREF_DEVICE_MODEL_MATCH, false);
    }

    public static void setDeviceImageUrl(Context ctx, String url){
        getPrefs(ctx).edit().putString(PREF_DEVICE_IMAGE_URL, url).commit();
    }

    public static String getDeviceImageUrl(Context ctx){
        return getPrefs(ctx).getString(PREF_DEVICE_IMAGE_URL, null);
    }

    public static void setAccExists(Context ctx, String username, boolean y) {
        if(username == null) return;
        getPrefs(ctx).edit().putBoolean(username, y).commit();
    }

    public static boolean getAccExists(Context ctx, String username) {
        if(username == null) return false;
        return getPrefs(ctx).getBoolean(username, false);
    }

    /* While checking on device data for search */
    public static void setDeviceCountSearch(Context ctx, int device_count) {
        getPrefs(ctx).edit().putInt(PREF_DEVICE_COUNT_SEARCH, device_count).commit();
    }

    public static int getDeviceCountSearch(Context ctx) {
        return getPrefs(ctx).getInt(PREF_DEVICE_COUNT_SEARCH, 0);
    }

    public static void setRandomNumber(Context ctx, String rand) {
        getPrefs(ctx).edit().putString(PREF_RANDOM_NUMBER, rand).commit();
    }

    public static String getRandomNumber(Context ctx) {
        return getPrefs(ctx).getString(PREF_RANDOM_NUMBER, null);
    }

    public static void MarkLocationDialogShown(Context ctx, boolean value){
        getPrefs(ctx).edit().putBoolean(PREF_LOCATION_DIALOG_SHOWN, value).commit();
    }

    public static boolean getLocationDialogStatus(Context ctx){
        return getPrefs(ctx).getBoolean(PREF_LOCATION_DIALOG_SHOWN, false);
    }

    public static void setOverallHappiness(Context ctx, int value){
        getPrefs(ctx).edit().putInt(PREF_FEEDBACK_HAPPINESS, value).commit();
    }
    public static int getOverallHappiness(Context ctx){
        return getPrefs(ctx).getInt(PREF_FEEDBACK_HAPPINESS, 0);
    }

    public static void setCameraRating(Context ctx, int value){
        getPrefs(ctx).edit().putInt(PREF_FEEDBACK_CAMERA_RATING, value).commit();
    }
    public static int getCameraRating(Context ctx){
        return getPrefs(ctx).getInt(PREF_FEEDBACK_CAMERA_RATING, 0);
    }

    public static void setBatteryRating(Context ctx, int value){
        getPrefs(ctx).edit().putInt(PREF_FEEDBACK_BATTERY_RATING, value).commit();
    }
    public static int getBatteryRating(Context ctx){
        return getPrefs(ctx).getInt(PREF_FEEDBACK_BATTERY_RATING, 0);
    }

    public static void setPerformanceRating(Context ctx, int value){
        getPrefs(ctx).edit().putInt(PREF_FEEDBACK_PERFORMANCE_RATING, value).commit();
    }
    public static int getPerformanceRating(Context ctx){
        return getPrefs(ctx).getInt(PREF_FEEDBACK_PERFORMANCE_RATING, 0);
    }

    public static void setBrandSupportRating(Context ctx, int value){
        getPrefs(ctx).edit().putInt(PREF_FEEDBACK_BRAND_SUPPORT, value).commit();
    }
    public static int getBrandSupportRating(Context ctx){
        return getPrefs(ctx).getInt(PREF_FEEDBACK_BRAND_SUPPORT, 0);
    }

    public static void MarkedFeedbackAvailable(Context ctx){
        getPrefs(ctx).edit().putBoolean(PREF_FEEDBACK_AVAILABLE, true).commit();
    }
    public static boolean getFeedbackAvailableStatus(Context ctx){
        return getPrefs(ctx).getBoolean(PREF_FEEDBACK_AVAILABLE, false);
    }

    public static void setCustomerComments(Context ctx, String comments){
        getPrefs(ctx).edit().putString(PREF_FEEDBACK_EXTRA_COMMENTS, comments).commit();
    }

    public static String getCustomerComments(Context ctx){
        return getPrefs(ctx).getString(PREF_FEEDBACK_EXTRA_COMMENTS, " ");
    }

    public static void setCustomerPros(Context ctx, String comments){
        getPrefs(ctx).edit().putString(PREF_FEEDBACK_EXTRA_PROS, comments).commit();
    }

    public static String getCustomerPros(Context ctx){
        return getPrefs(ctx).getString(PREF_FEEDBACK_EXTRA_PROS, " ");
    }
    public static void setCustomerCons(Context ctx, String comments){
        getPrefs(ctx).edit().putString(PREF_FEEDBACK_EXTRA_CONS, comments).commit();
    }

    public static String getCustomerCons(Context ctx){
        return getPrefs(ctx).getString(PREF_FEEDBACK_EXTRA_CONS, " ");
    }

    public static void setDevice_oldness_metric(Context ctx, int value){
        getPrefs(ctx).edit().putInt(PREF_DEVICE_OLDNESS_METRIC, value).commit();
    }
    public static int getDevice_oldness_metric(Context ctx){
        return getPrefs(ctx).getInt(PREF_DEVICE_OLDNESS_METRIC, 0);
    }

    public static void setDiscoverMinPrice(Context ctx, String minPrice){
        getPrefs(ctx).edit().putString(PREF_DISCOVER_MIN_PRICE, minPrice).commit();
    }

    public static String getDiscoverMinPrice(Context ctx){
        return getPrefs(ctx).getString(PREF_DISCOVER_MIN_PRICE, " ");
    }

    public static void setDiscoverMaxPrice(Context ctx, String minPrice){
        getPrefs(ctx).edit().putString(PREF_DISCOVER_MAX_PRICE, minPrice).commit();
    }

    public static String getDiscoverMaxPrice(Context ctx){
        return getPrefs(ctx).getString(PREF_DISCOVER_MAX_PRICE, " ");
    }

    public static void markSearchSyncImmediate(final Context context, boolean flag) {
        getPrefs(context).edit().putBoolean(PREF_SEARCH_SYNC_IMMEDIATE, flag).commit();
    }

    public static boolean getSearchSyncDataStatus(final Context context) {
        return getPrefs(context).getBoolean(PREF_SEARCH_SYNC_IMMEDIATE, false);
    }

    public static boolean getSplashScreenPref(Context ctx, boolean def) {
        return getPrefs(ctx).getBoolean(PREF_SPLASH_SCREEN, def);
    }

    public static void setSplashScreenPref(Context ctx, boolean value) {
        getPrefs(ctx).edit().putBoolean(PREF_SPLASH_SCREEN, value).commit();
    }

    public static void markUserPreference(final Context context) {
        getPrefs(context).edit().putBoolean(PREF_USER_SETTING_PREFERENCE, true).commit();
    }

    public static boolean getUserPreference(final Context context) {
        return getPrefs(context).getBoolean(PREF_USER_SETTING_PREFERENCE, false);
    }

    public static boolean getUserNotificationPreference(final Context context) {
        return getPrefs(context).getBoolean("notification_alerts", true);
    }

    public static void markPackageChangePreference(final Context context, boolean flag) {
        getPrefs(context).edit().putBoolean(PREF_PACKAGE_CHANGE_PREFERENCE, flag).commit();
    }

    public static boolean getPackageChangePreference(final Context context) {
        return getPrefs(context).getBoolean(PREF_PACKAGE_CHANGE_PREFERENCE, false);
    }
    public static void setAppsFlyerId(Context ctx, String s){
        if (s==null) return;
        getPrefs(ctx).edit().putString(PREF_APPSFLYER_UNIQUE_ID, s).commit();
    }
    public static String getAppsFlyerId(Context ctx){
        return getPrefs(ctx).getString(PREF_APPSFLYER_UNIQUE_ID, null);
    }

    public static void setReferralParams(Context ctx, String s){
        if (s==null) return;
        getPrefs(ctx).edit().putString(PREF_REFERRAL_PARAM, s).commit();
    }
    public static String getReferralParams(Context ctx){
        return getPrefs(ctx).getString(PREF_REFERRAL_PARAM, null);
    }

    public static void setTinyUrl(Context ctx, String s){
        if (s==null) return;
        getPrefs(ctx).edit().putString(PREF_TINY_URL, s).commit();
    }

    public static String getTinyUrl(Context ctx){
        return getPrefs(ctx).getString(PREF_TINY_URL, null);
    }
    public static void setPrefAdvertisingId(Context ctx, String code) {
        if (TextUtils.isEmpty(code)) return;
        getPrefs(ctx).edit().putString(PREF_ADVERTISING_ID, code).commit();
    }

    public static String getPrefAdvertisingId(Context ctx) {
        return getPrefs(ctx).getString(PREF_ADVERTISING_ID, null);
    }

    public static void saveDataWithKeyAndValue(Context context,String key,String value){
        SharedPreferences.Editor editor = context.getSharedPreferences("aina",context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDataFromKey(Context context,String key){
        SharedPreferences prefs = context.getSharedPreferences("aina",context.MODE_PRIVATE);
        String restoredText = prefs.getString(key, null);
        return restoredText;

    }
}