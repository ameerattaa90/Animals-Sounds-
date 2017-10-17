package com.example.ameer.animalssounds;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.MobileAds;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String[] animals = {
            "anhinga",
            "anteater",
            "armadillo",
            "baboon",
            "bear",
            "beaver",
            "bees",
            "bison",
            "blackbird",
            "blackjaguar",
            "ad",
            "bluejay",
            "boar",
            "buffalo",
            "bull",
            "camel",
            "canary",
            "capybara",
            "cardinal",
            "cat",
            "chaffinch",
            "chameleon",
            "cheetah",
            "chimpanzee",
            "cow",
            "coyote",
            "ad",
            "coypu",
            "crocodile",
            "crow",
            "deer",
            "dog",
            "dolphin",
            "donkey",
            "dove",
            "duck",
            "eagle",
            "elephant",
            "emu",
            "ferret",
            "flamingo",
            "fly",
            "ad",
            "fox",
            "frog",
            "geese",
            "gibbon",
            "giraffe",
            "goat",
            "gorilla",
            "grasshopper",
            "greywagtail",
            "guineafowl",
            "guineapig",
            "hamster",
            "hawk",
            "hedgehog",
            "hen",
            "ad",
            "hippo",
            "hoopoe",
            "horse",
            "hyena",
            "jackal",
            "kangaroo",
            "kingfisher",
            "koala",
            "lama",
            "lemur",
            "leopard",
            "lion",
            "loon",
            "lynx",
            "marmot",
            "meerkat",
            "mongoose",
            "monkey",
            "moose",
            "mosquito",
            "nightingale",
            "orangutan",
            "orca",
            "ostrich",
            "otter",
            "panda",
            "parrot",
            "peacock",
            "penquin",
            "pig",
            "polarbear",
            "porcupine",
            "puma",
            "quail",
            "rabbit",
            "raccoon",
           // "rat",
            "redpanda",
            "reindeer",
            "rhea",
            "rhino",
            "roe",
            "rooster",
            "seagull",
            "sealion",
            "sheep",
            "snake",
            "sparrow",
            "squirrel",
            "swan",
            "tiger",
            "turkey",
            "turtle",
            "walrus",
            "wasp",
            "whale",
            "wolf",
            "yak",
            "zebra",
            "zebu",
            "ad",
    };
    private FirebaseAnalytics mFirebaseAnalytics;
    ViewPager pager;
    ImageButton btnPreviews, btnNext, btnPlay, btnSpeakName;
    TextView txtName;
    ImageView img;
    private static MediaPlayer player;
    int currentPagePosition = 0;
    TextToSpeech t1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private AdView mAdView;
    AdRequest adRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation
        setContentView(R.layout.activity_main);
          MobileAds.initialize(this, "ca-app-pub-9439555193780784~6077562388");
        // start add modification
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //banner ad

       mAdView = (AdView) findViewById(R.id.adView);
        if(isNetworkAvailable()) {
            adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);

            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                  //  Log.i("Ads", "onAdLoaded");
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    // Code to be executed when an ad request fails.
                 //   Log.i("Ads", "onAdFailedToLoad");
                    adRequest = new AdRequest.Builder().build();
                    mAdView.loadAd(adRequest);
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                 //   Log.i("Ads", "onAdOpened");
                }

                @Override
                public void onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                //    Log.i("Ads", "onAdLeftApplication");
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when when the user is about to return
                    // to the app after tapping on an ad.
               //     Log.i("Ads", "onAdClosed");
                    hideBanner();
                }
            });
        }
/*
        //native add
       adView = (NativeExpressAdView)findViewById(R.id.nativeAdView);

      //  AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
         request = new AdRequest.Builder().build();
     //  adView.loadAd(request);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded Native Ad");
                adFinishedLoading=true;

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad  Native Ad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                Log.i("Ads", "onAdOpened  Native Ad");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication  Native Ad");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
                Log.i("Ads", "onAdClosed  Native Ad");
            }
        });
*/
        // end of modifications
        // Random animals
        Collections.shuffle(Arrays.asList(animals));

        txtName = (TextView) findViewById(R.id.txtName);
        img = (ImageView) findViewById(R.id.imageAnimal);
        txtName.setText(upperCaseFirstLetter(getString(getResources().getIdentifier(animals[0], "string", getPackageName()))));
        pager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, animals);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                boolean connctedToInternet =isNetworkAvailable();
if(animals[position].equals("ad") || !connctedToInternet){
  hideBanner();
}
    else{
    if(connctedToInternet)
  showBanner();

}
                //next line modified by me
                currentPagePosition = position;
                String ss = animals[position];
                String someStringFromXML = getString(getResources().getIdentifier(ss, "string", getPackageName()));
                txtName.setText(upperCaseFirstLetter(someStringFromXML));
                //  txtName.setText(upperCaseFirstLetter(animals[position]));
                if (player != null && player.isPlaying()){
                    player.stop();

                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        btnPreviews = (ImageButton) findViewById(R.id.btnPreviews);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPlay = (ImageButton) findViewById(R.id.btnSpeak);
        btnSpeakName = (ImageButton) findViewById(R.id.btnSpeakName);
        btnSpeakName.setOnClickListener(this);
        btnPreviews.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPlay.setOnClickListener(this);



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

/*
    //option menu start
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu); // set your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        Locale locale = null;
        switch (item.getItemId()) {
            case R.id.item_Arabic:
                locale = new Locale("ar");
                Toast.makeText(this, "Arabic", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_English:
                locale = new Locale("en");
                Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_French:
                locale = new Locale("fr");
                Toast.makeText(this, "French", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_Russian:
                locale = new Locale("ru");
                Toast.makeText(this, "Russeian", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_China:
                locale = new Locale("ch");
                Toast.makeText(this, "China", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_Hindi:
                locale = new Locale("hi");
                Toast.makeText(this, "India", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_Spain:
                locale = new Locale("es");
                Toast.makeText(this, "Spain", Toast.LENGTH_SHORT).show();
                break;

        }

        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
        return true;
    }
    //end of option menu code
    */
    private String upperCaseFirstLetter(String animal) {
        StringBuilder str = new StringBuilder(animal);
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSpeakName: {
               if ((Locale.getDefault().getLanguage().equals("ar")) ) {
                    int soundId = this.getResources().getIdentifier("voice_" + animals[currentPagePosition], "raw", this.getPackageName());
                    if (player == null) {
                        player = MediaPlayer.create(this, soundId);
                        player.setLooping(false);
                        player.setVolume(1.0f, 1.0f);
                        player.start();
                    } else {
                        player.stop();
                        player.release();
                        player = null;
                        player = MediaPlayer.create(this, soundId);
                        player.setLooping(false);
                        player.setVolume(1.0f, 1.0f);
                        player.start();
                    }


                }
                else{
                    t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {

                                t1.setLanguage(Locale.getDefault());
                                String text = txtName.getText().toString();

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    t1.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
                                } else {
                                    t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                                }
                            }
                        }
                    });


                }
            }
            break;

            case R.id.btnPreviews: {
                if (pager.getCurrentItem() == 0)
                    pager.setCurrentItem(animals.length - 1, true);
                else
                    pager.setCurrentItem(pager.getCurrentItem() - 1, true);
            }
            break;
            case R.id.btnNext: {

                if (pager.getCurrentItem() == animals.length - 1)
                    pager.setCurrentItem(0, true);
                else
                    pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            }
            break;

            case R.id.btnSpeak: {
                int animalSoundId = this.getResources().getIdentifier(animals[currentPagePosition], "raw", this.getPackageName());
                // int animalSoundId = this.getResources().getIdentifier(txtName.getText().toString().toLowerCase(), "raw", this.getPackageName());
                if (player == null) {
                    player = MediaPlayer.create(this, animalSoundId);
                    player.setLooping(false);
                    player.setVolume(1.0f, 1.0f);
                    player.start();
                } else {
                    player.stop();
                    player.release();
                    player = null;
                    player = MediaPlayer.create(this, animalSoundId);
                    player.setLooping(false);
                    player.setVolume(1.0f, 1.0f);
                    player.start();
                }
            }
            default:
                break;
        }
    }



    @Override
    public void onStart() {
        super.onStart();
       // Toast.makeText(this, "Activity Started Succecfully", Toast.LENGTH_SHORT).show();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.ameer.animalssounds/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);

//start of fixing deprecated
         /* If you’re logging an action on an item that has already been added to the index,
   you don’t have to add the following update line. See
   https://firebase.google.com/docs/app-indexing/android/personal-content#update-the-index for
   adding content to the index */
      // FirebaseAppIndex.getInstance().update(getIndexable());
       // FirebaseUserActions.getInstance().start(getAction());
        //end of fixing deprecated
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.ameer.animalssounds/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();


//start of fixing deprecated
      //  FirebaseUserActions.getInstance().end(getAction());
        super.onStop();
        //end of fixing deprecated
    }

    private void showBanner() {
        mAdView.resume();
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setVisibility(View.VISIBLE);
    }
    private void hideBanner() {
        mAdView.pause();
        mAdView.setVisibility(View.GONE);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
