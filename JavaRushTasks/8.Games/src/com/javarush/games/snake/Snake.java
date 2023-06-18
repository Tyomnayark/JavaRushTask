package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject{
    private List<GameObject> snakeParts = new ArrayList<>();
    private static final  String HEAD_SIGN = "\uD83D\uDC7E";
    private static final  String BODY_SIGN = "\u26AB";
    public  boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    public Snake(int x, int y) {
        super(x, y);
        GameObject ob1 = new GameObject(x,y);
        GameObject ob2 = new GameObject(x+1,y);
        GameObject ob3 = new GameObject(x+2,y);
        snakeParts.add(ob1);
        snakeParts.add(ob2);
        snakeParts.add(ob3);

    }
    public void draw(Game game) {
        for (int i = 0; i < snakeParts.size(); i++) {
            GameObject part = snakeParts.get(i);

            String sign = (i != 0) ? BODY_SIGN : HEAD_SIGN;
            Color color = (isAlive==true)? Color.BLUE: Color.RED;
            game.setCellValueEx(part.x, part.y,Color.NONE,sign,color,75);
        }
    }
    public void setDirection(Direction direction) {
        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && snakeParts.get(0).x == snakeParts.get(1).x) {
            return;
        }
        if ((this.direction == Direction.UP || this.direction == Direction.DOWN) && snakeParts.get(0).y == snakeParts.get(1).y) {
            return;
        }

        if (direction == Direction.UP && this.direction == Direction.DOWN) {
            return;
        } else if (direction == Direction.LEFT && this.direction == Direction.RIGHT) {
            return;
        } else if (direction == Direction.RIGHT && this.direction == Direction.LEFT) {
            return;
        } else if (direction == Direction.DOWN && this.direction == Direction.UP) {
            return;
        }

        this.direction = direction;
    }

    public Direction getDirection(){
        return direction;
    }

    public  void move(Apple apple){
        GameObject newHead = createNewHead();
        if (checkCollision(newHead)){
            isAlive = false;
        }else {
            if (newHead.x == apple.x && newHead.y == apple.y) {
                apple.isAlive = false;
                snakeParts.add(0, newHead);
            } else {
                    removeTail();
                    snakeParts.add(0, newHead);
            }
        }
    }
    public GameObject createNewHead(){
        GameObject head = snakeParts.get(0);
        switch (direction) {
            case UP:
                return new GameObject((head.x + SnakeGame.WIDTH) % SnakeGame.WIDTH, (head.y + SnakeGame.HEIGHT - 1) % SnakeGame.HEIGHT);
            case DOWN:
                return new GameObject((head.x + SnakeGame.WIDTH) % SnakeGame.WIDTH, (head.y + 1) % SnakeGame.HEIGHT);
            case LEFT:
                return new GameObject((head.x + SnakeGame.WIDTH - 1) % SnakeGame.WIDTH, (head.y + SnakeGame.HEIGHT) % SnakeGame.HEIGHT);
            case RIGHT:
                return new GameObject((head.x + 1) % SnakeGame.WIDTH, (head.y + SnakeGame.HEIGHT) % SnakeGame.HEIGHT);
        }
        return head;
    }
    public  void  removeTail(){
       snakeParts.remove( snakeParts.get(snakeParts.size()-1));
    }
    public   boolean checkCollision(GameObject gameObject){
        for (GameObject g:snakeParts) {
            if (g.x==gameObject.x&& g.y==gameObject.y){
                return true;
            }
        }
        return false;
    }
    public  int getLength(){
        return snakeParts.size();
    }
}
