package blackjack.dto;

import blackjack.domain.CardGroup;
import blackjack.domain.Gamer;
import java.util.ArrayList;
import java.util.List;

public class GamerCardsDto {
    private final String name;
    private final List<CardDto> cards;

    public GamerCardsDto(String name, List<CardDto> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static GamerCardsDto of(String name, CardGroup cards) {
        return new GamerCardsDto(name, CardDto.of(cards));
    }

    public static List<GamerCardsDto> of(List<Gamer> gamersCards) {
        List<GamerCardsDto> gamersCardsDto = new ArrayList<>();
        for (Gamer gamer : gamersCards) {
            gamersCardsDto.add(of(gamer.getName(), gamer.getCardGroup()));
        }
        return gamersCardsDto;
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCards() {
        return cards;
    }
}
