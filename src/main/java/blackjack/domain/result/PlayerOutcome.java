package blackjack.domain.result;

import blackjack.domain.card.CardComparator;
import blackjack.domain.card.HoldCards;

import java.util.Arrays;

public enum PlayerOutcome {
    BLACKJACK_WIN(1.5, ((playerCards, dealerCards) -> playerCards.isBlackJack() && !dealerCards.isBlackJack())),
    WIN(1, ((playerCards, dealerCards) -> ((dealerCards.isBust() || playerCards.isBigger(dealerCards)) && !playerCards.isBust()))),
    LOSE(-1, ((playerCards, dealerCards) -> playerCards.isBust() || (dealerCards.isBigger(playerCards)))),
    DRAW(0, ((playerCards, dealerCards) -> playerCards.isSame(dealerCards) || (playerCards.isBlackJack() && dealerCards.isBlackJack())));

    private final double dividendRate;
    private final CardComparator cardComparator;

    PlayerOutcome(double dividendRate, CardComparator cardComparator) {
        this.dividendRate = dividendRate;
        this.cardComparator = cardComparator;
    }

    public static PlayerOutcome match(HoldCards playerCards, HoldCards dealerCards) {
        return Arrays.stream(values())
                .filter(outcome -> outcome.compare(playerCards, dealerCards))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    private boolean compare(HoldCards playerCards, HoldCards dealerCards) {
        return cardComparator.compare(playerCards, dealerCards);
    }

    public double getDividendRate() {
        return dividendRate;
    }
}
