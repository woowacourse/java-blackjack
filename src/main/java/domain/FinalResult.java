package domain;

import static domain.BlackJackRules.RULES;

import domain.gamer.Dealer;
import domain.gamer.Player;

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
}
