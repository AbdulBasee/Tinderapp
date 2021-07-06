package com.example.tinderapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//public class SettingsActivity extends AppCompatActivity {
//
//
//    private EditText mNameField, mPhoneField;
//
//    private Button mBack, mConfirm;
//
//    private ImageView mProfileImage;
//
//    private FirebaseAuth mAuth;
//
//    private DatabaseReference mCustomerDatabase;
//
//    private String userId, name, phone, profileImageUrl, userSex;
//
//    private Uri resultUrl;
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);
//
//        userSex = getIntent().getExtras().getString("userSex");
//
//        mNameField = (EditText) findViewById(R.id.name);
//
//        mPhoneField = (EditText) findViewById(R.id.phone);
//
//        mProfileImage = (ImageView) findViewById(R.id.profileImage);
//
//        mBack = (Button) findViewById(R.id.back);
//        mConfirm = (Button) findViewById(R.id.confirm);
//
//        mAuth = FirebaseAuth.getInstance();
//        userId = mAuth.getCurrentUser().getUid();
//
//        mCustomerDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userSex).child(userId);
//
//        getUserInfo();
//
//        mProfileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                someActivityResultLauncher.launch(intent);
//
//
//
//            }
//        });
//
//        mConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    saveUserInformation();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        mBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                return;
//            }
//        });
//
//    }
//
//
//    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result != null) {
//                        // There are no request codes
////                        Intent data = result.getData();
////                        doSomeOperations();
//
//                        Intent data = result.getData();
//                        final Uri imageUri = data.getData();
//                        resultUrl = imageUri;
//                        mProfileImage.setImageURI(resultUrl);
//                    }
//                }
//            });
//
//
//
//    private void getUserInfo() {
//        mCustomerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists() && snapshot.getChildrenCount() > 0){
//                    Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                    if(map.get("name") != null){
//                        name = map.get("name").toString();
//                        mNameField.setText(name);
//                    }
//                    if(map.get("phone") != null){
//                        phone = map.get("phone").toString();
//                        mPhoneField.setText(phone);
//                    }
//                    if(map.get("profileImageUrl") != null){
//                        profileImageUrl = map.get("profileImageUrl").toString();
//                        Glide.with(getApplication()).load(profileImageUrl).into(mProfileImage);
//
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    private void saveUserInformation() throws IOException {
//
//        name = mNameField.getText().toString();
//        phone = mPhoneField.getText().toString();
//
//        Map userInfo = new HashMap<>();
//
//        userInfo.put("name", name);
//        userInfo.put("phone", phone);
//
//        if(resultUrl != null){
//
//
//            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("profileImages").child(userId);
//            Bitmap bitmap = null;
//            try{
//                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUrl);
//            }catch (IOException e) {
//                e.printStackTrace();
//            }
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
//            byte[] data = baos.toByteArray();
//
//            UploadTask uploadTask = filepath.putBytes(data);
//            uploadTask.addOnFailureListener(e -> finish());
//            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                    Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
////                  Uri downloadUrl = taskSnapshot.getStorage().getDownloadUrl()
////                    Uri downloadUrl = Uri.parse(taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
//                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//
//                        @Override
//                        public void onSuccess(Uri uri) {
////                            Map userInfo = new HashMap<>();
//
//
//                            userInfo.put("profileImageUrl", uri.toString());
//                            mCustomerDatabase.updateChildren(userInfo);
////                            mCustomerDatabase.updateChildren(userInfo);
//                            finish();
//                            return;
//
//                        }
//                    });
//
//
////                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
////                        @Override
////                        public void onSuccess(Uri uri) {
////                            Map userInfo = new HashMap<>();
////
////                            userInfo.put("profileImageUrl", downloadUrl.toString());
////                            userInfo.put("phone", phone);
////                            mCustomerDatabase.updateChildren(userInfo);
////
////                            finish();
////                            return;
////
////                        }
////                    });
//                }
//            });
//
//        }else{
//            finish();
//        }
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
//            final Uri imageUri = data.getData();
//            resultUrl = imageUri;
//            mProfileImage.setImageURI(resultUrl);
//        }
//
//    }













public class SettingsActivity extends AppCompatActivity {

    private EditText mNameField, mPhoneField;

    private RadioGroup mRadioGroup;

    private Button mBack, mConfirm;

    private ImageView mProfileImage;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;

    private String userId, name, phone, profileImageUrl, userSex;

    private Uri resultUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        int selectId = mRadioGroup.getCheckedRadioButtonId();
//        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

//        final RadioButton radioButton = (RadioButton) findViewById(selectId);
        userSex = getIntent().getExtras().getString("userSex");
        mNameField = (EditText) findViewById(R.id.name);
        mPhoneField = (EditText) findViewById(R.id.phone);

        mProfileImage = (ImageView) findViewById(R.id.profileImage);

        mBack = (Button) findViewById(R.id.back);
        mConfirm = (Button) findViewById(R.id.confirm);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userSex).child(userId);


        getUserInfo();

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });
    }


    private void getUserInfo() {
        mUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("name")!=null){
                        name = map.get("name").toString();
                        mNameField.setText(name);
                    }
                    if(map.get("phone")!=null){
                        phone = map.get("phone").toString();
                        mPhoneField.setText(phone);
                    }
                    if(map.get("sex")!=null){
                        userSex = map.get("sex").toString();
                    }
                    Glide.clear(mProfileImage);

                    if(map.get("profileImageUrl")!=null){
                        profileImageUrl = map.get("profileImageUrl").toString();
                        Glide.with(getApplication()).load(profileImageUrl).into(mProfileImage);
                        System.out.println("We are inside setting profile" + profileImageUrl);
                        switch(profileImageUrl){
                            case "default":
                                Glide.with(getApplication()).load(R.mipmap.ic_launcher).into(mProfileImage);
                                break;
                            default:
                                Glide.with(getApplication()).load(profileImageUrl).into(mProfileImage);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void saveUserInformation() {
        name = mNameField.getText().toString();
        phone = mPhoneField.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("name", name);
        userInfo.put("phone", phone);

        mUserDatabase.updateChildren(userInfo);
        if(resultUri != null){
            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("profileImages").child(userId);
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filepath.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    finish();
                }
            });
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                        @Override
                        public void onSuccess(Uri uri) {
                            Map userInfo = new HashMap<>();


                            userInfo.put("profileImageUrl", uri.toString());
                            mUserDatabase.updateChildren(userInfo);
//                            mCustomerDatabase.updateChildren(userInfo);
                            finish();
                            return;

                        }
                    });



                }
            });
        }else{
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            mProfileImage.setImageURI(resultUri);
        }
    }
}
