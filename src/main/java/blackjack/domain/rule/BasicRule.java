package blackjack.domain.rule;

import java.util.function.BiPredicate;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;

public enum BasicRule {
    PLAYER_BLACK_JACK((dealer, player) -> player.isBlackjack() && !dealer.isBlackjack(), MoneyRule.WIN_BLACK_JACK),
    PLAYER_BUST((dealer, player) -> player.isBust(), MoneyRule.LOSE),
    DEALER_BUST((dealer, player) -> dealer.isBust() && !player.isBust(), MoneyRule.WIN),
    PLAYER_SCORE_GREATER_THAN_DEALER_SCORE((dealer, player) -> dealer.score() < player.score(), MoneyRule.WIN),
    PLAYER_SCORE_EQUAL_DEALER_SCORE((dealer, player) -> dealer.score() == player.score(), MoneyRule.DRAW),
    PLAYER_SCORE_LESS_THAN_DEALER_SCORE((dealer, player) -> dealer.score() > player.score() && !dealer.isBust(),
        MoneyRule.LOSE);

    public static final int BUST_LIMIT = 21;
    public static final int BLACK_JACK_CARD_SIZE = 2;

    private final BiPredicate<Dealer, Player> condition;
    private final MoneyRule moneyRule;

    BasicRule(final BiPredicate<Dealer, Player> condition, final MoneyRule moneyRule) {
        this.condition = condition;
        this.moneyRule = moneyRule;
    }

    public static MoneyRule of(Dealer dealer, Player player) {
        for (BasicRule gameResult : BasicRule.values()) {
            if (gameResult.condition.test(dealer, player)) {
                return gameResult.moneyRule;
            }
        }
        throw new IllegalArgumentException("승무패 중 결정할 수 없습니다.");
    }
}
