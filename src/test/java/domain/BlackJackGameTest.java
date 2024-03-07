package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.strategy.SettedDecksGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @DisplayName("딜러와 플레이어에게 카드를 2장씩 준다.")
    @Test
    void giveCardTest() {
        // given
        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card3 = new Card(Symbol.HEART, Rank.NINE);
        Card card4 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card5 = new Card(Symbol.HEART, Rank.NINE);
        Card card6 = new Card(Symbol.SPADE, Rank.QUEEN);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2, card3, card4, card5, card6);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        Name name1 = new Name("lini");
        Name name2 = new Name("kaki");
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        Dealer dealer = new Dealer();
        Players players = new Players(List.of(player1, player2));

        Gamers gamers = Gamers.of(players, dealer);
        BlackJackGame blackJackGame = new BlackJackGame(gamers, decks);

        // when
        blackJackGame.prepareCards();

        // then
        assertAll(
                () -> assertThat(blackJackGame.getGamerHandAt(0)).hasSize(2),
                () -> assertThat(blackJackGame.getGamerHandAt(1)).hasSize(2),
                () -> assertThat(blackJackGame.getGamerHandAt(2)).hasSize(2)
        );
    }
}
