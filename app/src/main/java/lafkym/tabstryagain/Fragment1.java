package lafkym.tabstryagain;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class Fragment1 extends Fragment {

    private void initialize (View view){

        TextView bC = (TextView)view.findViewById(R.id.bC);
        TextView lT = (TextView)view.findViewById(R.id.lT);
        TextView bM = (TextView)view.findViewById(R.id.bM);
        TextView nS = (TextView)view.findViewById(R.id.nS);
        TextView uX = (TextView)view.findViewById(R.id.uX);
        TextView uY = (TextView)view.findViewById(R.id.uY);
        TextView b2sEff = (TextView)view.findViewById(R.id.b2sEff);
        TextView booCurr = (TextView)view.findViewById(R.id.booCurr);
        TextView ls1 = (TextView)view.findViewById(R.id.ls1);
        TextView ls2 = (TextView)view.findViewById(R.id.ls2);
        TextView xSize = (TextView)view.findViewById(R.id.xSize);
        TextView ySize = (TextView)view.findViewById(R.id.ySize);
        TextView xPos = (TextView)view.findViewById(R.id.xPos);
        TextView yPos = (TextView)view.findViewById(R.id.yPos);
        TextView xSTD = (TextView)view.findViewById(R.id.xSTD);
        TextView ySTD = (TextView)view.findViewById(R.id.ySTD);

        if(MainActivity.a){

            bC.setText(MainActivity.messages[1]);
            if (Double.parseDouble(bC.getText().toString()) > 199.9){
                bC.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(bC.getText().toString()) <= 199.9) &&  (Double.parseDouble(bC.getText().toString()) > 180)) {
                bC.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.warn_PV));
            } else{
                bC.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            }

            lT.setText(MainActivity.messages[2]);
            if (Double.parseDouble(lT.getText().toString()) > 24){
                lT.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(lT.getText().toString()) <= 24) &&  (Double.parseDouble(lT.getText().toString()) > 20)) {
                lT.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.warn_PV));
            } else{
                lT.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            }

            ls1.setText(MainActivity.messages[9]);
            if (Double.parseDouble(ls1.getText().toString()) > 90){
                ls1.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(lT.getText().toString()) <= 85) &&  (Double.parseDouble(lT.getText().toString()) > 60)) {
                ls1.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.warn_PV));
            } else{
                ls1.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            }

            ls2.setText(MainActivity.messages[10]);
            if (Double.parseDouble(ls2.getText().toString()) > 90){
                ls2.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(lT.getText().toString()) <= 85) &&  (Double.parseDouble(lT.getText().toString()) > 60)) {
                ls2.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.warn_PV));
            } else{
                ls2.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            }

            xSize.setText(MainActivity.messages[11]);
            if (Double.parseDouble(xSize.getText().toString()) < 320){
                xSize.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(xSize.getText().toString()) >= 320) &&  (Double.parseDouble(xSize.getText().toString()) < 335)) {
                xSize.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.warn_PV));
            } else{
                xSize.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            }

            ySize.setText(MainActivity.messages[12]);
            if (Double.parseDouble(ySize.getText().toString()) < 280){
                ySize.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(ySize.getText().toString()) >= 280) &&  (Double.parseDouble(ySize.getText().toString()) < 290)) {
                ySize.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.warn_PV));
            } else{
                ySize.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            }

            xSTD.setText(MainActivity.messages[15]);
            if (Double.parseDouble(xSTD.getText().toString()) < .5){
                xSTD.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(xSTD.getText().toString()) >= .5) &&  (Double.parseDouble(xSTD.getText().toString()) < 1)) {
                xSTD.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.warn_PV));
            } else{
                xSTD.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            }

            ySTD.setText(MainActivity.messages[16]);
            if (Double.parseDouble(ySTD.getText().toString()) < 1){
                ySTD.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.good_PV));
            } else if ((Double.parseDouble(ySTD.getText().toString()) >= 1) &&  (Double.parseDouble(ySTD.getText().toString()) < 1.5)) {
                ySTD.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.warn_PV));
            } else{
                ySTD.setTextColor(ContextCompat.getColor(getActivity().getApplicationContext(),R.color.bad_PV));
            }

            //Neutral PVs don't change colors
            bM.setText(MainActivity.messages[3]);
            nS.setText(MainActivity.messages[4]);
            uX.setText(MainActivity.messages[5]);
            uY.setText(MainActivity.messages[6]);
            b2sEff.setText(MainActivity.messages[7]);
            booCurr.setText(MainActivity.messages[8]);
            xPos.setText(MainActivity.messages[13]);
            yPos.setText(MainActivity.messages[14]);
            //Need to add a "+" if the value for the position is positive
            if (Double.parseDouble(MainActivity.messages[14]) > 0){
                yPos.setText("+"+MainActivity.messages[14]);
            }else{
                yPos.setText(MainActivity.messages[14]);
            }
            if (Double.parseDouble(xPos.getText().toString()) > 0){
                xPos.setText("+"+MainActivity.messages[13]);
            }else{
                xPos.setText(MainActivity.messages[13]);
            }
        }
    }

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        initialize(view);
        return view;

    }

}