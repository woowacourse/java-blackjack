package blackjack.dto;

import java.util.List;

import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

// TODO: 재사용 가능한 DTO로 수정하기
public record CurrentPlayerResponseDto(
    String name,
    List<CardDto> cards
) {

    public static CurrentPlayerResponseDto from(Gamer gamer) {
        return new CurrentPlayerResponseDto(
            getNameOf(gamer),
            gamer.getCards().stream()
                .map(CardDto::from)
                .toList());
    }

    // TODO: 중복 코드 제거 필요
    private static String getNameOf(Gamer gamer) {
        if (gamer instanceof Player player) {
            return player.getName();
        }
        return "딜러";
    }
}
