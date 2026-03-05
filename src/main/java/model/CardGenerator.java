package model;

import java.util.Random;

public class CardGenerator {

    public static Card generateCard() {
        Random random = new Random();
        int index = random.nextInt(4);
        int number = random.nextInt(13) + 1;

        CardShape shape = CardShape.from(index);
        CardValue value = CardValue.from(number);

        return new Card(shape, value);
    }
}
