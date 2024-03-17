package dto;

import domain.Player;

import java.util.List;

public record PlayerDto(String name, List<String> hands, int score) {

    public static PlayerDto from(Player player) {
        String name = player.getName();
        List<String> hands = player.getHand().getCards().stream()
                .map(card -> card.getRankText() + card.getSuit())
                .toList();
        int score = player.getTotalScore();
        return new PlayerDto(name, hands, score);
    }
}
