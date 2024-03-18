package dto;

import domain.player.Player;
import java.util.List;

public record PlayerDto(String name, List<String> hands) {

    public static PlayerDto from(final Player player) {
        String name = player.getName().name();
        List<String> hands = player.getHand().getCards().stream()
                .map(card -> RankDisplay.from(card.getRank()).getText() + SuitDiplay.from(card.getSuit()).getText())
                .toList();
        return new PlayerDto(name, hands);
    }


    public static List<PlayerDto> from(final List<Player> players) {
        return players.stream()
                .map(PlayerDto::from)
                .toList();
    }
}
