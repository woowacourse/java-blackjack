package domain.dto;

import domain.Cards;

public record PlayerResponse(
        String name,
        Cards cards
) {

}