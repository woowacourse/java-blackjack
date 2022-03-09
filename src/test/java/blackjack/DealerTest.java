package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {
    
    @ParameterizedTest
    @MethodSource("provideDealerWinningCaseCards")
    @DisplayName("딜러가 이기는 경우 판별 테스트")
    void dealerIsWinner(Cards dealerCards, Cards playerCards) {
        Dealer dealer = new Dealer(dealerCards);
        assertThat(dealer.judge(playerCards)).isEqualTo(Result.WIN);
    }

    private static Stream<Arguments> provideDealerWinningCaseCards() {
        return Stream.of(
            Arguments.of(new Cards(new Card("8다이아몬드"), new Card("J하트")), new Cards(new Card("7클로버"), new Card("8하트"))),
            Arguments.of(new Cards(new Card("8다이아몬드"), new Card("J하트")), new Cards(new Card("J하트"), new Card("Q하트"), new Card("4다이아몬드"))),
            Arguments.of(new Cards(new Card("A다이아몬드"), new Card("5하트")), new Cards(new Card("7클로버"), new Card("6하트"))),
            Arguments.of(new Cards(new Card("A다이아몬드"), new Card("J하트"), new Card("Q클로버")), new Cards(new Card("Q클로버"), new Card("J하트")))
        );
    }

    @ParameterizedTest
    @MethodSource("provideDealerLosingCaseCards")
    @DisplayName("딜러가 지는 경우 판별 테스트")
    void dealerIsLoser(Cards dealerCards, Cards playerCards) {
        Dealer dealer = new Dealer(dealerCards);
        assertThat(dealer.judge(playerCards)).isEqualTo(Result.LOSS);
    }

    private static Stream<Arguments> provideDealerLosingCaseCards() {
        return Stream.of(
            Arguments.of(new Cards(new Card("7클로버"), new Card("8하트")), new Cards(new Card("Q클로버"), new Card("J하트"))),
            Arguments.of(new Cards(new Card("J하트"), new Card("Q하트"), new Card("4다이아몬드")), new Cards(new Card("Q클로버"), new Card("J하트")))
        );
    }

    @Test
    @DisplayName("비기는 경우 판별 테스트")
    void dealerIsDraw() {
        Dealer dealer = new Dealer(new Cards(new Card("J다이아몬드"), new Card("4하트")));
        assertThat(dealer.judge(new Cards(new Card("8클로버"), new Card("6하트")))).isEqualTo(Result.DRAW);
    }
}
