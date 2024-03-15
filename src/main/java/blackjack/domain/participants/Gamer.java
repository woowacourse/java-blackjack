package blackjack.domain.participants;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;
import blackjack.domain.participants.GamerInformation.GamblingMoney;
import blackjack.domain.participants.GamerInformation.Name;
import blackjack.domain.participants.GamerInformation.PlayerStatus;

public abstract class Gamer {
    public static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    protected final Name name;
    protected final PlayerStatus playerStatus;

    public Gamer(Name name) {
        this.name = name;
        this.playerStatus = new PlayerStatus();
    }

    public void receiveCard(Card card) {
        playerStatus.addCard(card);
    }

    public int calculateScore() {
        return playerStatus.calculateScore();
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_SCORE && checkHandSize() == BLACKJACK_CARD_SIZE;
    }

    public abstract boolean isNotOver();

    public void loseMoney(GamblingMoney gamblingMoney) {
        playerStatus.subtractMoney(gamblingMoney);
    }

    private int checkHandSize() {
        return playerStatus.checkHandSize();
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return playerStatus.getHand();
    }

    public GamblingMoney getGamblingMoney() {
        return playerStatus.getGamblingMoney();
    }
}
