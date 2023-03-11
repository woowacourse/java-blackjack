package domain;

import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.Assistant.addCards;


public class GameTest {

    private List<Player> players;
    private Game game;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        players = List.of(
                new Player("조이", new Money(1_000)),
                new Player("땡칠", new Money(1_000)));

        dealer = new Dealer();

        game = new Game(players, new Deck(), dealer);
    }

    @Test
    @DisplayName("카드를 2장씩 분배한다.")
    void test_deal_two_cards() {
        game.dealCardsInFirstTurn();

        for (Player player : players) {
            assertThat(player.cards()).hasSize(2);
        }

        assertThat(dealer.cards()).hasSize(2);
    }

    @ParameterizedTest(name = "딜러와 플레이어중에 21에 가까운 사람이 이긴다.")
    @CsvSource({"0,9,K,LOSE", "1,A,K,WIN"})
    void test_21_win(int index, String playerLetter, String dealerLetter, Result result) {
        Player player = players.get(index);

        addCards(player, playerLetter);
        addCards(dealer, dealerLetter);

        assertThat(game.getResultBy(player)).isEqualTo(result);
    }

    @ParameterizedTest(name = "점수가 동일하면 무승부로 한다")
    @CsvSource({"0,10,K", "0,2,2"})
    void test_draw(int index, String playerLetter, String dealerLetter) {
        Player player = players.get(index);

        addCards(player, playerLetter);
        addCards(dealer, dealerLetter);

        assertThat(game.getResultBy(player)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어와 딜러가 모두 죽으면 무승부로 한다")
    @Test
    void test_draw_all_busted() {
        Player player = players.get(0);

        addCards(player, "10", "K", "J");
        addCards(dealer, "10", "K", "J");

        assertThat(game.getResultBy(player)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("특정 플레이어에게 카드를 한장 더 준다")
    void test_deal_a_card() {
        Player player = players.get(0);

        addCards(player, "10", "2");
        addCards(dealer, "K");

        game.dealCard(player);

        assertThat(player.cards()).hasSize(3);
    }

    @Test
    @DisplayName("딜러는 점수가 16 이하이면 카드 1장을 뽑는다")
    void test_draw_16() {
        Player player = players.get(0);

        addCards(player, "10", "2");
        addCards(dealer, "K", "6");

        game.dealCardToDealer();

        assertThat(dealer.score()
                .isGreaterThan(new Score(16)))
                .isTrue();
    }

    @Test
    @DisplayName("딜러는 점수가 16을 초과하면 카드를 뽑지 않는다")
    void test_not_draw_16() {
        Player player = players.get(0);

        addCards(player, "10", "2");
        addCards(dealer, "K", "7");

        game.dealCardToDealer();

        assertThat(dealer.score()).isEqualTo(new Score(17));
    }

}
