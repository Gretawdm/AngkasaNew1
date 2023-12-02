package com.greta.angkasanew.AKun;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AkunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AkunFragment extends Fragment {
    EditText txtnama_lengkap, txt_email, txt_notelp;
    TextView gender,jabatan,id;
    Button btn_simpan;

    private View view;
    private SharedPreferences preferences;


  /*  private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView img_editProfile;*/

   /* public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img_editProfile.setImageBitmap(bitmap);
        }
    }*/


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AkunFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AkunFragment newInstance(String param1, String param2) {
        AkunFragment fragment = new AkunFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // @RequiresApi(api = Build.VERSION_CODES.0)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_akun, container, false);
        initprofile();
        return view;
    }

    private void initprofile() {
        txtnama_lengkap = view.findViewById(R.id.txtNamaLengkap);
        txt_email = view.findViewById(R.id.txtEmail);
        txt_notelp = view.findViewById(R.id.txtNoTelp);
        gender = view.findViewById(R.id.txtJenis_Kelamin);
        jabatan = view.findViewById(R.id.txtJabatan);
        id = view.findViewById(R.id.id_user);
        btn_simpan = view.findViewById(R.id.btn_simpan);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        txtnama_lengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtnama_lengkap.setFocusable(true);
                txtnama_lengkap.setFocusableInTouchMode(true);
                txtnama_lengkap.requestFocus();

                showKeyboard(requireContext(), txtnama_lengkap);
            }
        });

        txt_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_email.setFocusable(true);
                txt_email.setFocusableInTouchMode(true);
                txt_email.requestFocus();

                showKeyboard(requireContext(), txt_email);
            }
        });

        txt_notelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_notelp.setFocusable(true);
                txt_notelp.setFocusableInTouchMode(true);
                txt_notelp.requestFocus();

                showKeyboard(requireContext(), txt_notelp);
            }
        });


        preferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        String idprofil = preferences.getString("id_user", "-");
        id.setText(idprofil);

        String namaprofil = preferences.getString("nama_lengkap", "-");
        txtnama_lengkap.setText(namaprofil);

        String emailprofil = preferences.getString("email", "-");
        txt_email.setText(emailprofil);

        String notelpprofil = preferences.getString("no_hp", "-");
        txt_notelp.setText(notelpprofil);

        String genderprofil = preferences.getString("jenis_kelamin", "-");
        gender.setText(genderprofil);

        String jabatanprofil = preferences.getString("jabatan", "-");
        jabatan.setText(jabatanprofil);



      /*  String id_akun = preferences.getString("id_user", "-");
        id.setText(id_akun);*/
    }

    @Override
    public void onResume() {
        super.onResume();
        // Panggil initprofile() atau metode lain yang sesuai untuk memperbarui tampilan
        initprofile();
    }

        private void showKeyboard(Context context, View view) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        }

    private void update(){
        StringRequest request = new StringRequest(Request.Method.POST, Api.urlUpdateAkun, response -> {
            try {
                //ini ngambil data
                JSONObject jsonObject = new JSONObject(response);
                /*Log.d("JSON Response", response);*/
                int code = jsonObject.getInt("code");
                String status = jsonObject.getString("status");

                if (code == 200 && status.equals("Sukses")) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("no_hp", txt_notelp.getText().toString().trim());
                    editor.putString("email", txt_email.getText().toString().trim());
                    editor.putString("nama_lengkap", txtnama_lengkap.getText().toString().trim());
                    editor.apply();

                    //mengatur agar berubah
                    String notelpprofilAfterUpdate = preferences.getString("no_hp", "-");
                    Log.d("DEBUG", "No. Telp setelah perubahan: " + notelpprofilAfterUpdate);

                    String emailpprofilAfterUpdate = preferences.getString("email", "-");
                    Log.d("DEBUG", "Email  setelah perubahan: " + emailpprofilAfterUpdate);

                    String namalengkapprofilAfterUpdate = preferences.getString("nama_lengkap", "-");
                    Log.d("DEBUG", "Email  setelah perubahan: " + namalengkapprofilAfterUpdate);


                    Toast.makeText(getActivity(), "Berhasil di ubah", Toast.LENGTH_SHORT).show();
                    txt_notelp.setText(txt_notelp.getText().toString().trim());

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Update TextView di sini
                            txt_notelp.setText(txt_notelp.getText().toString().trim());
                            txt_email.setText(txt_email.getText().toString().trim());
                           /* txtnama_lengkap.setText(txtnama_lengkap.getText().toString().);*/
                        }
                    });
                  /*  Intent intent = new Intent(DetailDiskon.this, MainActivity.class);
                    startActivity(intent);*/
                }else{
                    Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "JSON ERROR:" + response, Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id_user", id.getText().toString().trim());
                map.put("nama_lengkap", txtnama_lengkap.getText().toString().trim());
                map.put("email", txt_email.getText().toString().trim());
                map.put("no_hp", txt_notelp.getText().toString().trim());
                map.put("jenis_kelamin", gender.getText().toString().trim());
                /*map.put("status", "Selesai");
                map.put("id_pemesanan", id_pemesanan.getText().toString().trim());*/
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        queue.add(request);
    }
}




        /*View view = inflater.inflate(R.layout.fragment_akun, container, false);
        Bundle bundle = getArguments();

        User user = (User) bundle.getSerializable("user");
        img_editProfile = view.findViewById(R.id.edit_imgprofile);

        byte[] byteArray = user.getImageByte();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        img_editProfile.setImageBitmap(bitmap);

        List<String> genderList = new ArrayList<>();
        genderList.add("Jenis Kelamin");
        genderList.add("Laki Laki");
        genderList.add("Perempuan");

        TextInputLayout edit_txt_nama_lengkap = view.findViewById(R.id.tilinput);
        TextInputLayout edit_txt_email = view.findViewById(R.id.tilinput1);
        TextInputLayout edit_txt_no_hp = view.findViewById(R.id.tilinput3);
        TextInputLayout edit_txt_password = view.findViewById(R.id.tilinput4);


        EditText txt_nama = (EditText) edit_txt_nama_lengkap.getEditText();
        EditText txt_email = (EditText) edit_txt_email.getEditText();
        EditText txt_no_hp = (EditText) edit_txt_no_hp.getEditText();
        EditText txt_password = (EditText) edit_txt_password.getEditText();
        Spinner sp_gender = view.findViewById(R.id.edit_sp_gender);
        Button btn_register = view.findViewById(R.id.btn_simpan);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, genderList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(adapter);


        txt_nama.setText(user.getNama_lengkap());
        txt_email.setText(user.getEmail());
        txt_no_hp.setText(user.getNo_hp());
        txt_password.setText(user.getPassword());
        sp_gender.setSelection(user.getGender().equals("Laki Laki") ? 1 : 2);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = img_editProfile.getDrawable();

                Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] image = stream.toByteArray();

                String nama = txt_nama.getText().toString();
                String email = txt_email.getText().toString();
                String no_hp = txt_no_hp.getText().toString();
                String password = txt_password.getText().toString();
                String gender = sp_gender.getSelectedItem().toString();

                User newUser = new User(nama, email, no_hp, password, gender);

                DatabaseConnection db = new DatabaseConnection();
                try{
                    db.updateUser(user.getEmail(), newUser, new DatabaseCallback<UserResponse>() {
                        @Override
                        public void onSuccess(UserResponse data) {
                            Toast.makeText(getContext(), data.getStatus(), Toast.LENGTH_SHORT).show();
                            if (data.getStatus().equals("User Updated")) {

                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", getContext().MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", email);
                                editor.putString("password", password);
                                editor.putBoolean("checkBox", true);
                                editor.apply();

                                Intent intent = new Intent(getActivity(),   AkunFragment.class);
                                startActivity(intent);

                            }
                        }

                        public void onError(Throwable t) {
                            Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch (JSONException e){
                    throw new RuntimeException(e);
                }
            }
        });*/




        // Inflate the layout for this fragment
