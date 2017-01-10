package fbla.mobileapp.app.dysp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenv2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screenv2);
        //Typeface custom = Typeface.createFromAsset(getAssets(), "fonts/lettergothic.ttf");
        Intent intent = new Intent(this,LoginActivity.class);
      //  getWindow().setBackgroundDrawableResource(R.drawable.background_splash);
        startActivity(intent);
        //finish();
        //Intent intent1 = new Intent(this, SignIn.class);
      // startActivity(intent1);
    }
}
