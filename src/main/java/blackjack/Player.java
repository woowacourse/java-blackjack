package blackjack;

import java.util.List;

public class Player {
    private final String name;
    private final CardHolder cardHolder;

    public Player(String name, CardHolder cardHolder) {
        this.name = name;
        this.cardHolder = cardHolder;
    }

    public List<Integer> getPossibleSums() {
        return cardHolder.getPossibleSums();
    }

    public List<Card> getAllCards() {
        return cardHolder.getAllCards();
    }

    public void takeCard(Card newCard) {
        cardHolder.takeCard(newCard);
    }
}
