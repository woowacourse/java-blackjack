package domain.dto;

import java.util.List;

public record FinalCardDto(String name, List<CardDto> cards, int total) {
}
