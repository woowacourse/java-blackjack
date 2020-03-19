package blackjack.domain.result;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Money;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public enum ResultType {
    BLACKJACK("블랙잭", 1.5, (playerCards, dealerCards) -> {
        if (playerCards.isBlackJack() && !dealerCards.isBlackJack()) {
            return true;
        }
        return false;
    }),
    WIN("승", 1, (playerCards, dealerCards) -> {
        if (playerCards.isBust()) {
            return false;
        }
        if (dealerCards.isBust()) {
            return true;
        }
        if (!playerCards.isBlackJack() && playerCards.computeScore() > dealerCards.computeScore()) {
            return true;
        }
        return false;
    }),
    LOSE("패", -1, (playerCards, dealerCards) -> {
        if (playerCards.isBust()) {
            return true;
        }
        if (!playerCards.isBust() && !dealerCards.isBust()
                && playerCards.computeScore() < dealerCards.computeScore()) {
            return true;
        }
        if (!playerCards.isBlackJack() && dealerCards.isBlackJack()) {
            return true;
        }
        return false;
    }),
    DRAW("무", 0, (playerCards, dealerCards) -> {
        if (playerCards.isBust()) {
            return false;
        }
        if (playerCards.isBlackJack() && dealerCards.isBlackJack()) {
            return true;
        }
        if (playerCards.computeScore() == dealerCards.computeScore()) {
            if (!playerCards.isBlackJack() && !dealerCards.isBlackJack()) {
                return true;
            }
        }
        return false;
    });

    public static final int BUST = 21;

    private final String word;
    private final double profitRate;
    private final BiFunction<Cards, Cards, Boolean> judge;

    ResultType(String word, double profitRate, BiFunction<Cards, Cards, Boolean> judge) {
        this.word = word;
        this.profitRate = profitRate;
        this.judge = judge;
    }

    public static ResultType findResult(Cards playerCards, Cards dealerCards) {
        return Arrays.stream(ResultType.values())
                .filter(x -> x.judge.apply(playerCards, dealerCards))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."));
    }

    public ResultType reverse() {
        if (this == BLACKJACK) {
            return LOSE;
        }
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }

    public int countSameResultType(List<PlayerResult> playerResults) {
        return (int) playerResults.stream()
                .filter(playerResult -> playerResult.hasSameResult(this))
                .count();
    }

    public double computeProfit(Money money) {
        return money.getBettingMoney() * profitRate;
    }

    public String getWord() {
        return word;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
