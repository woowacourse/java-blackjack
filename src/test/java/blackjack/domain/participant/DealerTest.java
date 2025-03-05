package blackjack.domain.participant;

import static blackjack.fixture.TestFixture.provideTwoCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
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
        List<Card> cards = provideTwoCards();

        // when
        dealer.receiveCards(cards);

        // then
        assertThat(dealer).isEqualTo(new Dealer(cards));
    }

}