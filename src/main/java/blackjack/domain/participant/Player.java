package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.Name;
import blackjack.domain.card.Deck;

public class Player extends Participant {

    private final Money money;
    private final Name name;
    private PlayerStatus playerStatus;

    public Player(final String name) {
        super();
        this.money = new Money();
        this.name = new Name(name);
        this.playerStatus = PlayerStatus.HIT;
    }

    public void initMoney(final int bettingAmount) {
        money.init(bettingAmount);
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
        if (isBlackjack()) {
            playerStatus = PlayerStatus.BLACKJACK;
            return;
        }
        playerStatus = PlayerStatus.STAY;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public int getBettingAmount() {
        return money.getValue();
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
