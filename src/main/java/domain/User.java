package domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final List<Card> hand = new ArrayList<>();
    private GameResult gameResult;

    private User(String name) {
        this.name = name;
    }

    public static User from(String input) {
        return new User(input);
    }

    public String getName() {
        return name;
    }

    public void receiveInitCard(List<Card> cards) {
        hand.addAll(cards);
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
