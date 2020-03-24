package domain.result.rule;

import domain.gamer.Gamer;

@FunctionalInterface
public interface MatchRule {
    boolean isMatch(Gamer dealer, Gamer player);
}
