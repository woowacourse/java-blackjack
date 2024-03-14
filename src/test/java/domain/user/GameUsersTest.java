package domain.user;

import static domain.card.Number.ACE;
import static domain.card.Number.EIGHT;
import static domain.card.Number.FIVE;
import static domain.card.Number.JACK;
import static domain.card.Number.QUEEN;
import static domain.card.Number.SEVEN;
import static domain.card.Number.SIX;
import static domain.card.Number.TEN;
import static domain.card.Shape.CLUB;
import static domain.card.Shape.HEART;
import static domain.money.GameResult.DRAW;
import static domain.money.GameResult.LOSE;
import static domain.money.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.Deck;
import domain.card.Card;
import domain.money.GameResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameUsersTest {
    @Test
    @DisplayName("player(15), dealer(11) -> WIN")
    void should_win_when_player15Dealer11() {
        Player player = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player)), new Deck(List.of(
                new Card(CLUB, FIVE),
                new Card(CLUB, SIX),
                new Card(CLUB, SEVEN),
                new Card(CLUB, EIGHT)
        )));

        Map<Player, GameResult> playerResults = gameUsers.generatePlayersResult();

        assertThat(playerResults).containsExactly(Map.entry(player, WIN));
    }

    @Test
    @DisplayName("player(11), dealer(11) -> DRAW")
    void should_draw_when_player11Dealer11() {
        Player player = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player)), new Deck(List.of(
                new Card(CLUB, FIVE),
                new Card(CLUB, SIX),
                new Card(HEART, FIVE),
                new Card(HEART, SIX)
        )));

        Map<Player, GameResult> playerResults = gameUsers.generatePlayersResult();

        assertThat(playerResults).containsExactly(Map.entry(player, DRAW));
    }

    @Test
    @DisplayName("player(15), dealer(11) -> LOSE")
    void should_lose_when_player11Dealer15() {
        Player player = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player)), new Deck(List.of(
                new Card(CLUB, SEVEN),
                new Card(CLUB, EIGHT),
                new Card(CLUB, FIVE),
                new Card(CLUB, SIX)
        )));

        Map<Player, GameResult> playerResults = gameUsers.generatePlayersResult();

        assertThat(playerResults).containsExactly(Map.entry(player, LOSE));
    }

    @Test
    @DisplayName("player(Blackjack), dealer(21) -> WIN")
    void should_win_when_playerBlackjackDealer21() {
        Player player = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player)), new Deck(List.of(
                new Card(CLUB, FIVE),
                new Card(CLUB, SIX),
                new Card(CLUB, ACE),
                new Card(CLUB, TEN)
        )));
        gameUsers.getDealer().addCard(new Card(CLUB, JACK));

        Map<Player, GameResult> playerResults = gameUsers.generatePlayersResult();

        assertThat(playerResults).containsExactly(Map.entry(player, WIN));
    }

    @Test
    @DisplayName("player(Blackjack), dealer(Blackjack) -> DRAW")
    void should_draw_when_playerBlackjackDealerBlackjack() {
        Player player = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player)), new Deck(List.of(
                new Card(HEART, ACE),
                new Card(HEART, TEN),
                new Card(CLUB, ACE),
                new Card(CLUB, TEN)
        )));

        Map<Player, GameResult> playerResults = gameUsers.generatePlayersResult();

        assertThat(playerResults).containsExactly(Map.entry(player, DRAW));
    }

    @Test
    @DisplayName("player(BLACKJACK), dealer(21) -> LOSE")
    void should_lose_when_player21DealerBlackjack() {
        Player player = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player)), new Deck(List.of(
                new Card(CLUB, ACE),
                new Card(CLUB, TEN),
                new Card(CLUB, FIVE),
                new Card(CLUB, SIX)
        )));
        player.addCard(new Card(CLUB, JACK));

        Map<Player, GameResult> playerResults = gameUsers.generatePlayersResult();

        assertThat(playerResults).containsExactly(Map.entry(player, LOSE));
    }

    @Test
    @DisplayName("player(Busted), dealer(11) -> LOSE")
    void should_lose_when_playerBustedDealer11() {
        Player player = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player)), new Deck(List.of(
                new Card(CLUB, FIVE),
                new Card(CLUB, SIX),
                new Card(CLUB, QUEEN),
                new Card(CLUB, TEN)
        )));
        player.addCard(new Card(CLUB, JACK));

        Map<Player, GameResult> playerResults = gameUsers.generatePlayersResult();

        assertThat(playerResults).containsExactly(Map.entry(player, LOSE));
    }

    @Test
    @DisplayName("player(Busted), dealer(Busted) -> LOSE")
    void should_lose_when_playerBustedDealerBusted() {
        Player player = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player)), new Deck(List.of(
                new Card(HEART, QUEEN),
                new Card(HEART, TEN),
                new Card(CLUB, QUEEN),
                new Card(CLUB, TEN)
        )));
        player.addCard(new Card(CLUB, JACK));
        gameUsers.getDealer().addCard(new Card(HEART, JACK));

        Map<Player, GameResult> playerResults = gameUsers.generatePlayersResult();

        assertThat(playerResults).containsExactly(Map.entry(player, LOSE));
    }

    @Test
    @DisplayName("player(11), dealer(Busted) -> WIN")
    void should_lose_when_playerBustedDealer1() {
        Player player = new Player(new Name("a"));
        GameUsers gameUsers = new GameUsers(new Players(List.of(player)), new Deck(List.of(
                new Card(CLUB, QUEEN),
                new Card(CLUB, TEN),
                new Card(CLUB, FIVE),
                new Card(CLUB, SIX)
        )));
        gameUsers.getDealer().addCard(new Card(CLUB, JACK));

        Map<Player, GameResult> playerResults = gameUsers.generatePlayersResult();

        assertThat(playerResults).containsExactly(Map.entry(player, WIN));
    }
}
