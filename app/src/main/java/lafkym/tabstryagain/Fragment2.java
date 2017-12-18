package lafkym.tabstryagain;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;

public class Fragment2 extends Fragment {

    private void intialize (View view){

        TextView k1v = (TextView)view.findViewById(R.id.k1V);
        TextView k2v = (TextView)view.findViewById(R.id.k2V);
        TextView gV = (TextView)view.findViewById(R.id.gV);
        TextView linS = (TextView)view.findViewById(R.id.linS);
        TextView linH = (TextView)view.findViewById(R.id.linH);
        TextView linV = (TextView)view.findViewById(R.id.linV);
        TextView linQ = (TextView)view.findViewById(R.id.linQ);
        TextView ltbD = (TextView)view.findViewById(R.id.ltbD);
        TextView ltbH = (TextView)view.findViewById(R.id.ltbH);
        TextView ltbV = (TextView)view.findViewById(R.id.ltbV);
        TextView ltbQ = (TextView)view.findViewById(R.id.ltbQ);

        if(MainActivity.a){

            k1v.setText(MainActivity.messages[17]);
            k2v.setText(MainActivity.messages[18]);
            gV.setText(MainActivity.messages[19]);
            if (Double.parseDouble(k1v.getText().toString()) < 34){
                k1v.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            } else{
                k1v.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            }
            if (Double.parseDouble(k2v.getText().toString()) < 34){
                k2v.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            } else{
                k2v.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            }
            if (Double.parseDouble(gV.getText().toString()) < 89){
                gV.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            } else{
                gV.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            }
            if (MainActivity.messages[20].equals("0")){
                linS.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                linS.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[21].equals("0")){
                linH.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                linH.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[22].equals("0")){
                linV.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                linV.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[23].equals("0")){
                linQ.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                linQ.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[24].equals("3")){
                ltbD.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                ltbD.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[25].equals("2")){
                ltbH.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                ltbH.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[26].equals("4")){
                ltbV.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                ltbV.setBackgroundResource(R.drawable.button_bg_bad);
            }
            if (MainActivity.messages[27].equals("11")){
                ltbQ.setBackgroundResource(R.drawable.button_bg_good);
            }else{
                ltbQ.setBackgroundResource(R.drawable.button_bg_bad);
            }
        }

    }

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    public Fragment2() {
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
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        intialize(view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
