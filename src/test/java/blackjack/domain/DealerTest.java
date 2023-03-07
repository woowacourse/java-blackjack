package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
        User dealer = new Dealer(initialGroup);

        assertSoftly(softly -> {
            softly.assertThat(dealer.getName()).isEqualTo(Dealer.DEALER_NAME);
            softly.assertThat(dealer.getCardGroups().getCards()).containsExactly(cardKing, cardEight);
        });
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        final User dealer = new Dealer(initialGroup);

        assertThat(dealer.getFirstOpenCardGroup().getCards()).containsExactly(cardKing);
    }

    @Nested
    @DisplayName("딜러와 플레이어의 카드를 비교하여, WinningStatus를 반환하는 기능 테스트")
    class comparePlayerTest {

        @Test
        @DisplayName("딜러가 bust 플레이어가 bust일 때 무승부가 반환")
        void comparePlayerTestIfDealerBustPlayerBust() {
            final CardGroup bustCardGroup = initialGroup;
            bustCardGroup.add(new Card(CardShape.CLOVER, CardNumber.FIVE));
            final Dealer dealer = new Dealer(bustCardGroup);
            final Player player = new Player("제이미", bustCardGroup);

            assertThat(dealer.comparePlayer(player)).isEqualTo(WinningStatus.TIE);
        }

        @Test
        @DisplayName("딜러가 bust 플레이어가 bust가 아닐 때 승리가 반환")
        void comparePlayerTestIfDealerBustPlayerNonBust() {
            final CardGroup bustCardGroup = new CardGroup(cardKing, cardEight);
            bustCardGroup.add(new Card(CardShape.CLOVER, CardNumber.FIVE));
            final Dealer dealer = new Dealer(bustCardGroup);
            final Player player = new Player("제이미", initialGroup);

            assertThat(dealer.comparePlayer(player)).isEqualTo(WinningStatus.WIN);
        }

        @Test
        @DisplayName("딜러와 플레이어가 bust가 아니고, score가 동일할 때 무승부 반환")
        void comparePlayerEqualScore() {
            final Dealer dealer = new Dealer(new CardGroup(cardKing, cardEight));
            final Player player = new Player("필립", initialGroup);

            WinningStatus winningStatus = dealer.comparePlayer(player);

            assertThat(winningStatus).isEqualTo(WinningStatus.TIE);
        }

        @Test
        @DisplayName("딜러와 플레이어가 bust가 아니고, 플레이어의 스코어가 더 클 때 승리 반환")
        void comparePlayerIfPlayerScoreBiggerThanDealer() {
            final Dealer dealer = new Dealer(new CardGroup
                    (new Card(CardShape.HEART, CardNumber.FIVE), new Card(CardShape.CLOVER, CardNumber.EIGHT)));
            final Player player = new Player("제이미", initialGroup);

            WinningStatus winningStatus = dealer.comparePlayer(player);

            assertThat(winningStatus).isEqualTo(WinningStatus.WIN);
        }

        @Test
        @DisplayName("딜러와 플레이어는 버스트가 아니고 플레이어의 점수가 딜러보다 낮은 경우 패배 반환")
        void comparePlayerIfPlayerScoreSmallerThanDealer() {
            final Dealer dealer = new Dealer(new CardGroup(cardKing, cardKing));
            final Player player = new Player("홍실", initialGroup);

            final WinningStatus winningStatus = dealer.comparePlayer(player);

            assertThat(winningStatus).isEqualTo(WinningStatus.LOSE);
        }

        @Test
        @DisplayName("딜러는 버스트가 아니고, 플레이어는 버스트인 경우 패배 반환")
        void comparePlayerIfDealerNonBustPlayerBust() {
            final Player player = new Player("홍실", initialGroup);
            final Dealer dealer = new Dealer(new CardGroup(cardKing, cardEight));

            player.drawCard(new Deck(new TestDeckGenerator(List.of(cardEight))));

            final WinningStatus winningStatus = dealer.comparePlayer(player);

            assertThat(winningStatus).isEqualTo(WinningStatus.LOSE);
        }
    }
}
