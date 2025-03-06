package view;

import domain.Card;
import domain.CardDeck;
import domain.Dealer;
import domain.Player;
import domain.Rank;
import domain.SetUpCardsDTO;
import domain.Shape;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

/*    Map<Rank, String> //ACE -> "A"
    Map<Shape, String> //SPADE -> "스페이드"*/

    Map<Rank, String> rankNames = Map.ofEntries(
        Map.entry(Rank.ACE, "A"),
        Map.entry(Rank.ACE_HIGH, "A"),
        Map.entry(Rank.TWO, "2"),
        Map.entry(Rank.THREE, "3"),
        Map.entry(Rank.FOUR, "4"),
        Map.entry(Rank.FIVE, "5"),
        Map.entry(Rank.SIX, "6"),
        Map.entry(Rank.SEVEN, "7"),
        Map.entry(Rank.EIGHT, "8"),
        Map.entry(Rank.NINE, "9"),
        Map.entry(Rank.TEN, "10"),
        Map.entry(Rank.JACK, "J"),
        Map.entry(Rank.QUEEN, "Q"),
        Map.entry(Rank.KING, "K")
    );

    Map<Shape, String> shapeNames = Map.ofEntries(
        Map.entry(Shape.SPADE, "스페이드"),
        Map.entry(Shape.DIAMOND, "다이아몬드"),
        Map.entry(Shape.HEART, "하트"),
        Map.entry(Shape.CLOVER, "클로버")
    );

    private String cardNameOf(Card card) {
        return rankNames.get(card.getRank()) + shapeNames.get(card.getShape());
    }

    public void printSetUpCardDeck(SetUpCardsDTO setUpCardsDTO) {
        Card dealerOpenCard = setUpCardsDTO.dealerOpenCard();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.\n", playerNames(setUpCardsDTO));
        System.out.println("딜러카드: " + cardNameOf(dealerOpenCard));
        Map<String, List<Card>> playerCards = setUpCardsDTO.cards();
        playerCards.forEach((key, value) -> System.out.printf("%s카드: %s%n", key, cardNames(value)));
    }

    private String cardNames(List<Card> cards) {
        return cards.stream()
            .map(this::cardNameOf)
            .collect(Collectors.joining(", "));
    }

    private String playerNames(SetUpCardsDTO setUpCardsDTO) {
        return String.join(", ", setUpCardsDTO.cards().keySet());
    }
}
