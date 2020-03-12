package model;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {

    public static final String DELIMITER = ", ";

    private Result result;

    public Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }

    @Override
    public String toStringCardHand() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : cardHand.getCards()) {
            cardNames.add(card.toString());
        }
        return String.join(DELIMITER, cardNames);
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
