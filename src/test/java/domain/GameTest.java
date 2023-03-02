package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static domain.Face.SPADE;
import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {


    @Test
    @DisplayName("카드를 2장씩 분배한다.")
    void test_deal_two_cards() {
        var players = List.of(
                new Player("조이"),
                new Player("땡칠")
        );
        var game = new Game(players, new Deck(), new Dealer(createCards("K")));

        game.dealCards();

        for (Player player : players) {
            assertThat(player.getCards()).hasSize(2);
        }
    }

    @ParameterizedTest(name = "딜러와 플레이어중에 21에 가까운 사람이 이긴다.")
    @CsvSource({"0,LOSE", "1,WIN"})
    void test_21_win(int index, Result result) {
        var players = List.of(
                new Player("조이", createCards("9")),
                new Player("땡칠", createCards("A"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K")));

        assertThat(game.isWon(index)).isEqualTo(result);
    }

    @DisplayName("점수가 동일하면 무승부로 한다")
    @Test
    void test_draw() {
        var players = List.of(
                new Player("땡칠", createCards("10"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K")));

        assertThat(game.isWon(0)).isEqualTo(Result.DRAW);
    }

    @DisplayName("플레이어와 딜러가 모두 죽으면 무승부로 한다")
    @Test
    void test_draw_all_busted() {
        var players = List.of(
                new Player("땡칠", createCards("10", "K", "K"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K", "9", "9")));

        assertThat(game.isWon(0)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("특정 플레이어가 21미만이라면 카드를 한장 더 준다")
    void test_deal_a_card() {
        var players = List.of(
                new Player("조이", createCards("10", "2"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K")));
        game.dealAnotherCard(0);

        var player = game.getPlayers().get(0);
        assertThat(player.getCards()).hasSize(3);
    }

    @Test
    @DisplayName("특정 플레이어가 21이상이라면 카드를 주지 않는다")
    void test_do_not_deal_a_card() {
        var players = List.of(
                new Player("조이", createCards("10", "A"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K")));
        game.dealAnotherCard(0);

        var player = game.getPlayers().get(0);
        assertThat(player.getCards()).hasSize(2);
    }

    private List<Card> createCards(String... letters) {
        return Arrays.stream(letters)
                .map(letter -> new Card(SPADE, letter))
                .collect(Collectors.toList());
    }
}
