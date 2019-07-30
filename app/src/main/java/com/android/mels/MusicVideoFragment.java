package com.android.mels;

/*
    Tanggal Pengerjaan: 03-Mei-2019
    NIM     : 10116514
    Nama    : Albert Afriadi Sigiro
    Kelas   : AKB-12
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ceritamahasiswaa.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MusicVideoFragment extends Fragment {


    public MusicVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music_video, container, false);
    }

}
