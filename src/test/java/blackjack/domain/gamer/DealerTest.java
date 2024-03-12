package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러")
class DealerTest {
    @Test
    @DisplayName("딜러의 첫 카드를 반환한다.")
    void getFirstCardTest() {
        // given
        Dealer dealer = new Dealer(new Hand(List.of()));
        List<Card> cards = List.of(Card.SPADE_NINE, Card.CLUB_QUEEN);
        List<Card> expectedCard = List.of(Card.SPADE_NINE);

        // when
        dealer.draw(cards);

        // then
        assertThat(dealer.getCards())
                .isEqualTo(expectedCard);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 true를 반환한다.")
    void dealerHitUpperBoundTest() {
        // given
        Dealer dealer = new Dealer(new Hand(List.of()));
        List<Card> cards = List.of(Card.SPADE_NINE, Card.CLUB_SEVEN);
        Deck cardPicker = new Deck(Arrays.asList(Card.values())) {
            @Override
            public List<Card> pickCards(int count) {
                return cards;
            }
        };
        dealer.draw(cardPicker.pickCards(2));

        // when & then
        assertThat(dealer.isScoreUnderBound()).isEqualTo(true);
    }
}
