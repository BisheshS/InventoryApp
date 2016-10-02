package com.example.android.inventoryapp;

/**
 * Created by Bishesh on 02-10-2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *   Item List Adapter.
 */
public class ItemListAdapter extends ArrayAdapter<String> {

    public ItemListAdapter(Context context, ArrayList<String> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final String currentItem = getItem(position);

        TextView setTextItem = (TextView) listItemView.findViewById(R.id.text_1);
        setTextItem.setText(currentItem);
        Button btn = (Button) listItemView.findViewById(R.id.track_item);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final InventoryDBHelper db = new InventoryDBHelper(getContext());
                int pos = currentItem.indexOf("\nQuantity");
                final String subProductName = currentItem.substring(0, pos);
                final Cursor cur = db.getData(subProductName);
                if (cur.moveToFirst()) {
                    int quantity = cur.getInt(cur.getColumnIndex(InventoryContract.InventoryEntry.COLUMN_QUANTITY));
                    if (quantity > 0) {
                        db.updateData(subProductName, quantity, -1);
                        Toast.makeText(getContext(), "Refresh!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "It's empty! Order Now!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return listItemView;
    }
}
