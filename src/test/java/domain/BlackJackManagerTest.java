package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackManagerTest {

    @Test
    @DisplayName("게임이 시작되면 딜러를 포함한 참여자 모두에게 2장의 카드를 나눠준다")
    void should_given_all_players_that_of_2_card() {
        // given
        List<Participant> players = List.of(new Player("a"), new Player("b"));
        CardBundle cardBundle = new CardBundle();
        CardDeck cardDeck = new CardDeck(cardBundle.getAllCards());
        BlackJackManager blackJackManager = new BlackJackManager(players, cardDeck);

        // when
        blackJackManager.start();

        // then
        for (Participant player : players) {
            assertThat(player.getCards()).hasSize(2);
        }
    }
}
