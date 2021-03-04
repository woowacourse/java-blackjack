package blackjack.util;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardFactoryTest {

    @Test
    @DisplayName("일반적인 카드 리스트를 반환한다.")
    void getNormalCards_CardFactoryShouldReturnNormalCards() {
        List<Card> cards = CardFactory.getNormalCards();

        assertThat(cards).containsAll(getNormalCards());
    }

    private List<Card> getNormalCards() {
        List<Card> cards = new ArrayList<>();

        for (Symbol symbol : Symbol.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(symbol, cardNumber));
            }
        }

        return cards;
    }
}
