package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private final Card cardKing = new Card(CardShape.SPADE, CardNumber.KING);
    private final Card cardEight = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialGroup;

    @BeforeEach
    void setUp() {
        initialGroup = new CardGroup(cardKing, cardEight);
    }

    @Test
    @DisplayName("딜러 초기화 테스트")
    void initTest() {
        final User dealer = new Dealer(initialGroup);

        assertSoftly(softly -> {
            softly.assertThat(dealer.getName().getValue()).isEqualTo(DealerName.DEALER_NAME);
            softly.assertThat(dealer.getCardGroups().getCards()).containsExactly(cardKing, cardEight);
        });
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        final User dealer = new Dealer(initialGroup);

        assertThat(dealer.getFirstOpenCardGroup().getCards()).containsExactly(cardKing);
    }
}
