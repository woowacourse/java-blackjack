package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

import java.util.Objects;

public class Dealer implements Player {

    private static final int MIN_SCORE_TO_STAND = 17;

    private final Name name;
    private final Cards cards;
    private Money money;

    public Dealer() {
        name = new Name("딜러");
        cards = new Cards();
        this.money = new Money(0);
    }

    @Override
    public void initializeCards(final Deck deck) {
        for (int i = 0; i < NUMBER_OF_INITIAL_CARDS; i++) {
            cards.add(deck.draw());
        }
    }

    public void calculateProfit(Gambler gambler) {
        Money dealerProfit = gambler.inverseMoney();
        money = money.addMoney(dealerProfit);
    }

    @Override
    public void drawCard(final Deck deck) {
        cards.add(deck.draw());
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
    }

    @Override
    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    @Override
    public boolean isTwentyOne() {
        return cards.isTwentyOne();
    }

    @Override
    public boolean isSameName(Player player) {
        return Objects.equals(this.name(), player.name());
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public String name() {
        return name.getName();
    }

    @Override
    public Score score() {
        return cards.totalScore();
    }

    @Override
    public Money money() {
        return money;
    }

    public boolean ableToDraw() {
        return cards.totalScore().isLessThan(Score.of(MIN_SCORE_TO_STAND));
    }

    public boolean isBiggerThan(final Player player) {
        return cards.isBiggerThan(player.cards());
    }

    public boolean isLessThan(final Player player) {
        return cards.isLessThan(player.cards());
    }
}
