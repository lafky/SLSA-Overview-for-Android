package lafkym.tabstryagain;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
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
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabsListener {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tabTitles = new ArrayList<>();
    private MyPagerAdapter pagerAdapter;
    private TabLayout tabLayout;

    private WebSocketClient mWebSocketClient;
    private String TAG = this.getClass().getSimpleName();
    private Handler handler;



    //Main runnable to update the gui
    Runnable updateGUI = new Runnable(){
        public void run(){


            handler.postDelayed(this,1000);  // Run this again in 1 second
            //mWebSocketClient.send("overview");
            //Log.d("myTag",pagerAdapter.getCurrentFragment().toString());

            if (pagerAdapter.getCurrentFragment().toString().toLowerCase().contains("fragment1")) {
                mWebSocketClient.send("overview");
            } else if(pagerAdapter.getCurrentFragment().toString().toLowerCase().contains("fragment2")){
                mWebSocketClient.send("linac");
            }
            else{
                Log.d("myTag","WAITING");
            }


        }

    };

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://10.6.0.77:6000");      } catch (URISyntaxException e) {
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
                        TextView bc = (TextView)findViewById(R.id.bC);
                        TextView lt = (TextView)findViewById(R.id.lT);
                        TextView bm = (TextView)findViewById(R.id.bM);
                        TextView ns = (TextView)findViewById(R.id.nS);
                        TextView k1v = (TextView)findViewById(R.id.k1V);
                        TextView k2v = (TextView)findViewById(R.id.k2V);

                        if (messages[0].toString().equals("overview")) {
                            Log.d("UPDATING","Main window updating");
                            bc.setText(messages[1]);
                            lt.setText(messages[2]);
                            bm.setText(messages[3]);
                            ns.setText(messages[4]);
                        } else if (messages[0].toString().equals("linac")){
                            Log.d("UPDATING","Linac window updating");
                            k1v.setTextColor(getResources().getColor(R.color.good_PV));
                            k1v.setText(messages[1]);
                            k2v.setText(messages[2]);
                        }

                    }
                });
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket1", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket2", "Error " + e.getMessage());
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

        Log.i("test",tabTitles.get(0));

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
            super.setPrimaryItem(container, position, object);
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