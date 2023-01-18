package com.example.simplenav;

import static android.app.Activity.RESULT_OK;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentModify#newInstance} factory method to
 * create an instance of this fragment.
 *
 */


import com.example.simplenav.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentModify extends Fragment implements EasyPermissions.PermissionCallbacks{
    ArrayList<Uri> arrayList = new ArrayList<>();
    Uri uri;
    Button b;
    RecyclerView r;
    String base;
    EditText editText;


    public FragmentModify() {
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
        return inflater.inflate(R.layout.fragment_modify, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String sid ="gEv7N1OvV12Ud9xc8j3X";
        Button back = getView().findViewById(R.id.back);
        back.setOnClickListener(cc->{

            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.modifytoUser);
        });

        b = getView().findViewById(R.id.pick);
        r = getView().findViewById(R.id.re);

        b.setOnClickListener(vista->{


            String[] strings ={Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};
            if(EasyPermissions.hasPermissions(getContext(),strings)){
                imagePicker();

            }else{
                EasyPermissions.requestPermissions(this,"app needs access to your camera & storage",100,strings);
            }
        });

        Button butt = getView().findViewById(R.id.button2);
        editText = getView().findViewById(R.id.editText);
        butt.setOnClickListener(c->{
            Log.d("lunghezaza",base.length()+"");
            Log.d("ciaoo",editText.getText()+"");
            Log.d("ciaoo",base+"");
            String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
            ApiInterface registerAPI = retrofit.create(ApiInterface.class);

            Call<User> call = registerAPI.setProfile(sid,editText.getText().toString(),base);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.d("mama","Funziona"+response);

                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.modifytoUser);




                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("mama","non va nulla");
                }
            });
        });








    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            if(requestCode == FilePickerConst.REQUEST_CODE_PHOTO){
                arrayList = data.getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                //Uri uri = data.getData();
                uri = arrayList.get(0);
                //Uri selectedImageUri = data.getData();
                Bitmap bitmap= null;
                try {
                    InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                     bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                } catch (FileNotFoundException e) {
                    Log.d("non va","eee");
                } catch (IOException e) {
                    Log.d("non va","eee");
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                base = Base64.encodeToString(byteArray, Base64.DEFAULT);
                Log.d("funge",base.length()+"");







                Log.d("Immaginee",arrayList+"");
                Log.d("onlyone",uri+"");
                r.setLayoutManager(new LinearLayoutManager(getContext()));
                r.setAdapter(new MainAdp(arrayList));
            }
        }
    }



    private void imagePicker() {
        FilePickerBuilder.getInstance().setActivityTitle("select Images")
                .setSpan(FilePickerConst.SPAN_TYPE.FOLDER_SPAN,3)
                .setSpan(FilePickerConst.SPAN_TYPE.DETAIL_SPAN,4)
                .setMaxCount(1)
                .setSelectedFiles(arrayList)
                .setActivityTheme(R.style.CustomTheme)
                .pickPhoto(this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if(requestCode == 100 && perms.size()== 2){
            imagePicker();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            new AppSettingsDialog.Builder(this).build().show();
        }else{
            Toast.makeText(getContext(),"Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }



// You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed





    }





