package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.game.WinOrLose;

public class Dealer implements Player {
    private static final int LIMIT_SCORE_TO_HIT = 16;

    private final Name name = new Name("딜러");
    private final Cards cards = new Cards();
    private Money money = new Money(0);

    public boolean ableToDraw() {
        return cards.calculateScore().isBelow(LIMIT_SCORE_TO_HIT);
    }

    public void giveBackBettingMoney(Gambler gambler) {
        giveMoney(gambler, gambler.getBettingMoney());
    }

    public void giveWinningMoney(Gambler gambler) {
        Money bettingMoney = gambler.getBettingMoney();
        Money winningMoney = bettingMoney.add(bettingMoney);
        giveMoney(gambler, winningMoney);
    }

    public void giveMoneyByBlackJack(Gambler gambler) {
        Money bettingMoney = gambler.getBettingMoney();
        Money winningMoney = bettingMoney.add(bettingMoney.multiply(1.5));
        giveMoney(gambler, winningMoney);
    }

    public void takeMoney(Gambler gambler, Money money) {
        this.money = this.money.add(money);
        gambler.lose(money);
    }

    private void giveMoney(Gambler gambler, Money money) {
        this.money = this.money.sub(money);
        gambler.earn(money);
    }

    public WinOrLose calculateGamblerWinOrNot(final Player player) {
        if (cards.isBust() && !player.isBust()) {
            return WinOrLose.WIN;
        }

        if (!cards.isBust() && player.isBust()) {
            return WinOrLose.LOSE;
        }

        return player.getCards().compareCardsScore(cards);
    }

    public Money getMoney(){
        return money;
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
    }

    @Override
    public boolean hasBlackJack() {
        return cards.isBlackJack();
    }

    @Override
    public void receiveCard(final Card card) {
        cards.add(card);
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Score getScore() {
        return cards.calculateScore();
    }

    @Override
    public Cards getCards() {
        return cards;
    }


}
