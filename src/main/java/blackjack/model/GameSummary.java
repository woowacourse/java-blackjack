package blackjack.model;

public record GameSummary(User user, int totalScore, boolean bust, boolean blackjack) {
}
