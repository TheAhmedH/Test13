package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jokesandroid.JokesActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static String mJoke;
    public static Boolean mJokeLoadComplete = false;
    public InterstitialAd mInterstitialAd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        //Initializing the Ad
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
                                          @Override
                                          public void onAdClosed() {
                                              if (mJoke != null) {
                                                  AdRequest adRequest = new AdRequest.Builder()
                                                          .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                                                          .build();
                                                  mInterstitialAd.loadAd(adRequest);
                                                  //Tell the joke
                                                  tellJoke();
                                              }
                                          }
                                      }
        );


        //Setting a listener on the Tell Joke Button
        root.findViewById(R.id.tellJoke_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if Ad is loaded
                mJokeLoadComplete = mInterstitialAd.isLoaded();
                //show the Ad if it has been loaded
                if (mJokeLoadComplete) mInterstitialAd.show();
                else {
                    tellJoke();
                }
            }
        });


        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private void tellJoke() {
        new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {
                Intent intent = new Intent(getActivity(), JokesActivity.class);
                intent.putExtra("joke", result);
                startActivity(intent);
            }
        }.execute();
    }
}