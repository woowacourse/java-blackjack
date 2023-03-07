package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class DealerTest {

    private final Card firstCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card secondCard = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialGroup;

    @BeforeEach
    void setUp() {
        initialGroup = new CardGroup(firstCard, secondCard);
    }

    @Test
    @DisplayName("딜러 초기화 테스트")
    void initTest() {
        User dealer = new Dealer(initialGroup);

        assertSoftly(softly -> {
            softly.assertThat(dealer.getName()).isEqualTo(Dealer.DEALER_NAME);
            softly.assertThat(dealer.getHandholdingCards()).containsExactly(firstCard, secondCard);
        });
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        User dealer = new Dealer(initialGroup);

        assertThat(dealer.getInitialHoldingCards()).containsExactly(firstCard);
    }

    @Test
    @DisplayName("딜러가 카드를 추가로 뽑아야 하는지 확인 테스트")
    void isOverDrawTest() {
        final Dealer dealer = new Dealer(initialGroup);

        boolean isOverDraw = dealer.isOverDraw();

        assertThat(isOverDraw).isTrue();
    }

}
