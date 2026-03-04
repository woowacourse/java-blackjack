package dto;

import java.util.List;

public record GameStatus(String name, List<String> cards, int scoreSum) {
}
