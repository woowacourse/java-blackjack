package domain;

import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Letter;
import type.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    private List<Card> playerCards;
    private Players players;
    private List<String> names;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        List<Card> dealerCards = new ArrayList<>() {{
            add(new Card(Shape.CLOVER, Letter.JACK));
            add(new Card(Shape.HEART, Letter.EIGHT));
        }};
        playerCards = new ArrayList<>() {{
            add(new Card(Shape.HEART, Letter.JACK));
            add(new Card(Shape.CLOVER, Letter.EIGHT));
        }};
        dealer = new Dealer(new Hand(dealerCards));
        names = List.of("aa");
        players = createGamePlayers(names, playerCards);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트인 경우를 확인한다.")
    void calculateGameResultWhenAllBust() {
        dealer.pick(getParticipantBustCard());
        makePlayersBust();

        Result result = new Result(dealer, players);
        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어가 게임에서 비기는 경우를 확인한다.")
    void calculateGameResultWhenPlayerDraw() {
        Result result = new Result(dealer, players);
        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러만 버스트인 경우를 확인한다.")
    void calculateGameResultWhenDealerBust() {
        dealer.pick(getParticipantBustCard());

        Result result = new Result(dealer, players);
        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어가 게임에서 이기는 경우를 확인한다.")
    void calculateGameResultWhenPlayerWin() {
        playerCards.add(getParticipantWinCard());
        players = createGamePlayers(names, playerCards);

        Result result = new Result(dealer, players);
        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어만 버스트인 경우를 확인한다.")
    void calculateGameResultPlayerBust() {
        makePlayersBust();
        Result result = new Result(dealer, players);

        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어가 게임에서 지는 경우를 확인한다.")
    void calculateGameResultWhenPlayerLose() {
        dealer.pick(getParticipantWinCard());
        Result result = new Result(dealer, players);

        Map<String, GameResult> gameResult = result.getResult();

        assertThat(gameResult.get("aa")).isEqualTo(GameResult.LOSE);
    }

    private void makePlayersBust() {
        players.getPlayers().get(0).pick(new Card(Shape.HEART, Letter.KING));
    }

    private Card getParticipantWinCard() {
        return new Card(Shape.HEART, Letter.TWO);
    }

    private Card getParticipantBustCard() {
        return new Card(Shape.HEART, Letter.NINE);
    }

    private Players createGamePlayers(List<String> playerNames, List<Card> cards) {
        List<Player> players = playerNames.stream()
                .map(name -> distributeInitialCardForPlayer(name, cards))
                .collect(Collectors.toList());
        return Players.from(players);
    }

    private Player distributeInitialCardForPlayer(String playerName, List<Card> cards) {
        return Player.of(new Name(playerName), new Hand(cards));
    }

}
