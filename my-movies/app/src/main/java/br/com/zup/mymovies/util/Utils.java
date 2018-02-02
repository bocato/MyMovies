package br.com.zup.rwwhitelabel.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import br.com.zup.realwaveservice.RWGenericResponse;
import br.com.zup.realwaveservice.catalog.RWValidity;
import br.com.zup.realwaveservice.customer.PersonTypeEnum;
import br.com.zup.rwwhitelabel.R;
import br.com.zup.rwwhitelabel.WhiteLabelApplication;
import br.com.zup.rwwhitelabel.model.customer.Avatar;

import static br.com.zup.rwwhitelabel.model.customer.RegisterCustomerRequest.REF_ID_AVATAR;

/**
 * Created by rafaelneiva on 03/11/17.z
 */

public class Utils {

    public static final String SP_CUSTOMER_INFO = "customer-info-key";
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }
        return (netInfo != null && netInfo.isConnected());
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static String formatValue(final String str) {
        BigDecimal bigDecimal = new BigDecimal(str.replace(",", "."));
        return formatValue(bigDecimal);
    }

    public static String formatUnit(BigDecimal value) {
        return String.valueOf(Utils.isWholeNumber(value) ? value.intValue() : value);
    }

    public static boolean isWholeNumber(BigDecimal number) {
        return number.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
    }

    public static String formatValue(BigDecimal value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(value.doubleValue());
    }

    public static String formatValue(Double value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(value);
    }

    public static void expandView(final View v) {
        expandView(v, null);
    }

    public static void expandView(final View v, final Animation.AnimationListener animationListener) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms + 100
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density) + 100);
        if (animationListener != null) {
            a.setAnimationListener(animationListener);
        }
        v.startAnimation(a);
    }

    public static void collapseView(final View v) {
        collapseView(v, null);
    }

    public static void collapseView(final View v, final Animation.AnimationListener animationListener) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms + 100
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density) + 100);
        if (animationListener != null) {
            a.setAnimationListener(animationListener);
        }
        v.startAnimation(a);
    }

    /**
     * @param ctx      Context
     * @param jsonFile The file on assets
     * @return
     */
    public static String loadJSONFromAsset(Context ctx, String jsonFile) {
        String json;
        try {
            InputStream is = ctx.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static int convertSpToPixels(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }


    /**
     * @param ctx The Context
     * @param msg The message on dialog
     */
    public static void showSimpleDialog(Context ctx, String title, String msg) {
        if (ctx != null && ctx instanceof Activity) {

            new AlertDialog.Builder(ctx)
                    .setTitle(title)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss())
                    .create().show();
        }
    }

    public static void showErrorDialog(Context ctx, RWGenericResponse response) {
        Utils.showSimpleDialog(ctx, ctx.getString(R.string.default_service_error_title), response != null && response.getMessage() != null ? response.getMessage() : ctx.getString(R.string.default_error_message));
    }

    public static String getCountry() {
        return "Brasil"; // fixme
    }

    public static PersonTypeEnum getPersonType() {
        return PersonTypeEnum.INDIVIDUAL; // fixme
    }

    public static boolean alreadyInList(List<?> list, Object o) {
        for (Object object : list) {
            if (o.equals(object)) return true;
        }

        return false;
    }

    public static String formatPeriod(RWValidity validity) {
        Resources rs = WhiteLabelApplication.getInstance().getResources();
        switch (validity.getPeriod()) {
            case DAY:
                return validity.getDuration() == 1 ? rs.getString(R.string.validity_day_singular) : rs.getString(R.string.validity_day_plural);
            case HOUR:
                return validity.getDuration() == 1 ? rs.getString(R.string.validity_hour_singular) : rs.getString(R.string.validity_hour_plural);
            case MONTH:
                return validity.getDuration() == 1 ? rs.getString(R.string.validity_month_singular) : rs.getString(R.string.validity_month_plural);
        }

        return "";
    }

    /**
     * @param s        string to be completed
     * @param fillWith complete with
     * @param n        number of total chars
     * @return
     */
    public static String leftPad(String s, char fillWith, int n) {
        StringBuilder sb = new StringBuilder(s);
        int charsToGo = n - sb.length();
        while (charsToGo > 0) {
            sb.insert(0, fillWith);
            charsToGo--;
        }

        return sb.toString();
    }

    /**
     * @param month The month number starting count in 0
     * @return The name of respective month number
     */
    public static String getMonthName(Context context, int month) {
        String[] monthName = context.getResources().getStringArray(R.array.months);
        return monthName[month];
    }

    /**
     * @return The day
     */
    public static int getCurrentDayOfMonth() {
        int day;
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * @return The Month
     */
    public static int getCurrentMonth() {
        int month;
        Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH);
        return month;
    }

    /**
     * @return The year
     */
    public static int getCurrentYear() {
        int year;
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        return year;
    }

    public static Bitmap getBitmapFromBase64(String base64Hash) {
        byte[] decodedString = Base64.decode(base64Hash, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static String getBase64FromBitmap(Bitmap bmp) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /**
     * @param email Email
     * @return True if is a valid type of email
     */
    public static boolean validateEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isPackageInstalled(String packageName) {
        Context context = WhiteLabelApplication.getInstance();
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void shareByPackage(Context ctx, String message, String pack) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        if (pack != null)
            sendIntent.setPackage(pack);

        try {
            ctx.startActivity(sendIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Utils.showSimpleDialog(ctx, null, ctx.getString(R.string.error_no_package));
        }
    }

    public static void shareByEmail(Context ctx, String message) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "White Label Invite");
        sendIntent.setType("message/rfc822");
        ctx.startActivity(sendIntent);
    }

    /**
     * @return return the resource id of the Avatar chosen by the customer
     */
    public static int getLoggedCustomerAvatar() {
        Map<String, Object> cFields = SingletonCustomer.getInstance().getCustomer().getCustomFields();
        if (cFields != null && cFields.containsKey(REF_ID_AVATAR)) {
            try {
                return Avatar.getAvatarByValue((String) cFields.get(REF_ID_AVATAR)).getResId();
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * @return true if a user is logged with valid session, false otherwise
     */
    public static boolean isLoggedIn() {
        return SingletonCustomer.getInstance().getCredentials() != null;
    }


    public static final String getHours(final Date date, final String format) {
        SimpleDateFormat formatHourOut = new SimpleDateFormat(format, Locale.getDefault());
        formatHourOut.setTimeZone(TimeZone.getDefault());
        return formatHourOut.format(date);
    }

    public static final String formatDate(final Date date, final String format) {
        String formattedDate = "";
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    public static final Date getDateFromString(String date, String format) {
        DateFormat df1 = new SimpleDateFormat(format);
        df1.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return df1.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static final Date getDateFromStringTimeZone(String date, String format) {
        DateFormat df1 = new SimpleDateFormat(format, Locale.getDefault());
        df1.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return df1.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
