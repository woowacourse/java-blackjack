package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new ArrayList<>());
    }

    @DisplayName("카드들을 받는다.")
    @Test
    void receiveCards() {
        // given
        final List<Card> cards = provideCards(2);

        // when
        dealer.receiveCards(cards);

        // then
        assertThat(dealer).isEqualTo(new Dealer(cards));
    }

    @DisplayName("딜러는 카드 2개 중 1개만 보여준다.")
    @Test
    void showDealerCards() {
        // given
        final List<Card> cards = provideCards(2);
        dealer.receiveCards(cards);

        // when
        final List<Card> dealerCards = dealer.getCards();

        // then
        assertThat(dealerCards).isEqualTo(List.of(new Card(Shape.SPADE, Denomination.A)));
    }
}