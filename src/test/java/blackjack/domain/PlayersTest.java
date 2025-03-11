package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("플레이어들은 플레이어 명단과 딜러가 준 카드 덱을 바탕으로 생성된다.")
    @Test
    void testPlayerInitCardDeck() {
        Dealer dealer = new Dealer(new CardDeck(), new CardDump());

        List<String> playerNames = List.of("user1", "user2");

        Players players = new Players();
        players.receiveEachCardDeckFromDealer(playerNames, dealer);

        assertThat(players.getPlayers().size()).isEqualTo(2);
    }
}
