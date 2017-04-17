package com.john.inschool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Contact_feedback_query_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Contact_feedback_query_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Contact_feedback_query_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern =Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    private TextInputLayout name_input;
    private TextInputLayout email_input;
    private TextInputLayout phone_input;
    private TextInputLayout message_input;

    private Button submit_button;

    private String name,email,phone,message;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Contact_feedback_query_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Contact_feedback_query_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Contact_feedback_query_fragment newInstance(String param1, String param2) {
        Contact_feedback_query_fragment fragment = new Contact_feedback_query_fragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_feedback_query_fragment, container, false);

        name_input = (TextInputLayout) rootView.findViewById(R.id.name_input);
        email_input = (TextInputLayout) rootView.findViewById(R.id.Email_input);
        phone_input = (TextInputLayout) rootView.findViewById(R.id.phone_input);
        message_input = (TextInputLayout) rootView.findViewById(R.id.message_input);
        submit_button = (Button) rootView.findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = name_input.getEditText().getText().toString().trim();
                email = email_input.getEditText().getText().toString().trim();
                phone = phone_input.getEditText().getText().toString().trim();
                message = message_input.getEditText().getText().toString().trim();
//
//                Toast.makeText(getActivity() ,name , Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity() ,email , Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity() ,phone , Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity() ,message , Toast.LENGTH_SHORT).show();

                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
                    String userId = mDatabase.push().getKey();
                    Feedback feedback = new Feedback(name,email,phone,message);
                    mDatabase.child(userId).setValue(feedback);
                    Toast.makeText(getActivity() ,"Sent." , Toast.LENGTH_SHORT).show();

                }
        });



        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public boolean validateEmail(String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePhone(String password){
        return password.length() ==10;
    }
}
