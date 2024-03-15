package blackjack.dto;

import blackjack.domain.cards.Hand;
import blackjack.domain.participants.Gamer;
import blackjack.domain.participants.Name;
import java.util.List;

public record GamerDto(String GamerName, List<String> hand, int score) {
    public static GamerDto from(Gamer gamer) {
        Name name = gamer.getName();
        Hand hand = gamer.getHand();
        return new GamerDto(name.getValue(), HandDto.from(hand).cardNames(), gamer.calculateScore());
    }
}
