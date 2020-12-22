package com.ktdcl.fpo.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ktdcl.fpo.KtdclApplication;
import com.ktdcl.fpo.R;
import com.ktdcl.fpo.model.FPOAppModel;
import com.ktdcl.fpo.model.MarketDetailsModel;
import com.ktdcl.fpo.utils.AppUtils;
import com.ktdcl.fpo.utils.FilePath;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class MarketingDetailsFragment extends Fragment {
    private static final String TAG = "MarketingDetailsFragmen";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mView;
    private  View[] views = new View[15];
    private int count=0;
    private LinearLayout mLinearLayoutMarketView;
    private Button addCrop, removeCrop, buttonSave, button_aadhar, button_photo, uploadAadhar, uploadPhoto;
    private List<String> marketName;
    private List<String> transportMethod;
    private List<String> range;
    private List<String> marketInfo;
    private ImageView imageViewFarmerPhoto, imageViewAadharPhoto;


    public MarketingDetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MarketingDetailsFragment newInstance(String param1, String param2) {
        MarketingDetailsFragment fragment = new MarketingDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            fpoAppModel = getArguments().getParcelable("FPOMODEL");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_marketing_details, container, false);
        initViews();
        initData();
        initListeners();
        return mView;
    }

    private void initViews() {
        imageViewAadharPhoto = mView.findViewById(R.id.imageFarmerAadhar);
        imageViewFarmerPhoto = mView.findViewById(R.id.imageFarmerPhoto);
        buttonSave = mView.findViewById(R.id.button_save);
        addCrop = mView.findViewById(R.id.addCrop);
        removeCrop = mView.findViewById(R.id.removeCrop);
        mLinearLayoutMarketView = mView.findViewById(R.id.market_view);
        button_aadhar = mView.findViewById(R.id.button_aadhar);
        button_photo = mView.findViewById(R.id.button_photo);
        uploadPhoto = mView.findViewById(R.id.upload_photo);
        uploadAadhar = mView.findViewById(R.id.upload_aadhar);


    }

    private void initData() {
        marketName = new ArrayList<>();
        marketName.add(getString(R.string.market_name));
        marketName.add("ಸ್ಥಳೀಯ ಮಾರುಕಟ್ಟೆ");
        marketName.add("ಗ್ರಾಮ ವ್ಯವಹಾರ");
        marketName.add("ಸಹಕಾರಿ ಸಂಘ");
        marketName.add("ನಿಯಂತ್ರಿತ ಮಾರುಕಟ್ಟೆ");
        marketName.add("ಆಯೋಗದ ಕಾರ್ಯಕರ್ತ");
        marketName.add("ರಾಜ್ಯದ ಹೊರಗೆ");
        marketName.add("ದೇಶದ ಹೊರಗೆ");
        marketName.add("ಇತರರು");

        transportMethod = new ArrayList<>();
        transportMethod.add(getString(R.string.transport_method));
        transportMethod.add("ಎತ್ತಿನ ಗಾಡಿ");
        transportMethod.add("ಟ್ರ್ಯಾಕ್ಟರ್");
        transportMethod.add("ಬಸ್");
        transportMethod.add("ಟ್ರಕ್");
        transportMethod.add("ತಲೆ ಹೊರೆ");
        transportMethod.add("ರೈಲು");
        transportMethod.add("ವಿಮಾನ");
        transportMethod.add("ಇತರರು");

        range = new ArrayList<>();
        range.add(getString(R.string.crop_range));
        range.add("ಸ್ವಚ್ಛಗೊಳಿಸುವ");
        range.add("ತೊಳೆಯುವ");
        range.add("ವರ್ಗೀಕರಣ");
        range.add("ಸಂಸ್ಕರಣೆ");
        range.add("ಒಣಗಿಸುವುದು");
        range.add("ಇತರರು");

        marketInfo = new ArrayList<>();
        marketInfo.add(getString(R.string.crop_rate_info));
        marketInfo.add("ಗ್ರಾಮದ ಮುಖ್ಯಸ್ಥ");
        marketInfo.add("ವ್ಯಾಪಾರಿ");
        marketInfo.add("ಆಯೋಗದ ಕಾರ್ಯಕರ್ತ");
        marketInfo.add("ದಿನಪತ್ರಿಕೆ");
        marketInfo.add("ಮಾಧ್ಯಮ");
        marketInfo.add("ಸಹ ರೈತ");
        marketInfo.add("ಇಲಾಖೆ");
        marketInfo.add("ರೈತ ಸಂಪರ್ಕ ಕೇಂದ್ರ");
        marketInfo.add("ಕಂಪನಿ");
        marketInfo.add("ಇತರರು");

        for(int i=0;i<views.length; i++)
        {
            View view = getLayoutInflater().inflate(R.layout.product_marketing, null);
            view.findViewById(R.id.et_crop_name).setTag(i+11);
            view.findViewById(R.id.et_got_product).setTag(i+14);
            view.findViewById(R.id.et_crop_retained).setTag(i+15);
            view.findViewById(R.id.et_sold_product).setTag(i+16);
            view.findViewById(R.id.et_market_dist).setTag(i+17);
            view.findViewById(R.id.et_rate_per_qui).setTag(i+18);
            Spinner spinnerMarketName = view.findViewById(R.id.sp_marketname);
            Spinner spinnerTransport = view.findViewById(R.id.sp_transport_method);
            Spinner spinnerCropRange = view.findViewById(R.id.sp_crop_range);
            Spinner spinnerRateInfo = view.findViewById(R.id.sp_crop_rate_info);

            view.findViewById(R.id.sp_marketname).setTag(i+19);
            view.findViewById(R.id.sp_transport_method).setTag(i+20);
            view.findViewById(R.id.sp_crop_range).setTag(i+21);
            view.findViewById(R.id.sp_crop_rate_info).setTag(i+22);

            ArrayAdapter aa = new ArrayAdapter(getContext(), R.layout.spinner_item, marketName);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerMarketName.setAdapter(aa);
            spinnerMarketName.setSelection(0);

            ArrayAdapter transport = new ArrayAdapter(getContext(), R.layout.spinner_item, transportMethod);
            transport.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerTransport.setAdapter(transport);
            spinnerTransport.setSelection(0);

            ArrayAdapter range1 = new ArrayAdapter(getContext(), R.layout.spinner_item, range);
            range1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerCropRange.setAdapter(range1);
            spinnerCropRange.setSelection(0);


            ArrayAdapter rateInfo = new ArrayAdapter(getContext(), R.layout.spinner_item, marketInfo);
            rateInfo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerRateInfo.setAdapter(rateInfo);
            spinnerRateInfo.setSelection(0);


            views[i] = view;
        }

        mLinearLayoutMarketView.addView(views[count]);
        count++;
    }

    private int aadhar = 0;
    private List<MarketDetailsModel> marketDetailsModelList;
    private void initListeners() {
        button_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aadhar = 1;
                addPhoto();
            }
        });

        button_aadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aadhar = 2;
                addPhoto();
            }
        });

        uploadAadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aadharPath!=null) {
                    uploadFinal(aadharPath, 2);
                }
                else {
                    AppUtils.showToast(getString(R.string.upload_aadhar));
                }
            }
        });

        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(photoPath!=null)
                {
                    uploadFinal(photoPath, 1);
                }
                else {
                    AppUtils.showToast(getString(R.string.upload_photo));
                }
            }
        });

        addCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLinearLayoutMarketView.addView(views[count]);
                count++;
            }
        });
        removeCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0) {
                    count--;
                    mLinearLayoutMarketView.removeView(views[count]);
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null)
                {
                    if(fpoAppModel.getAadharPhoto()==null)
                    {
                        AppUtils.showToast(getString(R.string.upload_aadhar));
                        return;
                    }
                    if(fpoAppModel.getPhoto()==null)
                    {
                        AppUtils.showToast(getString(R.string.upload_photo));
                        return;
                    }
                    marketDetailsModelList = new ArrayList<>();
                    RadioGroup radioGroup = mView.findViewById(R.id.rg_drying_yard);
                    RadioButton radioButton = mView.findViewById(radioGroup.getCheckedRadioButtonId());
                    TextInputEditText model = mView.findViewById(R.id.model);
                    TextInputEditText capacity = mView.findViewById(R.id.capacity);
                    if(model!=null)
                    {
                        fpoAppModel.setModel(model.getText().toString());
                    }

                    if(capacity!=null)
                    {
                        fpoAppModel.setCapacity(capacity.getText().toString());
                    }

                    fpoAppModel.setDryingYard(radioButton.getText().toString());

                    for(int i=0;i<count; i++)
                    {
                        TextInputEditText cropName = mView.findViewWithTag(i+11);
                        TextInputEditText obtainedProduct = mView.findViewWithTag(i+14);
                        TextInputEditText cropRetained = mView.findViewWithTag(i+15);
                        TextInputEditText soldProduct = mView.findViewWithTag(i+16);
                        TextInputEditText marketDist = mView.findViewWithTag(i+17);
                        TextInputEditText ratePerQuintol = mView.findViewWithTag(i+18);

                        Spinner spinnerMarketName = mView.findViewWithTag(i+19);
                        Spinner spinnerTransport = mView.findViewWithTag(i+20);
                        Spinner spinnerCropRange = mView.findViewWithTag(i+21);
                        Spinner spinnerRateInfo = mView.findViewWithTag(i+22);

                        MarketDetailsModel marketDetailsModel = new MarketDetailsModel();
                        marketDetailsModel.setCropName(cropName.getText().toString());
                        marketDetailsModel.setObtainedProduct(obtainedProduct.getText().toString());
                        marketDetailsModel.setRetainedProduct(cropRetained.getText().toString());
                        marketDetailsModel.setSoldProduct(soldProduct.getText().toString());
                        marketDetailsModel.setMarketDistance(marketDist.getText().toString());
                       // marketDetailsModel.setRateInfo(ratePerQuintol.getText().toString());
                        marketDetailsModel.setMarketName(spinnerMarketName.getSelectedItem().toString());
                        marketDetailsModel.setTransportMethod(spinnerTransport.getSelectedItem().toString());
                        marketDetailsModel.setProductRange(spinnerCropRange.getSelectedItem().toString());
                        marketDetailsModel.setRateInfo(spinnerRateInfo.getSelectedItem().toString());
                        marketDetailsModel.setProductPrice(ratePerQuintol.getText().toString());
                        marketDetailsModelList.add(marketDetailsModel);
                    }

                    fpoAppModel.setMarketDetailsModelList(marketDetailsModelList);
                    DatabaseReference databaseReference = KtdclApplication.getFireBaseRef();
                    databaseReference = databaseReference.child("FPO").child("DataSave").child(fpoAppModel.getAadha());
                    databaseReference.setValue(fpoAppModel);
                    databaseReference = databaseReference.child("FPO").child("DataSaveStage").child(fpoAppModel.getAadha());
                    databaseReference.setValue("Market");
                    mListener.onFragmentInteractionMarket(fpoAppModel);
                }
            }
        });
    }

    private String userChoosenTask;
    private String[] PERMISSIONS = {
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    int PERMISSION_ALL = 1;
    private final int PICK_IMAGE_REQUEST = 71;
    private final int PICK_IMAGE_REQUEST_2 = 1111;
    private final int PICK_VIDEO_REQUEST_2 = 2222;
    private static final int SELECT_FILE = 1100;
    private static final int REQUEST_CAMERA = 1200;
    private static final int SELECT_VIDEO = 1300;
    private static final int REQUEST_CODE_DOC = 1400;
    private static final int RQS_RECORDING = 1500;
    private static final int VIDEO_CAPTURE = 1600;
    private static final int AUDIO_LOCAL = 1700;
    private void addPhoto() {
        if (!AppUtils.hasPermissions(getContext(), PERMISSIONS)) {
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, PERMISSION_ALL);
        }
        selectImage();
    }
    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Attachment!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean gallery = AppUtils.checkPermission(getActivity());
                boolean camera = AppUtils.checkPermissionCamera(getActivity());
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (camera)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (gallery)
                    {
                        chooseImage();
                        //galleryIntent();
                    }
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private FPOAppModel fpoAppModel;
    private OnFragmentInteractionListener mListener;
    public interface OnFragmentInteractionListener {
        public void onFragmentInteractionMarket(FPOAppModel uri);
    }
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST_2);
    }
    private Uri filePath;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null)
            filePath = data.getData();
        // imageViewUploaded.setImageURI(filePath);
        // imageViewUploaded.setVisibility(View.VISIBLE);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            // imageViewUploaded.setImageURI(filePath);
            // imageViewUploaded.setVisibility(View.VISIBLE);
            //   if(filePath!=null)
            //  uploadImage();
        }
        if (resultCode == RESULT_OK) {
            if(requestCode==PICK_IMAGE_REQUEST_2){
                uploadImage(filePath);
            }
            else if(requestCode==PICK_VIDEO_REQUEST_2) {
                uploadImage(filePath);
            }
            else if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data, "IMAGE");
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
            else if (requestCode == SELECT_VIDEO)
                onSelectFromGalleryResult(data, "VIDEO");
            else if (requestCode == REQUEST_CODE_DOC) {
                onSelectFromGalleryResult(data, "ALL");
            }else if(requestCode==AUDIO_LOCAL){
                onSelectFromGalleryResult(data, "AUDIO");
            }
            else if (requestCode == RQS_RECORDING) {
                String result = data.getStringExtra("result");
                if(result!=null){
                    // uploadImageToAWS(new File(result), "AUDIO");
                }
                else {

                }
            } else if (requestCode == VIDEO_CAPTURE) {
                Log.d(TAG, "onActivityResult: ");
                //onSelectFromGalleryResult(data, "VIDEO");
                //  isVideoCaptured = true;
              //  uploadImage(Uri.fromFile(mediaFile));
                // uploadImageToAWS(mediaFile, "VIDEO");
            }
        }
    }
    private void onSelectFromGalleryResult(Intent data, String mediaType) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Uri path = data.getData();
        File original;
        if (path != null) {
            if (path.toString().contains("com.google.android.apps.photos")) {
                String filePath = FilePath.getPathFromInputStreamUri(getContext(), path);

                original = new File(filePath);
                String extension_file = original.getAbsolutePath().substring(original.getAbsolutePath().lastIndexOf("."));
                if(extension_file.equalsIgnoreCase(".jpg") || extension_file.equalsIgnoreCase(".jpeg") || extension_file.equalsIgnoreCase(".png")) {
                    crop_ImageAndUpload(original,extension_file,mediaType);
                }else {
                    //uploadImageToAWS(new File(filePath), mediaType);
                    uploadImage(Uri.fromFile(original));
                }
                //OustSdkTools.showToast("can't select attachment from google photos app");
                //return;
            } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                Log.d(TAG,"from SDK more than Kitkat");
                String filePath = getRealPathFromUri(getContext(), path);
                if (filePath != null) {
                    original = new File(filePath);
                    uploadImage(Uri.parse(filePath));
                  /*  String extension_file = original.getAbsolutePath().substring(original.getAbsolutePath().lastIndexOf("."));
                    if(extension_file.equalsIgnoreCase(".jpg") || extension_file.equalsIgnoreCase(".jpeg") || extension_file.equalsIgnoreCase(".png")) {
                        crop_ImageAndUpload(original,extension_file,mediaType);
                    }else {
                        uploadImage(Uri.parse(filePath));
                        //uploadImageToAWS(new File(filePath), mediaType);
                    }*/
                } else {
                    AppUtils.showToast("unable to get file");
                }
            } else {

                String[] proj = {MediaStore.Images.Media.DATA};
                String result = null;

                CursorLoader cursorLoader = new CursorLoader(
                        getContext(),
                        path, proj, null, null, null);
                Cursor cursor = cursorLoader.loadInBackground();

                if (cursor != null) {
                    int column_index =
                            cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    result = cursor.getString(column_index);
                    if (result != null) {
                        uploadImage(Uri.parse(result));
                        //uploadImageToAWS(new File(result), mediaType);
                    }
                }
            }
        }
    }
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (destination != null) {
            String extension_file = destination.getAbsolutePath().substring(destination.getAbsolutePath().lastIndexOf("."));
            if(extension_file.equalsIgnoreCase(".jpg") || extension_file.equalsIgnoreCase(".jpeg") || extension_file.equalsIgnoreCase(".png")) {
                crop_ImageAndUpload(destination,extension_file,"IMAGE");
            }else {
                uploadImage(Uri.fromFile(destination));
                // uploadImageToAWS(destination, "IMAGE");
            }

        }
    }
    public void crop_ImageAndUpload(File original, String extension_file, String mediaType){
        try {
            //change the filepath
            Bitmap d = new BitmapDrawable(getResources(), original.getPath()).getBitmap();
            int nh = (int) (d.getHeight() * (512.0 / d.getWidth()));
            Bitmap bitmap_new = Bitmap.createScaledBitmap(d, 512, nh, true);
            Log.d(TAG, "original:" + d.getByteCount() + " -- duplicate:" + bitmap_new.getByteCount());
            //Log.d(TAG, "Bitmap width:" + bitmap_new.getWidth() + " -- height:" + bitmap_new.getHeight());

            File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + "" + extension_file);
            // storeImage(bitmap_new, destination);

            Log.d(TAG, "file size  duplicate:" + destination.length() + " -- Original:" + original.length());
            // uploadImageToAWS(destination, mediaType);
            uploadImage(Uri.fromFile(original));
        }catch (Exception e){
            e.printStackTrace();
            uploadImage(Uri.fromFile(original));
            //  uploadImageToAWS(original, mediaType);
            //Toast.makeText(this,"Couldn't able to load the image. Please try again.",Toast.LENGTH_LONG).show();
        }
    }

    public static String getRealPathFromUri(Context context, final Uri uri) {
        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }
    private Uri aadharPath, photoPath;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private void uploadImage(Uri filePath) {
        try {
            if(aadhar==1)
            {
                imageViewFarmerPhoto.setImageURI(filePath);
                imageViewFarmerPhoto.setVisibility(View.VISIBLE);
               // fpoAppModel.setPhoto(encodedImage);

            }
            else {
                imageViewAadharPhoto.setVisibility(View.VISIBLE);
                imageViewAadharPhoto.setImageURI(filePath);
               // fpoAppModel.setAadharPhoto(encodedImage);
            }

            if(fpoAppModel.getAadha()!=null) {
                storage = FirebaseStorage.getInstance();
                if(aadhar==1){
                    imageViewFarmerPhoto.setImageURI(filePath);
                    photoPath = filePath;
                    uploadPhoto.setVisibility(View.VISIBLE);

                }
                if(aadhar==2)
                {
                    imageViewAadharPhoto.setImageURI(filePath);
                    aadharPath = filePath;
                    uploadAadhar.setVisibility(View.VISIBLE);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void uploadFinal(Uri filePath, final int typ1)
    {
        storageReference = storage.getReference();
        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = storageReference.child("images").child("FPO").child(fpoAppModel.getAadha()).child(UUID.randomUUID().toString());
            //UploadTask uploadTask =
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;
                                    {
                                        if(typ1==1) {
                                            fpoAppModel.setPhoto(downloadUrl.toString());
                                            photoPath = null;
                                            uploadPhoto.setVisibility(View.GONE);
                                        }
                                        else if(typ1==2)
                                        {
                                            fpoAppModel.setAadharPhoto(downloadUrl.toString());
                                            aadharPath = null;
                                            uploadAadhar.setVisibility(View.GONE);
                                        }
                                        // SharedPrefsHelper.getInstance().save("PICK_URL", imageURL);

                                    }
                                    //imageViewUploaded.setVisibility(View.VISIBLE);
                                }
                            });

                            Toast.makeText(getActivity(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });

        }
    }

}