package domain;

import java.util.List;

public record FinalResultDTO(String name, List<Card> cards, int score) {
}
