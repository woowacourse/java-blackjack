package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomStrategy;

class GameTableTest {

    static Hand winningHand = new Hand(
            new RandomStrategy(),
            List.of(new Card(CardRank.ACE, CardMark.SPADE),
                    new Card(CardRank.QUEEN, CardMark.HEART)));

    static Hand loseHand = new Hand(
            new RandomStrategy(),
            List.of(new Card(CardRank.QUEEN, CardMark.SPADE),
                    new Card(CardRank.TWO, CardMark.HEART)));

//    @Test
//    @DisplayName("플레이어의 승패를 확인할 수 있어야 한다.(플레이어 패)")
//    void 플레이어_패_확인() {
//        Participant dealer = new Dealer("딜러", winningHand);
//        Participant player = new Player("pobi", loseHand);
//
//        GameTable gameTable = new GameTable(dealer, names);
//        WinningLog log = gameTable.result();
//
//        GameResult expected = GameResult.LOSE;
//        GameResult actual = log.result();
//
//        assertEquals(expected, actual);
//    }
}
