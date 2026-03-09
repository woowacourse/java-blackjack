package domain.participant;

import domain.card.Card;

import java.util.stream.Collectors;

public class Participant {
    private static final int BLACKJACK_LIMIT_VALUE = 21;

    private final PlayerName name;
    private final Hand hand;

    public Participant(PlayerName name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void keepCard(Card card) {
        hand.addCard(card);
    }

    public int handSize() {
        return hand.getHandSize();
    }

    public int getTotalCardScore() {
        return hand.calculateTotalScore();
    }

    public String getName() {
        return name.getName();
    }

    //버스트면 true
    public boolean isBust() {
        return hand.calculateTotalScore() > BLACKJACK_LIMIT_VALUE;
    }

    public String handDisplay() {
        return hand.getHandCards().stream()
                .map(card -> card.getCardCode() + card.getCardShape())
                .collect(Collectors.joining(", "));
    }
}
