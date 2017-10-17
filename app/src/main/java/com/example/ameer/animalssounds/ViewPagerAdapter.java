package com.example.ameer.animalssounds;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;

/**
 * Created by Ameer on 01/09/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {
    Activity activity;
    String[] animals;
    LayoutInflater inflater;
    boolean adLoadedSuccessfully=true;

    public ViewPagerAdapter(Activity activity, String[] animals) {
        this.activity = activity;
        this.animals = animals;
    }

    @Override
    public int getCount() {
        return animals.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater=( LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = inflater.inflate(R.layout.viewpager_item , container , false);
        final ImageView img =(ImageView)itemView.findViewById(R.id.imageAnimal);

        //start of native ad
        final NativeExpressAdView adView = itemView.findViewById(R.id.nativeAdView);

        //  AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        AdRequest request = new AdRequest.Builder().build();
        //  adView.loadAd(request);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
              //  Log.i("Ads", "onAdLoaded Native Ad");
adLoadedSuccessfully=true;
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            //    Log.i("Ads", "onAdFailedToLoad  Native Ad");
                adLoadedSuccessfully=false;

            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            //    Log.i("Ads", "onAdOpened  Native Ad");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            //    Log.i("Ads", "onAdLeftApplication  Native Ad");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            //    Log.i("Ads", "onAdClosed  Native Ad");
                adView.setVisibility(View.GONE);
            }
        });

        //end of native ad
        int animalImageId = activity.getResources().getIdentifier(animals[position],"drawable", activity.getPackageName());
        if ( ! (animals[position].equals("ad")) ){
            img.setImageResource(animalImageId);
        }
        if (animals[position].equals("ad")){
            adView.loadAd(request);
            if(! (adLoadedSuccessfully)) {
                animalImageId=activity.getResources().getIdentifier("rat","drawable",activity.getPackageName());
                img.setImageResource(animalImageId);
            }
        }

        container.addView(itemView);
Animation animation=AnimationUtils.loadAnimation(activity.getApplicationContext(),R.animator.zooming);
        img.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
              animation.setRepeatMode(Animation.INFINITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        img.startAnimation(animation);
        //end of animations
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewGroup)container).removeView((View)object);
    }
}
