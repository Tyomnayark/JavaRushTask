package com.javarush.games.moonlander;


import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;
    public static  final  int HEIGHT = 64;
    private Rocket rocket;
    private GameObject landscape;
    private GameObject platform;
    private  boolean isUpPressed;
    private  boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isGameStopped;
    private int score;
    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH,HEIGHT);
        createGame();
    }
    private void createGame(){
        isLeftPressed =false;
        isRightPressed = false;
        isUpPressed = false;
        isGameStopped=false;
        score = 1000;
        setTurnTimer(50);
        createGameObjects();
        drawScene();
    }
    private void drawScene(){
        for (int i = 0; i<WIDTH; i++){
            for (int j = 0; j<HEIGHT; j++){
                setCellColor(i,j,Color.DARKBLUE);
            }
        }
        rocket.draw(this);
        landscape.draw(this);
    }
    private void createGameObjects(){
        rocket = new Rocket(WIDTH/2,0);
        landscape= new GameObject(0,25,ShapeMatrix.LANDSCAPE);
        platform = new GameObject(23,HEIGHT-1,ShapeMatrix.PLATFORM);
    }

    @Override
    public void onTurn(int step) {
        if (score>0){
            score-=15;
        }else{
            score=0;
        }
        rocket.move(isUpPressed,isLeftPressed,isRightPressed);
        check();
        setScore(score);
        drawScene();

    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if( x>=WIDTH || y>=HEIGHT||x<0||y<0){
            return;
        }
        super.setCellColor(x, y, color);
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key)
        {
            case UP: isUpPressed = true;
            break;
            case RIGHT: isRightPressed = true;
            isLeftPressed = false;
            break;
            case LEFT: isLeftPressed = true;
            isRightPressed = false;
            break;
            case SPACE: if (isGameStopped){
                createGame();
            }
            break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key){
            case UP: isUpPressed = false;
                break;
            case RIGHT: isRightPressed = false;
                break;
            case LEFT: isLeftPressed = false;
                break;
        }
    }
    private  void check(){
        if(rocket.isCollision(platform)&& rocket.isStopped()&&rocket.returnSpeed()<1){
          win();
            return;
        }
        if(rocket.isCollision(platform)&& rocket.isStopped()&&rocket.returnSpeed()>1){
            gameOver();
            return;
        }
        if (score ==0){
            gameOver();
            return;
        }
        if (rocket.isCollision(landscape)){
            gameOver();
        }

    }
    private void win(){
        isGameStopped=true;
        rocket.land();
        stopTurnTimer();
        showMessageDialog(Color.NONE, "You win!\n"+"score: "+score, Color.WHITE, 75);
//        showMessageDialog(Color.NONE, String.valueOf((int)(rocket.returnSpeed()*10)), Color.WHITE, 75);
    }
    private  void gameOver(){
        rocket.crash();
        isGameStopped = true;
        score = 0;
        showMessageDialog(Color.NONE,"You died", Color.WHITE, 75);
//        showMessageDialog(Color.NONE, String.valueOf((int)(rocket.returnSpeed()*10)), Color.WHITE, 75);
        stopTurnTimer();
    }

}
