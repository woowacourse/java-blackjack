package dto;

import java.util.List;

public record InitialDto (
        CardDto dealerCard,
        List<PlayerDeckDto> playerDeckDtos
) {
    public static InitialDto of(CardDto cardDto, List<PlayerDeckDto> playerDeckDtos) {
        return new InitialDto(cardDto, playerDeckDtos);
    }
}
