package domain.participant;

import domain.BlackJackResult;
import domain.card.Card;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    private static final int BURST_NUMBER = 21;

    private final BettingMoney bettingMoney;

    public Player(Name name, DrawnCards drawnCards, BettingMoney bettingMoney) {
        super(name, drawnCards);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public List<Card> openDrawnCards() {
        return new ArrayList<>(drawnCards.getCards());
    }

    @Override
    public boolean isDrawable() {
        return calculateScore() < BURST_NUMBER;
    }

    public int calculatePrize(Dealer dealer) {
        if (isBlackJack()) {
            return calculateBlackJackCase(dealer);
        }

        if (isBurst()) {
            return calculateMoney(BlackJackResult.LOSE);
        }

        if (this.calculateScore() > dealer.calculateScore() || dealer.isBurst()) {
            return calculateMoney(BlackJackResult.WIN);
        }

        return calculateMoney(BlackJackResult.LOSE);
    }

    private int calculateBlackJackCase(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return calculateMoney(BlackJackResult.EACH_BLACKJACK);
        }
        return calculateMoney(BlackJackResult.BLACKJACK);
    }

    private int calculateMoney(BlackJackResult result) {
        return result.calculatePrize(bettingMoney.getMoney());
    }

    public boolean hasSameName(String name) {
        return this.name.getName().equals(name);
    }
}
