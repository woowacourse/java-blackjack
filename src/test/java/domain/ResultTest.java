package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Letter;
import type.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    private final List<Card> cards = new ArrayList<>();
    private List<String> names;
    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        cards.add(Card.of(Shape.CLOVER, Letter.JACK));
        cards.add(Card.of(Shape.HEART, Letter.EIGHT));
        dealer = new Dealer(new Cards(cards));
        names = List.of("aa");
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트인 경우를 확인한다.")
    void calculateGameResultWhenAllBust() {
        dealer.pick(Card.of(Shape.HEART, Letter.NINE));
        players = Players.of(names, new CardDistributor(generateCardsForTest(Card.of(Shape.HEART, Letter.JACK))));
        playerPick(Card.of(Shape.HEART, Letter.KING));

        Result result = new Result(dealer, players.getPlayers());
        Map<Player, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get(createPlayer())).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어가 게임에서 비기는 경우를 확인한다.")
    void calculateGameResultWhenPlayerDraw() {
        players = Players.of(names, new CardDistributor(generateCardsForTest(Card.of(Shape.HEART, Letter.NINE))));

        Result result = new Result(dealer, players.getPlayers());
        Map<Player, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get(createPlayer())).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러만 버스트인 경우를 확인한다.")
    void calculateGameResultWhenDealerBust() {
        players = Players.of(names, new CardDistributor(generateCardsForTest(Card.of(Shape.HEART, Letter.JACK))));
        dealer.pick(Card.of(Shape.HEART, Letter.QUEEN));

        Result result = new Result(dealer, players.getPlayers());
        Map<Player, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get(createPlayer())).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어가 게임에서 이기는 경우를 확인한다.")
    void calculateGameResultWhenPlayerWin() {
        players = Players.of(names, new CardDistributor(generateCardsForTest(Card.of(Shape.HEART, Letter.JACK))));

        Result result = new Result(dealer, players.getPlayers());
        Map<Player, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get(createPlayer())).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어만 버스트인 경우를 확인한다.")
    void calculateGameResultPlayerBust() {
        players = Players.of(names, new CardDistributor(generateCardsForTest(Card.of(Shape.HEART, Letter.NINE))));
        playerPick(Card.of(Shape.HEART, Letter.KING));
        Result result = new Result(dealer, players.getPlayers());

        Map<Player, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get(createPlayer())).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어가 게임에서 지는 경우를 확인한다.")
    void calculateGameResultWhenPlayerLose() {
        players = Players.of(names, new CardDistributor(generateCardsForTest(Card.of(Shape.HEART, Letter.TWO))));
        Result result = new Result(dealer, players.getPlayers());

        Map<Player, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get(createPlayer())).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("둘 다 21점인데 Player 만 Black Jack 인 경우")
    void scoreIsEqualsButPlayerBlackJack() {
        players = Players.of(names, new CardDistributor(generateBlackJackCard()));
        Dealer dealer = new Dealer(generateBlackJackCard());
        dealer.pick(Card.of(Shape.DIAMOND, Letter.JACK));
        Result result = new Result(dealer, players.getPlayers());

        Map<Player, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get(createPlayer())).isEqualTo(GameResult.BLACK_JACK_WIN);
    }

    @Test
    @DisplayName("Player 가 블랙잭으로 이기는 경우")
    void playerWinWhenPlayerHasBlackJack() {
        players = Players.of(names, new CardDistributor(generateBlackJackCard()));
        Result result = new Result(dealer, players.getPlayers());

        Map<Player, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get(createPlayer())).isEqualTo(GameResult.BLACK_JACK_WIN);
    }

    @Test
    @DisplayName("Player 가 블랙잭으로 지는 경우")
    void playerLoseWhenDealerHasBlackJack() {
        players = Players.of(names, new CardDistributor(generateBlackJackCard()));
        playerPick(Card.of(Shape.HEART, Letter.JACK));
        Dealer dealer = new Dealer(generateBlackJackCard());
        Result result = new Result(dealer, players.getPlayers());

        Map<Player, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get(createPlayer())).isEqualTo(GameResult.LOSE);
    }


    private Cards generateCardsForTest(Card card) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            cards.add(card);
        }

        return new Cards(cards);
    }

    private Player createPlayer() {
        return new Player(new Name("aa"), new Cards(Collections.emptyList()));
    }

    private Cards generateBlackJackCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(Shape.DIAMOND, Letter.JACK));
        cards.add(Card.of(Shape.DIAMOND, Letter.ACE));
        return new Cards(cards);
    }

    private void playerPick(Card card) {
        players.getPlayers()
                .get(0)
                .pick(card);
    }

}
