package model;

import java.util.List;

import model.card.CardDeck;
import model.card.ShuffledDeckGenerator;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GameManagerTest {

    private final CardDeck deck = new CardDeck(new ShuffledDeckGenerator().generateDeck());

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("카드 덱으로부터 원하는 카드 장 수를 입력 받아 플레이어에게 분배")
    void divideCard(int amount) {
        //given
        Players players = Players.from(List.of(
                "pobi",
                "hippo"
        ));
        Dealer dealer = new Dealer();
        //when
        //then
        for (Player player : players.getPlayers()) {
            dealer.divideCardByParticipant(player, deck ,amount);
            Assertions.assertThat(player.getCards().size()).isEqualTo(amount);
        }
    }

    @Test
    @DisplayName("모든 참가자에게 2장씩 카드를 배부했는 지")
    void divideInitialCardToParticipant() {
        // given
        List<String> allPlayer = List.of(
                "pobi",
                "hippo",
                "kali"
        );
        int amount = 2;
        Players players = Players.from(allPlayer);
        Dealer dealer = new Dealer();
        // when
        dealer.divideInitialCardToParticipant(players, deck);

        // then
        for (Player player : players.getPlayers()) {
            Assertions.assertThat(player.getCards().size()).isEqualTo(amount);
        }
        Assertions.assertThat(dealer.getCards().size()).isEqualTo(amount);
    }
}
