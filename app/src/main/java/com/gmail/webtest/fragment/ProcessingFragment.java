package com.gmail.webtest.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gmail.webtest.R;
import com.gmail.webtest.processing.TestSketch;

import processing.android.PFragment;
import processing.core.PApplet;

public class ProcessingFragment extends Fragment {
    private PApplet sketch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_processing, container, false);

        final FrameLayout frameLayout = view.findViewById(R.id.container);

        //processingのスクリーンサイズを設定するためには、viewのサイズが安定する必要がある
        frameLayout.post(new Runnable() {
            @Override
            public void run() {
                sketch = new TestSketch(frameLayout.getWidth(), frameLayout.getHeight());
                PFragment fragment = new PFragment(sketch);
                //castableなので問題なし
                fragment.setView(frameLayout, getActivity());
            }
        });


        return view;
    }

    /**
     * The onRequestPermissionsResult() method in the main activity is needed in the case the app uses any dangerous permissions.
     * If missing, the results of requesting the permission to the user will not reach the sketch, and it may fail to work properly.
     * onNewIntent() is also needed so the sketch can handle intents sent to the main activity.
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (sketch != null) {
            sketch.onRequestPermissionsResult(
                    requestCode, permissions, grantResults);
        }
    }

    public void onNewIntent(Intent intent){
        System.out.println("onNewIntent1 called.");

        if(sketch != null){
            try {
                sketch.onNewIntent(intent);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
