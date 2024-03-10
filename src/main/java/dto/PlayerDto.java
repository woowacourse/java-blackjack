package dto;

import domain.Player;
import java.util.List;

public record PlayerDto(String name, List<String> hands, int score) {

    public static PlayerDto from(Player player) {
        String name = player.getName();
        List<String> hands = player.getHand().getCards().stream()
                .map(card -> RankDisplay.from(card.getRank()).getText() + SuitDiplay.from(card.getSuit()).getText())
                .toList();
        int score = player.getTotalScore();
        return new PlayerDto(name, hands, score);
    }
}
