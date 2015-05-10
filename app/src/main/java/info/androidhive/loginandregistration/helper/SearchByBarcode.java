package info.androidhive.loginandregistration.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scan_barcode);
        HandleClick hc = new HandleClick();
        //  findViewById(R.id.butQR).setOnClickListener(hc);
        findViewById(R.id.butProd).setOnClickListener(hc);
        //  findViewById(R.id.butOther).setOnClickListener(hc);
        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);
        barcodeid=(TextView)findViewById(R.id.tvResult);
        txtName=(EditText)findViewById(R.id.txtName);
        txtPrice=(EditText)findViewById(R.id.txtPrice);
        txtDesc=(EditText)findViewById(R.id.txtDesc);
        // button click event

    }
    private class HandleClick implements View.OnClickListener {
        public void onClick(View arg0) {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            switch(arg0.getId()){
            /*    case R.id.butQR:
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    break;*/
                case R.id.butProd:
                    intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                    break;
              /*  case R.id.butOther:
                    intent.putExtra("SCAN_FORMATS", "CODE_39,CODE_93,CODE_128,DATA_MATRIX,ITF,CODABAR");
                    break;*/
            }
            startActivityForResult(intent, 0);    //Barcode Scanner to scan for us
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            TextView tvStatus=(TextView)findViewById(R.id.tvStatus);
            TextView tvResult=(TextView)findViewById(R.id.tvResult);
            if (resultCode == RESULT_OK) {
                tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
                tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
            } else if (resultCode == RESULT_CANCELED) {
                tvStatus.setText("Press a button to start a scan.");
                tvResult.setText("Scan cancelled.");
            }
        }
    }
    public void check(){
        if(barcodeid.getText()=="6253501790077"){
            txtName.setText("Nestle water");
            txtPrice.setText("2");
            txtDesc.setText("Good for health");
        }
    }


}