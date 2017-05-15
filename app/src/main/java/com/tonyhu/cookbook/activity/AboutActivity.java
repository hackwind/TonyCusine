
package com.tonyhu.cookbook.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tonyhu.cookbook.R;


/*Created by Tony on 2017/3/29.
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_about);
        getVersion();
        findViewById(R.id.reward).setOnClickListener(this);
    }

    private void getVersion() {
        try {
            PackageInfo pkg = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);

            String appName = pkg.applicationInfo.loadLabel(getPackageManager()).toString();
            String versionName = pkg.versionName;
            ((TextView)findViewById(R.id.about_version)).setText(appName + " v" + versionName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this,RewardActivity.class));
    }
}
