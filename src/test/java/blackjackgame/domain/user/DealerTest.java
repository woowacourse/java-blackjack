package blackjackgame.domain.user;

import static blackjackgame.domain.Fixtures.EIGHT_NINE_CARDS;
import static blackjackgame.domain.Fixtures.JACK_KING_NINE_CARDS;
import static blackjackgame.domain.Fixtures.SIX_FOUR_CARDS;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("딜러는 ")
class DealerTest {
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @DisplayName("합산 점수가 21을 초과하면 BUST 상태가 된다.")
    @Test
    void checkBustByScoreTest() {
        dealer.receiveCards(JACK_KING_NINE_CARDS);

        assertThat(dealer.getStatus()).isEqualTo(DealerStatus.BUST);
    }

    @DisplayName("합산 점수가 17~21 사이 값을 가지면 NORMAL 상태가 된다.")
    @Test
    void checkNormalByScoreTest() {
        dealer.receiveCards(EIGHT_NINE_CARDS);

        assertThat(dealer.getStatus()).isEqualTo(DealerStatus.NORMAL);
    }

    @DisplayName("합산 점수가 17 미만이면 UNDER_MIN_SCORE 상태가 된다.")
    @Test
        void checkUnderMinScoreByScoreTest() {
        dealer.receiveCards(SIX_FOUR_CARDS);

        assertThat(dealer.getStatus()).isEqualTo(DealerStatus.UNDER_MIN_SCORE);
    }

    @DisplayName("처음으로 받은 카드 외에 추가로 뽑은 카드가 몇 장인지 반환한다.")
    @Test
    void getExtraDrawCount_drawTwoMoreCards() {
        dealer.receiveCards(SIX_FOUR_CARDS);

        dealer.receiveCards(EIGHT_NINE_CARDS);
        int extraDrawCount = dealer.getExtraDrawCount(2);

        assertThat(extraDrawCount).isEqualTo(2);
    }
}
