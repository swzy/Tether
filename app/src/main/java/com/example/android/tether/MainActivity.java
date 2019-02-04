package com.example.android.tether;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.rendering.ModelRenderable;

import uk.co.appoly.arcorelocation.LocationMarker;
import uk.co.appoly.arcorelocation.LocationScene;


public class MainActivity extends AppCompatActivity {
    private LocationScene locationScene;
    private ModelRenderable andyRenderable;
    private ArSceneView arSceneView;
    private static final String TAG = "MainActivity"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Node node = new Node();
        node.setParent(arFragment.getArSceneView().getScene());
        node.setRenderable(andyRenderable);


        ModelRenderable.builder()
                .setSource(this, R.raw.andy)
                .build()
                .thenAccept(renderable -> andyRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Log.e(TAG, "Unable to load Renderable.", throwable);
                            return null;
                        });


//        CompletableFuture.allOf(andy)
//                .handle(
//                        (notUsed, throwable) ->
//                        {
//                            if (throwable != null) {
//                                DemoUtils.displayError(this, "Unable to load renderables", throwable);
//                                return null;
//                            }
//
//                            try {
//                                andyRenderable = andy.get();
//
//                            } catch (InterruptedException | ExecutionException ex) {
//                                DemoUtils.displayError(this, "Unable to load renderables", ex);
//                            }
//                            return null;
//                        });

        arSceneView
                .getScene()
                .setOnUpdateListener(
                        frameTime -> {

                            if (locationScene == null) {
                                locationScene = new LocationScene(this, this, arSceneView);
                                locationScene.mLocationMarkers.add(
                                        new LocationMarker(
                                                -0.119677,
                                                51.478494,
                                                getAndy()));
                            }

                            if (locationScene != null) {
                                locationScene.processFrame(frame);
                            }

                        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /***
         * Example Node of a 3D model
         *
         * @return
         */
        private Node getAndy() {
            Node base = new Node();
            base.setRenderable(andyRenderable);
            Context c = this;
            base.setOnTapListener((v, event) -> {
                Toast.makeText(
                        c, "Andy touched.", Toast.LENGTH_LONG)
                        .show();
            });
            return base;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
