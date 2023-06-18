package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private Snake snake;
    private  Apple apple;
    private  int turnDelay;
    private  boolean isGameStopped;
    private static final int GOAL=100;
    private  int score;
    @Override
    public void initialize() {
        setScreenSize(WIDTH,HEIGHT);
        createGame();
    }
    private void createGame(){
        snake = new Snake(WIDTH/2,HEIGHT/2);
        turnDelay= 300;
        isGameStopped = false;
        score = 0;
        setScore(score);
        setTurnTimer(turnDelay);
        createNewApple();
        drawScene();
    }
    private void drawScene(){
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.LAVENDER,"");

            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int step) {
        if (!snake.isAlive){
            gameOver();
        }
        if (snake.getLength()>GOAL){
            win();
        }        snake.move(apple);
        if (apple.isAlive==false){
            createNewApple();
            score +=5;
            setScore(score);
            turnDelay-=5;
            setTurnTimer(turnDelay);
        }        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.SPACE && isGameStopped) {
            createGame();
        }

        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        }
    }
    private  void createNewApple(){
        apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        if (snake.checkCollision(apple)){
            createNewApple();
        }
    }
    private  void  gameOver(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "YOU ARE DIED", Color.WHITE, 30 );

    }
    private  void win(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "YOU WIN", Color.WHITE, 30 );
    }
}
