package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.deck.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.Rank;
import blackjack.domain.deck.Shape;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private Player siso;
    private Player takan;
    private Players players;

    @BeforeEach
    void beforeEach() {
        siso = new Player(new Name("시소"));
        takan = new Player(new Name("타칸"));

        Deck sisoDeck = new Deck();
        sisoDeck.addCard(new Card(Shape.HEART, Rank.JACK));
        sisoDeck.addCard(new Card(Shape.HEART, Rank.SIX)); // 16

        Deck takanDeck = new Deck();
        takanDeck.addCard(new Card(Shape.SPADE, Rank.ACE));
        takanDeck.addCard(new Card(Shape.SPADE, Rank.JACK)); // 13

        siso.receiveDeck(sisoDeck);
        takan.receiveDeck(takanDeck);

        List<Player> playerList = List.of(siso, takan);
        players = new Players(playerList);

    }

    @Test
    @DisplayName("딜러보다 점수가 낮은 플레이어는 패배한다.")
    void calculateResultFailTest() {
        Map<Player, Boolean> result = players.calculateVictory(20);

        assertThat(result.get(siso)).isFalse();
    }

    @Test
    @DisplayName("딜러보다 점수가 높은 플레이어는 승리한다.")
    void calculateResultSuccessTest() {
        Map<Player, Boolean> result = players.calculateVictory(20);

        assertThat(result.get(takan)).isTrue();
    }

    @Test
    @DisplayName("한 플레이어는 하나의 카드를 받는다.")
    void receiveOnePlayerCardTest() {
        players.receiveOnePlayerCard(new Card(Shape.DIAMOND, Rank.TWO), 0);

        Assertions.assertThat(siso.getDeck().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("한 플레이어는 하나의 덱를 받는다.")
    void receiveOnePlayerDeckTest() {
        Deck deck = new Deck();

        deck.addCard(new Card(Shape.CLOVER, Rank.TWO));
        deck.addCard(new Card(Shape.CLOVER, Rank.FIVE));

        players.receiveOnePlayerDeck(deck, 0);

        Assertions.assertThat(siso.getDeck().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("한 플레이어는 기준 점수보다 낮은 점수다.")
    void isOnePlayerNotOverTest() {
        Assertions.assertThat(siso.isNotOver(17)).isTrue();
    }

    @Test
    @DisplayName("플레이어들의 사이즈를 잘 센다.")
    void sizeTest() {
        Assertions.assertThat(players.size()).isEqualTo(2);
    }
}
