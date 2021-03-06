package com.john.inschool;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Programsfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Programsfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Programsfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Programsfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Programsfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Programsfragment newInstance(String param1, String param2) {
        Programsfragment fragment = new Programsfragment();
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
        View rootView = inflater.inflate(R.layout.fragment_programsfragment, container, false);

        BannerSlider bannerSlider = (BannerSlider) rootView.findViewById(R.id.slider_programs);
        Button button = (Button) rootView.findViewById(R.id.lawn_tennis);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_programs , new Programs_Lawn_Fragment()).addToBackStack(null).commit();
            }
        });

        bannerSlider.addBanner(new DrawableBanner(R.drawable.programsimage1));
        bannerSlider.addBanner(new DrawableBanner(R.drawable.programsimage3));



        TextView link = (TextView) rootView.findViewById(R.id.kys_programs);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://d.vediorockerok.com")));
            }
        });

        TextView fb = (TextView) rootView.findViewById(R.id.fb_from_programs);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/Alcon-Tennis-Academy/116931401797088")));
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
}
