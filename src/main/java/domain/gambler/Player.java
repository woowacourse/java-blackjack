package domain.gambler;

import domain.WinningResult;
import domain.card.CardDeck;
import domain.card.Cards;
import domain.card.Score;

import java.util.Objects;

public class Player implements Gambler {
    private static final String NULL_EXCEPTION_MESSAGE = "Null exception occur.";
    private static final int PLAYER_MAX_HIT_SCORE = 20;

    private final Name name;
    private final Money money;
    private final Cards cards;

    public Player(Name name, Money money) {
        validateName(name);
        validateMoney(money);
        this.name = name;
        this.money = money;
        this.cards = new Cards();
    }

    private void validateName(Name name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException(NULL_EXCEPTION_MESSAGE);
        }
    }

    private void validateMoney(Money money) {
        if (Objects.isNull(money)) {
            throw new IllegalArgumentException(NULL_EXCEPTION_MESSAGE);
        }
    }

    public Money getProfitByComparing(Dealer dealer) {
        return money.multiply(calculateWinningResult(dealer).getProfitRatio());
    }

    public WinningResult calculateWinningResult(Dealer dealer) {
        return WinningResult.of(getCards(), dealer.getCards());
    }

    @Override
    public boolean canHit() {
        return cards.canHit(PLAYER_MAX_HIT_SCORE);
    }

    @Override
    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, 1);
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Score getScore() {
        return cards.getScore();
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}