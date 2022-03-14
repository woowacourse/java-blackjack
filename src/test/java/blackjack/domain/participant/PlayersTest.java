package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Kind;
import blackjack.domain.card.Number;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    static final Players players = Players.of(List.of("쿼리치", "토르", "우테코"));
    static final Dealer dealer = new Dealer();

    @BeforeAll
    static void beforeAll() {
        players.getPlayers().get(0).receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.HEART),
                Card.from(Number.KING, Kind.HEART))));
        players.getPlayers().get(1).receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.CLOVER))));
        players.getPlayers().get(2).receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.DIAMOND),
                Card.from(Number.EIGHT, Kind.DIAMOND))));

        dealer.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.NINE, Kind.SPADE))));
    }

    @DisplayName("플레이어들 객체 생성자 테스트")
    @Test
    void constructor_CreatePlayers_HasInstance() {

        assertThat(players).isNotNull();
    }

    @DisplayName("전체 결과값 포함 여부 확인 테스트")
    @Test
    void constructor_ContainsAllResults_Returns3() {

        assertThat(players.judgeResult(dealer).size()).isEqualTo(3);
    }

    @DisplayName("플레이어 승리 테스트")
    @Test
    void judgeResult_WinningPlayer_ReturnsWin() {

        assertThat(players.judgeResult(dealer).get(players.getPlayers().get(0))).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 무승부 테스트")
    @Test
    void judgeResult_DrawPlayer_ReturnsDraw() {

        assertThat(players.judgeResult(dealer).get(players.getPlayers().get(1))).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어 패배 테스트")
    @Test
    void judgeResult_LosingPlayer_ReturnsLose() {

        assertThat(players.judgeResult(dealer).get(players.getPlayers().get(2))).isEqualTo(Result.LOSE);
    }
}
