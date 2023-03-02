package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new Cards(List.of(new Card(Shape.CLOVER, Value.JACK), new Card(Shape.HEART, Value.EIGHT))));
    }

    @Test
    @DisplayName("플레이어가 게임에서 지는 경우를 확인한다.")
    void calculateGameResult1() {
        players = Players.from(List.of("aa"), new CardDistributor(generateCardsForTest(new Card(Shape.HEART, Value.TWO))));
        Result result = new Result(dealer, players);

        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 게임에서 이기는 경우를 확인한다.")
    void calculateGameResult2() {
        players = Players.from(List.of("aa"), new CardDistributor(generateCardsForTest(new Card(Shape.HEART, Value.JACK))));
        Result result = new Result(dealer, players);

        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 게임에서 비기는 경우를 확인한다.")
    void calculateGameResult3() {
        players = Players.from(List.of("aa"), new CardDistributor(generateCardsForTest(new Card(Shape.HEART, Value.NINE))));
        Result result = new Result(dealer, players);

        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.DRAW);
    }

    private List<Card> generateCardsForTest(Card card) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            cards.add(card);
        }

        return cards;
    }

}
