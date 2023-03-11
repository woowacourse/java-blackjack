package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Deck;
import blackjack.domain.card.TestNonShuffledDeckGenerator;
import blackjack.domain.result.WinningStatus;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private static final String TEST_PLAYER_NAME = "필립";
    private final String name = "test";
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
    @DisplayName("플레이어 초기화 테스트")
    void initTest() {
        final User player = new Player(name, initialCardGroup);
        assertSoftly(softly -> {
            softly.assertThat(player.getName().getValue()).isEqualTo(name);
            softly.assertThat(player.getCardGroups().getCards()).containsExactly(cardKing, cardEight);
        });
    }

    @Test
    @DisplayName("플레이어의 이름으로 '딜러'가 들어오는 경우 예외처리하는 기능 테스트")
    void throwExceptionWhenPlayerNameIsDealerName() {
        assertThatThrownBy(() -> new Player(Dealer.DEALER_NAME, initialCardGroup)).isInstanceOf(
                        IllegalArgumentException.class)
                .hasMessage(Player.NAME_IS_DEALER_NAME_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("유저의 점수를 계산하는 기능 테스트")
    void getScoreTest() {
        final User player = new Player(name, initialCardGroup);

        assertThat(player.getScore().getValue())
                .isEqualTo(cardKing.getNumber().getValue() + cardEight.getNumber().getValue());
    }

    @Test
    @DisplayName("유저가 카드를 하나 뽑는 기능 테스트")
    void drawCardTest() {
        final Card card = new Card(CardShape.HEART, CardNumber.JACK);
        final User player = new Player(name, initialCardGroup);
        final Deck deck = new Deck(new TestNonShuffledDeckGenerator(List.of(card)));

        player.drawCard(deck);

        Assertions.assertThat(player.getCardGroups().getCards()).containsExactly(cardKing, cardEight, card);
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        final User player = new Player(name, initialCardGroup);

        assertThat(player.getFirstOpenCardGroup().getCards()).containsExactly(cardKing, cardEight);
    }

    @Nested
    @DisplayName("딜러와 플레이어의 카드를 비교하여, WinningStatus를 반환하는 기능 테스트")
    class calculateWinningStatusTest {

        @Test
        @DisplayName("플레이어가 bust일 때 패배가 반환")
        void comparePlayerTestIfDealerBustPlayerBust() {
            final Dealer dealer = new Dealer(bustCardGroup);
            final Player player = new Player(TEST_PLAYER_NAME, bustCardGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.LOSE);
        }

        @Test
        @DisplayName("딜러가 bust 플레이어가 bust가 아닐 때 승리가 반환")
        void comparePlayerTestIfDealerBustPlayerNonBust() {
            final Dealer dealer = new Dealer(bustCardGroup);
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.WIN);
        }

        @Test
        @DisplayName("딜러와 플레이어가 bust가 아니고, score가 동일할 때 무승부 반환")
        void comparePlayerEqualScore() {
            final Dealer dealer = new Dealer(new CardGroup(cardKing, cardEight));
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.TIE);
        }

        @Test
        @DisplayName("딜러와 플레이어가 bust가 아니고, 플레이어의 스코어가 더 클 때 승리 반환")
        void comparePlayerIfPlayerScoreBiggerThanDealer() {
            final Dealer dealer = new Dealer(new CardGroup
                    (new Card(CardShape.HEART, CardNumber.FIVE), new Card(CardShape.CLOVER, CardNumber.EIGHT)));
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.WIN);
        }

        @Test
        @DisplayName("딜러와 플레이어는 버스트가 아니고 플레이어의 점수가 딜러보다 낮은 경우 패배 반환")
        void comparePlayerIfPlayerScoreSmallerThanDealer() {
            final Dealer dealer = new Dealer(new CardGroup(cardKing, cardKing));
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.LOSE);
        }
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

            assertThat(player.calculateProfitRate(dealer))
                    .isEqualTo(-1);
        }

        @Test
        @DisplayName("플레이어가 bust가 아니고 딜러가 bust일때 수익률은 1이 반환된다.")
        void whenPlayerIsNotBustDealerIsBust() {
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);
            final Dealer dealer = new Dealer(bustCardGroup);

            assertThat(player.calculateProfitRate(dealer))
                    .isEqualTo(1);
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 블랙잭이면 수익률은 0가 반환된다.")
        void whenPlayerAndDealerIsBlackjack() {
            final Player player = new Player(TEST_PLAYER_NAME, blackjackGroup);
            final Dealer dealer = new Dealer(blackjackGroup);

            assertThat(player.calculateProfitRate(dealer))
                    .isEqualTo(0);
        }

        @Test
        @DisplayName("플레이어만 블랙잭인 경우 수익률은 1.5가 반환된다.")
        void whenOnlyPlayerIsBlackjack() {
            final Player player = new Player(TEST_PLAYER_NAME, blackjackGroup);
            final Dealer dealer = new Dealer(initialCardGroup);

            assertThat(player.calculateProfitRate(dealer))
                    .isEqualTo(1.5);
        }

        @Test
        @DisplayName("둘다 블랙잭, 버스트가 아니고, 플레이어의 점수가 크면, 수익률은 1이 반환된다.")
        void whenPlayerScoreIsBiggerThanDealerScore() {
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);
            final Dealer dealer = new Dealer(new CardGroup(
                    new Card(CardShape.DIAMOND, CardNumber.TWO),
                    new Card(CardShape.SPADE, CardNumber.TWO)));

            assertThat(player.calculateProfitRate(dealer))
                    .isEqualTo(1);
        }

        @Test
        @DisplayName("둘다 블랙잭 버스트가 아니고 점수가 동일하면, 수익률은 0이 반환된다.")
        void whenPlayerScoreIsEqualToDealerScore() {
            final Player player = new Player(TEST_PLAYER_NAME, initialCardGroup);
            final Dealer dealer = new Dealer(initialCardGroup);

            assertThat(player.calculateProfitRate(dealer))
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

            assertThat(player.calculateProfitRate(dealer))
                    .isEqualTo(-1);
        }
    }
}
