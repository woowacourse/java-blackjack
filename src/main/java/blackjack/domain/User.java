package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final List<Card> cards;

    public User(String name) {
        validate(name);
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void bring(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<String> getCardsName() {
        List<String> cardsName = new ArrayList<>();
        for (Card card : cards) {
            cardsName.add(card.getName());
        }
        
        return cardsName;
    }

    private void validate(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("유저 이름은 5자가 넘을 수 없습니다.");
        }
    }

}
