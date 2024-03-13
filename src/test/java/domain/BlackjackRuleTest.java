package domain;

import dto.GameResult;
import dto.WinLose;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackjackRuleTest {
    @DisplayName("플레이어들과 딜러의 승패를 결정한다.")
    @Test
    void calculate() {
        final Player tebah = new Player(new Name("tebah"));
        tebah.dealCard(new Card(Denomination.KING, Suit.CLUBS));
        final Player pobi = new Player(new Name("pobi"));
        pobi.dealCard(new Card(Denomination.FIVE, Suit.CLUBS));
        final Players players = new Players(List.of(tebah, pobi));
        final Dealer dealer = new Dealer(new Deck());
        dealer.dealCard(new Card(Denomination.SIX, Suit.CLUBS));
        final BlackjackRule blackjackRule = new BlackjackRule();

        final GameResult gameResult = blackjackRule.calculate(players, dealer);

        assertAll(() -> assertEquals(WinLose.WIN, gameResult.playerResults().get(0).winLose()),
                () -> assertEquals(WinLose.LOSE, gameResult.playerResults().get(1).winLose()),
                () -> assertEquals(1, gameResult.dealerResult().winCount()),
                () -> assertEquals(1, gameResult.dealerResult().loseCount()));
    }
}