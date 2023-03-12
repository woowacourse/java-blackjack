package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CloverCard;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("딜러는 ")
class DealerTest {
    @ParameterizedTest(name = "{2} 상태 값을 확인할 수 있다.")
    @MethodSource("isDealerStatusTestCase")
    @DisplayName("자신의 상태값을 확인할 수 있다.")
    void isPlayerStatusTest(List<Card> firstTurnCards, Card drawCard, DealerStatus dealerStatus) {
        //given
        Dealer dealer = new Dealer(firstTurnCards);

        //when
        dealer.receiveCard(drawCard);

        //then
        assertThat(dealer.isUserStatus(dealerStatus)).isTrue();

    }

    static Stream<Arguments> isDealerStatusTestCase() {
        return Stream.of(
                Arguments.of(List.of(CloverCard.TEN, CloverCard.KING), CloverCard.QUEEN, Named.of("버스트", DealerStatus.BUST)),
                Arguments.of(List.of(CloverCard.TWO, CloverCard.EIGHT), CloverCard.SEVEN, Named.of("노멀", DealerStatus.NORMAL)),
                Arguments.of(List.of(CloverCard.TWO, CloverCard.THREE), CloverCard.FOUR, Named.of("17미만", DealerStatus.UNDER_SEVENTEEN))
        );
    }
}
