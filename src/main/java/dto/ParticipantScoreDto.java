package dto;

import java.util.List;

public record ParticipantScoreDto(String name, List<String> cardNames, int score) {
}