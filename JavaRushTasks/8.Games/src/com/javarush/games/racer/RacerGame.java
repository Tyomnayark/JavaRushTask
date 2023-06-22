package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static  final int HEIGHT = 64;
    public static final  int CENTER_X = WIDTH/2;
    public static final int ROADSIDE_WIDTH =14;
    private  RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private boolean isGameStopped;
    private FinishLine finishLine;
    private static final int RACE_GOAL_CARS_COUNT=40;
    private ProgressBar progressBar;
    private int score;

    @Override
    public void initialize() {
        showMessageDialog(Color.NONE,"Space - запуск игры, UP - ускорение, LEFT & RIGHT - перемещение",Color.WHITE,20);
        showGrid(false);
        setScreenSize(WIDTH,HEIGHT);
        createGame();
    }
    private void createGame(){
        setTurnTimer(40);
        score=3500;
        isGameStopped = false;
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        drawScene();
    }
    private void drawScene(){
        drawField();
        roadMarking.draw(this);
        player.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
        roadManager.draw(this);
    }
    private void drawField(){
        for (int i = 0; i<HEIGHT; i++){
            for (int j = 0; j<WIDTH; j++){
                if (j<ROADSIDE_WIDTH||j>=WIDTH-ROADSIDE_WIDTH){
                    setCellColor(j,i,Color.GREEN);
                }else {
                    setCellColor(j, i,Color.GREY);
                }
            }
            setCellColor(CENTER_X, i, Color.WHITE);
        }
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x>=WIDTH||y>=HEIGHT||x<0||y<0){
            return;
        }
        super.setCellColor(x, y, color);
    }
    private  void moveAll(){
        roadMarking.move(player.speed);
        roadManager.move(player.speed);
        finishLine.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
        player.move();
    }

    @Override
    public void onTurn(int step) {
       if( roadManager.checkCrush(player)){
           gameOver();
           drawScene();
           return;
       }
       if(roadManager.getPassedCarsCount()>=RACE_GOAL_CARS_COUNT) {
           finishLine.show();
       }
       if (finishLine.isCrossed(player)){
           win();
           drawScene();
           return;
       }
       score-=5;
       setScore(score);
       moveAll();
        roadManager.generateNewRoadObjects(this);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case RIGHT:player.setDirection(Direction.RIGHT);
            break;
            case LEFT:player.setDirection(Direction.LEFT);
            break;
            case SPACE: if(isGameStopped){
                createGame();
            }
            break;
            case UP: player.speed = 2;
            break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if( (player.getDirection()==Direction.RIGHT&&key==Key.RIGHT)
            ||(player.getDirection()==Direction.LEFT&&key==Key.LEFT)){
            player.setDirection(Direction.NONE);
        }
        if(key == Key.UP){
            player.speed = 1;
        }
    }
    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU DIED", Color.WHITE, 75);
        stopTurnTimer();
        player.stop();
    }
    private void win(){
        isGameStopped=true;
        stopTurnTimer();
        showMessageDialog(Color.NONE,"YOU WIN!" ,Color.WHITE,75);

    }
}
