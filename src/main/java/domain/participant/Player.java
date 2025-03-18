package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Player extends Participant {
    private static final int BLACKJACK_NUMBER = 21;
    private static final int INITIAL_HIT_COUNT = 2;
    private static final double BLACKJACK_PAYOUT_RATIO = 1.5;
    private static final int LOSS_PAYOUT_RATIO = -1;
    private static final int PUSH_PROFIT = 0;

    private final PlayerInfo playerInfo;

    public Player(final Hand hand, final PlayerInfo playerInfo) {
        super(hand);
        this.playerInfo = playerInfo;
    }

    public void hitCards(final Deck deck) {
        for (int i = 0; i < INITIAL_HIT_COUNT; i++) {
            super.addCard(deck.hitCard());
        }
    }

    public void draw(final Function<Player, Boolean> answer, final Consumer<Player> playerDeck, final Deck deck) {
        while (!isBust() && answer.apply(this)) {
            addCard(deck.hitCard());
            playerDeck.accept(this);
        }
    }

    public int calculateProfit(final int dealerSum) {
        if (calculateSum() == dealerSum) {
            return PUSH_PROFIT;
        }
        if (calculateSum() > BLACKJACK_NUMBER || calculateSum() < dealerSum) {
            return getMoney() * LOSS_PAYOUT_RATIO;
        }
        if (calculateSum() > dealerSum && calculateSum() == BLACKJACK_NUMBER) {
            return (int) (getMoney() * BLACKJACK_PAYOUT_RATIO);
        }
        return getMoney();
    }

    private boolean isBust() {
        return calculateSum() > BLACKJACK_NUMBER;
    }

    public String getName() {
        return playerInfo.getName();
    }

    public Hand getCards() {
        return super.getCards();
    }

    public int getMoney() { return playerInfo.getMoney();}

    @Override
    public void addCard(Card card) {
        super.addCard(card);
    }

    @Override
    public int calculateSum() {
        return super.calculateSum();
    }

    @Override
    public List<Card> openInitialCards() {
        return List.of(getCards().getHand().getFirst(), getCards().getHand().get(1));
    }
}
