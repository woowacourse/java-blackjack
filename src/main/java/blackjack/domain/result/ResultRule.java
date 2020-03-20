package blackjack.domain.result;

import blackjack.domain.card.Cards;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.domain.card.CardDeck.NUMBER_OF_FIRST_CARDS;
import static blackjack.domain.result.ResultType.*;

public enum ResultRule {
    ONLY_PLAYER_BLACKJACK((playerCards, dealerCards) -> isBlackJack(playerCards) && !isBlackJack(dealerCards), BLACKJACK),
    ONLY_DEALER_BLACKJACK((playerCards, dealerCards) -> !isBlackJack(playerCards) && isBlackJack(dealerCards), LOSE),
    BOTH_BLACKJACK((playerCards, dealerCards) -> isBlackJack(playerCards) && isBlackJack(dealerCards), DRAW),
    ONLY_PLAYER_BUST((playerCards, dealerCards) -> isBust(playerCards) && !isBust(dealerCards), LOSE),
    ONLY_DEALER_BUST((playerCards, dealerCards) -> !isBust(playerCards) && isBust(dealerCards), WIN),
    BOTH_BUST((playerCards, dealerCards) -> isBust(playerCards) && isBust(dealerCards), LOSE),
    PLAYER_WIN_IN_GENERAL((playerCards, dealerCards) -> playerCards.computeScore() > dealerCards.computeScore(), WIN),
    DEALER_WIN_IN_GENERAL((playerCards, dealerCards) -> playerCards.computeScore() < dealerCards.computeScore(), LOSE),
    DRAW_IN_GENERAL((playerCards, dealerCards) -> playerCards.computeScore() == dealerCards.computeScore(), DRAW);

    public static final int BLACKJACK_SCORE = 21;

    private final BiPredicate<Cards, Cards> condition;
    private final ResultType type;

    ResultRule(BiPredicate<Cards, Cards> condition, ResultType type) {
        this.condition = condition;
        this.type = type;
    }

    public static ResultType findResult(Cards playerCards, Cards dealerCards) {
        return Arrays.stream(ResultRule.values())
                .filter(rule -> rule.condition.test(playerCards, dealerCards))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결과를 찾을 수 없습니다."))
                .type;
    }

    public static boolean isBlackJack(Cards cards) {
        return cards.computeScore() == BLACKJACK_SCORE && cards.size() == NUMBER_OF_FIRST_CARDS;
    }

    public static boolean isBust(Cards cards) {
        return cards.computeScore() > BLACKJACK_SCORE;
    }
}
