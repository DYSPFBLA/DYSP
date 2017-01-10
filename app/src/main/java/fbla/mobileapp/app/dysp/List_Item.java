package fbla.mobileapp.app.dysp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class List_Item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__item);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users");
        final DatabaseReference itemRef = database.getReference("MasterItems");
        final ListView listView = (ListView)findViewById(R.id.list_view);
        //Typeface custom = Typeface.createFromAsset(getAssets(), "fonts/lettergothic.ttf");
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar6);
        final TextView NoItems = (TextView)findViewById(R.id.NoItems);
        NoItems.setVisibility(View.INVISIBLE);
        itemRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Item> ObjectList = new ArrayList<Item>();
                ArrayList<String> ObjectTitles = new ArrayList<String>();
                ArrayList<String> Image64 = new ArrayList<String>();
                ArrayList<String> PriceList = new ArrayList<String>();
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    Item tempItem = dsp.getValue(Item.class);
                    if(tempItem.getBought() != true)
                        ObjectList.add(tempItem);
                }
                for(Item item : ObjectList){
                    String title = item.getTitle().toString();
                    //Toast.makeText(List_Item.this, title, Toast.LENGTH_SHORT).show();
                    ObjectTitles.add(title);
                    String image64 = item.getPic().toString();
                    Image64.add(image64);
                    String price = item.getPrice().toString();
                    PriceList.add(price);
                }
                if(ObjectTitles.isEmpty()) {
                    NoItems.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }
               // ArrayAdapter<String> adapter = new ArrayAdapter<String>(List_Item.this, android.R.layout.simple_list_item_1, ObjectTitles);
                final CustomListAdapter adapter = new CustomListAdapter(List_Item.this, ObjectTitles, Image64, PriceList);
                progressBar.setVisibility(View.INVISIBLE);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
              // "WebOS","Ubuntu","Windows7","Max OS X"};
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mobileArray);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                int itemPosition = position;
                final String itemValue = (String)listView.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), itemValue, Toast.LENGTH_LONG).show();
                itemRef.child(itemValue).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final Item item = dataSnapshot.getValue(Item.class);
                        AlertDialog.Builder builder = new AlertDialog.Builder(List_Item.this);
                        String s1 = "<b>" + "Description: " + "</b>";
                        Spanned strMessage;
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                            strMessage = Html.fromHtml(s1, Html.FROM_HTML_MODE_COMPACT);
                        }
                        else
                            strMessage = Html.fromHtml(s1);
                        builder.setMessage(strMessage + item.getDescription() + "\n" + "Price: " + item.getPrice()).setTitle(item.getTitle()).setPositiveButton("View Item", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent viewItem = new Intent(List_Item.this, ViewItem.class);
                                viewItem.putExtra("itemname", itemValue);
                                startActivity(viewItem);
                                dialog.cancel();

                            }
                        }).setNegativeButton("No Thanks", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        LayoutInflater inflater = LayoutInflater.from(List_Item.this);
                        final View dialogLayout = inflater.inflate(R.layout.test, null);
                        ImageView img = (ImageView)dialogLayout.findViewById(R.id.goProDialogImage);
                        byte[] decode = Base64.decode(item.getPic().getBytes(), 0);
                        Bitmap bit = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                        //img.setMinimumHeight(500);
                        img.setImageBitmap(bit);
                        dialog.setView(dialogLayout);
                        dialog.show();

                        //builder.show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
