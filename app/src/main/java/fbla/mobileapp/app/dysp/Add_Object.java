package fbla.mobileapp.app.dysp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Add_Object extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 101;
    private static final int CAMERA_REQUEST = 1888;
    public static final String IMAGE_TYPE = "image/*";
    TextView select_image, take_image;
    ImageView image_diplay;
    Button add_item, fromgallery, takeimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__object);
        fromgallery = (Button)findViewById(R.id.button2);
        takeimage = (Button)findViewById(R.id.button);
        image_diplay = (ImageView)findViewById(R.id.image_display) ;
        fromgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType(IMAGE_TYPE);
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i,
                        getString(R.string.select_picture)), RESULT_LOAD_IMAGE);
            }
        });
        takeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
       // Typeface custom = Typeface.createFromAsset(getAssets(), "fonts/lettergothic.ttf");
      add_item = (Button)findViewById(R.id.add_item);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        image_diplay = (ImageView)findViewById(R.id.image_display) ;
       final Bitmap user; final Bitmap user1;
        if (resultCode == RESULT_OK) {
            if (requestCode == RESULT_LOAD_IMAGE) {

                final Uri selectedImageUri = data.getData();
                try {
                    user1 = new UserPicture(selectedImageUri, getContentResolver()).getBitmap();
                    image_diplay.setImageBitmap(user1);
                    add_item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent confirm_item = new Intent(Add_Object.this, Add_Details.class);
                            ByteArrayOutputStream bs = new ByteArrayOutputStream();
                            user1.compress(Bitmap.CompressFormat.JPEG, 50, bs);
                            confirm_item.putExtra("byteArray", bs.toByteArray());
                            startActivity(confirm_item);
                        }
                    });

                } catch (IOException e) {
                    //Log.e(Add_Object.class.getSimpleName(), "Failed to load image", e);
                }
                // original code
//                String selectedImagePath = getPath(selectedImageUri);
//                selectedImagePreview.setImageURI(selectedImageUri);
            }
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                user = (Bitmap) data.getExtras().get("data");
                image_diplay.setImageBitmap(user);
                //final Uri selectedImageUri = data.getData();
                add_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent confirm_item = new Intent(Add_Object.this, Add_Details.class);
                        ByteArrayOutputStream bs = new ByteArrayOutputStream();
                        user.compress(Bitmap.CompressFormat.JPEG, 99, bs);
                        confirm_item.putExtra("byteArray", bs.toByteArray());
                        startActivity(confirm_item);
                    }
                });
            }
        } else {
            // report failure
            Toast.makeText(getApplicationContext(), R.string.msg_failed_to_get_intent_data, Toast.LENGTH_LONG).show();
            Log.d(Add_Object.class.getSimpleName(), "Failed to get intent data, result code is " + resultCode);
        }
    }

}
