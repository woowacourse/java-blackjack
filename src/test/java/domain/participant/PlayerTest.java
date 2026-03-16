package domain.participant;

import domain.match.MatchResult;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.money.Bet;
import domain.money.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayerTest {

    @Test
    @DisplayName("hit 처리 시, 1장을 뽑는다.")
    void Hand에_1장_추가() {
        // given
        Player player = new Player(new Name("pobi"), new Bet(10000));

        // when
        player.receive(new Card(Rank.ACE, Suit.DIAMOND));

        // then
        Assertions.assertEquals(player.getCards().size(), 1);
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
        Player player = new Player(new Name("pobi"), new Bet(3000));

        // when
        Money profit = player.applyMatchResultToBet(matchResult);

        // then
        Assertions.assertEquals(Money.of(expectedProfit), profit);
    }

    @Test
    @DisplayName("블랙잭은 배팅금액의 1.5배를 환급받는다.")
    void applyMatchResultToBet_BlackJackTest() {
        // given
        Player player = new Player(new Name("pobi"), new Bet(3000));

        player.receive(new Card(Rank.ACE, Suit.CLOVER));
        player.receive(new Card(Rank.JACK, Suit.SPADE));
        // when
        Money profit = player.applyMatchResultToBet(MatchResult.WIN);

        // then
        Assertions.assertEquals(Money.of(4500), profit);
    }
}
