package blackjack.model;

public record GameSummary(User user, int score, boolean isBust, boolean isBlackjack) {
}
