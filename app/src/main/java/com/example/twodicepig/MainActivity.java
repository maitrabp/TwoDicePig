package com.example.twodicepig;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String playerTurn;
    int player1TotalScore;
    int player2TotalScore;
    int playerTurnTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerTurn = "Player1";
        player1TotalScore = 0;
        player2TotalScore = 0;
        playerTurnTotal = 0;
        final Button roll = (Button) findViewById(R.id.rolldice);
        final Button hold = (Button) findViewById(R.id.hold);
        final TextView player1Total  = (TextView) findViewById(R.id.player1Total);
        final TextView player2Total = (TextView) findViewById(R.id.player2Total);
        final TextView currentPlayer = (TextView) findViewById(R.id.currentPlayer);
        final TextView turnTotal = (TextView) findViewById(R.id.turnTotal);
        final TextView winner = (TextView) findViewById(R.id.winner);
        final ImageView dice1 = (ImageView) findViewById(R.id.dice1);
        final ImageView dice2 = (ImageView) findViewById(R.id.dice2);
        final TextView turnTotalTag = (TextView) findViewById(R.id.textView11);
        currentPlayer.setText(playerTurn);
        player1Total.setText(String.valueOf(player1TotalScore));
        player2Total.setText(String.valueOf(player2TotalScore));
        turnTotal.setText(String.valueOf(playerTurnTotal));

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enable the button if it was disabled previously
                hold.setEnabled(true);
                Random rand = new Random();
                int rand1 = rand.nextInt(6) + 1;
                int rand2 = rand.nextInt(6) + 1;
                switch(rand1)
                {
                    case 1:
                        dice1.setImageResource(R.drawable.dice_one);
                        break;
                    case 2:
                        dice1.setImageResource(R.drawable.dice_two);
                        break;
                    case 3:
                        dice1.setImageResource(R.drawable.dice_three);
                        break;
                    case 4:
                        dice1.setImageResource(R.drawable.dice_four);
                        break;
                    case 5:
                        dice1.setImageResource(R.drawable.dice_five);
                        break;
                    case 6:
                        dice1.setImageResource(R.drawable.dice_six);
                        break;
                }
                switch(rand2)
                {
                    case 1:
                        dice2.setImageResource(R.drawable.dice_one);
                        break;
                    case 2:
                        dice2.setImageResource(R.drawable.dice_two);
                        break;
                    case 3:
                        dice2.setImageResource(R.drawable.dice_three);
                        break;
                    case 4:
                        dice2.setImageResource(R.drawable.dice_four);
                        break;
                    case 5:
                        dice2.setImageResource(R.drawable.dice_five);
                        break;
                    case 6:
                        dice2.setImageResource(R.drawable.dice_six);
                        break;
                }
                //If one of the dices are 1, then turn ends & turn total is 0
                if(((rand1 == 1) && (rand2 != 1)) || ((rand1 != 1) && (rand2 == 1)))
                {
                    if(playerTurn.equals("Player1"))
                    {
                        playerTurn = "Player2";
                        currentPlayer.setText(playerTurn);
                        playerTurnTotal = 0;
                        turnTotal.setText(String.valueOf(playerTurnTotal));
                    }
                    else
                    {
                        playerTurn = "Player1";
                        currentPlayer.setText(playerTurn);
                        playerTurnTotal = 0;
                        turnTotal.setText(String.valueOf(playerTurnTotal));
                    }
                } //if both of the dices are 1, their total score is lost & their turn ends
                else if((rand1==1) && (rand2 ==1))
                {
                    if(playerTurn.equals("Player1"))
                    {
                        playerTurnTotal = 0;
                        turnTotal.setText(String.valueOf(playerTurnTotal));
                        player1TotalScore = 0;
                        player1Total.setText(String.valueOf(player1TotalScore));
                        playerTurn = "Player2";
                        currentPlayer.setText(playerTurn);
                    }
                    else
                    {
                        playerTurnTotal = 0;
                        turnTotal.setText(String.valueOf(playerTurnTotal));
                        player2TotalScore = 0;
                        player2Total.setText(String.valueOf(player2TotalScore));
                        playerTurn = "Player1";
                        currentPlayer.setText(playerTurn);
                    }
                } //If both dices are same (excluding two ones), the player keeps their turn & must roll again (disable hold button)!
                else if (rand1 == rand2)
                {
                        playerTurnTotal += (rand1 + rand2);
                        turnTotal.setText(String.valueOf(playerTurnTotal));
                        hold.setEnabled(false);
                } //Stays their turn, and turnTotal keeps adding
                else
                {
                    playerTurnTotal += (rand1 + rand2);
                    turnTotal.setText(String.valueOf(playerTurnTotal));
                }
            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If player1 holds, then add their turn total to their total score & move on to next player (also clear turn total variable)
                if (playerTurn.equals("Player1")) {
                    player1TotalScore += playerTurnTotal;
                    player1Total.setText(String.valueOf(player1TotalScore));
                    playerTurn = "Player2";
                    currentPlayer.setText(playerTurn);
                    playerTurnTotal = 0;
                } //if player2 holds, then add their turn total to their total score & move on to next player (also clear turn total variable)
                else if (playerTurn.equals("Player2")) {
                    player2TotalScore += playerTurnTotal;
                    player2Total.setText(String.valueOf(player2TotalScore));
                    playerTurn = "Player1";
                    currentPlayer.setText(playerTurn);
                    playerTurnTotal = 0;
                }
                turnTotal.setText(String.valueOf(playerTurnTotal));
                //Check if any player won!
                if (player1TotalScore >= 50) {
                    dice1.setVisibility(View.GONE);
                    dice2.setVisibility(View.GONE);
                    turnTotal.setVisibility(View.GONE);
                    turnTotalTag.setVisibility(View.GONE);
                    roll.setVisibility(View.GONE);
                    hold.setVisibility((View.GONE));
                    winner.setVisibility(View.VISIBLE);
                    currentPlayer.setText("Player1");
                    winner.setText("CONGRATULATIONS PLAYER1, YOU WON!");
                } else if (player2TotalScore >= 50) {
                    dice1.setVisibility(View.GONE);
                    dice2.setVisibility(View.GONE);
                    turnTotal.setVisibility(View.GONE);
                    turnTotalTag.setVisibility(View.GONE);
                    roll.setVisibility(View.GONE);
                    hold.setVisibility((View.GONE));
                    winner.setVisibility(View.VISIBLE);
                    currentPlayer.setText("Player2");
                    winner.setText("CONGRATULATIONS PLAYER2, YOU WON!");
                }
            }
        });
    }
}
