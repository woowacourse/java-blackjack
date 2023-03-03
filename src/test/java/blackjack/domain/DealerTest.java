package blackjack.domain;

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
            softly.assertThat(dealer.getStatus()).containsExactly(firstCard, secondCard);
        });
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        User dealer = new Dealer(initialGroup);

        assertThat(dealer.getInitialStatus()).containsExactly(firstCard);
    }

    @Test
    @DisplayName("Ace의 개수를 반환하는 기능 테스트")
    void getAceCountTest() {
        final User dealer = new Dealer(initialGroup);

        assertThat(dealer.getAceCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어와 비교해 플레이어의 승리여부 판단하는기능 테스트")
    void comparePlayerTest() {
        final Dealer dealer = new Dealer(initialGroup);
        final Player player = new Player("필립", initialGroup);

        WinningStatus winningStatus = dealer.comparePlayer(player);

        assertThat(winningStatus).isEqualTo(WinningStatus.TIE);
    }
}
