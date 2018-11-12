package com.example.gilbertitoramos.morpionandroidstudio;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button [] btns;
    Jeu game;
    boolean fini;
    private int[] tabGagne;
    private TextView messagewin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
         for (int i=0;i<9;i++){
             String btnId="btn"+i;
             int resID = getResources().getIdentifier(btnId, "id", getPackageName());
             btns[i] = ((Button) findViewById(resID));
             outState.putString(btnId,btns[i].getText().toString());

         }



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onRestoreInstanceState(savedInstanceState);

        for (int i=0;i<9;i++){
            String btnId="btn"+i;
            int resID = getResources().getIdentifier(btnId, "id", getPackageName());
            btns[i] = ((Button) findViewById(resID));
            btns[i].setText(savedInstanceState.getString(btnId));


        }

    }



    public void changeButtonO(int i){
        String buttonID = "btn" + i ;
        int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
        btns[i] = ((Button) findViewById(resID));
        btns[i].setText("O");

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
        for(int i=0; i<3;i++){
            btns[t[i]].setTextColor(Color.RED);
        }
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
                //
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

    @Override
    public void onClick(View v) {
        boolean jouee=false;
        switch(v.getId()) {

            case R.id.btn0:
                if(!fini && btns[0].getText().equals("")) {
                    btns[0].setText("X");

                    game.setX(0);
                    jouee=true;
                }
                break;
            case R.id.btn1:
                if(!fini && btns[1].getText().equals("")) {
                    btns[1].setText("X");
                    game.setX(1);
                    jouee=true;
                }
                break;

            case R.id.btn2:
                if(!fini && btns[2].getText().equals("")) {
                    btns[2].setText("X");
                    game.setX(2);
                    jouee=true;
                }
                break;
            case R.id.btn3:
                if(!fini && btns[3].getText().equals("")) {
                    btns[3].setText("X");
                    game.setX(3);
                    jouee=true;
                }
                break;

            case R.id.btn4:
                if(!fini && btns[4].getText().equals("")) {
                    btns[4].setText("X");
                    game.setX(4);
                    jouee=true;
                }
                break;

            case R.id.btn5:
                if(!fini && btns[5].getText().equals("")) {
                    btns[5].setText("X");
                    game.setX(5);
                    jouee=true;
                }
                break;

            case R.id.btn6:
                if(!fini && btns[6].getText().equals("")) {
                    btns[6].setText("X");
                    game.setX(6);
                    jouee=true;
                }
                break;

            case R.id.btn7:
                if(!fini && btns[7].getText().equals("")) {
                    btns[7].setText("X");
                    game.setX(7);
                    jouee=true;
                }

                break;

            case R.id.btn8:
                if(!fini && btns[8].getText().equals("")) {
                    btns[8].setText("X");
                    game.setX(8);
                    jouee=true;
                }
                break;


        }
        if(jouee)
            Game();

    }
}
