package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BlackJackGameTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("참가자에게 올바르게 카드를 준다.")
    void shouldSuccessDistributeCard(int num) {
        Deck deck = new Deck();
        Players players = new Players(List.of(new Player(new Name("dino")), new Player(new Name("sungha"))));
        BlackJackGame blackJackGame = new BlackJackGame(players, new Dealer());
        blackJackGame.distributeCard(deck, players.getPlayers().get(0), num);

        assertThat(players.getPlayers().get(0).getHandCards().getCards().size()).isEqualTo(num);
    }
}
