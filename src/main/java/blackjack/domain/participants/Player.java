package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;

import java.util.List;

public class Player {

    private final User user;
    private final Hand hand;
    private final Status status;
    private BettingMoney bettingMoney;

    Player(String userName) {
        user = new User(userName);
        hand = new Hand();
        status = new Status();
        bettingMoney = new BettingMoney();
    }

    void bet(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    void drawCard(Card card) {
        hand.addCard(card);
    }

    boolean isDrawable() {
        return !isBusted() && !isBlackjack();
    }






    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public Score currentScore() {
        return hand.getScore();
    }

    List<Card> getCards() {
        return hand.getPossessedCards();
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
