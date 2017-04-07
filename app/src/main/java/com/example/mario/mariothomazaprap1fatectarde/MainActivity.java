package com.example.mario.mariothomazaprap1fatectarde;

import android.app.ActionBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private View view;
    private FloatingActionButton fab_add;
    private ImageSwitcher imageSwitcher;
    private Button nextBtn, previewBtn;
    private Cart books;

    private Cart cart;

    private TextView price;
    private EditText amount;
    private TextView itemTotal;
    private TextView cartTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        price = (TextView) findViewById(R.id.txtPrice);
        amount = (EditText) findViewById(R.id.txtAmount);
        itemTotal = (TextView) findViewById(R.id.txtItemTotal);
        cartTotal = (TextView) findViewById(R.id.txtCartTotal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_add = (FloatingActionButton) findViewById(R.id.fab);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addItemToChart();

                Snackbar.make(view, R.string.added, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        nextBtn = (Button) findViewById(R.id.btnForwd);
        previewBtn = (Button) findViewById(R.id.btnReturn);

        books = new Cart();
        addBooks(books);

        cart = new Cart();


        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return myView;
            }
        });

        amount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    itemTotal.setText(String.valueOf(Double.valueOf(price.getText().toString()) * Double.valueOf(amount.getText().toString())));
                }
            }
        });

        previewBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                previewImage();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextImage();
            }
        });

        nextBtn.callOnClick();

    }

    private void nextImage(){
        imageSwitcher.setImageResource(books.nextBook().image);
        price.setText(String.valueOf(books.actualBook().price));
        amount.setText("0");
        itemTotal.setText("0");
    }

    private void previewImage(){
        imageSwitcher.setImageResource(books.prevBook().image);
        price.setText(String.valueOf(books.actualBook().price));
        amount.setText("0");
        itemTotal.setText("0");
    }

    private void addBooks(Cart cart){
        Book book = new Book(R.drawable.livro1, 10);
        cart.addItem(book);
        book = new Book(R.drawable.livro2, 20);
        cart.addItem(book);
        book = new Book(R.drawable.livro3, 30);
        cart.addItem(book);
        book = new Book(R.drawable.livro4, 40);
        cart.addItem(book);
        book = new Book(R.drawable.livro5, 50);
        cart.addItem(book);
        book = new Book(R.drawable.livro6, 60);
        cart.addItem(book);
    }


    private void addItemToChart(){
        cart.addItem(books.actualBook(), Integer.parseInt(amount.getText().toString()));
        cartTotal.setText(String.valueOf(cart.getTotal()));

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
