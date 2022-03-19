package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.CardGroup;
import blackjack.domain.gamer.role.Role;

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

    public static List<GamerCardsDto> of(List<Role> roles) {
        List<GamerCardsDto> gamersCardsDto = new ArrayList<>();
        for (Role role : roles) {
            gamersCardsDto.add(of(role.getName(), role.getCardGroup()));
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
