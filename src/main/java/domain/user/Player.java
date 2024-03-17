package domain.user;

import static domain.money.GameResult.LOSE;
import static domain.money.GameResult.WIN;

import domain.card.Card;
import domain.money.GameResult;
import java.util.List;

public class Player {
    private static final int RECEIVABLE_THRESHOLD = 21;
    private final Name name;
    private final Hand hand;

    public Player(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public int sumHand() {
        return hand.sumCard();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isReceivable() {
        return hand.sumCard() < RECEIVABLE_THRESHOLD;
    }

    public void receive(Card card) {
        hand.receive(card);
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public GameResult generateResult(Dealer dealer) {
        if (hand.isBusted() || isDealerBlackjackOnly(dealer)) {
            return LOSE;
        }
        if (dealer.isBusted() || isPlayerBlackjackOnly(dealer)) {
            return WIN;
        }
        return GameResult.compare(sumHand(), dealer.sumCard());
    }

    private boolean isDealerBlackjackOnly(Dealer dealer) {
        return !isBlackjack() && dealer.isBlackjack();
    }

    private boolean isPlayerBlackjackOnly(Dealer dealer) {
        return isBlackjack() && !dealer.isBlackjack();
    }

    public List<Card> getAllCards() {
        return hand.getCards();
    }

    public String getNameValue() {
        return name.value();
    }
}
