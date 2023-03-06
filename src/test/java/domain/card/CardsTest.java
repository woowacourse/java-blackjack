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

        //when
        for (int i = 0; i < ranks.size(); i++) {
            cards.takeCard(deck.dealCard());
        }
        final List<Card> result =
                List.of(Card.of(Suit.CLUBS, Rank.EIGHT), Card.of(Suit.CLUBS, Rank.SIX), Card.of(Suit.CLUBS, Rank.SEVEN));

        //then
        assertThat(cards.getCards()).isEqualTo(result);
    }

    @Test
    @DisplayName("카드를 받으면, 총점에 카드의 점수만큼 더해준다.")
    void takeCards_thenAddSum() {
        //given
        final Cards cards = Cards.from(8);

        final List<Rank> ranks = List.of(Rank.EIGHT, Rank.SIX, Rank.SEVEN);
        final Deck deck = Deck.from(TestCardGenerator.from(ranks));

        //when
        for (int i = 0; i < ranks.size(); i++) {
            cards.takeCard(deck.dealCard());
        }

        //then
        assertThat(cards.getScore()).isEqualTo(8 + 8 + 6 + 7);
    }
}
