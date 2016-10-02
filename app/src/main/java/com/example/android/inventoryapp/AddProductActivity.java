package com.example.android.inventoryapp;

/**
 * Created by Bishesh on 02-10-2016.
 */
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Add Product Activity
 */
public class AddProductActivity extends AppCompatActivity {

    private int REQUEST_IMAGE_CAPTURE = 1;
    byte[] image;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data!=null) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView testImage = (ImageView) findViewById(R.id.testImage);
            testImage.setImageBitmap(imageBitmap);

            // Convert Bitmap to byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            image = stream.toByteArray();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final InventoryDBHelper db = new InventoryDBHelper(this);
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editTextProductName = (EditText) findViewById(R.id.product_name);
                EditText editTextQuantity = (EditText) findViewById(R.id.quantity);
                EditText editTextPrice = (EditText) findViewById(R.id.price);
                String productName = editTextProductName.getText().toString();
                if (productName.matches("")) {
                    Toast.makeText(AddProductActivity.this, "Please enter a Product Name.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sQuantity = editTextQuantity.getText().toString();
                if (sQuantity.matches("")) {
                    Toast.makeText(AddProductActivity.this, "Please enter quantity.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int quantity = Integer.parseInt(sQuantity);
                String sPrice = editTextPrice.getText().toString();
                if (sPrice.matches("")) {
                    Toast.makeText(AddProductActivity.this, "Please enter price.", Toast.LENGTH_SHORT).show();
                    return;
                }
                int price = Integer.parseInt(sPrice);
                // Check if image has been set or not
                if(image == null) {
                    Toast.makeText(AddProductActivity.this, "Please set an image.", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.insertData(productName, quantity, price, image);
                Intent mainIntent = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(mainIntent);
                String message = "Product Added!";
                Toast.makeText(AddProductActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        // Capture Image
        Button addImage = (Button) findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }
}
