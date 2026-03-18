package domain.dto;

import java.util.List;

public record FinalCardDto(String name, List<String> cards, int total) {
}
