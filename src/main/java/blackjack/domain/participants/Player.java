package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;

import java.util.List;

public class Player {

    private final User user;
    private final CardPocket cardPocket;
    private Money bettingMoney;


    public Player(final User user) {
        this.user = user;
        cardPocket = new CardPocket();
        bettingMoney = new Money();
    }

    public void bet(final Money money) {
        bettingMoney = bettingMoney.add(money);
    }

    public void drawCard(final Card card) {
        cardPocket.addCard(card);
    }

    public boolean isDrawable() {
        return !isBusted() && !isBlackjack();
    }

    public boolean isBlackjack() {
        return cardPocket.isBlackjack();
    }

    public boolean isBusted() {
        return cardPocket.isBusted();
    }

    public void hit(final Card card) {
        cardPocket.addCard(card);
    }

    public Score currentScore() {
        return cardPocket.getScore();
    }

    public List<Card> getCards() {
        return cardPocket.getPossessedCards();
    }

    public boolean hasName(final String playerName) {
        return user.getName()
                .equals(playerName);
    }

    public String getName() {
        return user.getName();
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }

}
