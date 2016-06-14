package edu.hsl.myappnewsday.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/12.
 */
public class ShareUtil {
    private static final String TAG = "ShareUtil";

    public static void shareTextWithImage(Context context, String subject, String title, String
            text, String imageUrl, String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imageUrl != null && !imageUrl.equals("")) {
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(imageUrl));
        } else {
            intent.setType("text/plain");
        }
        if (subject != null)
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (text != null)
            intent.putExtra(Intent.EXTRA_TEXT, url);
        if (title != null)
            intent.putExtra(Intent.EXTRA_TITLE, title);
        if (url != null)
//            intent.setData(Uri.parse(url));
//            intent.putExtra("url",url);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "请选择"));
        Log.d(TAG, "shareTextWithImage: " + intent.toString());
    }
}
