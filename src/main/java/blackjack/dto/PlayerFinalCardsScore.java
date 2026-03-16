package blackjack.dto;

import java.util.List;

public record PlayerFinalCardsScore(String playerName, List<String> cardNames, int totalScore) {
}
