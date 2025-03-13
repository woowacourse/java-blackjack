package domain.participant;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import java.util.function.Consumer;
import java.util.function.Function;

public class Player extends Participant {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int INITIAL_HIT_COUNT = 2;

    private final String name;

    public Player(Hand hand, final String name) {
        super(hand);
        this.name = name;
    }

    public void hitCards(Deck cardDeck) {
        for (int i = 0; i < INITIAL_HIT_COUNT; i++) {
            super.addCard(cardDeck.hitCard());
        }
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck, final Deck cardDeck) {
        while (!isBust() && answer.apply(this)) {
            addCard(cardDeck.hitCard());
            playerDeck.accept(this);
        }
    }

    public MatchResult calculateWinner(final int dealerSum) {
        return MatchResult.calculateWinner(dealerSum, this.calculateSum());
    }

    private boolean isBust() {
        return calculateSum() > BLACKJACK_NUMBER;
    }

    public String getName() {
        return name;
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
    }

    @Override
    public int calculateSum() {
        return super.calculateSum();
    }

    public Hand getHand() {
        return super.getHand();
    }

}
