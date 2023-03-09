package domain.user;

import domain.Card;
import domain.CardHand;
import domain.CardNumber;
import domain.Deck;
import domain.Symbol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer extends AbstractUser {
    private static final String DEALER_NAME = "딜러";
    private static final int UPPER_LIMIT_TO_DRAW = 16;
    private final int INITIAL_CARDS_COUNT = 2;

    private final Deck deck;

    public Dealer() {
        super(DEALER_NAME);
        this.deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
    }

    public Dealer(CardHand cardHand) {
        super(DEALER_NAME, cardHand);
        this.deck = Deck.of(Arrays.stream(Symbol.values()).collect(Collectors.toList()),
                Arrays.stream(CardNumber.values()).collect(Collectors.toList()));
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() <= UPPER_LIMIT_TO_DRAW;
    }

    public void drawInitCardsDealer() {
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            cardHand.add(deck.draw());
        }
    }

    public List<Card> drawInitCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            cards.add(deck.draw());
        }
        return cards;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public  void drawCardDealer() {
        cardHand.add(deck.draw());
    }
}
