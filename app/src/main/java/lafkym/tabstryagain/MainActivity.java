package lafkym.tabstryagain;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import java.util.Arrays;
import android.graphics.DashPathEffect;


public class MainActivity extends AppCompatActivity implements TabsListener {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tabTitles = new ArrayList<>();
    private MyPagerAdapter pagerAdapter;
    private TabLayout tabLayout;

    private WebSocketClient mWebSocketClient;
    private String TAG = this.getClass().getSimpleName();
    private Handler handler;

    public String alldata[];



    //Main runnable to update the gui
    Runnable updateGUI = new Runnable(){
        public void run(){
            try{
                mWebSocketClient.send("everything");
            }catch(Exception e){
                Log.d("debug","Unable to connect");
            }
            handler.postDelayed(this,1000);  // Run this again in 1 second

        }

    };

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://10.6.0.138:6000");
                  } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("WEBSOCKET", "Opened");
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                final String messages[] = message.split("::");
                //final Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

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

                        //Change tab colors for Linac/Booster if there's a problem

                        if (messages[0].toString().contains("bad1")){
                            tabTitles.set(1,"Linac ALERT");
                        }else{
                            tabTitles.set(1,"Linac");
                        }

                        if (messages[0].toString().contains("bad2")){
                            tabTitles.set(2,"Booster ALERT");
                        }else{
                            tabTitles.set(2,"Booster");
                        }
                        //First conditional parses the returning message for which tab to update
                        if ((pagerAdapter.getCurrentFragment().toString().toLowerCase().contains("fragment1"))) {
                            Log.d("UPDATING","Updating Main Tab");

                            //Some PVs change colors when they're alarming, etc...
                            bC.setText(messages[1]);
                            if (Double.parseDouble(bC.getText().toString()) > 199.9){
                                bC.setTextColor(getResources().getColor(R.color.good_PV));
                            } else if ((Double.parseDouble(bC.getText().toString()) <= 199.9) &&  (Double.parseDouble(bC.getText().toString()) > 180)) {
                                bC.setTextColor(getResources().getColor(R.color.warn_PV));
                            } else{
                                bC.setTextColor(getResources().getColor(R.color.bad_PV));
                            }

                            lT.setText(messages[2]);
                            if (Double.parseDouble(lT.getText().toString()) > 24){
                                lT.setTextColor(getResources().getColor(R.color.good_PV));
                            } else if ((Double.parseDouble(lT.getText().toString()) <= 24) &&  (Double.parseDouble(lT.getText().toString()) > 20)) {
                                lT.setTextColor(getResources().getColor(R.color.warn_PV));
                            } else{
                                lT.setTextColor(getResources().getColor(R.color.bad_PV));
                            }

                            ls1.setText(messages[9]);
                            if (Double.parseDouble(ls1.getText().toString()) > 90){
                                ls1.setTextColor(getResources().getColor(R.color.good_PV));
                            } else if ((Double.parseDouble(lT.getText().toString()) <= 85) &&  (Double.parseDouble(lT.getText().toString()) > 60)) {
                                ls1.setTextColor(getResources().getColor(R.color.warn_PV));
                            } else{
                                ls1.setTextColor(getResources().getColor(R.color.bad_PV));
                            }

                            ls2.setText(messages[10]);
                            if (Double.parseDouble(ls2.getText().toString()) > 90){
                                ls2.setTextColor(getResources().getColor(R.color.good_PV));
                            } else if ((Double.parseDouble(lT.getText().toString()) <= 85) &&  (Double.parseDouble(lT.getText().toString()) > 60)) {
                                ls2.setTextColor(getResources().getColor(R.color.warn_PV));
                            } else{
                                ls2.setTextColor(getResources().getColor(R.color.bad_PV));
                            }

                            xSize.setText(messages[11]);
                            if (Double.parseDouble(xSize.getText().toString()) < 320){
                                xSize.setTextColor(getResources().getColor(R.color.good_PV));
                            } else if ((Double.parseDouble(xSize.getText().toString()) >= 320) &&  (Double.parseDouble(xSize.getText().toString()) < 335)) {
                                xSize.setTextColor(getResources().getColor(R.color.warn_PV));
                            } else{
                                xSize.setTextColor(getResources().getColor(R.color.bad_PV));
                            }

                            ySize.setText(messages[12]);
                            if (Double.parseDouble(ySize.getText().toString()) < 280){
                                ySize.setTextColor(getResources().getColor(R.color.good_PV));
                            } else if ((Double.parseDouble(ySize.getText().toString()) >= 280) &&  (Double.parseDouble(ySize.getText().toString()) < 290)) {
                                ySize.setTextColor(getResources().getColor(R.color.warn_PV));
                            } else{
                                ySize.setTextColor(getResources().getColor(R.color.bad_PV));
                            }

                            xSTD.setText(messages[15]);
                            if (Double.parseDouble(xSTD.getText().toString()) < .5){
                                xSTD.setTextColor(getResources().getColor(R.color.good_PV));
                            } else if ((Double.parseDouble(xSTD.getText().toString()) >= .5) &&  (Double.parseDouble(xSTD.getText().toString()) < 1)) {
                                xSTD.setTextColor(getResources().getColor(R.color.warn_PV));
                            } else{
                                xSTD.setTextColor(getResources().getColor(R.color.bad_PV));
                            }

                            ySTD.setText(messages[16]);
                            if (Double.parseDouble(ySTD.getText().toString()) < 1){
                                ySTD.setTextColor(getResources().getColor(R.color.good_PV));
                            } else if ((Double.parseDouble(ySTD.getText().toString()) >= 1) &&  (Double.parseDouble(ySTD.getText().toString()) < 1.5)) {
                                ySTD.setTextColor(getResources().getColor(R.color.warn_PV));
                            } else{
                                ySTD.setTextColor(getResources().getColor(R.color.bad_PV));
                            }

                            //Neutral PVs don't change colors
                            bM.setText(messages[3]);
                            nS.setText(messages[4]);
                            uX.setText(messages[5]);
                            uY.setText(messages[6]);
                            b2sEff.setText(messages[7]);
                            booCurr.setText(messages[8]);
                            xPos.setText(messages[13]);
                            yPos.setText(messages[14]);
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
                                k1v.setTextColor(getResources().getColor(R.color.bad_PV));
                            } else{
                                k1v.setTextColor(getResources().getColor(R.color.good_PV));
                            }
                            if (Double.parseDouble(k2v.getText().toString()) < 34){
                                k2v.setTextColor(getResources().getColor(R.color.bad_PV));
                            } else{
                                k2v.setTextColor(getResources().getColor(R.color.good_PV));
                            }
                            if (Double.parseDouble(gV.getText().toString()) < 89){
                                gV.setTextColor(getResources().getColor(R.color.bad_PV));
                            } else{
                                gV.setTextColor(getResources().getColor(R.color.good_PV));
                            }
                            if (messages[20].toString().equals("0")){
                                linS.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            }else{
                                linS.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[21].toString().equals("0")){
                                linH.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            }else{
                                linH.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[22].toString().equals("0")){
                                linV.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            }else{
                                linV.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[23].toString().equals("0")){
                                linQ.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            }else{
                                linQ.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[24].toString().equals("3")){
                                ltbD.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            }else{
                                ltbD.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[25].toString().equals("2")){
                                ltbH.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            }else{
                                ltbH.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[26].toString().equals("4")){
                                ltbV.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            }else{
                                ltbV.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[27].toString().equals("11")){
                                ltbQ.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            }else{
                                ltbQ.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                        }
                        else if ((pagerAdapter.getCurrentFragment().toString().toLowerCase().contains("fragment3"))) {
                            Log.d("UPDATING", "Updating Booster Tab");
                            freqdif.setText(messages[29]);

                            if (messages[28].toString().equals("1")) {
                                borf.setText("Good");
                                borf.setTextColor(getResources().getColor(R.color.good_PV));
                            } else {
                                borf.setText("Bad");
                                borf.setTextColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[30].toString().equals("16")) {
                                rmps.setText(("Good"));
                                rmps.setTextColor(getResources().getColor(R.color.good_PV));
                            } else {
                                rmps.setText(("Bad"));
                                rmps.setTextColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[31].toString().equals("3")) {
                                booD.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            } else {
                                booD.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[32].toString().equals("2")) {
                                booQ.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            } else {
                                booQ.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[33].toString().equals("2")) {
                                booS.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            } else {
                                booS.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[34].toString().equals("12")) {
                                booH.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            } else {
                                booH.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[35].toString().equals("12")) {
                                booV.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            } else {
                                booV.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[36].toString().equals("6")) {
                                btsD.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            } else {
                                btsD.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[37].toString().equals("12")) {
                                btsQ.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            } else {
                                btsQ.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                            if (messages[38].toString().equals("5")) {
                                btsV.setBackgroundColor(getResources().getColor(R.color.good_PV));
                            } else {
                                btsV.setBackgroundColor(getResources().getColor(R.color.bad_PV));
                            }
                        }
                        else if ((pagerAdapter.getCurrentFragment().toString().toLowerCase().contains("fragment4"))) {
                            if (messages[39].toString().equals("2")){
                                sh1.setBackgroundResource(R.drawable.button_bg_good);
                            }else{
                                sh1.setBackgroundResource(R.drawable.button_bg_bad);
                            }
                            if (messages[40].toString().equals("3")){
                                sh2.setBackgroundResource(R.drawable.button_bg_good);
                            }else if(messages[40].toString().equals("1")){
                                sh2.setBackgroundResource(R.drawable.button_bg_yellow);
                            }else{
                                sh2.setBackgroundResource(R.drawable.button_bg_bad);
                            }
                            if (messages[41].toString().equals("1")){
                                sh3.setBackgroundResource(R.drawable.button_bg_good);
                            }else{
                                sh3.setBackgroundResource(R.drawable.button_bg_bad);
                            }
                            if (messages[42].toString().equals("1")){
                                sh4.setBackgroundResource(R.drawable.button_bg_good);
                            }else{
                                sh4.setBackgroundResource(R.drawable.button_bg_bad);
                            }
                            if (messages[43].toString().equals("1")){
                                sh5.setBackgroundResource(R.drawable.button_bg_good);
                            }else{
                                sh5.setBackgroundResource(R.drawable.button_bg_bad);
                            }
                            if (messages[44].toString().equals("1")){
                                sh6.setBackgroundResource(R.drawable.button_bg_good);
                            }else{
                                sh6.setBackgroundResource(R.drawable.button_bg_bad);
                            }
                            if (messages[45].toString().equals("3")){
                                sh7.setBackgroundResource(R.drawable.button_bg_good);
                            }else{
                                sh7.setBackgroundResource(R.drawable.button_bg_bad);
                            }
                            if (messages[46].toString().equals("2")){
                                sh8.setBackgroundResource(R.drawable.button_bg_bad);
                            }else{
                                sh8.setBackgroundResource(R.drawable.button_bg_good);
                            }
                            if (messages[47].toString().equals("1")){
                                sh9.setBackgroundResource(R.drawable.button_bg_good);
                            }else{
                                sh9.setBackgroundResource(R.drawable.button_bg_bad);
                            }
                            if (messages[48].toString().equals("1")){
                                sh10.setBackgroundResource(R.drawable.button_bg_good);
                            }else{
                                sh10.setBackgroundResource(R.drawable.button_bg_bad);
                            }

                            mx1.setText(messages[49].toString());
                            xfm.setText(messages[50].toString());
                            imbl.setText(messages[51].toString());
                            xas.setText(messages[52].toString());
                            swax.setText(messages[53].toString());
                            sxr.setText(messages[54].toString());
                        }
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

    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //connectWebSocket();
        //handler.postDelayed(updateGUI,1000);
    }

    @Override
    public void onTabAdded() {
        pagerAdapter.addTab(Fragment1.newInstance(), "Tab " + (tabTitles.size() + 1));
    }

    @Override
    public void onTabRemoved() {
        pagerAdapter.removeTab(tabTitles.size()-1);
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

        public void addTab(Fragment fragment, String tabTitle) {
            fragmentList.add(fragment);
            tabTitles.add(tabTitle);
            notifyDataSetChanged();
        }

        public void removeTab(int tabPosition) {
            if (!fragmentList.isEmpty()) {
                fragmentList.remove(tabPosition);
                tabTitles.remove(tabPosition);
                notifyDataSetChanged();
            }
        }
    }
}