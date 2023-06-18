package com.javarush.games.minesweeper;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private static final String FLAG="\uD83D\uDEA9";
    private int countFlags;
    private  boolean isGameStopped=false;
    private int countClosedTiles = SIDE*SIDE;
    private  int score;
    private boolean isFail = false;
    private boolean isFirstTurn = true;

    private static final String MINE = "\uD83D\uDCA3";


    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped==true){
            restart();
            return;
        }
        openTile(x,y);
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) ==1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.BEIGE);
                setCellValue(x,y,"");
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1 ; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1 ; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                GameObject gameObject = gameField[y][x];
                if (!gameObject.isMine) {
                    for (GameObject neighbor : getNeighbors(gameObject)) {
                        if (neighbor.isMine) {
                            gameObject.countMineNeighbors++;
                        }
                    }
                }
            }
        }
    }

    private void openTile(int x, int y) {

        GameObject gameObject = gameField[y][x];

        if (gameObject.isFlag||gameObject.isOpen||isGameStopped==true){
            return;
        }

        if (gameObject.isMine) {
            if (isFirstTurn==true){
                restart();
                openTile(x, y);
                return;
            }
            isFail= true;
            setCellValue(gameObject.x, gameObject.y, MINE);
            gameObject.isOpen = true;
            setCellValueEx(x,y,Color.RED,MINE);
            gameOver();
            return;

        } else if (gameObject.countMineNeighbors==0){
            gameObject.isOpen = true;
            isFirstTurn=false;
            countClosedTiles--;
            score+=5;
            setScore(score);

            if (countClosedTiles==countMinesOnField&&isFail==false){
                win();
                return;
            }
            setCellColor(x, y, Color.GREEN);
            setCellValue(x,y,"");
            List<GameObject> gameObjectList = getNeighbors(gameObject);
            for (GameObject g:gameObjectList) {
                if (g.isOpen==false) {
                    openTile(g.x, g.y);
                }
            }
            return;
        }
        setCellNumber(x, y, gameObject.countMineNeighbors);
        gameObject.isOpen = true;
        isFirstTurn=false;

        score+=5;
        setScore(score);
        countClosedTiles--;
        setCellColor(x, y, Color.GREEN);
        if (countClosedTiles==countMinesOnField&&isFail==false){
            win();
            return;
        }

    }
    private void markTile(int x, int y) {
        if (isGameStopped) {
            return;
        }
        GameObject gameObject = gameField[y][x];

        if (!gameObject.isFlag && !gameObject.isOpen) {
            if (countFlags != 0) {
                countFlags--;
                gameObject.isFlag = true;
                setCellValue(x, y, FLAG);
                setCellColor(x, y, Color.YELLOW);
            }
        } else if (gameObject.isFlag) {
            countFlags++;
            gameObject.isFlag = false;
            setCellValue(x, y, "");
            setCellColor(x, y, Color.BEIGE);
        }
    }
    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x,y);
    }
    private void gameOver(){
        showMessageDialog(Color.RED, "You are died", Color.WHITE, 30);
        for( int i = 0 ; i<SIDE; i++){
            for (int j = 0; j<SIDE;j++){
                openTile(i,j);
            }
        }
        isGameStopped = true;
    }
    private  void win(){
        isGameStopped =true;
        showMessageDialog(Color.GREEN, "You win!", Color.WHITE, 30);
    }
    private  void restart(){
        isFirstTurn=true;
        countFlags=0;
        score=0;
        isGameStopped=false;
        countClosedTiles=SIDE*SIDE;
        countMinesOnField=0;
        createGame();
        setScore(score);
        isFail=false;
    }
}