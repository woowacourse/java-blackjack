package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러")
class DealerTest {
    @Test
    @DisplayName("딜러의 첫 카드를 반환한다.")
    void getFirstCardTest() {
        // given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(Card.SPADE_NINE, Card.CLUB_QUEEN);
        Card exptecedCard = Card.SPADE_NINE;
        Deck deck = new Deck() {
            @Override
            public List<Card> pick(int count) {
                return cards;
            }
        };

        // when
        dealer.deal(deck);

        // then
        assertThat(dealer.getFirstCard())
                .isEqualTo(exptecedCard);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 true를 반환하는 메소드를 테스트한다.")
    void dealerHitUpperBoundTest() {
        // given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(Card.SPADE_NINE, Card.CLUB_SEVEN);
        Deck deck = new Deck() {
            @Override
            public List<Card> pick(int count) {
                return cards;
            }
        };

        // when & then
        assertThat(dealer.isHitUnderBound()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러의 카드의 합이 16 이하이면 카드를 한장 더 뽑늗다.")
    void dealerHitTest() {
        // given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(Card.SPADE_NINE, Card.CLUB_SEVEN);
        Deck deck = new Deck();

        // when
        dealer.deal(deck);
        if (dealer.isHitUnderBound()) {
            dealer.hit(deck);
        }

        // then
        assertThat(dealer.getCards().size())
                .isBetween(2, 3);
    }
}
