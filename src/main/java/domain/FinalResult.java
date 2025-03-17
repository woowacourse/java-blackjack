package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;
import java.util.Arrays;
import java.util.List;

public enum FinalResult {
    WIN,
    LOSE,
    DRAW;

    public static FinalResult determine(final Player player, final Dealer dealer) {
        return RULES.stream()
                .filter(rule -> rule.matches(player, dealer))
                .findFirst()
                .map(Rule::getFinalResult)
                .orElse(DRAW);
    }

    private static final List<Rule> RULES = Arrays.asList(
            new Rule((player, dealer) -> player.isBlackJack() && dealer.isBlackJack(), DRAW),
            new Rule((player, dealer) -> player.isBlackJack(), WIN),
            new Rule((player, dealer) -> dealer.isBlackJack(), LOSE),
            new Rule((player, dealer) -> player.isBust() && dealer.isBust(), LOSE),
            new Rule((player, dealer) -> player.isBust(), LOSE),
            new Rule((player, dealer) -> dealer.isBust(), WIN),
            new Rule((player, dealer) -> player.getSumOfRank() > dealer.getSumOfRank(), WIN),
            new Rule((player, dealer) -> player.getSumOfRank() < dealer.getSumOfRank(), LOSE),
            new Rule((player, dealer) -> player.getSumOfRank() == dealer.getSumOfRank(), DRAW)
    );
}
