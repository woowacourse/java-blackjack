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
        List<Name> cardNames = cards.getCardList()
                .stream()
                .map(Card::getName)
                .collect(Collectors.toList());

        if (name.equals(Name.getDealer())) {
            cardNames = getCardsNameWithSecret(cardNames);
        }

        return Name.chainingNames(cardNames);
    }

    private List<Name> getCardsNameWithSecret(List<Name> cardNames) {
        return cardNames.stream()
                .limit(cards.getCardSize() - 1)
                .collect(Collectors.toList());
    }

    public int getScore() {
        return cards.calculateScore();
    }
}
