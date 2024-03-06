package blackjack.fixture;

import blackjack.Card;
import blackjack.CardSymbol;
import blackjack.CardValue;
import blackjack.Cards;
import java.util.List;

public class CardFixture {
    public static Cards 카드_목록_생성(List<CardValue> cardValues) {
        return new Cards(cardValues.stream()
                                   .map(cardValue -> new Card(cardValue, CardSymbol.DIAMOND))
                                   .toList());
    }
}
