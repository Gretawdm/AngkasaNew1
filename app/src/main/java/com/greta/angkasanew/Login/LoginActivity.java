package com.greta.angkasanew.Login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.Main.MainActivity;
import com.greta.angkasanew.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmail, txtPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        btnLogin.setOnClickListener(v -> {
            if (validateInput()) {
                login();
            }
        });
    }

    private void login(){
        StringRequest request = new StringRequest(Request.Method.POST, Api.urlLogin, response -> {
            try {
                //ini ngambil data
                JSONObject jsonObject = new JSONObject(response);

                int code = jsonObject.getInt("code");
                String status = jsonObject.getString("status");

                if (code == 200 && status.equals("Sukses")) {
                    JSONArray dataArray = jsonObject.getJSONArray("data");

                    if (dataArray.length() > 0) {
                        JSONObject res = dataArray.getJSONObject(0);

                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("id_user", res.getString("id_user"));
                        editor.putString("nama_lengkap", res.getString("nama_lengkap"));
                        editor.putString("email", res.getString("email"));
                        editor.putString("no_hp", res.getString("no_hp"));
                        editor.putString("jenis_kelamin", res.getString("jenis_kelamin"));
                        editor.putString("jabatan", res.getString("jabatan"));
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("NAMA",  res.getString("nama_lengkap"));
                        startActivity(intent);
                        finish();


                       /* startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();*/

                        Toast.makeText(getApplicationContext(), "Login Sukses!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No data found in the response.", Toast.LENGTH_SHORT).show();
                    }
                    }else if (code == 401) {
                    Toast.makeText(this, "Password atau Email Salah", Toast.LENGTH_SHORT).show();
                } else if (code == 404 && !status.equals("Sukses")) {
                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "JSON ERROR", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", txtEmail.getText().toString().trim());
                map.put("password", txtPassword.getText().toString().trim());
                return map;
            }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
            }


        private boolean validateInput() {
            String email = txtEmail.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();

            boolean isValidEmail = false;
            boolean isValidPassword = false;

            if (email.isEmpty()) {
                txtEmail.setError("Tolong Isi Email");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                txtEmail.setError("Format email salah");
            }else {
                isValidEmail = true;
            }

            if(password.isEmpty()) {
                txtPassword.setError("Tolong Isi Passwoord");
            }else if (password.length() < 3) {
                txtPassword.setError("Password terlalu pendek");
            }else {
                isValidPassword = true;
            }
            btnLogin.setEnabled(isValidEmail && isValidPassword);

            return isValidEmail && isValidPassword;
        }
    }




           /* @Override
            public void onClick(View v) {
                if (txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                postDataUsingVolley(txtEmail.getText().toString() , txtPassword.getText().toString());
            }*/

   /* @Override*/
   /* public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flFragment);
        // Check if the fragment handles the back press
        if (fragment instanceof OnBackPressedListener && ((OnBackPressedListener) fragment).onBackPressed()) {
            // The fragment has handled the back press
            return;
        }

        // If the fragment didn't handle the back press, perform the default action
        super.onBackPressed();
    }*/

  /*  private void postDataUsingVolley(String email, String password) {
       // String url = "http://192.168.1.18/project_sem3/ApiLogin.php";
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        //req method untuk post data di api,, lalu manggil method post ny
        StringRequest request = new StringRequest(Request.Method.POST, Api.urlLogin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // and setting data to edit text as empty
                //txtEmail.setText("");
               // txtPassword.setText("");
                // on below line we are displaying a success toast message.

                try {
                    //ini ngambil data
                    JSONObject respObj = new JSONObject(response);
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
                        Toast.makeText(LoginActivity.this, "email dan password salah", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        },*//* new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(LoginActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();
                // and value pair to our parameters.
                params.put("email", email);
                params.put("password", password);
                // returning our params.
                return params;
            }
        };
        // a json object request.
        queue.add(request);
    }*/
