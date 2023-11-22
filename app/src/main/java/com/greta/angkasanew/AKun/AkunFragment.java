package com.greta.angkasanew.AKun;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greta.angkasanew.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AkunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AkunFragment extends Fragment {
    TextView txtnama_lengkap, txt_email, txt_notelp;
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

        preferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        String namaprofil = preferences.getString("nama_lengkap", "-");
        txtnama_lengkap.setText(namaprofil);

        String emailprofil = preferences.getString("email", "-");
        txt_email.setText(emailprofil);

        String notelpprofil = preferences.getString("no_hp", "-");
        txt_notelp.setText(notelpprofil);
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
