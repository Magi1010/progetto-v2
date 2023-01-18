package com.example.simplenav;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;


public class AddTwokFragment extends Fragment {
    final float[] fontSize = {20, 30, 40};
    final Typeface[] fontType = {Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL),Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC),Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)};
    int idCheckedha = -1;
    int idCheckedva = -1;
    boolean testo,bgcolor,textcol = false;





    public AddTwokFragment() {
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
        return inflater.inflate(R.layout.fragment_add_twok, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText text = getView().findViewById(R.id.testo);
        EditText bgcol = getView().findViewById(R.id.bgcolor);
        EditText textcolor = getView().findViewById(R.id.textcolor);
        RadioGroup radiosize = getView().findViewById(R.id.radiosize);
        RadioGroup radiotype = getView().findViewById(R.id.radiotype);
        RadioGroup radiohalign = getView().findViewById(R.id.radiohalign);
        RadioGroup radiovalign = getView().findViewById(R.id.radiovalign);

        View viewProva = getView().findViewById(R.id.view4);

        TextView testoProva = getView().findViewById(R.id.text2);

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("prova",testoProva.getText().toString());
                if(testoProva.getText().length() <= 100){
                    testoProva.setText(s);
                    testo=false;
               }else{
                   testo = true;
               }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bgcol.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if(s.length() == 6){
                    String hexColor = s.toString();
                    try {
                        int color = Color.parseColor("#"+hexColor);
                        viewProva.setBackgroundColor(color);
                        bgcolor= false;
                        // la stringa è valida per un colore esadecimale
                    } catch (IllegalArgumentException e) {
                         bgcolor= true;
                        int color = Color.parseColor("#FFFFFF");
                        viewProva.setBackgroundColor(color);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Conferma");
                        builder.setMessage("Sei sicuro di voler continuare?");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // esegui l'azione
                            }
                        });
                    }


                }
           // }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textcolor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if(s.length() == 6){
                String hexColor = s.toString();
                try {
                    int color = Color.parseColor("#"+hexColor);
                    testoProva.setTextColor(color);
                    textcol = false;
                    // la stringa è valida per un colore esadecimale
                } catch (IllegalArgumentException e) {
                    textcol = true;
                    int color = Color.parseColor("#000000");
                    testoProva.setTextColor(color);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Conferma");
                    builder.setMessage("Sei sicuro di voler continuare?");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // esegui l'azione
                        }
                    });
                }


            }
            // }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        radiosize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        RadioButton rb = getView().findViewById(R.id.radioButton1);

                       testoProva.setTextSize(fontSize[Integer.parseInt(rb.getText().toString())]);
                        break;
                    case R.id.radioButton2:
                        RadioButton rb2 = getView().findViewById(R.id.radioButton2);

                        testoProva.setTextSize(fontSize[Integer.parseInt(rb2.getText().toString())]);
                        break;
                    case R.id.radioButton3:
                        RadioButton rb3 = getView().findViewById(R.id.radioButton3);

                        testoProva.setTextSize(fontSize[Integer.parseInt(rb3.getText().toString())]);
                        break;
                }
            }
        });
        radiotype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1ty:
                        RadioButton rb = getView().findViewById(R.id.radioButton1ty);

                        testoProva.setTypeface(fontType[Integer.parseInt(rb.getText().toString())]);
                        break;
                    case R.id.radioButton2ty:
                        RadioButton rb2 = getView().findViewById(R.id.radioButton2ty);

                        testoProva.setTypeface(fontType[Integer.parseInt(rb2.getText().toString())]);
                        break;
                    case R.id.radioButton3ty:
                        RadioButton rb3 = getView().findViewById(R.id.radioButton3ty);

                        testoProva.setTypeface(fontType[Integer.parseInt(rb3.getText().toString())]);
                        break;
                }
            }
        });
        radiohalign.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                idCheckedha = checkedId;
                Log.d("halign",idCheckedha+"");
                switch (checkedId) {
                    case R.id.radioButton1ha:
                        switch(idCheckedva){
                            case R.id.radioButton1va:


                                testoProva.setGravity(Gravity.LEFT|Gravity.TOP);
                                break;
                            case R.id.radioButton2va:


                                testoProva.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
                                break;
                            case R.id.radioButton3va:


                                testoProva.setGravity(Gravity.LEFT|Gravity.BOTTOM);
                                break;
                            default:

                                testoProva.setGravity(Gravity.LEFT);
                                break;



                        }



                        break;
                    case R.id.radioButton2ha:
                        switch(idCheckedva){
                            case R.id.radioButton1va:


                                testoProva.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP);
                                break;
                            case R.id.radioButton2va:


                                testoProva.setGravity(Gravity.CENTER);
                                break;
                            case R.id.radioButton3va:


                                testoProva.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
                                break;
                            default:

                                testoProva.setGravity(Gravity.CENTER_HORIZONTAL);
                                break;



                        }

                        break;
                    case R.id.radioButton3ha:
                        switch(idCheckedva){
                            case R.id.radioButton1va:


                                testoProva.setGravity(Gravity.RIGHT|Gravity.TOP);
                                break;
                            case R.id.radioButton2va:


                                testoProva.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
                                break;
                            case R.id.radioButton3va:


                                testoProva.setGravity(Gravity.RIGHT|Gravity.BOTTOM);
                                break;
                            default:

                                testoProva.setGravity(Gravity.RIGHT);
                                break;



                        }



                        break;
                }
            }
        });
        radiovalign.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                idCheckedva = checkedId;
                Log.d("valign",idCheckedva+"");
                switch (checkedId) {
                    case R.id.radioButton1va:
                        switch(idCheckedha){
                            case R.id.radioButton1ha:


                                testoProva.setGravity(Gravity.TOP|Gravity.LEFT);
                                break;
                            case R.id.radioButton2ha:


                                testoProva.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
                                break;
                            case R.id.radioButton3ha:


                                testoProva.setGravity(Gravity.TOP|Gravity.RIGHT);
                                break;
                            default:

                                testoProva.setGravity(Gravity.TOP);
                                break;



                        }

                        break;

                    case R.id.radioButton2va:
                        switch(idCheckedha){
                            case R.id.radioButton1ha:


                                testoProva.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                                break;
                            case R.id.radioButton2ha:


                                testoProva.setGravity(Gravity.CENTER);
                                break;
                            case R.id.radioButton3ha:


                                testoProva.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
                                break;
                            default:

                                testoProva.setGravity(Gravity.CENTER_VERTICAL);
                                break;



                        }
                        break;

                    case R.id.radioButton3va:
                        switch(idCheckedha){
                            case R.id.radioButton1ha:


                                testoProva.setGravity(Gravity.BOTTOM|Gravity.LEFT);
                                break;
                            case R.id.radioButton2ha:


                                testoProva.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
                                break;
                            case R.id.radioButton3ha:


                                testoProva.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
                                break;
                            default:

                                testoProva.setGravity(Gravity.BOTTOM);
                                break;



                        }
                        break;

                }
            }
        });

        Button invia = getView().findViewById(R.id.invia);

        invia.setOnClickListener(inv->{
            Log.d("controlli",testo+ ""+bgcolor+""+textcol+"");
            if(!testo && !bgcolor &&!textcol){

                int selectedIdsize = radiosize.getCheckedRadioButtonId();
                int selectedIdtype = radiotype.getCheckedRadioButtonId();
                int selectedIdhalign = radiohalign.getCheckedRadioButtonId();
                int selectedIdvalign = radiovalign.getCheckedRadioButtonId();
                RadioButton sizer = (RadioButton) getView().findViewById(selectedIdsize);
                RadioButton typer = (RadioButton) getView().findViewById(selectedIdtype);
                RadioButton har = (RadioButton) getView().findViewById(selectedIdhalign);
                RadioButton var = (RadioButton) getView().findViewById(selectedIdvalign);
                String sizestr = sizer.getText().toString();
                String typestr = typer.getText().toString();
                String hastr = har.getText().toString();
                String vastr = var.getText().toString();
                Switch s = getView().findViewById(R.id.switch_example);
                Double latitudine,longitudine;
                if(s.isChecked()){
                    Position p = new Position(getContext(),getActivity());
                    latitudine = p.getLatitude();
                    longitudine = p.getLongitude();
                }else{
                    latitudine = null;
                    longitudine = null;

                }
                Log.d("posizione",latitudine +"/"+longitudine);

                Log.d("controllo",sizestr+typestr+hastr+vastr);



                SidRepository.initializeSid(getContext(),sid -> {
                    String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
                    ApiInterface registerAPI = retrofit.create(ApiInterface.class);

                    Log.d("controllo",sid.getSid()+text.getText().toString()+bgcol.getText().toString()+textcolor.getText().toString()+sizestr+typestr+hastr+vastr+"");
                    Call<Twok> call = registerAPI.addTwok(sid.getSid(),text.getText().toString(),bgcol.getText().toString(),textcolor.getText().toString(),Integer.parseInt(sizestr),Integer.parseInt(typestr),Integer.parseInt(hastr),Integer.parseInt(vastr),null,null);
                    call.enqueue(new Callback<Twok>() {
                        @Override
                        public void onResponse(Call<Twok> call, Response<Twok> response) {
                            Log.d("funziona", "ciao"+response);

                        }

                        @Override
                        public void onFailure(Call<Twok> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });


                });



            }else{
                Log.d("controllo","non si può inviare");
            }
        });
        //Call<Twok> addTwok(@Field("sid") String sid,@Field("text") String text,@Field("bgcol") String bgcol,@Field("fontcol") String fontcol,@Field("fontsize") String fontsize,@Field("fonttype") String fonttype,@Field("halign") String halign,@Field("valign") String valign,@Field("lat") String lat,@Field("lon") String lon);















    }
}