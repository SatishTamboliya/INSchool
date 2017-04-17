package com.john.inschool;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Galleryfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Galleryfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Galleryfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Galleryfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Galleryfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Galleryfragment newInstance(String param1, String param2) {
        Galleryfragment fragment = new Galleryfragment();
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
        View rootView = inflater.inflate(R.layout.fragment_galleryfragment, container, false);

        BannerSlider bs = (BannerSlider) rootView.findViewById(R.id.slider1);

        bs.addBanner(new RemoteBanner("https://scontent-hkg3-1.xx.fbcdn.net/v/t34.0-12/17760017_412637032445238_3991699630688447844_n.jpg?oh=1b41e1bdee33319d4424d35d8a9740d5&oe=58E860A5"));
        bs.addBanner(new RemoteBanner("https://scontent-hkg3-1.xx.fbcdn.net/v/t34.0-12/17760155_412637042445237_5318310555719947495_n.jpg?oh=9dad48173a636db39496a921acecd5fd&oe=58E78FEC"));
        bs.addBanner(new RemoteBanner("https://scontent-hkg3-1.xx.fbcdn.net/v/t34.0-12/17796101_412635972445344_3462819886732472617_n.jpg?oh=b3c0a1f6f9c2a299400c3c08b1de2d6e&oe=58E85E5B"));
        bs.addBanner(new RemoteBanner("https://scontent-hkg3-1.xx.fbcdn.net/v/t34.0-12/17523360_412636372445304_1189610919673881987_n.jpg?oh=54817d962779c0a7410b65fac6c58b65&oe=58E78912"));
        bs.addBanner(new RemoteBanner("https://scontent-hkg3-1.xx.fbcdn.net/v/t34.0-12/17796483_412635389112069_5786443172481776130_n.jpg?oh=0a1f4f881fb957a6b7d207c451fef5ee&oe=58E8C4BC"));
        bs.addBanner(new RemoteBanner("https://scontent-hkg3-1.xx.fbcdn.net/v/t34.0-12/17759910_412635385778736_1814914217528641914_n.jpg?oh=256151c076c302eda7b6ee4c9791a5c6&oe=58E78A83"));
        bs.addBanner(new RemoteBanner("https://scontent.fdel3-1.fna.fbcdn.net/v/t34.0-12/17974654_416582895383985_230200403_n.jpg?oh=34a3eb0cc9d2f6a8ad6896f03a444841&oe=58F67581"));
        bs.addBanner(new RemoteBanner("https://scontent.fdel3-1.fna.fbcdn.net/v/t34.0-12/17909343_416582892050652_552740738_n.jpg?oh=61505de2e9f20b541bb3256de6f979b8&oe=58F627C7"));
        bs.addBanner(new RemoteBanner("https://scontent.fdel3-1.fna.fbcdn.net/v/t34.0-12/17973952_416582898717318_1674672290_n.jpg?oh=3e8dd6e7d284a4db873e57669c405cf1&oe=58F65D27"));
        bs.addBanner(new RemoteBanner("https://scontent.fdel3-1.fna.fbcdn.net/v/t34.0-12/17974259_416582902050651_1959454277_n.jpg?oh=f02d62dc598e8c9998748d2a019347d9&oe=58F69511"));
        bs.addBanner(new RemoteBanner("https://scontent.fdel3-1.fna.fbcdn.net/v/t34.0-12/17857217_412637179111890_1207664156_n.jpg?oh=dc69bcdea77d73b59e271498ecd378ed&oe=58F66665"));
        bs.addBanner(new RemoteBanner("https://scontent.fdel3-1.fna.fbcdn.net/v/t34.0-12/17857291_412636512445290_1536844261_n.jpg?oh=401f7e060a9520d802e1415558f2a2be&oe=58F68DCA"));
        bs.addBanner(new RemoteBanner("https://scontent.fdel3-1.fna.fbcdn.net/v/t34.0-12/17857261_412635449112063_1954710662_n.jpg?oh=2518a1ed8c4bfc4d60f611b5fe9996fb&oe=58F637BD"));
        bs.addBanner(new RemoteBanner("https://scontent.fdel3-1.fna.fbcdn.net/v/t34.0-12/17842325_412635445778730_592467587_n.jpg?oh=3ad98450eb3c5999ac3dd696063a3ed5&oe=58F62EE0"));



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
}
