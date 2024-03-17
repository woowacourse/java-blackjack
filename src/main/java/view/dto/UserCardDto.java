package view.dto;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.player.Name;

public class UserCardDto {

    private final String name;
    private final List<String> cards;

    public UserCardDto(Name name, Cards cards) {
        this.name = name.getValue();
        this.cards = cardsToString(cards);
    }

    private List<String> cardsToString(Cards cards) {
        return cards.getCards().stream()
                .map(this::cardToString)
                .toList();
    }

    private String cardToString(Card card) {
        return card.getNumber().getName() + card.getShape().getName();
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
