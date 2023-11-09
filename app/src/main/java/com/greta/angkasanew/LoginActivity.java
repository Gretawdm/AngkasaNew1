package com.greta.angkasanew;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmail, txtPassword;
    private Button btnLogin;

    private String KEY_NAME = "NAMA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                postDataUsingVolley(txtEmail.getText().toString() , txtPassword.getText().toString());
            }
        });
    }

    private void postDataUsingVolley(String email, String password) {
        String url = "http://192.168.1.18/project_sem3/ApiLogin.php";
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        //req method untuk post data di api,, lalu manggil method post ny
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty
                txtEmail.setText("");
                txtPassword.setText("");
                // on below line we are displaying a success toast message.

                try {
                    // on below line we are parsing the response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);
                    // below are the strings which we
                    // extract from our json object.
                    String status = respObj.getString("status");
                    String message = respObj.getString("message");

                    // on below line we are setting this string s to our text view.
                    if(message.equals("success")){
                        String namaLengkap = respObj.getString("nama_lengkap");
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(KEY_NAME, namaLengkap);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "email dan password salahgabi", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(LoginActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("email", email);
                params.put("password", password);

                // at last we are
                // returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}