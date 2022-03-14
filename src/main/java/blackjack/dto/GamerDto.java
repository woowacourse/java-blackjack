package blackjack.dto;

import blackjack.domain.Gamer;

public class GamerDto {

    private final String name;
    private final CardsDto cards;

    public GamerDto(String name, CardsDto cards) {
        this.name = name;
        this.cards = cards;
    }

    public static GamerDto from(Gamer gamer) {
        return new GamerDto(gamer.getName(),
                CardsDto.from(gamer.getCards()));
    }

    public String getName() {
        return name;
    }

    public CardsDto getCards() {
        return cards;
    }
}
