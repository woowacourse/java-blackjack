package domain;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        PlayingCards cardsOfPlayer = new PlayingCards(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.KING, Type.SPADE)));
        PlayingCards cardsOfDealer = new PlayingCards(Arrays.asList(new Card(Symbol.TWO, Type.SPADE), new Card(Symbol.THREE, Type.SPADE)));

        gameResult = GameResult.calculate(Collections.singletonList(new Player(cardsOfPlayer, "playerName")), new Dealer(cardsOfDealer));
    }

    @Test
    void calculate() {
        PlayingCards cardsOfPlayer = new PlayingCards(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.KING, Type.SPADE)));
        PlayingCards cardsOfDealer = new PlayingCards(Arrays.asList(new Card(Symbol.TWO, Type.SPADE), new Card(Symbol.THREE, Type.SPADE)));

        GameResult gameResult = GameResult.calculate(Collections.singletonList(new Player(cardsOfPlayer, "playerName")), new Dealer(cardsOfDealer));

        assertThat(gameResult).isNotNull();
    }

    @Test
    void sizeOfDealerWins() {
        assertThat(gameResult.sizeOfDealerWins()).isEqualTo(0);
    }

    @Test
    void sizeOfDraws() {
        assertThat(gameResult.sizeOfDraws()).isEqualTo(0);
    }

    @Test
    void sizeOfDealerLosses() {
        assertThat(gameResult.sizeOfDealerLosses()).isEqualTo(1);
    }

    @Test
    void getPlayersOf() {
        PlayingCards cardsOfPlayer = new PlayingCards(Arrays.asList(new Card(Symbol.QUEEN, Type.SPADE), new Card(Symbol.KING, Type.SPADE)));
        assertThat(gameResult.getPlayersOf(PlayerResult.WIN)).isEqualTo(Collections.singletonList(new Player(cardsOfPlayer, "playerName")));
    }
}