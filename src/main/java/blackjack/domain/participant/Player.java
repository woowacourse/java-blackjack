package blackjack.domain.participant;

import blackjack.domain.BettingAmount;
import blackjack.domain.Name;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.playerstatus.Blackjack;
import blackjack.domain.participant.playerstatus.Bust;
import blackjack.domain.participant.playerstatus.CalculableStatus;
import blackjack.domain.participant.playerstatus.Stay;

public class Player extends Participant {

    private final Name name;
    private final BettingAmount bettingAmount;

    public Player(final Name name, final BettingAmount bettingAmount) {
        super();
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    Player(final String name, final int bettingAmount) {
        super();
        this.name = new Name(name);
        this.bettingAmount = new BettingAmount(bettingAmount);
    }

    @Override
    public boolean isDrawable() {
        return playerStatus.isRunning();
    }

    @Override
    public void hit(final Deck deck) {
        cards.add(deck.drawCard());

        if (cards.isBust()) {
            playerStatus = Bust.getInstance();
        }
    }

    public void stay() {
        if (checkBlackjack()) {
            playerStatus = Blackjack.getInstance();
            return;
        }
        playerStatus = Stay.getInstance();
    }

    public double calculateProfit(final int dealerScore, boolean dealerBlackjack) {
        if (playerStatus.isRunning()) {
            throw new IllegalStateException();
        }
        return ((CalculableStatus) playerStatus).calculateProfit(getScore(), dealerScore, dealerBlackjack,
                bettingAmount.getValue());
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
