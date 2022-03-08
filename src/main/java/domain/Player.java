package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ExceptionMessages;

public class Player {

    public static final int MAX_CARD_SUM = 21;

    private final String name;
    private final List<Card> cards = new ArrayList<>();

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.EMPTY_NAME_ERROR);
        }
    }

    private int calculateSum() {
       return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    public boolean canDrawCard(){
        return calculateSum() < MAX_CARD_SUM;
    }

    public void hit(Card card){
        if (!canDrawCard()) {
            throw new IllegalStateException(ExceptionMessages.OVER_CARD_LIMIT_ERROR);
        }
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
