package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Gamer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamerCardsDto {
    private final String name;
    private final List<CardDto> cards;

    public GamerCardsDto(String name, List<CardDto> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static GamerCardsDto of(String name, List<Card> cards) {
        return new GamerCardsDto(name, CardDto.of(cards));
    }

    public static List<GamerCardsDto> of(List<Gamer> gamersCards) {
        List<GamerCardsDto> gamersCardsDto = new ArrayList<>();
        for (Gamer gamer : gamersCards) {
            gamersCardsDto.add(of(gamer.getName(), gamer.getCards()));
        }
        return Collections.unmodifiableList(gamersCardsDto);
    }

    public String getName() {
        return name;
    }

    public List<CardDto> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
