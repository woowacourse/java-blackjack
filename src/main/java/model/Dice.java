package model;

import java.util.Random;

public class Dice {

    public static int getRandomIndex(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }
}
