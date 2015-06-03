package info.androidhive.loginandregistration.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bpf.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.loginandregistration.R;



public class SearchByBarcode extends Activity {
    // JSON Node names
    EditText txtName;
    EditText txtPrice;
    EditText txtDesc;
    private ProgressDialog pDialog;
    TextView barcodeid;
    private   ListView list;
    JSONParser jsonParser = new JSONParser();
    Button get;
    Button scan;
    String id;
    String name;
    String price;
    String pid;
    String barcode;
    String description;
    InputStream is=null;
    String result=null;
    String line=null;
    private static final String url_product_detials ="http://bestpricefinder.eb2a.com/android_connect/searchbarcode.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCT = "product";
    private static final String TAG_PID = "pid";
    private static final String TAG_BARCODE = "barcode";
    private static final String TAG_NAME = "name";
    private static final String TAG_PRICE = "price";
    private static final String TAG_DESCRIPTION = "description";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_barcode);
        HandleClick hc = new HandleClick();
        findViewById(R.id.scan).setOnClickListener(hc);
        barcodeid = (TextView) findViewById(R.id.barcode);
        txtName = (EditText) findViewById(R.id.txtName);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        txtDesc = (EditText) findViewById(R.id.txtDesc);
        Button get = (Button) findViewById(R.id.get);
   //     ListView list=(ListView)findViewById(R.id.mylist);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String pid = ((TextView) view.findViewById(R.id.pid)).getText()
                        .toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        SearchByBarcode.class);
                // sending pid to next activity
                in.putExtra(TAG_PID, pid);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
            }
        });

        // Getting complete product details in background thread


        get.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //  new GetProductDetails().execute();
                barcodeid = (TextView) findViewById(R.id.barcode);

                String input = barcodeid.getText().toString();
                if (input.equals("5449000096258")) {
                    txtName.setText("Nestle water", TextView.BufferType.EDITABLE);
                    txtPrice.setText("2", TextView.BufferType.EDITABLE);
                    txtDesc.setText("Good for health", TextView.BufferType.EDITABLE);
                } else if (input.equals("544900009625")) {
                    txtName.setText("Jericho water", TextView.BufferType.EDITABLE);
                    txtPrice.setText("5", TextView.BufferType.EDITABLE);
                    txtDesc.setText("Good for health", TextView.BufferType.EDITABLE);
                } else if (input.equals("Scan cancelled.") || input.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please scan product's barcode",
                            Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Sorry, this product isn't available", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private class HandleClick implements View.OnClickListener {
        public void onClick(View arg0) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            switch(arg0.getId()){

                case R.id.scan:
                    intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                    break;

            }
            startActivityForResult(intent, 0);    //Barcode Scanner to scan for us
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    //    ListView list = (ListView)findViewById(R.id.mylist);

        if (requestCode == 0) {
            TextView tvStatus=(TextView)findViewById(R.id.tvStatus);
            TextView tvResult=(TextView)findViewById(R.id.barcode);
            if (resultCode == RESULT_OK) {
            //    tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
              //  tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
                String[] rank = new String[] {intent.getStringExtra("SCAN_RESULT")};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, rank);
                list.setAdapter(adapter);
            } else if (resultCode == RESULT_CANCELED) {
             //   tvStatus.setText("Press a button to start a scan.");
             //   tvResult.setText("Scan cancelled.");
                String[] rank = new String[] {"Scan cancelled."};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, rank);
                list.setAdapter(adapter);

            }
        }
        //super.onActivityResult(requestCode, resultCode, intent);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent1 = getIntent();
            finish();
            startActivity(intent1);
        }
    }
    class GetProductDetails extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SearchByBarcode.this);
            pDialog.setMessage("Loading product's best price");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Getting product details in background thread
         * */
        protected String doInBackground(String... params) {

            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    // Check for success tag
                    int success;



                    try {
                        // Building Parameters
                        List<NameValuePair> params = new ArrayList<NameValuePair>();

                        params.add(new BasicNameValuePair("barcode", barcodeid.getText().toString()));
                        // params.add(new BasicNameValuePair("barcode",barcode));

                        // getting product details by making HTTP request
                        // Note that product details url will use GET request
                        JSONObject json = jsonParser.makeHttpRequest(
                                url_product_detials, "GET", params);

                        // check your log for json response
                      //  Log.d("Single Product Details", json.toString());

                        // json success tag
                        success = json.getInt(TAG_SUCCESS);
                        if (success == 1) {
                            // successfully received product details
                            JSONArray productObj = json.getJSONArray(TAG_PRODUCT); // JSON Array

                            // get first product object from JSON Array
                            JSONObject product = productObj.getJSONObject(0);

                            // product with this pid found
                            // Edit Text
                            txtName = (EditText) findViewById(R.id.inputName);
                            txtPrice = (EditText) findViewById(R.id.inputPrice);
                            txtDesc = (EditText) findViewById(R.id.inputDesc);

                            // display product data in EditText
                            txtName.setText(product.getString(TAG_NAME));
                            txtPrice.setText(product.getString(TAG_PRICE));
                            txtDesc.setText(product.getString(TAG_DESCRIPTION));

                        }else{
                            // product with pid not found
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            });

            return null;
        }


        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once got all details
            pDialog.dismiss();
        }
    }

}