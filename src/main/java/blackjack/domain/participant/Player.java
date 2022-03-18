package blackjack.domain.participant;

import blackjack.domain.BettingAmount;
import blackjack.domain.Name;
import blackjack.domain.card.Deck;
import blackjack.domain.prizecalculator.PrizeCalculator;

public class Player extends Participant {

    private final BettingAmount bettingAmount;
    private final Name name;

    public Player(final String name) {
        super();
        this.bettingAmount = new BettingAmount();
        this.name = new Name(name);
    }

    public void initMoney(final int bettingAmount) {
        this.bettingAmount.init(bettingAmount);
    }

    @Override
    public boolean isDrawable() {
        return playerStatus.isRunning();
    }

    @Override
    public void hit(final Deck deck) {
        cards.add(deck.drawCard());

        if (cards.isBust()) {
            playerStatus = PlayerStatus.BUST;
        }
    }

    public void stay() {
        if (checkBlackjack()) {
            playerStatus = PlayerStatus.BLACKJACK;
            return;
        }
        playerStatus = PlayerStatus.STAY;
    }

    public PrizeCalculator findCalculator() {
        return playerStatus.findCalculator();
    }

    PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public double getBettingAmount() {
        return bettingAmount.getValue();
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
