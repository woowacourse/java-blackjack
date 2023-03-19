package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Pattern;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private final Dealer dealer = new Dealer();

    @BeforeEach
    void setUp() {
        dealer.receiveCard(new Card(Number.Q, Pattern.CLUB));
        dealer.receiveCard(new Card(Number.SIX, Pattern.CLUB));
    }

    @Test
    @DisplayName("딜러의 카드가 16점 이하이면 true 반환")
    void canDraw_true() {
        // expect
        assertThat(dealer.canDraw()).isTrue();
    }

    @Test
    @DisplayName("딜러의 카드가 16점 초과이면 false 반환")
    void canDraw_false() {
        // given
        dealer.receiveCard(new Card(Number.ACE, Pattern.CLUB));

        // expect
        assertThat(dealer.canDraw()).isFalse();
    }

    @Test
    @DisplayName("딜러가 갖고 있는 카드 중 하나 반환")
    void getOneCardToShow() {
        // expect
        assertThat(dealer.showInitCards()).isEqualTo(List.of(new Card(Number.Q, Pattern.CLUB)));
    }
}
