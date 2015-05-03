package com.example.alejandro.trycards;

/**
 * Created by Alejandro on 4/27/2015.
    */


    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.app.Activity;
    import android.widget.ScrollView;
    import android.widget.LinearLayout;
    import android.view.View;
    import android.widget.Button;
    import android.widget.TextView;
    import android.widget.EditText;
    import android.widget.CheckBox;
    import android.content.Intent;
    /**
     * Created by Alejandro on 4/27/2015.
     */

    public class  CreateCards extends Activity {


        // This is where and how the view is used

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            ScrollView sv = new ScrollView(this);
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            sv.addView(ll);
            TextView tv = new TextView(this);
            tv.setText("This is only a test.");
            ll.addView(tv);

            EditText et = new EditText(this);
            et.setText("Edit Text. . . Text... whoa.");
            ll.addView(et);

            Button b = new Button(this);
            b.setText("This button has this text");
            ll.addView(b);

            for (int i = 0; i < 20; i++) {
                CheckBox cb = new CheckBox(this);
                cb.setText("What are these?");
                ll.addView((cb));
            }
            this.setContentView(sv);

        }
    }


