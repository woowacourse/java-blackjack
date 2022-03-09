package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    @Test
    @DisplayName("createDealer 메서드는 딜러 객체를 생성한 뒤 카드를 2장 나누어준다.")
    void createDealerTest() {
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = blackjackGame.createDealer(new FixDeck());
        assertThat(dealer.getCards().get(0)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
        assertThat(dealer.getCards().get(1)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
    }

    @Test
    @DisplayName("createPlayers 메서드는 입력받은 이름들로 Players 객체를 생성한 뒤 카드를 2장씩 나누어준다.")
    void createPlayersTest() {
        BlackjackGame blackjackGame = new BlackjackGame();
        List<String> names = List.of("aki", "alien");
        Players players = blackjackGame.createPlayers(names, new FixDeck());
        List<Player> allPlayer = players.get();

        assertThat(allPlayer).allSatisfy(player -> {
            assertThat(player.getCards().get(0)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
            assertThat(player.getCards().get(1)).isEqualTo(new Card(CardNumber.TEN, Type.SPADE));
        });
    }
}
