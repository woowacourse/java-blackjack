package domain.blackjack;

import domain.card.Card;
import domain.card.CardSelectStrategy;
import domain.card.Deck;
import domain.card.RandomCardSelectStrategy;
import java.util.List;

abstract class Gamer {
    private static final int INITIAL_CARD_COUNT = 2;
    protected final BlackJackGameMachine blackJackGameMachine;
    private final int bettingMoney;

    Gamer(BlackJackGameMachine blackJackGameMachine, int bettingMoney) {
        this.blackJackGameMachine = blackJackGameMachine;
        if (bettingMoney < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
        this.bettingMoney = bettingMoney;
    }

    abstract DrawResult draw(Deck deck, CardSelectStrategy cardSelectStrategy);

    final DrawResult drawRandom(Deck deck) {
        return draw(deck, RandomCardSelectStrategy.INSTANCE);
    }

    public final List<Card> getRawHoldingCards() {
        return blackJackGameMachine.getRawHoldingCards();
    }

    public final int calculateSummationCardPointAsInt() {
        return blackJackGameMachine.calculateSummationCardPointAsInt();
    }

    final SummationCardPoint calculateSummationCardPoint() {
        return blackJackGameMachine.calculateSummationCardPoint();
    }

    final boolean isBust() {
        return blackJackGameMachine.isBust();
    }

    final boolean isBlackJack() {
        List<Card> rawHoldingCards = blackJackGameMachine.getRawHoldingCards();
        int holdingCardCount = rawHoldingCards.size();
        SummationCardPoint summationCardPoint = calculateSummationCardPoint();
        return holdingCardCount == INITIAL_CARD_COUNT && summationCardPoint.isBlackJackPoint();
    }

    int getBettingMoney() {
        return bettingMoney;
    }
}
