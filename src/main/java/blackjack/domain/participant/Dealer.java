package blackjack.domain.participant;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import java.util.List;
import java.util.function.Consumer;

public class Dealer {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_DRAW_THRESHOLD = 16;

    private final Name name;
    private final Hands hands;

    private Dealer(Name name, Hands hands) {
        this.name = name;
        this.hands = hands;
    }

    public static Dealer createDealerWithCards(List<Card> initialCards) {
        Hands initialHands = new Hands();
        for (Card card : initialCards) {
            initialHands.addCard(card);
        }
        return new Dealer(new Name(DEALER_NAME), initialHands);
    }

    public String getFirstCardName() {
        return hands.getFirstCardName();
    }

    public void confirmDealerHands(Deck deck, Consumer<String> consumer) {
        while (shouldDraw()) {
            addCard(deck.draw());
            consumer.accept(name.getName());
        }
    }

    public boolean shouldDraw() {
        return hands.findHandsScore() <= DEALER_DRAW_THRESHOLD;
    }

    public void addCard(Card card) {
        hands.addCard(card);
    }

    public String getName() {
        return name.getName();
    }

    public Hands getHands() {
        return hands;
    }

    public List<Card> getHandsCards() {
        return hands.getHands();
    }

    public int findHandsScore() {
        return hands.findHandsScore();
    }
}
