package com.example.simplenav;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Home extends Fragment {
    private List<Twok> listat = new ArrayList<>();
    private int cont = 0;
    private myViewModel mvm;
    private int conta = 1;
    private final String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        /*
        Twok t1 = new Twok("Pippo", "Buongiornissimo Caffe!?!");
        Twok t2 = new Twok("Pluto", "Saluti da Gubbio");
        Twok t3 = new Twok("Paperino", "Bello questo social");
        Twok t4 = new Twok("Topolino", "stacca stacca!!!");
        ArrayList twoks = new ArrayList<>();
        twoks.add(t1);
        twoks.add(t2);
        twoks.add(t3);
        twoks.add(t4);

 */

        Position p = new Position(getContext(),getActivity());
        Log.d("eddai",p.getLatitude()+""+p.getLongitude());


        ViewPager2 viewPager = getView().findViewById(R.id.pagina);


        SidRepository.initializeSid(getContext(), sid -> {

            Log.d("sid", sid.getSid());
            //myViewModel mvm = new ViewModelProvider(this).get(myViewModel.class);
            mvm = new myViewModel(getActivity(), sid, getContext());



            mvm.getLista(listat).observe(getActivity(), twoks -> {
                Log.d("lista",listat+"");
                TwokAdapter twokAdapter = new TwokAdapter(getContext(), twoks);
                viewPager.setAdapter(twokAdapter);
                ImageView image = getView().findViewById(R.id.imageView);


                image.setOnClickListener(tocco -> {

                    Log.d("tocco", image + "" + cont);
                    Bundle args = new Bundle();
                    TextView t = getView().findViewById(R.id.name);
                    ImageView imageView = getView().findViewById(R.id.imageView);
                    TextView t1 = getView().findViewById(R.id.uid);


                    // ImageView imageView = findViewById(R.id.image_view);


                    Drawable drawable = imageView.getDrawable();


                    if (drawable != null) {
                        // Converti il Drawable in un oggetto Bitmap
                        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                        args.putParcelable("key_immagine", bitmap);
                        //imageView.setImageBitmap(bitmap);

                    }
                    //Log.d();
                    String testo = t.getText().toString();
                    String uid = t1.getText().toString();
                    args.putString("someS", testo);
                    args.putString("someu", uid);
                    args.putString("Sid", sid.getSid());
                   // args.putParcelableArrayList("s", (ArrayList<? extends Parcelable>) listat);
/*
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)

                            .replace(R.id.fragmentContainerView, ProfileFragment.class, args)

                            .commit();

 */

                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.homeToProfile, args);


                });

                cont++;
            });
        });


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Log.d("scroll","va");
                /*
                mvm.getLista(listat).observe(getActivity(), twoks -> {
                    Log.d("lista", listat + "");
                    TwokAdapter twokAdapter = new TwokAdapter(getContext(), twoks);
                    viewPager.setAdapter(twokAdapter);
                });
                */





            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("page",position+"");
/*
                   if(position%4== 0){

                        mvm.plusTwok(BASE_URL).observe(getActivity(),twok->{
                            listat.add(twok);
                            TwokAdapter twokAdapter = new TwokAdapter(getContext(), listat.subList(position,listat.size()));
                            viewPager.setAdapter(twokAdapter);
                            ImageView image = getView().findViewById(R.id.imageView);
                            Log.d("add",listat.size()+"");

                        });
                        */

                    }










            }






/*
        byte[] imageBytes = Base64.decode(image, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
     ImageView imagev = getView().findViewById(R.id.imageView2   );
    imagev.setImageBitmap(bitmap);
*/


        )


        ;

    }
}

