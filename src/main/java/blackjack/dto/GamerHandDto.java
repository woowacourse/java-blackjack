package blackjack.dto;

import java.util.List;

public record GamerHandDto(String name, List<CardDto> gamerHand) {
}
