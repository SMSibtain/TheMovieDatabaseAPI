package com.android.themoviedatabaseapi.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.support.annotation.LayoutRes;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.themoviedatabaseapi.R;


public class DialogUtils {


    /*
    * custom dialog with two buttons

    * @params context       activity context
    * @params title         dialog title
    * @params message       dialog message
    * @params posBtn        positive button text
    * @params negBtn        negative button text
    * @params interaction   listener for buttons interaction
    *
    * Send 0 for not showing description in dialog.
    * */
    public static Dialog createSimpleOkDialog(Context context, String title, String message, final DialogButtonClick dialogButtonClick) {
        return createCustomDialog(context, R.layout.dialog_error_layout, 0, "Error", message, "Close", dialogButtonClick);
    }
    /*
    * custom dialog with single button

    * @params context       activity context
    * @params title         dialog title
    * @params message       dialog message
    * @params btn           button text
    * @params buttonClick   listener for buttons interaction
    *
    * send 0 for not showing description in dialog
    * */

    public static Dialog createCustomDialog(Context context,
                                            @LayoutRes int layoutResId,
                                            @LayoutRes int dlgDescRes, String title, String message,
                                            String btn, final DialogButtonClick buttonClick) {

        final Dialog dialog = new Dialog(context, R.style.customDialog);

        Rect displayRectangle = new Rect();
        Window window = dialog.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(layoutResId, null);
        customView.setMinimumWidth((int) (displayRectangle.width() * 0.90f));

        dialog.setCancelable(false);
        ColorDrawable color = new ColorDrawable(android.graphics.Color.TRANSPARENT);
        InsetDrawable insetDrawable = new InsetDrawable(color, 20);
        dialog.getWindow().setBackgroundDrawable(insetDrawable);
        dialog.setContentView(customView);

        TextView mTitle = (TextView) dialog.findViewById(R.id.dialog_title);
        TextView mMessage = (TextView) dialog.findViewById(R.id.dialog_msg);
        LinearLayout mButtonsLayout = (LinearLayout) dialog.findViewById(R.id.dialog_button_layout);
        TextView mPosBtn = (TextView) dialog.findViewById(R.id.dialog_pos_btn);
        TextView mNegBtn = (TextView) dialog.findViewById(R.id.dialog_neg_btn);
        View divider = (View) dialog.findViewById(R.id.dialog_button_divider);

        mTitle.setText(title);
        mMessage.setText(Html.fromHtml(message), TextView.BufferType.SPANNABLE);
        mPosBtn.setText(btn);

        mPosBtn.setLayoutParams(new LinearLayout.LayoutParams(0, convertDPToPixel(context, 40), 2));
        mNegBtn.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);

        mPosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick.buttonClick(dialog);
            }
        });

        return dialog;

    }


    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    /*when dialog has only an "OK" button*/
    public interface DialogButtonClick {
        void buttonClick(DialogInterface dialogInterface);
    }

    private static int convertDPToPixel(Context context, int dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);
        return pixels;
    }
}
