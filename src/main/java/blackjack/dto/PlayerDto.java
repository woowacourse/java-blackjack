package blackjack.dto;

import blackjack.domain.Cards.Hand;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import java.util.List;

public record PlayerDto(String playerName, List<String> hand, int score) {
    public static PlayerDto from(Player player) {
        Name name = player.getName();
        Hand hand = player.getHand();
        return new PlayerDto(name.getName(), HandDto.from(hand).cardNames(), player.calculateScore());
    }
}
