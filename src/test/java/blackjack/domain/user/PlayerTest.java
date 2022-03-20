package blackjack.domain.user;

import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static blackjack.fixture.CardBundleFixture.*;
import static blackjack.fixture.CardBundleFixture._15_INIT_CARDS;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @ParameterizedTest
    @MethodSource("calculateProfit")
    @DisplayName("플레이어가 블랙잭으로 우승하면 1.5배, 그냥 우승은 1배, 패배는 -1배, 무승부는 0의 수익을 얻는다.")
    void calculateProfitTest(Cards cards) {
        Player player = new Player("기론", Money.from(1000), cards);
        player.calculateProfit(new Dealer(_15_INIT_CARDS));
    }
    private static Stream<Arguments> calculateProfit() {
        return Stream.of(
                Arguments.of(JACK_ACE_BLACKJACK),
                Arguments.of(_16_CARDS),
                Arguments.of(_5_CARDS),
                Arguments.of(_15_INIT_CARDS)
        );

    }
}
