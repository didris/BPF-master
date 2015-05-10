package info.androidhive.loginandregistration.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidhive.JSONParser;

import info.androidhive.loginandregistration.R;



public class SearchByBarcode extends Activity {
    // JSON Node names
    EditText txtName;
    EditText txtPrice;
    EditText txtDesc;
    private static final String TAG_SUCCESS = "success";
    private ProgressDialog pDialog;
    TextView barcodeid;
    JSONParser jsonParser = new JSONParser();
    Button get;
    Button scan;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_barcode);
        HandleClick hc = new HandleClick();
        findViewById(R.id.scan).setOnClickListener(hc);
        barcodeid=(TextView)findViewById(R.id.barcode);
        txtName=(EditText)findViewById(R.id.txtName);
        txtPrice=(EditText)findViewById(R.id.txtPrice);
        txtDesc=(EditText)findViewById(R.id.txtDesc);
        Button get=(Button) findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(barcodeid.getText()=="6253501790077"){
                    txtName.setText("Nestle water", TextView.BufferType.EDITABLE);
                    txtPrice.setText("2", TextView.BufferType.EDITABLE);
                    txtDesc.setText("Good for health", TextView.BufferType.EDITABLE);
                }
                else if (barcodeid.getText()=="5449000096258"){
                    txtName.setText("Jericho water", TextView.BufferType.EDITABLE);
                    txtPrice.setText("5", TextView.BufferType.EDITABLE);
                    txtDesc.setText("Good for health", TextView.BufferType.EDITABLE);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please scan product's barcode",
                            Toast.LENGTH_LONG).show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            TextView tvStatus=(TextView)findViewById(R.id.tvStatus);
            TextView tvResult=(TextView)findViewById(R.id.barcode);
            if (resultCode == RESULT_OK) {
                tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
                tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                tvStatus.setText("Press a button to start a scan.");
                tvResult.setText("Scan cancelled.");
            }
        }
    }
}