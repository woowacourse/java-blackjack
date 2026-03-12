package domain.participant;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PlayerTest {

    @Test
    @DisplayName("플레이어 이름은 1글자 이상 8글자 이하여야 한다.")
    void 이름은_1글자_이상_8글자_이하_성공() {
        // given
        String name = "pobi";

        // when - then
        Assertions.assertDoesNotThrow(() -> new Player(name));
    }

    @Test
    @DisplayName("플레이어 이름은 공백을 허용하지 않는다.")
    void 이름_공백_실패() {
        // given
        String name = "";

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player(name));
    }

    @Test
    @DisplayName("플레이어 이름은 8글자 초과를 허용하지 않는다.")
    void 이름_8글자_초과_실패() {
        // given
        String name = "pobipobip";

        // when - then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player(name));
    }

    @Test
    @DisplayName("hit 처리 시, 1장을 뽑는다.")
    void Hand에_1장_추가() {
        // given
        Player player = new Player("pobi");

        // when
        player.receive(new Card(Rank.ACE, Suit.DIAMOND));

        // then
        Assertions.assertEquals(player.getCards().size(), 1);
    }

    @Test
    @DisplayName("플레이어의 승패 결과에 따라 돌려받는 수익을 계산한다.")
    void applyMatchResultToBetTest() {
        // given
        Player player = new Player("pobi");

        // when
        player.placeBet(3000);

        // then
        Assertions.assertEquals(player.applyMatchResultToBet(MatchResult.WIN), 3000);
    }

    @ParameterizedTest
    @DisplayName("플레이어의 승패 결과에 따라 돌려받는 수익을 계산한다.")
    @CsvSource({
            "WIN, 3000",
            "DRAW, 0",
            "LOSE, -3000"
    })
    void applyMatchResultToBetTest(MatchResult matchResult, int expectedProfit) {
        // given
        Player player = new Player("pobi");
        player.placeBet(3000);

        // when
        int profit = player.applyMatchResultToBet(matchResult);

        // then
        Assertions.assertEquals(expectedProfit, profit);
    }

    @Test
    @DisplayName("블랙잭은 배팅금액의 1.5배를 환급받는다.")
    void applyMatchResultToBet_BlackJackTest() {
        // given
        Player player = new Player("pobi");
        player.placeBet(3000);

        player.receive(new Card(Rank.ACE, Suit.CLOVER));
        player.receive(new Card(Rank.JACK, Suit.SPADE));
        // when
        int profit = player.applyMatchResultToBet(MatchResult.WIN);

        // then
        Assertions.assertEquals(4500, profit);
    }
}
