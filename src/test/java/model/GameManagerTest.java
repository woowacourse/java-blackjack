package model;

import java.util.List;

import card.CardDeck;
import card.ShuffledDeckGenerator;
import game.BlackJack;
import participant.Dealer;
import participant.Player;
import participant.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GameManagerTest {

    private final Players players = Players.from(List.of(
            "pobi",
            "hippo"
    ));
    private final Dealer dealer = new Dealer();
    private final CardDeck deck = new CardDeck(new ShuffledDeckGenerator().generateDeck());
    private final BlackJack blackJack = new BlackJack(players, dealer, deck);

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("카드 덱으로부터 원하는 카드 장 수를 입력 받아 플레이어에게 분배")
    void divideCard(int amount) {
        //given
        //when
        //then
        for (Player player : players.getPlayers()) {
            blackJack.dealCard(player, amount);
            Assertions.assertThat(player.getCards().size()).isEqualTo(amount);
        }
    }

    @Test
    @DisplayName("모든 참가자에게 2장씩 카드를 배부했는 지")
    void divideInitialCardToParticipant() {
        // given
        int amount = 2;
        // when
        blackJack.dealInitialCards();
        // then
        for (Player player : players.getPlayers()) {
            Assertions.assertThat(player.getCards().size()).isEqualTo(amount);
        }
        Assertions.assertThat(dealer.getCards().size()).isEqualTo(amount);
    }
}
