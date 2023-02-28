package domain;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final String ERROR_NAME_LENGTH = "[ERROR] 플레이어의 이름은 2 ~ 10 글자여야 합니다.";
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;

    private final String name;
    private final List<Card> pickedCards = new ArrayList<>();

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ERROR_NAME_LENGTH);
        }
    }
}
