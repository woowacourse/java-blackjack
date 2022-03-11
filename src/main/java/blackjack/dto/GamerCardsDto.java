package blackjack.dto;

import blackjack.domain.card.CardGroup;
import blackjack.domain.gamer.Gamer;
import java.util.ArrayList;
import java.util.List;

public class GamerCardsDto {
    private final String name;
    private final List<CardDto> cards;
    private final int sum;

    public GamerCardsDto(String name, List<CardDto> cards, int sum) {
        this.name = name;
        this.cards = cards;
        this.sum = sum;
    }

    public static GamerCardsDto of(String name, CardGroup cards) {
        return new GamerCardsDto(name, CardDto.of(cards), cards.getScore());
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

    public int getSum() {
        return sum;
    }
}
