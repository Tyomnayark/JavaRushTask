package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager {
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();
    public  static  final  int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public  static  final  int RIGHT_BORDER = RacerGame.WIDTH-LEFT_BORDER;
    private RoadObjectType type;
    private static final int PLAYER_CAR_DISTANCE =12;
    private int passedCarsCount=0;


    private RoadObject createRoadObject(RoadObjectType type, int x, int y){
        if (type ==RoadObjectType.THORN){
            return new Thorn(x,y);
        }
        if (type==RoadObjectType.DRUNK_CAR){
            return new MovingCar(x,y);
        }
        return  new Car(type, x, y);

    }
    private void addRoadObject(RoadObjectType type, Game game){
        int x = game.getRandomNumber(FIRST_LANE_POSITION,FOURTH_LANE_POSITION);
        int y = -1*RoadObject.getHeight(type);
        RoadObject newRoadObject = createRoadObject(type,x,y);
        if (newRoadObject!=null&&isRoadSpaceFree(newRoadObject)){
            items.add(newRoadObject);
        }
    }
    public void draw(Game game){
        for (RoadObject roadObject: items) {
            roadObject.draw(game);
        }
    }
    public  void move(int boost){
        for (RoadObject roadObject: items) {
            roadObject.move(boost+roadObject.speed,items);
        }
        deletePassedItems();
    }
    private boolean isThornExists() {
        for (RoadObject item : items) {
            if (item instanceof Thorn) {
                return true;
            }
        }
        return false;
    }
    private boolean isMovingCarExists() {
        for (RoadObject item : items) {
            if (item instanceof MovingCar) {
                return true;
            }
        }
        return false;
    }
    private void generateThorn(Game game){
        if(game.getRandomNumber(100)<10 && !isThornExists()){
            addRoadObject(RoadObjectType.THORN,game);
        }
    }
    private void generateMovingCar(Game game){
        if(game.getRandomNumber(100)<10 && !isMovingCarExists()){
            addRoadObject(RoadObjectType.DRUNK_CAR,game);
        }
    }
    public  void generateNewRoadObjects(Game game){
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }
    private void deletePassedItems() {
        for (RoadObject item : new ArrayList<>(items)) {
            if (item.y >= RacerGame.HEIGHT) {
                items.remove(item);
                if(!(item instanceof Thorn))
                passedCarsCount++;
            }
        }
    }
    public  boolean checkCrush(PlayerCar playerCar){
        for (RoadObject item : items) {
            if(item.isCollision(playerCar)){
                return true;
            }
        }
        return false;
    }
    private void generateRegularCar(Game game){
        int carTypeNumber =game.getRandomNumber(4);
       if( game.getRandomNumber(100)<30){
           addRoadObject(RoadObjectType.values()[carTypeNumber],game);
       }
    }
    private  boolean isRoadSpaceFree(RoadObject object){
        for (RoadObject item:items) {
           if ( item.isCollisionWithDistance(object,PLAYER_CAR_DISTANCE)){
               return false;
           }
        }
        return true;
    }

    public int getPassedCarsCount() {
        return passedCarsCount;
    }
}