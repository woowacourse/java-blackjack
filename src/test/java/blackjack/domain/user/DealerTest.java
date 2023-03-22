package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    private static final String TEST_PLAYER_NAME = "필립";

    private final Card cardKing = new Card(CardShape.SPADE, CardNumber.KING);
    private final Card cardEight = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialCardGroup;
    private CardGroup bustCardGroup;

    @BeforeEach
    void setUp() {
        initialCardGroup = new CardGroup(cardKing, cardEight);
        bustCardGroup = new CardGroup(cardKing, cardEight);
        bustCardGroup.add(cardKing);
    }

    @Test
    @DisplayName("딜러 초기화 테스트")
    void initTest() {
        final User dealer = new Dealer(initialCardGroup);

        assertSoftly(softly -> {
            softly.assertThat(dealer.getName().getValue()).isEqualTo(DealerName.DEALER_NAME);
            softly.assertThat(dealer.getCardGroups().getCards()).containsExactly(cardKing, cardEight);
        });
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        final User dealer = new Dealer(initialCardGroup);

        assertThat(dealer.getFirstOpenCardGroup().getCards()).containsExactly(cardKing);
    }

    @Nested
    @DisplayName("플레이어의 수익률을 반환하는 기능 테스트")
    class calculateProfitTest {

        private final CardGroup blackjackGroup = new CardGroup(
                new Card(CardShape.DIAMOND, CardNumber.ACE),
                new Card(CardShape.SPADE, CardNumber.TEN)
        );

        @Test
        @DisplayName("플레이어가 bust이면 수익률은 -1이 반환된다")
        void whenPlayerBust() {
            final Player player = new Player(TEST_PLAYER_NAME, bustCardGroup);
            final Dealer dealer = new Dealer(initialCardGroup);

            assertThat(dealer.calculateProfitRate(player))
                    .isEqualTo(-1);
        }

        @Test
        @DisplayName("플레이어가 bust가 아니고 딜러가 bust일때 수익률은 1이 반환된다.")
        void whenPlayerIsNotBustDealerIsBust() {
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);
            final Dealer dealer = new Dealer(bustCardGroup);

            assertThat(dealer.calculateProfitRate(player))
                    .isEqualTo(1);
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 블랙잭이면 수익률은 0가 반환된다.")
        void whenPlayerAndDealerIsBlackjack() {
            final Player player = new Player(TEST_PLAYER_NAME, blackjackGroup);
            final Dealer dealer = new Dealer(blackjackGroup);

            assertThat(dealer.calculateProfitRate(player))
                    .isEqualTo(0);
        }

        @Test
        @DisplayName("플레이어만 블랙잭인 경우 수익률은 1.5가 반환된다.")
        void whenOnlyPlayerIsBlackjack() {
            final Player player = new Player(TEST_PLAYER_NAME, blackjackGroup);
            final Dealer dealer = new Dealer(initialCardGroup);

            assertThat(dealer.calculateProfitRate(player))
                    .isEqualTo(1.5);
        }

        @Test
        @DisplayName("둘다 블랙잭, 버스트가 아니고, 플레이어의 점수가 크면, 수익률은 1이 반환된다.")
        void whenPlayerScoreIsBiggerThanDealerScore() {
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);
            final Dealer dealer = new Dealer(new CardGroup(
                    new Card(CardShape.DIAMOND, CardNumber.TWO),
                    new Card(CardShape.SPADE, CardNumber.TWO)));

            assertThat(dealer.calculateProfitRate(player))
                    .isEqualTo(1);
        }

        @Test
        @DisplayName("둘다 블랙잭 버스트가 아니고 점수가 동일하면, 수익률은 0이 반환된다.")
        void whenPlayerScoreIsEqualToDealerScore() {
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);
            final Dealer dealer = new Dealer(initialCardGroup);

            assertThat(dealer.calculateProfitRate(player))
                    .isEqualTo(0);
        }

        @Test
        @DisplayName("둘다 블랙잭 버스트가 아니고 플레이어의 점수가 작으면, 수익률은 -1이 반환된다.")
        void whenPlayerScoreIsSmallerThanDealerScore() {
            final Player player = new Player(TEST_PLAYER_NAME, new CardGroup(
                    new Card(CardShape.DIAMOND, CardNumber.TWO),
                    new Card(CardShape.SPADE, CardNumber.TWO)
            ));
            final Dealer dealer = new Dealer(initialCardGroup);
            assertThat(dealer.calculateProfitRate(player))
                    .isEqualTo(-1);
        }
    }
}
