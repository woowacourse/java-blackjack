package view.dto;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.card.Cards;
import model.player.Name;

public class UserCardDto {
    private final Map<String, List<String>> participantCards;
    private final Map<String, List<String>> dealerCards;

    public UserCardDto(Map<Name, Cards> participantCards, Map<Name, Cards> dealerCards) {
        this.participantCards = convertUserCardsToString(participantCards);
        this.dealerCards = convertUserCardsToString(dealerCards);
    }

    private Map<String, List<String>> convertUserCardsToString(Map<Name, Cards> usersNameAndCards) {
        return usersNameAndCards.entrySet().stream()
                .collect(toMap(
                        userCard -> userCard.getKey().getValue(),
                        userCard -> cardsToString(userCard.getValue())));
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

    public Map<String, List<String>> getParticipantCards() {
        return participantCards;
    }

    public Map<String, List<String>> getDealerCards() {
        return dealerCards;
    }
}
