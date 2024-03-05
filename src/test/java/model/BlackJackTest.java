package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Player;
import model.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {

    @DisplayName("참가자에게 카드를 준다.")
    @Test
    void offerCardToPlayer() {
        Players players = new Players(List.of(new Participant("배키"), new Dealer()), new Cards());
        BlackJack blackJack = new BlackJack(players);

        blackJack.offerCardToPlayers(2);
        List<Player> result = players.getPlayers();

        assertAll(
                () -> assertThat(result.get(0).getCards()).hasSize(2),
                () -> assertThat(result.get(1).getCards()).hasSize(2)
        );
    }
}
