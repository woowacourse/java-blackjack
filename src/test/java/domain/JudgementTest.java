package domain;

import domain.card.Card;
import domain.card.CardSuit;
import domain.card.CardValue;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JudgementTest {

    private Judgement judgement;

    @BeforeEach
    void setUp() {
        judgement = new Judgement();
    }

    private Dealer createDealer(CardValue value1, CardSuit suit1, CardValue value2, CardSuit suit2) {
        List<Card> hand = new ArrayList<>(List.of(
                new Card(value1, suit1),
                new Card(value2, suit2)));
        return new Dealer(hand);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 버스트라면 무승부이다.")
    void bothBustDrawTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardValue.TEN, CardSuit.CLUB),
                new Card(CardValue.TEN, CardSuit.SPADE),
                new Card(CardValue.TEN, CardSuit.HEART)));
        Player player = new Player(playerCards, "pobi", new Money(1000));
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardValue.TEN, CardSuit.CLUB, CardValue.TEN, CardSuit.SPADE);
        dealer.addCard(new Card(CardValue.TEN, CardSuit.HEART));

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어만 버스트라면 패배한다.")
    void playerBustLoseTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardValue.TEN, CardSuit.CLUB),
                new Card(CardValue.TEN, CardSuit.SPADE),
                new Card(CardValue.TEN, CardSuit.HEART)));
        Player player = new Player(playerCards, "pobi", new Money(1000));
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardValue.TEN, CardSuit.CLUB, CardValue.SEVEN, CardSuit.SPADE);

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러만 버스트라면 플레이어가 승리한다.")
    void dealerBustWinTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardValue.TEN, CardSuit.CLUB),
                new Card(CardValue.SEVEN, CardSuit.SPADE)));
        Player player = new Player(playerCards, "pobi", new Money(1000));
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardValue.TEN, CardSuit.CLUB, CardValue.TEN, CardSuit.SPADE);
        dealer.addCard(new Card(CardValue.TEN, CardSuit.HEART));

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 높으면 승리한다.")
    void playerHigherScoreWinTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardValue.TEN, CardSuit.CLUB),
                new Card(CardValue.NINE, CardSuit.SPADE)));
        Player player = new Player(playerCards, "pobi", new Money(1000));
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardValue.TEN, CardSuit.CLUB, CardValue.SEVEN, CardSuit.SPADE);

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러와 같으면 무승부이다.")
    void sameScoreDrawTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardValue.TEN, CardSuit.CLUB),
                new Card(CardValue.SEVEN, CardSuit.SPADE)));
        Player player = new Player(playerCards, "pobi", new Money(1000));
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardValue.TEN, CardSuit.CLUB, CardValue.SEVEN, CardSuit.SPADE);

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 낮으면 패배한다.")
    void playerLowerScoreLoseTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardValue.TEN, CardSuit.CLUB),
                new Card(CardValue.FIVE, CardSuit.SPADE)));
        Player player = new Player(playerCards, "pobi", new Money(1000));
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardValue.TEN, CardSuit.CLUB, CardValue.SEVEN, CardSuit.SPADE);

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.LOSE);
    }
}
