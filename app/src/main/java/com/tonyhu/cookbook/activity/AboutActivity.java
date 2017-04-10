
package com.tonyhu.cookbook.activity;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.tonyhu.cookbook.R;


/*Created by Tony on 2017/3/29.
 */
public class AboutActivity extends BaseActivity  {

    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_about);
        getVersion();

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


}
