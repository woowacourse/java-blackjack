package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        Player dealer = new Dealer();
        List<Card> cards = Arrays.asList(
            new Card(Symbol.CLOVER, CardNumber.ACE),
            new Card(Symbol.HEART, CardNumber.JACK),
            new Card(Symbol.CLOVER, CardNumber.JACK),
            new Card(Symbol.HEART, CardNumber.ACE),
            new Card(Symbol.SPADE, CardNumber.ACE),
            new Card(Symbol.DIAMOND, CardNumber.ACE),
            new Card(Symbol.CLOVER, CardNumber.TWO),
            new Card(Symbol.HEART, CardNumber.TWO),
            new Card(Symbol.HEART, CardNumber.THREE)
        );
        game = Game.ofTest(new Cards(cards), dealer, new Gamers("nabom", "neozal"));
    }

    @Test
    void drawCardToGamer() {
        Player gamer = game.findGamerByName("nabom");
        game.drawCardToPlayer(gamer);
        Assertions.assertThat(game.isPlayerDrawable(gamer)).isTrue();
        game.drawCardToPlayer(gamer);
        Assertions.assertThat(game.isPlayerDrawable(gamer)).isFalse();
    }

    @Test
    void getGamerResult() {
        Player gamer = game.findGamerByName("nabom");
        gamer.addCardToDeck(new Card(Symbol.HEART, CardNumber.ACE));
        gamer.addCardToDeck(new Card(Symbol.HEART, CardNumber.JACK));

        Player dealer = game.getDealer();
        dealer.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.ACE));

        Map<String, GameResult.WinOrLose> gamerResult = game.getGamerResult();
        Assertions.assertThat(gamerResult.get(gamer.getName())).isEqualTo(GameResult.WinOrLose.WIN);
        Assertions.assertThat(gamerResult.get("neozal")).isEqualTo(GameResult.WinOrLose.LOSE);
    }

    @Test
    void getDealerResult() {
        getGamerResult();
        List<GameResult.WinOrLose> dealerResult = game.getDealerResult();
        Assertions.assertThat(dealerResult.size()).isEqualTo(2);
        Assertions.assertThat(dealerResult.contains(GameResult.WinOrLose.WIN)).isTrue();
        Assertions.assertThat(dealerResult.contains(GameResult.WinOrLose.LOSE)).isTrue();
    }


}
