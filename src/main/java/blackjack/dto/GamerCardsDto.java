package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.CardGroup;
import blackjack.domain.gamer.Player;

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

    public static List<GamerCardsDto> of(List<Player> playersCards) {
        List<GamerCardsDto> gamersCardsDto = new ArrayList<>();
        for (Player player : playersCards) {
            gamersCardsDto.add(of(player.getName(), player.getCardGroup()));
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
