package fixture;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardFixture {
    public static Card 카드(Denomination denomination) {
        return new Card(denomination, Suit.CLOVER);
    }

    public static Card 카드() {
        return 카드(Denomination.ACE);
    }

    public static List<Card> 카드들(Card... cards) {
        return Arrays.asList(cards);
    }

    public static List<Card> 전체_카드() {
        List<Card> cards = new ArrayList<>();

        cards.addAll(하트_카드());
        cards.addAll(클로버_카드());
        cards.addAll(스페이드_카드());
        cards.addAll(다이아몬드_카드());
        return cards;
    }

    public static List<Card> 하트_카드() {
        return 카드_그룹(Suit.HEART);
    }

    public static List<Card> 클로버_카드() {
        return 카드_그룹(Suit.CLOVER);
    }

    public static List<Card> 스페이드_카드() {
        return 카드_그룹(Suit.SPADE);
    }

    public static List<Card> 다이아몬드_카드() {
        return 카드_그룹(Suit.DIAMOND);
    }

    private static List<Card> 카드_그룹(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(denomination, suit))
                .toList();
    }
}
