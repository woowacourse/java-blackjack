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
    private CardGroup initialGroup;

    @BeforeEach
    void setUp() {
        initialGroup = new CardGroup(cardKing, cardEight);
    }

    @Test
    @DisplayName("플레이어 초기화 테스트")
    void initTest() {
        final User player = new Player(name, initialGroup);
        assertSoftly(softly -> {
            softly.assertThat(player.getName().getValue()).isEqualTo(name);
            softly.assertThat(player.getCardGroups().getCards()).containsExactly(cardKing, cardEight);
        });
    }

    @Test
    @DisplayName("플레이어의 이름으로 '딜러'가 들어오는 경우 예외처리하는 기능 테스트")
    void throwExceptionWhenPlayerNameIsDealerName() {
        assertThatThrownBy(() -> new Player(Dealer.DEALER_NAME, initialGroup)).isInstanceOf(
                        IllegalArgumentException.class)
                .hasMessage(Player.NAME_IS_DEALER_NAME_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("유저의 점수를 계산하는 기능 테스트")
    void getScoreTest() {
        final User player = new Player(name, initialGroup);

        assertThat(player.getScore().getValue())
                .isEqualTo(cardKing.getNumber().getValue() + cardEight.getNumber().getValue());
    }

    @Test
    @DisplayName("유저가 카드를 하나 뽑는 기능 테스트")
    void drawCardTest() {
        final Card card = new Card(CardShape.HEART, CardNumber.JACK);
        final User player = new Player(name, initialGroup);
        final Deck deck = new Deck(new TestNonShuffledDeckGenerator(List.of(card)));

        player.drawCard(deck);

        Assertions.assertThat(player.getCardGroups().getCards()).containsExactly(cardKing, cardEight, card);
    }

    @Test
    @DisplayName("첫 패 확인 테스트")
    void getInitialStatus() {
        final User player = new Player(name, initialGroup);

        assertThat(player.getFirstOpenCardGroup().getCards()).containsExactly(cardKing, cardEight);
    }

    @Nested
    @DisplayName("딜러와 플레이어의 카드를 비교하여, WinningStatus를 반환하는 기능 테스트")
    class comparePlayerTest {

        @Test
        @DisplayName("딜러가 bust 플레이어가 bust일 때 패배가 반환")
        void comparePlayerTestIfDealerBustPlayerBust() {
            final CardGroup bustCardGroup = initialGroup;
            bustCardGroup.add(new Card(CardShape.CLOVER, CardNumber.FIVE));
            final Dealer dealer = new Dealer(bustCardGroup);
            final Player player = new Player(TEST_PLAYER_NAME, bustCardGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.LOSE);
        }

        @Test
        @DisplayName("딜러가 bust 플레이어가 bust가 아닐 때 승리가 반환")
        void comparePlayerTestIfDealerBustPlayerNonBust() {
            final CardGroup bustCardGroup = new CardGroup(cardKing, cardEight);
            bustCardGroup.add(new Card(CardShape.CLOVER, CardNumber.FIVE));
            final Dealer dealer = new Dealer(bustCardGroup);
            final Player player = new Player(TEST_PLAYER_NAME, initialGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.WIN);
        }

        @Test
        @DisplayName("딜러와 플레이어가 bust가 아니고, score가 동일할 때 무승부 반환")
        void comparePlayerEqualScore() {
            final Dealer dealer = new Dealer(new CardGroup(cardKing, cardEight));
            final Player player = new Player(TEST_PLAYER_NAME, initialGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.TIE);
        }

        @Test
        @DisplayName("딜러와 플레이어가 bust가 아니고, 플레이어의 스코어가 더 클 때 승리 반환")
        void comparePlayerIfPlayerScoreBiggerThanDealer() {
            final Dealer dealer = new Dealer(new CardGroup
                    (new Card(CardShape.HEART, CardNumber.FIVE), new Card(CardShape.CLOVER, CardNumber.EIGHT)));
            final Player player = new Player(TEST_PLAYER_NAME, initialGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.WIN);
        }

        @Test
        @DisplayName("딜러와 플레이어는 버스트가 아니고 플레이어의 점수가 딜러보다 낮은 경우 패배 반환")
        void comparePlayerIfPlayerScoreSmallerThanDealer() {
            final Dealer dealer = new Dealer(new CardGroup(cardKing, cardKing));
            final Player player = new Player(TEST_PLAYER_NAME, initialGroup);

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.LOSE);
        }

        @Test
        @DisplayName("딜러는 버스트가 아니고, 플레이어는 버스트인 경우 패배 반환")
        void comparePlayerIfDealerNonBustPlayerBust() {
            final Player player = new Player(TEST_PLAYER_NAME, initialGroup);
            final Dealer dealer = new Dealer(new CardGroup(cardKing, cardEight));

            player.drawCard(new Deck(new TestNonShuffledDeckGenerator(List.of(cardEight))));

            assertThat(player.calculateWinningStatus(dealer)).isEqualTo(WinningStatus.LOSE);
        }
    }
}
