package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.game.WinOrLose;

public class Dealer implements Player {
    private static final Score LIMIT_SCORE_TO_HIT = Score.of(16);

    private final Name name = new Name("딜러");
    private final Cards cards = new Cards();
    private Money money = Money.emptyMoney;

    public void giveWinningMoney(final Gambler gambler) {
        Money bettingMoney = gambler.getBettingMoney();
        Money winningMoney = bettingMoney.add(bettingMoney);
        giveMoney(gambler, winningMoney);
    }

    public void takeMoney(final Gambler gambler, final Money money) {
        this.money = this.money.add(money);
        gambler.lose(money);
    }

    private void giveMoney(Gambler gambler, final Money money) {
        this.money = this.money.sub(money);
        gambler.earn(money);
    }

    public WinOrLose calculateWinOrLose(final Gambler gambler) {
        return cards.compareCardsScore(gambler.getCards());
    }

    public void checkBlackJack(final Gambler gambler) {
        if (hasBlackJack() && gambler.hasBlackJack()) {
            giveBackBettingMoney(gambler);
        }

        if (!hasBlackJack() && gambler.hasBlackJack()) {
            giveBlackJackMoney(gambler);
        }
    }

    public void calculateMoney(final Gambler gambler, final WinOrLose winOrLose) {
        if (winOrLose.equals(WinOrLose.LOSE)) {
            giveWinningMoney(gambler);
        }

        if (winOrLose.equals(WinOrLose.DRAW)) {
            giveBackBettingMoney(gambler);
        }
    }

    private void giveBackBettingMoney(final Gambler gambler) {
        giveMoney(gambler, gambler.getBettingMoney());
    }

    private void giveBlackJackMoney(final Gambler gambler) {
        final Money bettingMoney = gambler.getBettingMoney();
        final Money winningMoney = bettingMoney.add(bettingMoney.multiply(1.5));
        giveMoney(gambler, winningMoney);
    }

    @Override
    public boolean ableToDraw() {
        return cards.calculateScore().isBelow(LIMIT_SCORE_TO_HIT);
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
    public String getNameValue() {
        return name.getValue();
    }

    @Override
    public int getMoneyValue() {
        return money.getValue();
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
