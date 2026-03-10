package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;
import domain.card.Deck;
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

    private Dealer createDealer(CardNumber number1, CardSuit suit1, CardNumber number2, CardSuit suit2) {
        List<Card> hand = new ArrayList<>(List.of(
                new Card(number1, suit1),
                new Card(number2, suit2)));
        return new Dealer(hand, new Deck());
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 버스트라면 무승부이다.")
    void bothBustDrawTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardSuit.CLUB),
                new Card(CardNumber.TEN, CardSuit.SPADE),
                new Card(CardNumber.TEN, CardSuit.HEART)));
        Player player = new Player("pobi", playerCards);
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardNumber.TEN, CardSuit.CLUB, CardNumber.TEN, CardSuit.SPADE);
        dealer.addCard(new Card(CardNumber.TEN, CardSuit.HEART));

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어만 버스트라면 패배한다.")
    void playerBustLoseTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardSuit.CLUB),
                new Card(CardNumber.TEN, CardSuit.SPADE),
                new Card(CardNumber.TEN, CardSuit.HEART)));
        Player player = new Player("pobi", playerCards);
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardNumber.TEN, CardSuit.CLUB, CardNumber.SEVEN, CardSuit.SPADE);

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러만 버스트라면 플레이어가 승리한다.")
    void dealerBustWinTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardSuit.CLUB),
                new Card(CardNumber.SEVEN, CardSuit.SPADE)));
        Player player = new Player("pobi", playerCards);
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardNumber.TEN, CardSuit.CLUB, CardNumber.TEN, CardSuit.SPADE);
        dealer.addCard(new Card(CardNumber.TEN, CardSuit.HEART));

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 높으면 승리한다.")
    void playerHigherScoreWinTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardSuit.CLUB),
                new Card(CardNumber.NINE, CardSuit.SPADE)));
        Player player = new Player("pobi", playerCards);
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardNumber.TEN, CardSuit.CLUB, CardNumber.SEVEN, CardSuit.SPADE);

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러와 같으면 무승부이다.")
    void sameScoreDrawTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardSuit.CLUB),
                new Card(CardNumber.SEVEN, CardSuit.SPADE)));
        Player player = new Player("pobi", playerCards);
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardNumber.TEN, CardSuit.CLUB, CardNumber.SEVEN, CardSuit.SPADE);

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 낮으면 패배한다.")
    void playerLowerScoreLoseTest() {
        List<Card> playerCards = new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardSuit.CLUB),
                new Card(CardNumber.FIVE, CardSuit.SPADE)));
        Player player = new Player("pobi", playerCards);
        Players players = new Players(List.of(player));

        Dealer dealer = createDealer(CardNumber.TEN, CardSuit.CLUB, CardNumber.SEVEN, CardSuit.SPADE);

        Map<String, GameResult> results = judgement.judgePlayerResults(players, dealer);
        Assertions.assertThat(results.get("pobi")).isEqualTo(GameResult.LOSE);
    }
}
