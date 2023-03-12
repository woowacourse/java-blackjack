package domain.player;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Suit;
import domain.game.Outcome;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    private static final String PLAYER_NAME = "player";

    List<Name> names = Stream.of(PLAYER_NAME)
            .map(Name::new)
            .collect(Collectors.toList());
    List<Amount> amounts = Stream.of(10)
            .map(Amount::new)
            .collect(Collectors.toList());
    Name name = new Name(PLAYER_NAME);
    Players players;
    Player player;
    Dealer dealer;

    @BeforeEach
    void setup() {
        players = new Players(names, amounts);
        player = players.getPlayers().get(0);
        dealer = new Dealer();
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭일 경우 플레이어는 무승부다.")
    @Test
    void allBlackjackTest() {
        player.drawCard(new Card(Suit.CLOVER, Rank.ACE));
        player.drawCard(new Card(Suit.DIAMOND, Rank.TEN));
        dealer.drawCard(new Card(Suit.SPADE, Rank.KING));
        dealer.drawCard(new Card(Suit.HEART, Rank.ACE));

        final Map<Name, Outcome> outcomes = players.judgePlayersOutcome(dealer);
        Assertions.assertThat(outcomes.get(name))
                .isEqualTo(Outcome.DRAW);
    }

    @DisplayName("플레이어만 블랙잭일 경우 플레이어는 블랙잭이다.")
    @Test
    void playerBlackjackTest() {
        player.drawCard(new Card(Suit.CLOVER, Rank.ACE));
        player.drawCard(new Card(Suit.DIAMOND, Rank.TEN));
        dealer.drawCard(new Card(Suit.SPADE, Rank.KING));
        dealer.drawCard(new Card(Suit.HEART, Rank.QUEEN));

        final Map<Name, Outcome> outcomes = players.judgePlayersOutcome(dealer);
        Assertions.assertThat(outcomes.get(name))
                .isEqualTo(Outcome.BLACKJACK);
    }

    @DisplayName("딜러만 블랙잭일 경우 플레이어는 패배이다.")
    @Test
    void dealerBlackjackTest() {
        player.drawCard(new Card(Suit.CLOVER, Rank.ACE));
        player.drawCard(new Card(Suit.DIAMOND, Rank.ACE));
        dealer.drawCard(new Card(Suit.SPADE, Rank.KING));
        dealer.drawCard(new Card(Suit.HEART, Rank.ACE));

        final Map<Name, Outcome> outcomes = players.judgePlayersOutcome(dealer);
        Assertions.assertThat(outcomes.get(name))
                .isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러와 플레이어 모두 버스트일 경우 플레이어의 패배이다.")
    @Test
    void allBurstTest() {
        player.drawCard(new Card(Suit.CLOVER, Rank.TEN));
        player.drawCard(new Card(Suit.DIAMOND, Rank.NINE));
        player.drawCard(new Card(Suit.DIAMOND, Rank.EIGHT));
        dealer.drawCard(new Card(Suit.SPADE, Rank.KING));
        dealer.drawCard(new Card(Suit.HEART, Rank.FIVE));
        dealer.drawCard(new Card(Suit.HEART, Rank.SEVEN));

        final Map<Name, Outcome> outcomes = players.judgePlayersOutcome(dealer);
        Assertions.assertThat(outcomes.get(name))
                .isEqualTo(Outcome.LOSE);
    }

    @DisplayName("플레이어만 버스트일 경우 플레이어의 패배이다.")
    @Test
    void playerBurstTest() {
        player.drawCard(new Card(Suit.CLOVER, Rank.TEN));
        player.drawCard(new Card(Suit.DIAMOND, Rank.NINE));
        player.drawCard(new Card(Suit.DIAMOND, Rank.EIGHT));
        dealer.drawCard(new Card(Suit.SPADE, Rank.KING));
        dealer.drawCard(new Card(Suit.HEART, Rank.SEVEN));

        final Map<Name, Outcome> outcomes = players.judgePlayersOutcome(dealer);
        Assertions.assertThat(outcomes.get(name))
                .isEqualTo(Outcome.LOSE);
    }

    @DisplayName("딜러만 버스트일 경우 플레이어의 승리이다.")
    @Test
    void dealerBurstTest() {
        player.drawCard(new Card(Suit.CLOVER, Rank.TEN));
        player.drawCard(new Card(Suit.DIAMOND, Rank.EIGHT));
        dealer.drawCard(new Card(Suit.SPADE, Rank.KING));
        dealer.drawCard(new Card(Suit.HEART, Rank.FIVE));
        dealer.drawCard(new Card(Suit.HEART, Rank.SEVEN));

        final Map<Name, Outcome> outcomes = players.judgePlayersOutcome(dealer);
        Assertions.assertThat(outcomes.get(name))
                .isEqualTo(Outcome.WIN);
    }

    @DisplayName("둘 다 버스트가 아니고 플레이어의 점수가 더 높을 경우 플레이어의 승리이다.")
    @Test
    void playerWinTest() {
        player.drawCard(new Card(Suit.CLOVER, Rank.TEN));
        player.drawCard(new Card(Suit.DIAMOND, Rank.EIGHT));
        dealer.drawCard(new Card(Suit.SPADE, Rank.KING));
        dealer.drawCard(new Card(Suit.HEART, Rank.SEVEN));

        final Map<Name, Outcome> outcomes = players.judgePlayersOutcome(dealer);
        Assertions.assertThat(outcomes.get(name))
                .isEqualTo(Outcome.WIN);
    }

    @DisplayName("둘 다 버스트가 아니고 딜러의 점수가 더 높을 경우 플레이어의 패배이다.")
    @Test
    void playerLoseTest() {
        player.drawCard(new Card(Suit.CLOVER, Rank.TEN));
        player.drawCard(new Card(Suit.DIAMOND, Rank.EIGHT));
        dealer.drawCard(new Card(Suit.SPADE, Rank.KING));
        dealer.drawCard(new Card(Suit.HEART, Rank.QUEEN));

        final Map<Name, Outcome> outcomes = players.judgePlayersOutcome(dealer);
        Assertions.assertThat(outcomes.get(name))
                .isEqualTo(Outcome.LOSE);
    }

    @DisplayName("둘 다 버스트가 아니고 딜러와 플레이어의 점수가 같을 경우 무승부이다.")
    @Test
    void playerDrawTest() {
        player.drawCard(new Card(Suit.CLOVER, Rank.TEN));
        player.drawCard(new Card(Suit.DIAMOND, Rank.EIGHT));
        dealer.drawCard(new Card(Suit.SPADE, Rank.KING));
        dealer.drawCard(new Card(Suit.HEART, Rank.EIGHT));

        final Map<Name, Outcome> outcomes = players.judgePlayersOutcome(dealer);
        Assertions.assertThat(outcomes.get(name))
                .isEqualTo(Outcome.DRAW);
    }
}
