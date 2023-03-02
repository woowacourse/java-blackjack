package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {


    @Test
    @DisplayName("카드를 2장씩 분배한다.")
    void test_deal_two_cards() {
        var players = List.of(
                new Player(),
                new Player()
        );
        var game = new Game(players, new Deck(), new Dealer(createCards("K")));

        game.dealCards();

        for (Player player : players) {
            assertThat(player.getCards()).hasSize(2);
        }
    }

    @ParameterizedTest(name = "딜러와 플레이어중에 21에 가까운 사람이 이긴다.")
    @CsvSource({"0,false", "1,true"})
    void test_21_win(int index, boolean isWon) {
        var players = List.of(
                new Player(createCards("9")),
                new Player(createCards("A"))
        );

        var game = new Game(players, new Deck(), new Dealer(createCards("K")));

        assertThat(game.isWon(index)).isEqualTo(isWon);
    }

    private List<Card> createCards(String... letters) {
        return Arrays.stream(letters)
                .map(letter -> new Card("heart", letter))
                .collect(Collectors.toList());
    }
}
