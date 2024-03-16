package view.dto;

import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.card.Cards;
import model.player.Name;
import model.player.Participant;

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
        return cardNumberToString(card.getNumber()) + cardShapeToString(card.getShape());
    }

    private String cardNumberToString(CardNumber cardNumber) {
        if (cardNumber == CardNumber.ACE) {
            return "A";
        }
        if (cardNumber == CardNumber.QUEEN) {
            return "Q";
        }
        if (cardNumber == CardNumber.KING) {
            return "K";
        }
        if (cardNumber == CardNumber.JACK) {
            return "J";
        }
        return String.valueOf(cardNumber.minimumNumber());
    }

    private String cardShapeToString(CardShape cardShape) {
        if (cardShape == CardShape.SPACE) {
            return "스페이드";
        }
        if (cardShape == CardShape.CLOVER) {
            return "클로버";
        }
        if (cardShape == CardShape.HEART) {
            return "하트";
        }
        return "다이아몬드";
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}
