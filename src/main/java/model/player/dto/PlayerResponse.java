package model.player.dto;

import model.card.Card;
import model.cards.Cards;
import model.name.Name;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerResponse {

    private static final String ALERT_CARD = "카드";
    private static final String ALERT_RESULT = " - 결과";
    private static final String ALERT_MARK = ": ";

    private final String name;
    private final Cards cards;

    private PlayerResponse(final String name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerResponse of(final Name name, final Cards cards) {
        return new PlayerResponse(name.getName(), cards);
    }


    public String getCardsWithSecret() {
        List<Name> cardNames = cards.getCardList()
                .stream()
                .limit(cards.getCardSize() - 1)
                .map(Card::getName)
                .collect(Collectors.toList());

        return ALERT_MARK + Name.chainingNames(cardNames);
    }

    public String getPlayerCardsResult() {
        return getCardsName() + ALERT_RESULT + ALERT_MARK + getScore();
    }

    public String getDealerCardsResult() {
        List<Name> cardNames = cards.getCardList()
                .stream()
                .map(Card::getName)
                .collect(Collectors.toList());

        return Name.getDealer() + " " + ALERT_CARD + ALERT_MARK + Name.chainingNames(cardNames) +
                ALERT_RESULT + ALERT_MARK + getScore();
    }

    public String getNameValue() {
        return name;
    }

    public String getCardsName() {
        List<Name> cardNames = cards.getCardList()
                .stream()
                .map(Card::getName)
                .collect(Collectors.toList());

        return Name.chainingNames(cardNames);
    }

    public int getScore() {
        return cards.calculateScore();
    }
}
