package blackjack.dto;

import blackjack.domain.gamer.BlackjackGamer;
import blackjack.domain.gamer.Hand;
import java.util.List;

public record GamerHandDto(String name, List<CardDto> gamerHand) {

    public static GamerHandDto fromBlackjackGamer(BlackjackGamer blackjackGamer) {
        String playerName = blackjackGamer.getName().value();
        List<CardDto> gamerHand = convertHandToCardDto(blackjackGamer.getHand());

        return new GamerHandDto(playerName, gamerHand);
    }

    public static List<CardDto> convertHandToCardDto(Hand hand) {
        return hand.getCards().stream()
                .map(CardDto::fromCard)
                .toList();
    }
}
