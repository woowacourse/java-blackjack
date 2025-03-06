package blackjack.dto;

import java.util.List;

import blackjack.domain.gamer.Gamer;

// TODO: 재사용 가능한 DTO로 수정하기
public record CurrentPlayerResponseDto(
    String name,
    List<CardDto> cards
) {

    public static CurrentPlayerResponseDto from(Gamer gamer) {
        return new CurrentPlayerResponseDto(
            gamer.getName(),
            gamer.getCards().stream()
                .map(CardDto::from)
                .toList());
    }
}
