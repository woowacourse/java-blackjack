package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Card;
import blackjack.domain.Card.Shape;
import blackjack.domain.Card.Value;
import blackjack.domain.Deck;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @DisplayName("카드의 총 점수가 16을 넘지 않으면, 카드를 더 뽑을 수 있다")
    @Test
    void isDrawableTest_whenScoreIsUnder16_returnTrue() {
        List<Card> CardsScore16 = List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.SIX, Shape.HEART));
        Dealer dealer = new Dealer(CardsScore16);

        assertThat(dealer.isDrawable()).isTrue();
    }

    @DisplayName("카드의 총 점수가 17을 넘으면, 카드를 더 뽑을 수 없다")
    @Test
    void isDrawableTest_whenScoreIsOver17_returnFalse() {
        List<Card> CardsScore17 = List.of(new Card(Value.JACK, Shape.HEART), new Card(Value.SEVEN, Shape.HEART));
        Dealer dealer = new Dealer(CardsScore17);

        assertThat(dealer.isDrawable()).isFalse();
    }

    @DisplayName("점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideCardsAndScore")
    void calculateScoreTest(List<Card> cards, int expected) {
        Dealer dealer = new Dealer(cards);

        assertThat(dealer.calculateScore()).isEqualTo(expected);
    }

    // TODO Fixture로 추출하기
    static Stream<Arguments> provideCardsAndScore() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Value.ACE, Shape.HEART),
                                new Card(Value.KING, Shape.HEART)
                        ), 21),
                Arguments.of(
                        List.of(
                                new Card(Value.ACE, Shape.HEART),
                                new Card(Value.ACE, Shape.SPADE)
                        ), 12),
                Arguments.of(
                        List.of(
                                new Card(Value.ACE, Shape.HEART),
                                new Card(Value.KING, Shape.HEART),
                                new Card(Value.TWO, Shape.HEART)
                        ), 13),
                Arguments.of(
                        List.of(
                                new Card(Value.KING, Shape.HEART),
                                new Card(Value.TWO, Shape.HEART)
                        ), 12)
        );
    }

    @DisplayName("게임을 시작할 때는 카드를 두 장 뽑는다.")
    @Test
    void drawStartCardsTest() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createShuffledDeck();

        dealer.drawStartCards(deck);

        assertThat(dealer.getCards()).hasSize(2);
    }

    @DisplayName("이미 카드를 가지고 있는 경우, 시작 카드를 뽑을 수 없다.")
    @Test
    void drawStartCardsTest_whenAlreadyStarted_throwException() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.createShuffledDeck();
        dealer.drawStartCards(deck);

        assertThatThrownBy(() -> dealer.drawStartCards(deck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 시작 카드를 뽑았습니다.");
    }

    @DisplayName("카드의 총 점수가 16을 넘지 않으면, 카드를 한 장 뽑는다")
    @Test
    void addTest_whenScoreIsUnder16() {
        Dealer dealer = new Dealer(List.of(
                new Card(Value.KING, Shape.HEART),
                new Card(Value.SIX, Shape.HEART)
        ));

        dealer.add(new Card(Value.ACE, Shape.HEART));

        assertThat(dealer.getCards()).containsExactly(
                new Card(Value.KING, Shape.HEART),
                new Card(Value.SIX, Shape.HEART),
                new Card(Value.ACE, Shape.HEART)
        );
    }

    @DisplayName("카드의 총 점수가 16을 넘으면, 카드를 뽑을 때 예외가 발생한다.")
    @Test
    void addTest_whenScoreIsOver16_throwException() {
        Dealer dealer = new Dealer(List.of(
                new Card(Value.KING, Shape.HEART),
                new Card(Value.SEVEN, Shape.HEART)
        ));
        Card card = new Card(Value.ACE, Shape.HEART);

        assertThatThrownBy(() -> dealer.add(card))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 카드를 추가할 수 없습니다.");
    }

    @DisplayName("플레이어와의 승패를 판단할 수 있다.")
    @Nested
    class IsWinTest {

        @DisplayName("플레이어가 21일 넘을 경우, 딜러가 이긴다.")
        @ParameterizedTest
        @MethodSource("provideDealerCards")
        void whenPlayerBusted_dealerWin(List<Card> cards) {
            Dealer dealer = new Dealer(cards);
            Player player = new Player(List.of(
                    new Card(Value.KING, Shape.DIAMOND),
                    new Card(Value.QUEEN, Shape.DIAMOND),
                    new Card(Value.JACK, Shape.DIAMOND)
            ), new Name("name"));

            assertThat(dealer.isWin(player)).isTrue();
        }

        static Stream<List<Card>> provideDealerCards() {
            return Stream.of(
                    List.of(
                            new Card(Value.ACE, Shape.HEART),
                            new Card(Value.KING, Shape.HEART)
                    ),
                    List.of(
                            new Card(Value.TWO, Shape.HEART),
                            new Card(Value.TWO, Shape.SPADE)
                    ),
                    List.of(
                            new Card(Value.KING, Shape.HEART),
                            new Card(Value.TWO, Shape.HEART)
                    ),
                    List.of(
                            new Card(Value.KING, Shape.HEART),
                            new Card(Value.QUEEN, Shape.HEART),
                            new Card(Value.JACK, Shape.HEART)
                    )
            );
        }

        @DisplayName("딜러만 21을 넘길 경우, 플레이어가 이긴다.")
        @ParameterizedTest
        @MethodSource("providePlayerCards")
        void whenOnlyDealerBusted_playerWin(List<Card> cards) {

            Dealer dealer = new Dealer(List.of(
                    new Card(Value.KING, Shape.DIAMOND),
                    new Card(Value.QUEEN, Shape.DIAMOND),
                    new Card(Value.JACK, Shape.DIAMOND)
            ));
            Player player = new Player(cards, new Name("name"));

            assertThat(dealer.isWin(player)).isFalse();
        }

        //TODO 픽스쳐 생성
        static Stream<List<Card>> providePlayerCards() {
            return Stream.of(
                    List.of(
                            new Card(Value.ACE, Shape.HEART),
                            new Card(Value.KING, Shape.HEART)
                    ),
                    List.of(
                            new Card(Value.TWO, Shape.HEART),
                            new Card(Value.TWO, Shape.SPADE)
                    ),
                    List.of(
                            new Card(Value.KING, Shape.HEART),
                            new Card(Value.TWO, Shape.HEART)
                    )
            );
        }

        @DisplayName("둘 다 21을 넘기지 않을 경우, 플레이어가 딜러의 숫자보다 크다면 플레이어가 이긴다.")
        @Test
        void whenPlayerScoreIsBiggerThanDealerScore_playerWin() {
            Player player = new Player(List.of(
                    new Card(Value.KING, Shape.HEART),
                    new Card(Value.SEVEN, Shape.HEART)
            ), new Name("name"));
            Dealer dealer = new Dealer(List.of(
                    new Card(Value.KING, Shape.HEART),
                    new Card(Value.SIX, Shape.HEART)
            ));

            assertThat(dealer.isWin(player)).isFalse();
        }

        @DisplayName("둘 다 21을 넘기지 않을 경우, 플레이어가 딜러의 숫자보다 같다면 딜러가 이긴다.")
        @Test
        void whenPlayerScoreIsEqualToDealerScore_dealerWin() {
            Player player = new Player(List.of(
                    new Card(Value.KING, Shape.HEART),
                    new Card(Value.SEVEN, Shape.HEART)
            ), new Name("name"));
            Dealer dealer = new Dealer(List.of(
                    new Card(Value.KING, Shape.HEART),
                    new Card(Value.SEVEN, Shape.HEART)
            ));

            assertThat(dealer.isWin(player)).isTrue();
        }

        @DisplayName("둘 다 21을 넘기지 않을 경우, 플레이어가 딜러의 숫자보다 작다면 딜러가 이긴다.")
        @Test
        void whenPlayerScoreIsSmallerThanDealerScore_dealerWin() {
            Player player = new Player(List.of(
                    new Card(Value.KING, Shape.HEART),
                    new Card(Value.SIX, Shape.HEART)
            ), new Name("name"));
            Dealer dealer = new Dealer(List.of(
                    new Card(Value.KING, Shape.HEART),
                    new Card(Value.SEVEN, Shape.HEART)
            ));

            assertThat(dealer.isWin(player)).isTrue();
        }
    }
}
