package model.player.dto;

import model.card.Card;
import model.cards.Cards;
import model.name.Name;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerResponse {

    private final String name;
    private final Cards cards;

    private PlayerResponse(final String name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerResponse of(final Name name, final Cards cards) {
        return new PlayerResponse(name.getName(), cards);
    }

    public String getNameValue() {
        return name;
    }

    public String getCardsName() {
        List<Name> cardNames = getPlayerCardNames();
        return Name.chainingNames(cardNames);
    }

    public String getCardsNameWithSecret() {
        List<Name> cardNames = getPlayerCardNames().stream()
                .limit(cards.getCardSize() - 1)
                .collect(Collectors.toList());
        return Name.chainingNames(cardNames);
    }

    private List<Name> getPlayerCardNames() {
        return cards.getCardList()
                .stream()
                .map(Card::getName)
                .collect(Collectors.toList());
    }

    public boolean isDealerResponse() {
        return name.equals(Name.getDealer());
    }

    public int getScore() {
        return cards.calculateScore();
    }
}
