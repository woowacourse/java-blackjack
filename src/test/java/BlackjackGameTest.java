import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @DisplayName("게임 시작 시, 모든 플레이어에게 두 장의 카드를 나눠준다.")
    @Test
    void giveInitCardsSuccessTest() {
        Player pobi = new Player(new PlayerName("pobi"));
        Player crong = new Player(new PlayerName("crong"));
        Players players = new Players(List.of(pobi, crong));

        BlackjackGame blackjackGame = new BlackjackGame(players);

        blackjackGame.giveInitCards();

        Assertions.assertThat(pobi.cardSize()).isEqualTo(2);
        Assertions.assertThat(crong.cardSize()).isEqualTo(2);
    }

}
