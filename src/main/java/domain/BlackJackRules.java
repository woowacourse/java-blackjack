package domain;

import static domain.FinalResult.DRAW;
import static domain.FinalResult.LOSE;
import static domain.FinalResult.WIN;

import java.util.Arrays;
import java.util.List;

public class BlackJackRules {

    public static final List<Rule> RULES = Arrays.asList(
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
