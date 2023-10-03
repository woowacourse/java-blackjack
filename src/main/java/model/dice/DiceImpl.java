package model.dice;

import java.util.Random;

public class DiceImpl implements Dice {

    private static final int MAX_NUMBER = 52;
    
    @Override
    public int getRandomIndex() {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER);
    }
}
