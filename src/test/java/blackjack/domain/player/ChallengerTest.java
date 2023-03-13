package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.dto.ChallengerNameAndMoneyDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChallengerTest {

    @ParameterizedTest
    @MethodSource("provideCards")
    @DisplayName("가진 카드의 합이 21 초과인지 확인한다")
    void checking_sum_is_over_21(List<Card> cards, boolean expected) {
        ChallengerNameAndMoneyDto dto = new ChallengerNameAndMoneyDto(new ChallengerName("neo"), Money.from(1000));
        Player player = new Challenger(dto);
        for (Card card : cards) {
            player.pick(card);
        }

        assertThat(player.canPick()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCards() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Number.QUEEN),
                                new Card(Shape.CLOVER, Number.FIVE)),
                        true),
                Arguments.of(
                        List.of(
                                new Card(Shape.DIAMOND, Number.QUEEN),
                                new Card(Shape.CLOVER, Number.FIVE),
                                new Card(Shape.HEART, Number.EIGHT)),
                        false)
        );
    }

    @Test
    @DisplayName("도전자는 딜러가 아니다")
    void challenger_is_not_dealer() {
        ChallengerNameAndMoneyDto dto = new ChallengerNameAndMoneyDto(new ChallengerName("ditoo"), Money.from(1000));
        Player challenger = new Challenger(dto);

        assertThat(challenger.isChallenger()).isTrue();
        assertThat(challenger.isDealer()).isFalse();
    }
 }
