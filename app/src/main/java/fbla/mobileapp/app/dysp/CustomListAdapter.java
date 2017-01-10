package fbla.mobileapp.app.dysp;

/**
 * Created by nikhil on 1/9/2017.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> ItemNames;
    private final ArrayList<String> Image64;
    private final ArrayList<String> PriceList;

    public CustomListAdapter(Activity context, ArrayList<String> ItemNames,ArrayList<String> Image64, ArrayList<String> PriceList) {
        super(context, R.layout.listwithpic, ItemNames);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.ItemNames=ItemNames;
        this.Image64=Image64;
        this.PriceList = PriceList;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listwithpic, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(ItemNames.get(position));
        byte[] decode = Base64.decode(Image64.get(position).getBytes(), 0);
        Bitmap bit = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        imageView.setImageBitmap(bit);
        extratxt.setText("Price: "+PriceList.get(position));
        return rowView;

    };
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ArrayList<String> ItemNames2 = new ArrayList<String>();
        ItemNames2.clear();
        if (charText.length() == 0) {
            ItemNames2.addAll(ItemNames);
        } else {
            for (String wp : ItemNames) {
                if (wp.toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    ItemNames2.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }}