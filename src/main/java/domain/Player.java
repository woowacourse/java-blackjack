package domain;

import java.util.stream.Collectors;

public class Player {
    private String name;
    protected Hand hand;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getHandTotal() {
        return hand.getTotal();
    }

    public String openAllCards() {
        return hand.getCards().stream()
                .map(card -> card.getDenomination().getValue() + card.getSuit().getShape())
                .collect(Collectors.joining(", "));
    }

    public boolean isHandBust() {
        return hand.isBust();
    }

    public boolean containsOriginalAce() {
        return hand.containsOriginalAce();
    }

    public void setOriginalAceValueToOne() {
        hand.setOriginalAceValueToOne();
    }

    public String getName() {
        return name;
    }
}
