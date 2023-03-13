package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import type.Letter;
import type.Shape;

public class ResultTest {

    private List<Card> playerCards;
    private Players players;
    private List<String> names;
    private Dealer dealer;
    private Name name;

    @BeforeEach
    void setUp() {
        List<Card> dealerCards = new ArrayList<>() {{
            add(Card.of(Shape.CLOVER, Letter.JACK));
            add(Card.of(Shape.HEART, Letter.EIGHT));
        }};
        playerCards = new ArrayList<>() {{
            add(Card.of(Shape.HEART, Letter.JACK));
            add(Card.of(Shape.CLOVER, Letter.EIGHT));
        }};
        dealer = new Dealer(new Hand(dealerCards));
        names = List.of("aa");
        players = createGamePlayers(names, playerCards);
        name = new Name("aa");
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트이면, 플레이어가 진다.")
    void calculateGameResultWhenAllBust() {
        dealer.pick(getParticipantBustCard());
        makePlayersBust();

        Result result = new Result(dealer, players);
        Map<Name, GameResult> gameResult = result.getPlayersWinResult();

        assertThat(gameResult.get(name)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러가 버스트, 블랙잭이 아니고 점수가 같으면 비긴다.")
    void calculateGameResultWhenPlayerDraw() {
        Result result = new Result(dealer, players);
        Map<Name, GameResult> gameResult = result.getPlayersWinResult();

        assertThat(gameResult.get(name)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러만 버스트라면 플레이어가 이긴다.")
    void calculateGameResultWhenDealerBust() {
        dealer.pick(getParticipantBustCard());

        Result result = new Result(dealer, players);
        Map<Name, GameResult> gameResult = result.getPlayersWinResult();

        assertThat(gameResult.get(name)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어의 점수가 높다면 플레이어가 이긴다.")
    void calculateGameResultWhenPlayerWin() {
        playerCards.add(getParticipantWinCard());
        players = createGamePlayers(names, playerCards);

        Result result = new Result(dealer, players);
        Map<Name, GameResult> gameResult = result.getPlayersWinResult();

        assertThat(gameResult.get(name)).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어만 버스트라면 플레이어가 진다.")
    void calculateGameResultPlayerBust() {
        makePlayersBust();
        Result result = new Result(dealer, players);

        Map<Name, GameResult> gameResult = result.getPlayersWinResult();

        assertThat(gameResult.get(name)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("둘 다 버스트가 아니고 플레이어의 점수가 낮으면 플레이어가 진다.")
    void calculateGameResultWhenPlayerLose() {
        dealer.pick(getParticipantWinCard());
        Result result = new Result(dealer, players);

        Map<Name, GameResult> gameResult = result.getPlayersWinResult();

        assertThat(gameResult.get(name)).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어만 블랙잭이라면, 플레이어는 블랙잭이다.")
    void calculateGameResultWhenPlayerBlackjack() {
        players = createGamePlayers(names, makeBlackjackCards());
        Result result = new Result(dealer, players);

        Map<Name, GameResult> gameResult = result.getPlayersWinResult();

        assertThat(gameResult.get(name)).isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭이라면, 비긴다.")
    void calculateGameResultWhenAllBlackjack() {
        dealer = new Dealer(new Hand(makeBlackjackCards()));
        players = createGamePlayers(names, makeBlackjackCards());
        Result result = new Result(dealer, players);

        Map<Name, GameResult> gameResult = result.getPlayersWinResult();

        assertThat(gameResult.get(name)).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러만 블랙잭이라면 플레이어가 진다.")
    void calculateGameResultWhenDealerBlackjack() {
        dealer = new Dealer(new Hand(makeBlackjackCards()));
        Result result = new Result(dealer, players);

        Map<Name, GameResult> gameResult = result.getPlayersWinResult();

        assertThat(gameResult.get(name)).isEqualTo(GameResult.LOSE);
    }

    private void makePlayersBust() {
        players.getPlayers().get(0).pick(Card.of(Shape.HEART, Letter.KING));
    }

    private List<Card> makeBlackjackCards() {
        return List.of(Card.of(Shape.HEART, Letter.KING), Card.of(Shape.HEART, Letter.ACE));
    }

    private Card getParticipantWinCard() {
        return Card.of(Shape.HEART, Letter.TWO);
    }

    private Card getParticipantBustCard() {
        return Card.of(Shape.HEART, Letter.NINE);
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
