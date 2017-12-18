package lafkym.tabstryagain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class Fragment4 extends Fragment {

    private void initialize (View view){

        ImageButton sh1 = (ImageButton)view.findViewById(R.id.imageButton);
        ImageButton sh2 = (ImageButton)view.findViewById(R.id.imageButton2);
        ImageButton sh3 = (ImageButton)view.findViewById(R.id.imageButton3);
        ImageButton sh4 = (ImageButton)view.findViewById(R.id.imageButton4);
        ImageButton sh5 = (ImageButton)view.findViewById(R.id.imageButton5);
        ImageButton sh6 = (ImageButton)view.findViewById(R.id.imageButton6);
        ImageButton sh7 = (ImageButton)view.findViewById(R.id.imageButton7);
        ImageButton sh8 = (ImageButton)view.findViewById(R.id.imageButton8);
        ImageButton sh9 = (ImageButton)view.findViewById(R.id.imageButton9);
        ImageButton sh10 = (ImageButton)view.findViewById(R.id.imageButton10);

        TextView mx1 = (TextView)view.findViewById(R.id.mx1Gap);
        TextView xfm = (TextView)view.findViewById(R.id.xfmGap);
        TextView imbl = (TextView)view.findViewById(R.id.imblField);
        TextView xas = (TextView)view.findViewById(R.id.xasGap);
        TextView swax = (TextView)view.findViewById(R.id.swaxGap);
        TextView sxr = (TextView)view.findViewById(R.id.sxrGap);
        TextView ir = (TextView)view.findViewById(R.id.irGap);

        if(MainActivity.a) {

            if (MainActivity.messages[39].equals("2")) {
                sh1.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                sh1.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[40].equals("3")) {
                sh2.setBackgroundResource(R.drawable.button_bg_good);
                ir.setText("Inserted");
            } else if (MainActivity.messages[40].equals("1")) {
                sh2.setBackgroundResource(R.drawable.button_bg_yellow);
                ir.setText("Moving");
            } else {
                sh2.setBackgroundResource(R.drawable.button_bg_bad);
                ir.setText("Out");
            }
            if (MainActivity.messages[41].equals("1")) {
                sh3.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                sh3.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[42].equals("1")) {
                sh4.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                sh4.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[43].equals("1")) {
                sh5.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                sh5.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[44].equals("1")) {
                sh6.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                sh6.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[45].equals("1")) {
                sh7.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                sh7.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[46].equals("2")) {
                sh8.setBackgroundResource(R.drawable.button_bg_bad);
            } else {
                sh8.setBackgroundResource(R.drawable.button_bg_good);
            }
            if (MainActivity.messages[47].equals("1")) {
                sh9.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                sh9.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[48].equals("1")) {
                sh10.setBackgroundResource(R.drawable.button_bg_good);
            } else {
                sh10.setBackgroundResource(R.drawable.button_bg_bad);
            }

            mx1.setText(MainActivity.messages[49]);
            xfm.setText(MainActivity.messages[50]);
            imbl.setText(MainActivity.messages[51]);
            xas.setText(MainActivity.messages[52]);
            swax.setText(MainActivity.messages[53]);
            sxr.setText(MainActivity.messages[54]);
        }

    }

    public static Fragment4 newInstance() {
        return new Fragment4();
    }

    public Fragment4() {
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
        View view = inflater.inflate(R.layout.fragment_fragment4, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}