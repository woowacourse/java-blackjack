package blackjack.domain.participants;

import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participants.money.Money;
import blackjack.exceptions.InvalidPlayerException;

public class Player implements Participant {
    private final Cards cards;
    private final String name;
    private Money money;

    public Player(final String name) {
        validate(name);
        this.name = name;
        this.cards = new Cards();
        this.money = Money.zero();
    }

    private void validate(final String name) {
        if (isNullOrEmpty(name)) {
            throw new InvalidPlayerException("빈 칸 또는 null 값이 들어올 수 없습니다.");
        }
    }

    private boolean isNullOrEmpty(final String name) {
        return Objects.isNull(name) || name.trim().isEmpty();
    }

    @Override
    public int score() {
        return cards.calculate();
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        draw(deck);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public int cardCount() {
        return cards.size();
    }

    @Override
    public String cards() {
        return cards.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void draw(final Deck deck) {
        cards.add(deck.pop());
    }

    // 테스트용

    public void draw(final Card card) {
        cards.add(card);
    }

    @Override
    public void bet(final String amount) {
        this.money = Money.create(amount);
    }

    @Override
    public void earn(final Money money) {
        this.money = this.money.add(money);
    }

    @Override
    public void lose(final Money money) {
        this.money = this.money.subtract(money);
    }

    @Override
    public void loseAll() {
        money = money.getOpposite();
    }

    @Override
    public Money getMoney() {
        return money;
    }
}
