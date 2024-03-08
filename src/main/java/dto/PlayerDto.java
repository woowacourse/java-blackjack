package dto;

import domain.Player;

import java.util.List;

public record PlayerDto(String name, List<String> hands, int score) {

    public static PlayerDto from(Player player) {
        String name = player.getName().name();
        List<String> hands = player.getHand().getCards().stream()
                .map(card -> card.getLetterText() + card.getMark())
                .toList();
        int score = player.getTotalScore();
        return new PlayerDto(name, hands, score);
    }
}
