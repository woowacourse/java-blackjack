package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardsTest {
    @Test
    @DisplayName("getCards하면 입력받은 카드 전부를 리스트로 반환한다.")
    void getCardsTest() {
        //given
        final Cards cards = Cards.from(0);

        final List<Rank> ranks = List.of(Rank.EIGHT, Rank.SIX, Rank.SEVEN);
        final Deck deck = Deck.from(TestCardGenerator.from(ranks));

        for (int i = 0; i < ranks.size(); i++) {
            cards.takeCard(deck.dealCard());
        }
        final List<Card> result =
                List.of(Card.of(Suit.CLUBS, Rank.EIGHT), Card.of(Suit.CLUBS, Rank.SIX), Card.of(Suit.CLUBS, Rank.SEVEN));

        //then
        assertThat(cards.getCards()).isEqualTo(result);
    }
}
