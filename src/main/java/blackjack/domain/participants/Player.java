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

    Player(final String userName) {
        user = new User(userName);
        status = new Hit();
    }

    void bet(final BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    void drawInitialCards(final Card card1, final Card card2) {
        status = status.initCards(card1, card2);
    }

    public void drawCard(final Card card) {
        status = status.addCard(card);
    }

    public boolean isDrawable() {
        return !status.isFinished();
    }

    public void stay() {
        status = status.stay();
    }

    public ResultType findResult(final Dealer dealer) {
        return status.findResultType(dealer.getStatus());
    }

    public Score currentScore() {
        return status.score();
    }

    public List<Card> getCards() {
        return status.getCards();
    }

    boolean hasName(final String playerName) {
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
