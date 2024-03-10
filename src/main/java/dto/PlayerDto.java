package dto;

import domain.player.Player;
import java.util.List;

public record PlayerDto(String name, List<String> hands, int score) {

    public static PlayerDto from(Player player) {
        String name = player.getName();
        List<String> hands = player.getHand().getCards().stream()
                .map(card -> RankDisplay.from(card.getRank()).getText() + SuitDiplay.from(card.getSuit()).getText())
                .toList();
        int score = player.getTotalScore().get();
        return new PlayerDto(name, hands, score);
    }

    public static List<PlayerDto> from(List<Player> players) {
        return players.stream()
                .map(PlayerDto::from)
                .toList();
    }
}
