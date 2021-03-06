package lafkym.tabstryagain;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.w3c.dom.Text;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.androidplot.xy.*;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tabTitles = new ArrayList<>();
    private MyPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private WebSocketClient mWebSocketClient;
    private Handler handler;
    private Boolean topUpCheck = false;

    public static String[] messages;

    //Switches to true after messages[] has values
    public static Boolean a = false;

    private void updateGUIMethod(){

        final Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        //Connect variable names to activity IDs
        //Main page
        TextView bC = (TextView)findViewById(R.id.bC);
        TextView lT = (TextView)findViewById(R.id.lT);
        TextView bM = (TextView)findViewById(R.id.bM);
        TextView nS = (TextView)findViewById(R.id.nS);
        TextView uX = (TextView)findViewById(R.id.uX);
        TextView uY = (TextView)findViewById(R.id.uY);
        TextView b2sEff = (TextView)findViewById(R.id.b2sEff);
        TextView booCurr = (TextView)findViewById(R.id.booCurr);
        TextView ls1 = (TextView)findViewById(R.id.ls1);
        TextView ls2 = (TextView)findViewById(R.id.ls2);
        TextView xSize = (TextView)findViewById(R.id.xSize);
        TextView ySize = (TextView)findViewById(R.id.ySize);
        TextView xPos = (TextView)findViewById(R.id.xPos);
        TextView yPos = (TextView)findViewById(R.id.yPos);
        TextView xSTD = (TextView)findViewById(R.id.xSTD);
        TextView ySTD = (TextView)findViewById(R.id.ySTD);
        //Linac
        TextView k1v = (TextView)findViewById(R.id.k1V);
        TextView k2v = (TextView)findViewById(R.id.k2V);
        TextView gV = (TextView)findViewById(R.id.gV);
        TextView linS = (TextView)findViewById(R.id.linS);
        TextView linH = (TextView)findViewById(R.id.linH);
        TextView linV = (TextView)findViewById(R.id.linV);
        TextView linQ = (TextView)findViewById(R.id.linQ);
        TextView ltbD = (TextView)findViewById(R.id.ltbD);
        TextView ltbH = (TextView)findViewById(R.id.ltbH);
        TextView ltbV = (TextView)findViewById(R.id.ltbV);
        TextView ltbQ = (TextView)findViewById(R.id.ltbQ);

        //Booster
        TextView borf = (TextView)findViewById(R.id.borf);
        TextView freqdif = (TextView)findViewById(R.id.freqdif);
        TextView rmps = (TextView)findViewById(R.id.rmps);
        TextView booD = (TextView)findViewById(R.id.booD);
        TextView booQ = (TextView)findViewById(R.id.booQ);
        TextView booS = (TextView)findViewById(R.id.booS);
        TextView booH = (TextView)findViewById(R.id.booH);
        TextView booV = (TextView)findViewById(R.id.booV);
        TextView btsD = (TextView)findViewById(R.id.btsD);
        TextView btsQ = (TextView)findViewById(R.id.btsQ);
        TextView btsV = (TextView)findViewById(R.id.btsV);

        //Storage Ring
        ImageButton sh1 = (ImageButton)findViewById(R.id.imageButton);
        ImageButton sh2 = (ImageButton)findViewById(R.id.imageButton2);
        ImageButton sh3 = (ImageButton)findViewById(R.id.imageButton3);
        ImageButton sh4 = (ImageButton)findViewById(R.id.imageButton4);
        ImageButton sh5 = (ImageButton)findViewById(R.id.imageButton5);
        ImageButton sh6 = (ImageButton)findViewById(R.id.imageButton6);
        ImageButton sh7 = (ImageButton)findViewById(R.id.imageButton7);
        ImageButton sh8 = (ImageButton)findViewById(R.id.imageButton8);
        ImageButton sh9 = (ImageButton)findViewById(R.id.imageButton9);
        ImageButton sh10 = (ImageButton)findViewById(R.id.imageButton10);

        TextView mx1 = (TextView)findViewById(R.id.mx1Gap);
        TextView xfm = (TextView)findViewById(R.id.xfmGap);
        TextView imbl = (TextView)findViewById(R.id.imblField);
        TextView xas = (TextView)findViewById(R.id.xasGap);
        TextView swax = (TextView)findViewById(R.id.swaxGap);
        TextView sxr = (TextView)findViewById(R.id.sxrGap);
        TextView ir = (TextView)findViewById(R.id.irGap);

        //Check and make sure we haven't dropped out of topup.  If we have, vibrate phone

        if (messages[55].contains("UserBeam Top Up") & messages[3].contains("Top Up") & topUpCheck){
            topUpCheck = true;
        }else if (messages[55].contains("UserBeam Top Up") & !messages[3].contains("Top Up") & topUpCheck){
            topUpCheck = false;
            v.vibrate(1000);
            /*I'll have to check and see if this works later.
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("SLSA Overview")
                            .setContentText("TopUp Dropout!");

            // Sets an ID for the notification
            int mNotificationId = 001;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder.build());
            */
        }else if (messages[55].contains("UserBeam Top Up") & !messages[3].contains("Top Up") & !topUpCheck){
            topUpCheck = false;
        }else if (messages[55].contains("UserBeam Top Up") & messages[3].contains("Top Up") & !topUpCheck) {
            topUpCheck = true;
        }

        //Haven't found a way to change the tab title text dynamically
                        /*
                        if (messages[0].toString().contains("bad1")){
                            tabLayout.
                            tv.setText("hello");
                        }else{
                            //tv.setTextColor(Color.GREEN);
                            tv.setText("sup");
                        }

                        if (messages[0].toString().contains("bad2")){
                            tabLayout.
                        }else{
                            tabTitles.set(2, "boster");
                            Log.d("error",tabTitles.toString());
                        }*/
        //First conditional parses the returning message for which tab to update
        if ((pagerAdapter.getCurrentFragment().toString().toLowerCase().contains("fragment1"))) {
            Log.d("UPDATING","Updating Main Tab");

            //Some PVs change colors when they're alarming, etc...
            bC.setText(messages[1]);
            lT.setText(messages[2]);
            bM.setText(messages[3]);
            nS.setText(messages[4]);
            uX.setText(messages[5]);
            uY.setText(messages[6]);
            b2sEff.setText(messages[7]);
            booCurr.setText(messages[8]);
            ls1.setText(messages[9]);
            ls2.setText(messages[10]);
            xSize.setText(messages[11]);
            ySize.setText(messages[12]);
            xPos.setText(messages[13]);
            yPos.setText(messages[14]);
            xSTD.setText(messages[15]);
            ySTD.setText(messages[16]);

            if (Double.parseDouble(bC.getText().toString()) > 199.9){
                bC.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(bC.getText().toString()) <= 199.9) &&  (Double.parseDouble(bC.getText().toString()) > 180)) {
                bC.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.warn_PV));
            } else{
                bC.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }

            if (Double.parseDouble(lT.getText().toString()) > 24){
                lT.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(lT.getText().toString()) <= 24) &&  (Double.parseDouble(lT.getText().toString()) > 20)) {
                lT.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.warn_PV));
            } else{
                lT.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }

            if (Double.parseDouble(ls1.getText().toString()) > 90){
                ls1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(lT.getText().toString()) <= 85) &&  (Double.parseDouble(lT.getText().toString()) > 60)) {
                ls1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.warn_PV));
            } else{
                ls1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }

            if (Double.parseDouble(ls2.getText().toString()) > 90){
                ls2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(lT.getText().toString()) <= 85) &&  (Double.parseDouble(lT.getText().toString()) > 60)) {
                ls2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.warn_PV));
            } else{
                ls2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }

            if (Double.parseDouble(xSize.getText().toString()) < 320){
                xSize.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(xSize.getText().toString()) >= 320) &&  (Double.parseDouble(xSize.getText().toString()) < 335)) {
                xSize.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.warn_PV));
            } else{
                xSize.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }

            if (Double.parseDouble(ySize.getText().toString()) < 280){
                ySize.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(ySize.getText().toString()) >= 280) &&  (Double.parseDouble(ySize.getText().toString()) < 290)) {
                ySize.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.warn_PV));
            } else{
                ySize.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }

            if (Double.parseDouble(xSTD.getText().toString()) < .5){
                xSTD.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(xSTD.getText().toString()) >= .5) &&  (Double.parseDouble(xSTD.getText().toString()) < 1)) {
                xSTD.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.warn_PV));
            } else{
                xSTD.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }

            if (Double.parseDouble(ySTD.getText().toString()) < 1){
                ySTD.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(ySTD.getText().toString()) >= 1) &&  (Double.parseDouble(ySTD.getText().toString()) < 1.5)) {
                ySTD.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.warn_PV));
            } else{
                ySTD.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }

            //Need to add a "+" if the value for the position is positive
            if (Double.parseDouble(messages[14].toString()) > 0){
                yPos.setText("+"+messages[14]);
            }else{
                yPos.setText(messages[14]);
            }
            if (Double.parseDouble(xPos.getText().toString()) > 0){
                xPos.setText("+"+messages[13]);
            }else{
                xPos.setText(messages[13]);
            }

            //Parse it for the Linac tab
        } else if ((pagerAdapter.getCurrentFragment().toString().toLowerCase().contains("fragment2"))) {
            Log.d("UPDATING","Updating Linac Tab");
            k1v.setText(messages[17]);
            k2v.setText(messages[18]);
            gV.setText(messages[19]);

            //Set PV colors if they're not alarming
            if (Double.parseDouble(k1v.getText().toString()) < 34){
                k1v.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            } else{
                k1v.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            }
            if (Double.parseDouble(k2v.getText().toString()) < 34){
                k2v.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            } else{
                k2v.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            }
            if (Double.parseDouble(gV.getText().toString()) < 89){
                gV.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            } else{
                gV.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            }
            if (messages[20].equals("0")){
                linS.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                linS.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[21].equals("0")){
                linH.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                linH.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[22].equals("0")){
                linV.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                linV.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[23].equals("0")){
                linQ.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                linQ.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[24].equals("3")){
                ltbD.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                ltbD.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[25].equals("2")){
                ltbH.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                ltbH.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[26].equals("4")){
                ltbV.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                ltbV.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[27].equals("11")){
                ltbQ.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                ltbQ.setBackgroundResource(R.drawable.button_bg_bad);
            }
        }
        else if ((pagerAdapter.getCurrentFragment().toString().toLowerCase().contains("fragment3"))) {
            Log.d("UPDATING", "Updating Booster Tab");
            freqdif.setText(messages[29]);

            if (messages[28].equals("1")) {
                borf.setText("Good");
                //borf.setTextColor(getResources().getColor(R.color.good_PV));
                borf.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else {
                borf.setText("Bad");
                borf.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }
            if (messages[30].equals("16")) {
                rmps.setText(("Good"));
                rmps.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.good_PV));
            } else {
                rmps.setText(("Bad"));
                rmps.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.bad_PV));
            }
            if (messages[31].equals("3")) {
                booD.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booD.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[32].equals("2")) {
                booQ.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booQ.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[33].equals("2")) {
                booS.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booS.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[34].equals("12")) {
                booH.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booH.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[35].equals("12")) {
                booV.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booV.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[36].equals("6")) {
                btsD.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                btsD.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[37].equals("12")) {
                btsQ.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                btsQ.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[38].equals("5")) {
                btsV.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                btsV.setBackgroundResource(R.drawable.button_bg_bad);
            }
        }
        else if ((pagerAdapter.getCurrentFragment().toString().toLowerCase().contains("fragment4"))) {
            Log.d("UPDATING:","Updating SR Tab");

            mx1.setText(messages[49]);
            xfm.setText(messages[50]);
            imbl.setText(messages[51]);
            xas.setText(messages[52]);
            swax.setText(messages[53]);
            sxr.setText(messages[54]);

            if (messages[39].equals("2")){
                sh1.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                sh1.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[40].equals("3")){
                sh2.setBackgroundResource(R.drawable.button_bg_good);
                ir.setText("Inserted");
            }else if(messages[40].equals("1")){
                sh2.setBackgroundResource(R.drawable.button_bg_yellow);
                ir.setText("Moving");
            }else{
                sh2.setBackgroundResource(R.drawable.button_bg_bad);
                ir.setText("Out");
            }
            if (messages[41].equals("1")){
                sh3.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                sh3.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[42].equals("1")){
                sh4.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                sh4.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[43].equals("1")){
                sh5.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                sh5.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[44].equals("1")){
                sh6.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                sh6.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[45].equals("1")){
                sh7.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                sh7.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[46].equals("2")){
                sh8.setBackgroundResource(R.drawable.button_bg_bad);
            }else{
                sh8.setBackgroundResource(R.drawable.button_bg_good);
            }
            if (messages[47].equals("1")){
                sh9.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                sh9.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (messages[48].equals("1")){
                sh10.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                sh10.setBackgroundResource(R.drawable.button_bg_bad);
            }
        }
    }

    //Main runnable to update the gui
    Runnable updateGUI = new Runnable(){
        public void run(){
            try{
                mWebSocketClient.send("everything");
            }catch(Exception e){

                //Toast.makeText(MainActivity.this, "Connection to server lost!", Toast.LENGTH_SHORT).show();
                //Need to make it smart enough to know when the connection has been lost and reconnected.
                Log.d("debug","Unable to connect");

                try {
                    connectWebSocket();
                }catch(Exception ee){
                    Log.d("debug","Tried and failed to connect");
                }
                //connectWebSocket();
            }
            handler.postDelayed(this,1000);  // Run this again in 1 second

        }

    };

    //Control room computer IP: 10.6.11.15
    //ioc 99 IP: 10.6.100.199
    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://10.6.11.15:6000/");
                  } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("WEBSOCKET", "Opened");
            }

            //Function gets the server's string and splits it and then activates the updateGUIMethod that updates the gui
            @Override
            public void onMessage(String s) {
                final String message = s;
                messages = message.split("::");
                //Let the other views display correctly upon opening
                a = true;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateGUIMethod();
                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mWebSocketClient.close();
        handler.removeCallbacks(updateGUI);
    }

    /*
    @Override
    protected void onPause(){
        super.onPause();

        mWebSocketClient.close();
        handler.removeCallbacks(updateGUI);
    }

    @Override
    protected void onResume(){
        super.onResume();

        connectWebSocket();
        handler.postDelayed(updateGUI,1000);
    }
    */

    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.ic_launcher);

        handler = new Handler();

        fragmentList.add(Fragment1.newInstance());
        fragmentList.add(Fragment2.newInstance());
        fragmentList.add(Fragment3.newInstance());
        fragmentList.add(Fragment4.newInstance());

        tabTitles.add("Main");
        tabTitles.add("Linac");
        tabTitles.add("Booster");
        tabTitles.add("Storage Ring");



        // Set a toolbar which will replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup the viewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Setup the Tabs
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        // This method ensures that tab selection events update the ViewPager and page changes update the selected tab.
        tabLayout.setupWithViewPager(viewPager);

        connectWebSocket();
        handler.postDelayed(updateGUI,1000);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        private Fragment mCurrentFragment;

        public Fragment getCurrentFragment(){
            return mCurrentFragment;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((Fragment) object);
            }
            //handler.removeCallbacks(updateGUI);
            super.setPrimaryItem(container, position, object);
            //handler.postDelayed(updateGUI,100);
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            return fragmentList.get(pos);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        // This is called when notifyDataSetChanged() is called. Without this, getItem() is not triggered
        @Override
        public int getItemPosition(Object object) {
            // refresh all fragments when data set changed
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles.get(position);
        }

    }
}