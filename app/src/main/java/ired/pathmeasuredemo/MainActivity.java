package ired.pathmeasuredemo;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Path path = new Path();
        path.moveTo(1,1);
        path.lineTo(2,2);

        PathMeasure pathMeasure = new PathMeasure(path,false);
        Log.i("mmq",pathMeasure.getLength()+"");
    }
}
