package com.chariotsolutions.nfc.plugin;

import android.content.Context;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * A Utility class that makes it easy to output Toast messages to the user without having to handle
 * cases where multiple toasts get queued, since the class only allows one toast per activity (and
 * changes the text when a new toast request is generated).
 *
 * @author Jordon Tolotti
 */
public class ToastUtility
{
    private static ToastUtility instance;
    private Map<Context, Toast> toasts;

    /**
     * Creates a new ToastUtility instance if it hasn't been already created, then return the instance
     * @return a singleton instance of the ToastUtility
     */
    public static ToastUtility getInstance()
    {
        if (instance == null)
        {
            instance = new ToastUtility();
        }

        return instance;
    }

    private ToastUtility()
    {
        toasts = new HashMap<Context, Toast>();
    }

    /**
     * Presents a toast to the user for Toast.LENGTH_LONG
     *
     * If a toast is currently being shown it will be replaced by sequence
     *
     * @param context context in which the toast will be presented
     * @param sequence the message to be seen by the user
     */
    public void showLongToast(Context context, CharSequence sequence)
    {
        showToast(context, Toast.LENGTH_LONG, sequence);
    }

    /**
     * Presents a toast to the user for Toast.LENGTH_SHORT
     *
     * If a toast is currently being shown it will be replaced by sequence
     *
     * @param context context in which the toast will be presented
     * @param sequence the message to be seen by the user
     */
    public void showShortToast(Context context, CharSequence sequence)
    {
        showToast(context, Toast.LENGTH_SHORT, sequence);
    }

    private void showToast(Context context, int length, CharSequence sequence)
    {
        Toast toast = getToastForActivity(context);

        if (toast != null)
        {
            toast.setText(sequence);
            toast.setDuration(length);
            toast.show();
        }
    }

    private Toast getToastForActivity(Context context)
    {
        if (!toasts.containsKey(context))
        {
            toasts.put(context, Toast.makeText(context, "", Toast.LENGTH_SHORT));
        }

        return toasts.get(context);
    }

}
