package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    private List<Card> cards = new ArrayList<>();
    private List<String> names;
    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        cards.add(new Card(Shape.CLOVER, Value.JACK));
        cards.add(new Card(Shape.HEART, Value.EIGHT));
        dealer = new Dealer(new Cards(cards));
        names = List.of("aa");
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트인 경우를 확인한다.")
    void calculateGameResultWhenAllBust() {
        dealer.pick(new Card(Shape.HEART, Value.NINE));
        players = Players.from(names, new CardDistributor(generateCardsForTest(new Card(Shape.HEART, Value.JACK))));
        makePlayersBust();

        Result result = new Result(dealer, players);
        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어가 게임에서 비기는 경우를 확인한다.")
    void calculateGameResultWhenPlayerDraw() {
        players = Players.from(names, new CardDistributor(generateCardsForTest(new Card(Shape.HEART, Value.NINE))));
        Result result = new Result(dealer, players);
        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러만 버스트인 경우를 확인한다.")
    void calculateGameResultWhenDealerBust() {
        players = Players.from(names, new CardDistributor(generateCardsForTest(new Card(Shape.HEART, Value.JACK))));
        dealer.pick(new Card(Shape.HEART, Value.QUEEN));

        Result result = new Result(dealer, players);
        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어가 게임에서 이기는 경우를 확인한다.")
    void calculateGameResultWhenPlayerWin() {
        players = Players.from(names, new CardDistributor(generateCardsForTest(new Card(Shape.HEART, Value.JACK))));

        Result result = new Result(dealer, players);
        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어만 버스트인 경우를 확인한다.")
    void calculateGameResultPlayerBust() {
        players = Players.from(names, new CardDistributor(generateCardsForTest(new Card(Shape.HEART, Value.NINE))));
        makePlayersBust();
        Result result = new Result(dealer, players);

        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어가 게임에서 지는 경우를 확인한다.")
    void calculateGameResultWhenPlayerLose() {
        players = Players.from(names, new CardDistributor(generateCardsForTest(new Card(Shape.HEART, Value.TWO))));
        Result result = new Result(dealer, players);

        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.LOSE);
    }

    private List<Card> generateCardsForTest(Card card) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            cards.add(card);
        }

        return cards;
    }

    private void makePlayersBust() {
        players.getPlayers().get(0).pick(new Card(Shape.HEART, Value.KING));
    }

}
