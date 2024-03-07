package fixture;

import domain.Card;
import domain.Denomination;
import domain.Emblem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardFixture {
    public static Card 카드(Denomination denomination) {
        return new Card(denomination, Emblem.CLOVER);
    }

    public static Card 카드() {
        return 카드(Denomination.ACE);
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
        return 카드_그룹(Emblem.HEART);
    }

    public static List<Card> 클로버_카드() {
        return 카드_그룹(Emblem.CLOVER);
    }

    public static List<Card> 스페이드_카드() {
        return 카드_그룹(Emblem.SPADE);
    }

    public static List<Card> 다이아몬드_카드() {
        return 카드_그룹(Emblem.DIAMOND);
    }

    private static List<Card> 카드_그룹(Emblem emblem) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(denomination, emblem))
                .toList();
    }


}
