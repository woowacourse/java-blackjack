package blackjack;

import blackjack.domain.*;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    private Dealer dealer;
    private Players players;
    private CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck(new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.HEART, Type.TEN),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.SPADE, Type.FIVE),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.DIAMOND, Type.SIX),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.CLOVER, Type.QUEEN))
        ));
        dealer = new Dealer();
        players = new Players(new ArrayList<>(
                Arrays.asList(
                        new Player("bossdog", 10000),
                        new Player("yes", 10000),
                        new Player("pobi", 10000)
                )
        ));
        players.getPlayers()
                .forEach(player -> player.receiveInitialCards(cardDeck));
        players.getPlayers()
                .get(2).receiveOneMoreCard(cardDeck);
    }

    @DisplayName("딜러 블랙잭인 경우 결과 확인")
    @Test
    void calculateResultWhenDealerBlackJack() {
        CardDeck dealerDeck = new CardDeck(new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.HEART, Type.ACE),
                        new Card(Symbol.CLOVER, Type.TEN)
                )
        ));
        dealer.receiveInitialCards(dealerDeck);
        Map<User, Integer> result = GameResult.calculateGameResult(dealer, players).getGameResult();

        assertThat(result.get(players.getPlayers().get(0))).isEqualTo(0);
        assertThat(result.get(players.getPlayers().get(1))).isEqualTo(-10000);
        assertThat(result.get(players.getPlayers().get(2))).isEqualTo(-10000);
    }

    @DisplayName("딜러 버스트인 경우 결과 확인")
    @Test
    void calculateResultWhenDealerBust() {
        CardDeck dealerDeck = new CardDeck(new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.SPADE, Type.JACK),
                        new Card(Symbol.HEART, Type.NINE),
                        new Card(Symbol.CLOVER, Type.FIVE)
                )
        ));
        dealer.receiveInitialCards(dealerDeck);
        dealer.receiveOneMoreCard(dealerDeck);
        Map<User, Integer> result = GameResult.calculateGameResult(dealer, players).getGameResult();

        assertThat(result.get(players.getPlayers().get(0))).isEqualTo(15000);
        assertThat(result.get(players.getPlayers().get(1))).isEqualTo(10000);
        assertThat(result.get(players.getPlayers().get(2))).isEqualTo(-10000);
    }

    @DisplayName("딜러 NONE인 경우 점수 비교 결과 확인")
    @Test
    void calculateResultWhenDealerStatusNone() {
        CardDeck dealerDeck = new CardDeck(new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.HEART, Type.ACE),
                        new Card(Symbol.CLOVER, Type.SEVEN)
                )
        ));
        dealer.receiveInitialCards(dealerDeck);
        Map<User, Integer> result = GameResult.calculateGameResult(dealer, players).getGameResult();

        assertThat(result.get(players.getPlayers().get(0))).isEqualTo(15000);
        assertThat(result.get(players.getPlayers().get(1))).isEqualTo(-10000);
        assertThat(result.get(players.getPlayers().get(2))).isEqualTo(-10000);
    }
}
