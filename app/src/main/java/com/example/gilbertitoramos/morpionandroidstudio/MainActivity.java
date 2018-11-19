package com.example.gilbertitoramos.morpionandroidstudio;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button [] btns;
    Jeu game;
    boolean fini;
    private int[] tabGagne;
    private TextView messagewin;
    boolean jouee=false;
    Toolbar t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* toolbar */
        t= (Toolbar) findViewById(R.id.toolbar_g);
        setSupportActionBar(t);
        getSupportActionBar().setTitle("Tic Tac Toe");
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_foreground);

        /* toolbar */


        messagewin= findViewById(R.id.msgwin);
        tabGagne=new int[3];
        btns= new Button[10];
        game= new Jeu();
        InitGame();
        btns[9]=((Button) findViewById(R.id.newgame));
        btns[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitGame();
                messagewin.setText("match start !!");
                btns[9].setTextColor(Color.BLACK);
                }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    /* Options Item Selected*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context;
        Resources resources;


        switch (item.getItemId()){

            case R.id.Francais:
                Toast.makeText(getApplication(), "francais!",
                        Toast.LENGTH_LONG).show();


                break;
            case R.id.Anglais:
                Toast.makeText(getApplication(), "Anglais!",
                        Toast.LENGTH_LONG).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public Button[] ListeBtnsLoad(){

        for (int i=0;i<9;i++){
             String btnId="btn"+i;
             int resID = getResources().getIdentifier(btnId, "id", getPackageName());
             btns[i] = ((Button) findViewById(resID));
           }
        return btns;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (Button btn : ListeBtnsLoad()){
               outState.putString(""+btn.getId(),btn.getText().toString());
        }
    }

  @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);
        for (Button btn: ListeBtnsLoad()
             ) {

            btn.setText(savedInstanceState.getString(""+btn.getId()));
        }
    }

    public void changeButtonO(int i){
        ListeBtnsLoad()[i].setText("O");
    }
    public void InitGame (){
        for(int i=0; i<9;i++){
            String buttonID = "btn" + i ;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            btns[i] = ((Button) findViewById(resID));
            btns[i].setText("");
            btns[i].setOnClickListener(this);
            btns[i].setTextColor(Color.BLACK);

        }

        fini=false;
        game.initialise();
        for(int i=0; i<2;i++)
            tabGagne[i]=-1;
    }

    public void marque(int[] t){
        for(int i=0; i<3;i++)
            btns[t[i]].setTextColor(Color.RED);
    }
    public void Game(){

        if(game.gagnant("X",tabGagne)){
            fini = true;
            messagewin.setText("X win");
            marque(tabGagne);
            btns[9].setBackgroundColor(Color.BLUE);
        }
        else {
            if (!game.isPartieNulle()) {

                int cellule = game.getO();
                changeButtonO(cellule);
                if(game.gagnant("O",tabGagne)){
                    fini = true;
                    marque(tabGagne);
                    messagewin.setText("O gagne!");
                    btns[9].setTextColor(Color.BLUE);
                }
            }
            else
            {
                fini = true;
                messagewin.setText("Partie nulle!");
                btns[9].setTextColor(Color.BLUE);
            }
        }
    }

    public void BtnSetX(int position){
        btns[position].setText("X");
        game.setX(position);
        jouee=true;
    }

    @Override
    public void onClick(View v) {
        jouee=false;
        switch(v.getId()) {

            case R.id.btn0:
                if(!fini && btns[0].getText().equals(""))
                    BtnSetX(0);

                break;

            case R.id.btn1:
                if(!fini && btns[1].getText().equals(""))
                   BtnSetX(1);
                break;

            case R.id.btn2:
                if(!fini && btns[2].getText().equals(""))
                    BtnSetX(2);
                break;

            case R.id.btn3:
                if(!fini && btns[3].getText().equals(""))
                    BtnSetX(3);
                break;

            case R.id.btn4:
                if(!fini && btns[4].getText().equals(""))
                    BtnSetX(4);
                break;

            case R.id.btn5:
                if(!fini && btns[5].getText().equals(""))
                   BtnSetX(5);
                break;

            case R.id.btn6:
                if(!fini && btns[6].getText().equals(""))
                    BtnSetX(6);
                break;

            case R.id.btn7:
                if(!fini && btns[7].getText().equals(""))
                    BtnSetX(7);
                break;

            case R.id.btn8:
                if(!fini && btns[8].getText().equals(""))
                    BtnSetX(8);
                break;

        }
        if(jouee)
            Game();
    }
}
