package blackjack;

import blackjack.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
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
                        new Card(Symbol.CLOVER, Type.QUEEN),
                        new Card(Symbol.CLOVER, Type.ACE),
                        new Card(Symbol.DIAMOND, Type.SIX),
                        new Card(Symbol.HEART, Type.SEVEN),
                        new Card(Symbol.SPADE, Type.FIVE),
                        new Card(Symbol.SPADE, Type.QUEEN),
                        new Card(Symbol.HEART, Type.TEN)
                )
        ));
        dealer = Dealer.getDealer();
        players = new Players(new ArrayList<>(
                Arrays.asList(
                        new Player("bossdog", 10000),
                        new Player("yes", 10000),
                        new Player("pobi", 10000)
                )
        ));
        players.getPlayers()
                .forEach(player -> player.receiveDistributedCards(cardDeck));
        players.getPlayers()
                .get(2).receiveOneMoreCard(cardDeck);
    }

    @DisplayName("딜러 블랙잭인 경우 결과 확인")
    @Test
    void calculateResultWhenDealerBlackJack() {
        CardDeck dealerDeck = new CardDeck(new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.CLOVER, Type.TEN),
                        new Card(Symbol.HEART, Type.ACE)
                )
        ));
        dealer.receiveDistributedCards(dealerDeck);
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
                        new Card(Symbol.CLOVER, Type.FIVE),
                        new Card(Symbol.HEART, Type.NINE),
                        new Card(Symbol.SPADE, Type.JACK)
                )
        ));
        dealer.receiveDistributedCards(dealerDeck);
        dealer.receiveOneMoreCard(dealerDeck);
        Map<User, Integer> result = GameResult.calculateGameResult(dealer, players).getGameResult();

        assertThat(result.get(players.getPlayers().get(0))).isEqualTo(15000);
        assertThat(result.get(players.getPlayers().get(1))).isEqualTo(10000);
        assertThat(result.get(players.getPlayers().get(2))).isEqualTo(-10000);
    }

    @DisplayName("딜러 NONE인 경우 점수 비교 결과 확인")
    @Test
    void calculateResultWhenDealerStatusHitable() {
        CardDeck dealerDeck = new CardDeck(new ArrayList<>(
                Arrays.asList(
                        new Card(Symbol.CLOVER, Type.SEVEN),
                        new Card(Symbol.HEART, Type.ACE)
                )
        ));
        dealer.receiveDistributedCards(dealerDeck);
        Map<User, Integer> result = GameResult.calculateGameResult(dealer, players).getGameResult();

        assertThat(result.get(players.getPlayers().get(0))).isEqualTo(15000);
        assertThat(result.get(players.getPlayers().get(1))).isEqualTo(-10000);
        assertThat(result.get(players.getPlayers().get(2))).isEqualTo(-10000);
    }

    @AfterEach
    void tearDown() throws NoSuchFieldException, IllegalAccessException {
        Field dealer_instance = Dealer.class.getDeclaredField("dealerInstance");
        dealer_instance.setAccessible(true);
        dealer_instance.set(null, null);
    }
}
