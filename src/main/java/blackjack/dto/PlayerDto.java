package blackjack.dto;

import blackjack.domain.deck.Hands;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import java.util.List;

public record PlayerDto(String playerName, List<String> allHands, int score) {
    public static PlayerDto from(Player player) {
        Name name = player.getName();
        Hands hands = player.getHands();
        List<String> allHands = hands.getCards().stream()
                .map(tempHands -> tempHands.getRank().getName() + tempHands.getShape().name())
                .toList();
        return new PlayerDto(name.getName(), allHands, player.calculateScore());
    }
}
