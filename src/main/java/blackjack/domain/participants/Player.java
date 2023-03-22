package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import blackjack.domain.participants.status.Status;
import blackjack.domain.participants.status.running.Hit;

import java.util.List;

public class Player {

    private final User user;
    private Status status;
    private BettingMoney bettingMoney;

    Player(String userName) {
        user = new User(userName);
        status = new Hit();
    }

    void bet(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    void drawInitialCards(Card card1, Card card2) {
        drawCard(card1);
        drawCard(card2);
    }

    public void drawCard(Card card) {
        status = status.addCard(card);
    }

    public boolean isDrawable() {
        return !status.isFinished();
    }

    public void stay() {
        status = status.stay();
    }

    public ResultType findResult(Dealer dealer) {
        return status.findResultType(dealer.getStatus());
    }

    public Score currentScore() {
        return status.score();
    }

    public List<Card> getCards() {
        return status.getCards();
    }

    boolean hasName(String playerName) {
        return user.getName()
                .equals(playerName);
    }

    String getName() {
        return user.getName();
    }

    Money getBettingMoney() {
        return bettingMoney.getMoney();
    }

}
