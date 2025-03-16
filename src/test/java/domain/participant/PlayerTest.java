package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Hand;
import java.util.ArrayList;
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
        Player player = Player.init("플레이어", Money.of(100000));
        Card card = new Card(CardNumber.A, CardShape.CLOVER);
        player.addCard(card);

        // when
        Hand hand = player.getHand();

        // then
        assertThat(hand.getCards()).contains(card);
    }

    @DisplayName("21 이하일 때, 최적의 결과를 선택할 수 있다.")
    @ParameterizedTest
    @MethodSource("createCardsCase")
    void 최적_결과_선택_21_이하(List<Card> inputCards, int expected) {
        // given
        Hand hand = Hand.of(inputCards);
        Player player = Player.of(hand, "플레이어1", Money.of(100000));

        // when
        final int result = player.calculateScore();

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
        Hand hand = Hand.of(inputCards);
        Player player = Player.of(hand, "플레이어1", Money.of(100000));

        //when
        int actual = player.calculateScore();

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

    @DisplayName("플레이어가 소유한 카드에 따라서 burst 여부를 판단한다.")
    @ParameterizedTest
    @MethodSource("createBurstCase")
    void test1(List<Card> inputCard, boolean expected) {
        //given
        Hand hand = Hand.of(inputCard);
        Player dealer = Player.of(hand, "플레이어1", Money.of(100000));
        //when
        final boolean actual = dealer.isBurst();
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

    @DisplayName("플레이어가 가진 카드의 블랙잭 여부를 판단한다.")
    @ParameterizedTest
    @MethodSource("createBlackJackCase")
    void test2(List<Card> inputCard, boolean expected) {
        //given
        Hand hand = Hand.of(inputCard);
        Player dealer = Player.of(hand, "플레이어1", Money.of(100000));
        //when
        final boolean actual = dealer.isBlackJack();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream createBlackJackCase() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(CardNumber.TEN, CardShape.CLOVER),
                                new Card(CardNumber.A, CardShape.CLOVER)
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

    @Test
    void 플레이어가_이겼을_경우_수익을_계산한다() {
        //given
        Hand hand = Hand.of(new ArrayList<>());
        Player player = Player.of(hand, "플레이어1", Money.of(100000));
        //when
        Money actual = player.calculateRevenue(GameResult.WIN);
        //then
        assertThat(actual).isEqualTo(Money.of(100000));
    }

    @Test
    void 플레이어가_블랙잭으로_이겼을_경우_수익을_계산한다() {
        //given
        Hand hand = Hand.of(new ArrayList<>());
        Player player = Player.of(hand, "플레이어1", Money.of(100000));
        //when
        Money actual = player.calculateRevenue(GameResult.BLACKJACK);
        //then
        assertThat(actual).isEqualTo(Money.of(150000));
    }

    @Test
    void 플레이어가_비겼을_경우_수익을_계산한다() {
        //given
        Hand hand = Hand.of(new ArrayList<>());
        Player player = Player.of(hand, "플레이어1", Money.of(100000));
        //when
        Money actual = player.calculateRevenue(GameResult.DRAW);
        //then
        assertThat(actual).isEqualTo(Money.of(0));
    }

    @Test
    void 플레이어가_졌을_경우_수익을_계산한다() {
        //given
        Hand hand = Hand.of(new ArrayList<>());
        Player player = Player.of(hand, "플레이어1", Money.of(100000));
        //when
        Money actual = player.calculateRevenue(GameResult.LOSE);
        //then
        assertThat(actual).isEqualTo(Money.of(-100000));
    }
}
