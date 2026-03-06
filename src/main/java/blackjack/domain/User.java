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

    public int calculateCardsValue() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }

        return applyBestAceValue(sum);
    }

    public boolean isBurst() {
        return calculateCardsValue() > 21;
    }

    private int applyBestAceValue(int sum) {
        if (hasAce() && (sum + 10) <= 21) {
            return sum + 10;
        }
        return sum;
    }

    private boolean hasAce() {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    private void validate(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("유저 이름은 5자가 넘을 수 없습니다.");
        }
    }

}
