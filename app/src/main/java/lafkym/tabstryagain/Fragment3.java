package lafkym.tabstryagain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment3 extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     @return A new instance of fragment Fragment3.
     */
    public static Fragment3 newInstance() {
        return new Fragment3();
    }

    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);

        TextView borf = (TextView)view.findViewById(R.id.borf);
        TextView freqdif = (TextView)view.findViewById(R.id.freqdif);
        TextView rmps = (TextView)view.findViewById(R.id.rmps);
        TextView booD = (TextView)view.findViewById(R.id.booD);
        TextView booQ = (TextView)view.findViewById(R.id.booQ);
        TextView booS = (TextView)view.findViewById(R.id.booS);
        TextView booH = (TextView)view.findViewById(R.id.booH);
        TextView booV = (TextView)view.findViewById(R.id.booV);
        TextView btsD = (TextView)view.findViewById(R.id.btsD);
        TextView btsQ = (TextView)view.findViewById(R.id.btsQ);
        TextView btsV = (TextView)view.findViewById(R.id.btsV);

        if(MainActivity.a) {
            Log.d("UPDATING", "Updating Booster Tab");
            freqdif.setText(MainActivity.messages[29]);

            if (MainActivity.messages[28].equals("1")) {
                borf.setText("Good");
                borf.setTextColor(getResources().getColor(R.color.good_PV));
            } else {
                borf.setText("Bad");
                borf.setTextColor(getResources().getColor(R.color.bad_PV));
            }
            if (MainActivity.messages[30].equals("16")) {
                rmps.setText(("Good"));
                rmps.setTextColor(getResources().getColor(R.color.good_PV));
            } else {
                rmps.setText(("Bad"));
                rmps.setTextColor(getResources().getColor(R.color.bad_PV));
            }
            if (MainActivity.messages[31].equals("3")) {
                booD.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booD.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[32].equals("2")) {
                booQ.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booQ.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[33].equals("2")) {
                booS.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booS.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[34].equals("12")) {
                booH.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booH.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[35].equals("12")) {
                booV.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                booV.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[36].equals("6")) {
                btsD.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                btsD.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[37].equals("12")) {
                btsQ.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                btsQ.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[38].equals("5")) {
                btsV.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                btsV.setBackgroundResource(R.drawable.button_bg_bad);
            }
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}