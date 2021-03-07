package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("딜러가 잘 생성되었는지 확인")
    void create() {
        assertThatCode(() -> new Dealer())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러가 Participant로 부터 상속받았는지 확인")
    void extend() {
        final Participant participant = new Dealer();
        participant.receiveAdditionalCard(new Card(CardNumber.ACE, CardType.HEART));
        assertThat(participant.getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인")
    void checkMoreCardAvailable() {
        dealer.receiveAdditionalCard(new Card(CardNumber.TWO, CardType.CLOVER));
        dealer.receiveAdditionalCard(new Card(CardNumber.TEN, CardType.CLOVER));
        assertThat(dealer.checkMoreCardAvailable()).isTrue();
    }

    @Test
    @DisplayName("딜러가 버스트인지 확인")
    void isBust() {
        dealer.receiveAdditionalCard(new Card(CardNumber.TEN, CardType.CLOVER));
        dealer.receiveAdditionalCard(new Card(CardNumber.NINE, CardType.HEART));
        dealer.receiveAdditionalCard(new Card(CardNumber.EIGHT, CardType.HEART));
        assertThat(dealer.isBust()).isTrue();
    }
}
