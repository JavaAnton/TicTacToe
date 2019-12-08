package de.example.anton.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int turnCount = 1;
    boolean isGameFinished = false;
    Button[] arrayOfButtons = new Button[9];
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button restartButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restartButton = findViewById(R.id.restartButton);

        restartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                restart();
            }
        });

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        Drawable buttonBackground = button1.getBackground();
        Log.d("Backgroundcolor", "Backgroundcolor of button: " + buttonBackground.toString());

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        arrayOfButtons[0] = button1;
        arrayOfButtons[1] = button2;
        arrayOfButtons[2] = button3;
        arrayOfButtons[3] = button4;
        arrayOfButtons[4] = button5;
        arrayOfButtons[5] = button6;
        arrayOfButtons[6] = button7;
        arrayOfButtons[7] = button8;
        arrayOfButtons[8] = button9;


    }



    public boolean checkIfEnd(String character) {
        if (arrayOfButtons[0].getText().equals(character) && arrayOfButtons[1].getText().equals(character) && arrayOfButtons[2].getText().equals(character)) {
            return true;
        } if (arrayOfButtons[3].getText().equals(character) && arrayOfButtons[4].getText().equals(character) && arrayOfButtons[5].getText().equals(character)) {
            return true;
        } if (arrayOfButtons[6].getText().equals(character) && arrayOfButtons[7].getText().equals(character) && arrayOfButtons[8].getText().equals(character)) {
            return true;
        } if (arrayOfButtons[0].getText().equals(character) && arrayOfButtons[3].getText().equals(character) && arrayOfButtons[6].getText().equals(character)) {
            return true;
        } if (arrayOfButtons[1].getText().equals(character) && arrayOfButtons[4].getText().equals(character) && arrayOfButtons[7].getText().equals(character)) {
            return true;
        } if (arrayOfButtons[2].getText().equals(character) && arrayOfButtons[5].getText().equals(character) && arrayOfButtons[8].getText().equals(character)) {
            return true;
        } if (arrayOfButtons[0].getText().equals(character) && arrayOfButtons[4].getText().equals(character) && arrayOfButtons[8].getText().equals(character)) {
            return true;
        } if (arrayOfButtons[6].getText().equals(character) && arrayOfButtons[4].getText().equals(character) && arrayOfButtons[2].getText().equals(character)) {
            return true;
        }
        return false;
    }

    public void restart() {
        for (int i = 0; i < 9; i++) {
            arrayOfButtons[i].setText("");
        }
        this.turnCount = 1;
        this.isGameFinished = false;
    }

    public void showDrawAlarm() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final AlertDialog closeAlertDialog = alert.create();
        this.isGameFinished = true;
        alert.setTitle("Draw!");
        alert.setCancelable(false);
        alert.setMessage("Nobody has won!");
        alert.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                restart();
            }
        });
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeAlertDialog.cancel();
            }
        });
        alert.show();
    }


    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        if (this.isGameFinished == false) {

            if (!(button.getText().length() > 0)) {

                int setXorO = this.turnCount % 2;

                if (setXorO == 1) {
                    button.setText("x");
                    this.turnCount++;
                } else {
                    button.setText("o");
                    this.turnCount++;
                }
                if (checkIfEnd("x")) {
                    isGameFinished = true;
                    Log.d("checkX", "checkIfEnd x");
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    final AlertDialog closeAlertDialog = alert.create();
                    alert.setTitle("Game Over!");
                    alert.setCancelable(false);
                    alert.setMessage("X gewinnt!");
                    alert.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restart();
                        }
                    });
                    alert.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            closeAlertDialog.cancel();
                        }
                    });
                    alert.show();
                }
                if (checkIfEnd("o")) {
                    isGameFinished = true;
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    final AlertDialog closeAlertDialog = alert.create();
                    alert.setTitle("Game Over!");
                    alert.setCancelable(false);
                    alert.setMessage("O gewinnt!");
                    alert.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            restart();
                        }
                    });
                    alert.setNegativeButton("Schließen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            closeAlertDialog.cancel();
                        }
                    });
                    alert.show();
                }
                if (this.turnCount == 10 && isGameFinished == false) {

                    showDrawAlarm();
                }


            }
        }


    }
}
