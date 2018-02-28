package io.github.akndmr.popvieapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import io.github.akndmr.popvieapp.R;

/**
 * Created by AKIN on 25.02.2018.
 */

public class DialogUtil {

    private static AlertDialog dialog;
    private static AlertDialog dialogWithButtons;

    public static void showDialogWith(final Context context, int resId, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(customView);

        ImageView dialogImage = customView.findViewById(R.id.iv_dialog_image);
        ImageView dialogClose = customView.findViewById(R.id.iv_dialog_close);
        TextView dialogText = customView.findViewById(R.id.tv_dialog_text);
        Picasso.with(context).load(resId).into(dialogImage);
        dialogText.setText(message);

        dialog = builder.create();

        dialogImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Activity activity = (Activity) context;
                activity.recreate();
            }
        });

        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void closeDialog() {
        dialog.dismiss();
    }

    // To be implemented on 2nd Stage
    public static void showDialogWithButtons(final Context context, int resId, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.custom_dialog_with_buttons, null);
        builder.setView(customView);

        ImageView dialogImage = customView.findViewById(R.id.iv_dialog_image);
        ImageView dialogClose = customView.findViewById(R.id.iv_dialog_close);
        TextView dialogText = customView.findViewById(R.id.tv_dialog_text);
        Button dialogButtonStay = customView.findViewById(R.id.btn_stay);
        Button dialogButtonExit = customView.findViewById(R.id.btn_exit);
        Picasso.with(context).load(resId).into(dialogImage);
        dialogText.setText(message);

        dialogWithButtons = builder.create();

        dialogButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogWithButtons.dismiss();
                //To be implemented
            }
        });

        dialogButtonStay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogWithButtons.dismiss();
            }
        });

        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogWithButtons.dismiss();
            }
        });

        dialogWithButtons.show();
    }
}
