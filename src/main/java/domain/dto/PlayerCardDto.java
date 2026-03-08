package domain.dto;

import java.util.List;

public record PlayerCardDto(String name, List<CardDto> cards) {

}
