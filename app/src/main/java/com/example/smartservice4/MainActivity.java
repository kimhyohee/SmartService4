package com.example.SmartService4;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private GoogleMap myMap;
    private SearchView mySearchView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);
        final ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        String[] list2 = new String[2];
        list2[0]="교통정보";
        list2[1]="주변장소";

        ArrayAdapter spinnerAdapter;
        spinnerAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,list2);
        spinner.setAdapter(spinnerAdapter);

        mySearchView = findViewById(R.id.searchV);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapmap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        myMap = googleMap;

        LatLng CHEONGJU = new LatLng(36.628, 127.457);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(CHEONGJU);
        String text = "차량 넘버 : 2449 /n 운전자 : 김은진 /n 위치 : 충북 청주시 서원구 충대로 1/n 사용상태" +
                "ON/n온도 : 30도/n 습도 : 10%/n 충격량 : 10%/n 자세히보기";
        tvLinkify.setText(text);

        Linkify.TransformFilter mTransform = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };
        Pattern pattern1 = Pattern.compile("자세히보기");
        Linkify.addLinks(tvLinkify, pattern1, "http://naver.com",null,mTransform);



        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.truck1);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap mMarker = Bitmap.createScaledBitmap(b, 180, 180, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(mMarker));

        myMap.addMarker(markerOptions);

        myMap.moveCamera(CameraUpdateFactory.newLatLng(CHEONGJU));
        myMap.animateCamera(CameraUpdateFactory.zoomTo(17));
    }

}
public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView txtPhone1 = (TextView)findViewById(R.id.txtPhone1);
        Linkify.addLinks(txtPhone1, Linkify.PHONE_NUMBERS);

        TextView txtPhone2 = (TextView)findViewById(R.id.txtPhone2);
        Linkify.addLinks(txtPhone2, Linkify.PHONE_NUMBERS);

        TextView txtWeb = (TextView)findViewById(R.id.txtWeb);
        Linkify.addLinks(txtWeb, Linkify.WEB_URLS);
    }
}

