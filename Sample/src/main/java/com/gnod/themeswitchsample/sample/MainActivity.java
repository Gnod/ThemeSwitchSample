package com.gnod.themeswitchsample.sample;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gnod.themeswitchsample.sample.util.SystemUiHider;


public class MainActivity extends Activity {

    private boolean isInTheme = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final View contentView = findViewById(R.id.tv_content);
        final View background = findViewById(R.id.background);
        final TextView contentText = (TextView) findViewById(R.id.tv_content);
        final Button contentBtn = (Button) findViewById(R.id.change_button);

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isInTheme) {

                    String remotePkg = "com.gnod.themepkg.themepkg";
                    try {
                        Context remoteContext = createPackageContext(remotePkg, CONTEXT_IGNORE_SECURITY);
                        Resources remoteRes = remoteContext.getResources();

                        int bgColor = remoteRes.getColor(remoteRes.getIdentifier("color_bg", "color", remotePkg));
                        background.setBackgroundColor(bgColor);

                        CharSequence content = remoteRes.getText(remoteRes.getIdentifier("change_content", "string", remotePkg));
                        contentText.setText(content);

                        CharSequence btnText = remoteRes.getText(remoteRes.getIdentifier("change_button", "string", remotePkg));
                        contentBtn.setText(btnText);

                        isInTheme = true;

                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    background.setBackgroundColor(getResources().getColor(R.color.color_bg));
                    contentText.setText(R.string.change_content);
                    contentBtn.setText(R.string.change_button);
                    isInTheme = false;
                }

            }
        });

    }

}
