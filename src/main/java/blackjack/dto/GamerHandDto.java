package blackjack.dto;

import blackjack.domain.gamer.BlackjackGamer;
import java.util.List;

public class GamerHandDto {

    private final String name;
    private final List<CardDto> gamerHand;

    private GamerHandDto(String name, List<CardDto> gamerHand) {
        this.name = name;
        this.gamerHand = gamerHand;
    }

    public static GamerHandDto fromGamer(BlackjackGamer gamer) {
        String gamerName = gamer.getName();
        List<CardDto> cards = gamer.getCurrentCards().stream()
                .map(CardDto::fromCard)
                .toList();

        return new GamerHandDto(gamerName, cards);
    }

    public String name() {
        return name;
    }

    public List<CardDto> gamerHand() {
        return gamerHand;
    }
}
