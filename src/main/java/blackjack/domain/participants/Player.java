package blackjack.domain.participants;


import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;

public class Player {
    private final Name name;
    private final PlayerStatus playerStatus;

    public Player(Name name) {
        this.name = name;
        this.playerStatus = new PlayerStatus();
    }

    public void receiveCard(Card card) {
        playerStatus.addCard(card);
    }

    public int calculateScore() {
        return playerStatus.calculateScore();
    }

    public boolean isNotOver(int boundaryScore) {
        return playerStatus.calculateScore() < boundaryScore;
    }

    public void betMoney(Money money) {
        playerStatus.addMoney(money);
    }

    public void loseMoney(Money money) {
        playerStatus.subtractMoney(money);
    }

    public void earnBetSuccessMoney() {
        playerStatus.calculateWinMoney();
    }

    public void payBetFailMoney() {
        playerStatus.calculateLoseMoney();
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return playerStatus.getHand();
    }

    public Money getMoney() {
        return playerStatus.getMoney();
    }
}
