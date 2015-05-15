package in.sarnathkj.silentkiller;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity {

    private boolean myPhoneIsSilent;
    private AudioManager myAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("DEV-LOG","INSIDE onCreate");
        checkIfPhoneIsSilent();
        toggleUI();
    }

    public void silentKillerToggle(View view) {
        Log.i("DEV-LOG","INSIDE silentKillerToggle");
        if(myPhoneIsSilent) {
            //Change Back to Normal Mode
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            myPhoneIsSilent = false;
        } else {
            //Change to Silent Mode
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            myPhoneIsSilent = true;
        }
        toggleUI();
    }

    /**
     * Check if the phone is currently in Silent Mode.
     */
    private void checkIfPhoneIsSilent() {
        Log.i("DEV-LOG","INSIDE checkIfPhoneIsSilent");
        myAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int ringerMode = myAudioManager.getRingerMode();
        if(ringerMode==AudioManager.RINGER_MODE_SILENT || ringerMode==AudioManager.RINGER_MODE_VIBRATE){
            myPhoneIsSilent = true;
        } else {
            myPhoneIsSilent = false;
        }
    }

    /**
     * Toggle UI images from Silent to Normal and Vice Versa.
     */
    private void toggleUI() {
        Log.i("DEV-LOG","INSIDE toggleUI");
        ImageView myImageView = (ImageView) findViewById(R.id.phone_icon);
        Drawable newPhoneIcon;

        if(myPhoneIsSilent) {
            newPhoneIcon = getResources().getDrawable(R.drawable.red);
        } else {
            newPhoneIcon = getResources().getDrawable(R.drawable.green);
        }

        myImageView.setImageDrawable(newPhoneIcon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        Log.i("DEV-LOG","INSIDE RESUME");
        super.onResume();
        checkIfPhoneIsSilent();
        toggleUI();
    }
}
