package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.card.Denomination.*;
import static blackjack.domain.card.Symbol.*;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    @DisplayName("현재 턴이면서 hit이 가능한 player의 이름을 반환한다")
    void testGetCurrentHitablePlayerName() {
        // given
        String name1 = "pobi";
        List<Card> initialCards1 = List.of(new Card(CLOVER, JACK), new Card(DIAMOND, EIGHT));
        Player player1 = new Player(name1, Cards.of(initialCards1));
        player1.stay();

        String name2 = "jason";
        List<Card> initialCards2 = List.of(new Card(SPADE, JACK), new Card(HEART, EIGHT));
        Player player2 = new Player(name2, Cards.of(initialCards2));

        Players players = new Players(List.of(player1, player2));
        Game game = new Game(players, deck);

        assertThat(game.getCurrentHitablePlayerName()).isEqualTo(player2.getName());
    }
}
