package blackjack.domain;

import blackjack.domain.card.Cards;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

public enum MatchRule {
    BUST_PLAYER((playerCards, dealerCards) -> playerCards.isBust(), ResultType.LOSE),
    BUST_DEALER((playerCards, dealerCards) -> !playerCards.isBust() && dealerCards.isBust(), ResultType.WIN),
    BLACKJACK_PLAYER_WIN((playerCards, dealerCards) -> playerCards.isBlackJack() && !dealerCards.isBlackJack(), ResultType.WIN),
    BLACKJACK_DEALER_WIN((playerCards, dealerCards) -> !playerCards.isBlackJack() && dealerCards.isBlackJack(), ResultType.LOSE),
    BLACKJACK_DRAW((playerCards, dealerCards) -> playerCards.isBlackJack() && dealerCards.isBlackJack(), ResultType.DRAW),
    SCORE_COMPARISION_PLAYER_WIN((playerCards, dealerCards) -> playerCards.getScore() > dealerCards.getScore(), ResultType.WIN),
    SCORE_COMPARISION_DEALER_WIN((playerCards, dealerCards) -> playerCards.getScore() < dealerCards.getScore(), ResultType.LOSE),
    SCORE_COMPARISION_DRAW((playerCards, dealerCards) -> playerCards.getScore() == dealerCards.getScore(), ResultType.DRAW);

    private final BiPredicate<Cards, Cards> condition;
    private final ResultType matchResult;

    MatchRule(BiPredicate<Cards, Cards> condition, ResultType matchResult) {
        this.condition = condition;
        this.matchResult = matchResult;
    }

    public static ResultType getMatchResult(Cards playerCards, Cards dealerCards) {
        return Stream.of(MatchRule.values())
                .filter(matchRule -> matchRule.condition.test(playerCards, dealerCards))
                .findAny()
                .map(matchRule -> matchRule.matchResult)
                .orElseThrow(() -> new IllegalArgumentException("조건에 맞는 승부 결과가 없습니다."));
    }
}
