package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.*;
import domain.card.cardsGenerator.RandomCardsGenerator;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.rule.BlackjackRule;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackRuleTest {
    private final BlackjackRule rule = new BlackjackRule();
    private final Deck deck = new Deck(new RandomCardsGenerator());

    @DisplayName("21 이하일 때, 최적의 결과를 선택할 수 있다.")
    @ParameterizedTest
    @MethodSource("createCardsCase")
    void 최적_결과_선택_21_이하(List<Card> inputCards, int expected) {
        // given
        Cards cards = Cards.of(inputCards);

        // when
        final int score = rule.getScore(cards);

        // then
        assertThat(score).isEqualTo(expected);
    }

    private static Stream createCardsCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER)
                        ),
                        21
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER),
                                new Card(CardNumber.FIVE, CardShape.CLOVER)
                        ),
                        16
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.A, CardShape.DIAMOND)
                        ),
                        12
                )
        );
    }

    @DisplayName("21 초과할 때, 21에 가장 가까운 값을 선택할 수 있다")
    @ParameterizedTest
    @MethodSource("createBurstCardsCase")
    void 가장_가까운_값_선택(List<Card> inputCards, int expected) {
        // given
        Cards cards = Cards.of(inputCards);

        // when
        final int score = rule.getScore(cards);

        //then
        assertThat(score).isEqualTo(expected);
    }

    private static Stream createBurstCardsCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER)
                        ),
                        22
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER),
                                new Card(CardNumber.FIVE, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER)
                        ),
                        26
                )
        );
    }

    @DisplayName("플레이어가 소유한 카드에 따라서 bust 여부를 판단한다.")
    @ParameterizedTest
    @MethodSource("createBurstCase")
    void test1(List<Card> inputCard, boolean expected) {
        // given
        Cards cards = Cards.of(inputCard);

        // when
        final boolean actual = rule.isBust(cards);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream createBurstCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        true
                ),
                Arguments.of(
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        false
                )
        );
    }

    @DisplayName("게임 결과를 올바르게 반환할 수 있다.")
    @ParameterizedTest
    @MethodSource("createGameResultCase")
    void 게임_결과_반환(List<Card> self, List<Card> other, GameResult expected) {
        // given
        Cards playerCards = Cards.of(self);
        Cards dealerCards = Cards.of(other);
        Player player = Player.from("player", playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = rule.getResult(player, dealer);

        // then
        assertThat(actual).isSameAs(expected);
    }

    private static Stream createGameResultCase() {
        return Stream.of(
                Arguments.of(
                        // 21
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER)
                        ),
                        // 12
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        "WIN"
                ),
                Arguments.of(
                        // 12
                        List.of(
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        // 13
                        List.of(
                                new Card(CardNumber.A, CardShape.CLOVER),
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        "LOSE"
                ),
                Arguments.of(
                        // 22
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        // 22
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                                new Card(CardNumber.TWO, CardShape.CLOVER)
                        ),
                        "LOSE"
                ),
                Arguments.of(
                        // 20
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.QUEEN, CardShape.CLOVER)
                        ),
                        // 20
                        List.of(
                                new Card(CardNumber.JACK, CardShape.CLOVER),
                                new Card(CardNumber.KING, CardShape.CLOVER)
                        ),
                        "DRAW"
                )
        );
    }

    @DisplayName("두 명 모두 버스트가 아닌 경우, 합이 더 큰 사람이 승리한다")
    @Test
    void 승패_검증() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.THREE, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from("player", playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = rule.getResult(player, dealer);

        //then
        assertThat(actual).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어의 카드가 버스트인 경우, 패배한다")
    @Test
    void 플레이어의_카드가_버스트인_경우_패배한다() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from("player", playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = rule.getResult(player, dealer);

        //then
        assertThat(actual).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러가 버스트인 경우, 승리한다")
    @Test
    void 딜러가_버스트인_경우_승리한다() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.A, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from("player", playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = rule.getResult(player, dealer);

        //then
        assertThat(actual).isEqualTo(GameResult.WIN);
    }

    @DisplayName("모두 버스트가 아니면서 합이 같으면 비긴다")
    @Test
    void 두명_모두_버스트가_아니면서_합이_같으면_비긴다() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from("player", playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = rule.getResult(player, dealer);

        //then
        assertThat(actual).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("둘 다 버스트인 경우, 패배한다")
    @Test
    void 둘_다_버스트인_경우_패배한다() {
        // given
        Cards playerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Cards dealerCards = Cards.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER)
        ));
        Player player = Player.from("player", playerCards);
        Dealer dealer = new Dealer(dealerCards, deck);

        // when
        GameResult actual = rule.getResult(player, dealer);

        //then
        assertThat(actual).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("카드가 2장이면서 합이 21인 경우 블랙잭으로 판단할 수 있다.")
    @ParameterizedTest
    @MethodSource("createBlackjackCards")
    void 블랙잭_판단(List<Card> inputCards) {
        // given
        Cards cards = Cards.of(inputCards);

        // when
        final boolean isBlackjack = rule.isBlackjack(cards);

        // then
        assertThat(isBlackjack).isTrue();
    }

    private static Stream createBlackjackCards() {
        return Stream.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.TEN, CardShape.HEART)
                ),
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.KING, CardShape.HEART)
                ),
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.QUEEN, CardShape.HEART)
                ),
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.JACK, CardShape.HEART)
                )
        );
    }
}
