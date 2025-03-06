package blackjack.dto;

import java.util.List;

import blackjack.domain.gamer.Gamer;

// TODO: 재사용 가능한 DTO로 수정하기
// TODO: DTO 패키지 분리하기
public record GamerDto(
    String name,
    List<CardDto> cards
) {

    public static GamerDto from(Gamer gamer) {
        return new GamerDto(
            gamer.getName(),
            gamer.getCards().stream()
                .map(CardDto::from)
                .toList());
    }
}
