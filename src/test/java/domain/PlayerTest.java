package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Cards;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {
    @DisplayName("카드를 추가할 수 있다.")
    @Test
    void 카드_추가_가능() {
        // given
        Player player = Player.init(new Nickname("플레이어"));
        Card card = new Card(CardNumber.A, CardShape.CLOVER);
        player.addCard(card);

        // when
        Cards cards = player.getCards();

        // then
        assertThat(cards.getValues()).contains(card);
    }

    @DisplayName("21 이하일 때, 최적의 결과를 선택할 수 있다.")
    @ParameterizedTest
    @MethodSource("createCardsCase")
    void 최적_결과_선택_21_이하(List<Card> inputCards, int expected) {
        // given
        Cards cards = Cards.of(inputCards);
        Player player = Player.from(new Nickname("플레이어1"), cards);

        // when
        final int result = player.getCardScore();

        // then
        assertThat(result).isEqualTo(expected);
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
        //given
        Cards cards = Cards.of(inputCards);
        Player player = Player.from(new Nickname("플레이어1"), cards);

        //when
        int actual = player.getCardScore();

        //then
        assertThat(actual).isEqualTo(expected);
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
        //given
        Cards cards = Cards.of(inputCard);
        Player dealer = Player.from(new Nickname("플레이어1"), cards);
        //when
        final boolean actual = dealer.isBust();
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

    @DisplayName("카드가 2장이면서 합이 21인 경우 블랙잭으로 판단할 수 있다.")
    @ParameterizedTest
    @MethodSource("createBlackjackCards")
    void 블랙잭_판단(List<Card> inputCards) {
        // given
        Cards cards = Cards.of(inputCards);
        Player player = Player.from(new Nickname("player"), cards);

        // when
        final boolean isBlackjack = player.isBlackjack();

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

    @DisplayName("블랙잭이 아닌 경우를 판단할 수 있다.")
    @ParameterizedTest
    @MethodSource("createNotBlackjackCards")
    void 블랙잭_아닌_경우_판단(List<Card> inputCards) {
        // given
        Cards cards = Cards.of(inputCards);
        Player player = Player.from(new Nickname("player"), cards);

        // when
        final boolean isBlackjack = player.isBlackjack();

        // then
        assertThat(isBlackjack).isFalse();
    }

    private static Stream createNotBlackjackCards() {
        return Stream.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.TEN, CardShape.HEART),
                        new Card(CardNumber.FIVE, CardShape.CLOVER)
                ),
                List.of(
                        new Card(CardNumber.QUEEN, CardShape.CLOVER),
                        new Card(CardNumber.SIX, CardShape.HEART),
                        new Card(CardNumber.FIVE, CardShape.DIAMOND)
                ),
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.A, CardShape.HEART),
                        new Card(CardNumber.TEN, CardShape.DIAMOND),
                        new Card(CardNumber.NINE, CardShape.SPADE)
                ),
                List.of(
                        new Card(CardNumber.TWO, CardShape.CLOVER),
                        new Card(CardNumber.THREE, CardShape.HEART),
                        new Card(CardNumber.TWO, CardShape.SPADE),
                        new Card(CardNumber.FIVE, CardShape.HEART),
                        new Card(CardNumber.EIGHT, CardShape.SPADE)
                )
        );
    }
}
