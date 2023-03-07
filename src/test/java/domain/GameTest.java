package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static domain.Suit.HEART;
import static domain.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {


    @Test
    @DisplayName("카드를 2장씩 분배한다.")
    void test_deal_two_cards() {
        var players = List.of(
                new Player("조이"),
                new Player("땡칠")
        );
        Dealer dealer = new Dealer();

        var game = new Game(players, new Deck(), dealer);

        game.dealTwoCards();

        for (Player player : players) {
            assertThat(player.cards()).hasSize(2);
        }

        assertThat(dealer.cards()).hasSize(2);
    }

    @ParameterizedTest(name = "딜러와 플레이어중에 21에 가까운 사람이 이긴다.")
    @CsvSource({"0,LOSE", "1,WIN"})
    void test_21_win(int index, Result result) {
        var players = List.of(
                new Player("조이", createCards("9")),
                new Player("땡칠", createCards("A"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K")));

        assertThat(game.getResultBy(players.get(index))).isEqualTo(result);
    }

    @DisplayName("점수가 동일하면 무승부로 한다")
    @Test
    void test_draw() {
        var players = List.of(
                new Player("땡칠", createCards("10"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K")));

        assertThat(game.getResultBy(players.get(0))).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어와 딜러가 모두 죽으면 무승부로 한다")
    @Test
    void test_draw_all_busted() {
        var players = List.of(
                new Player("땡칠", createCards("10", "K", "K"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K", "9", "9")));

        assertThat(game.getResultBy(players.get(0))).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("특정 플레이어에게 카드를 한장 더 준다")
    void test_deal_a_card() {
        var players = List.of(
                new Player("조이", createCards("10", "2"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K")));
        game.dealCard(players.get(0));

        var player = game.getPlayers().get(0);
        assertThat(player.cards()).hasSize(3);
    }



    @Test
    @DisplayName("딜러는 점수가 16 이하이면 카드 1장을 뽑는다")
    void test_draw_16() {
        var players = List.of(
                new Player("조이", createCards("10", "2"))
        );

        var dealer = new Dealer(new ArrayList<>(List.of(
                new Card(HEART, "10"),
                new Card(HEART, "6"))));

        var game = new Game(players, new Deck(), dealer);

        game.dealCardToDealer();

        assertThat(dealer.score().getValue()).isGreaterThan(16);
    }

    @Test
    @DisplayName("딜러는 점수가 16을 초과하면 카드를 뽑지 않는다")
    void test_not_draw_16() {
        var players = List.of(
                new Player("조이", createCards("10", "2"))
        );

        var dealer = new Dealer(List.of(
                new Card(HEART, "10"),
                new Card(HEART, "7")));

        var game = new Game(players, new Deck(), dealer);

        game.dealCardToDealer();

        assertThat(dealer.score()).isEqualTo(new Score(17));
    }

    private List<Card> createCards(String... letters) {
        return Arrays.stream(letters)
                .map(letter -> new Card(SPADE, letter))
                .collect(Collectors.toList());
    }

}
