package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.Card;
import blackjack.model.Deck;
import blackjack.model.FixShuffleStrategy;
import blackjack.model.Rank;
import blackjack.model.Suit;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackControllerTest {

    private static final String NEW_LINE = System.lineSeparator();

    private String run(String input, List<Card> fixCards) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        new BlackjackController(
                new InputView(),
                new OutputView(),
                new Deck(new FixShuffleStrategy(fixCards))
        ).run();

        return output.toString();
    }


    @Test
    @DisplayName("플레이어 점수가 딜러 점수보다 높으면 플레이어 승")
    void test_player_score_higher_than_dealer() {
        List<Card> fixCards = List.of(
                new Card(Suit.DIAMOND, Rank.NINE),
                new Card(Suit.HEART, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.CLOVER, Rank.TEN)
        );

        String result = run("pobi" + NEW_LINE + "1000" + NEW_LINE + "n" + NEW_LINE, fixCards);
        assertThat(result).contains("딜러: -1000");
        assertThat(result).contains("pobi: 1000");
    }

    @Test
    @DisplayName("플레이어 점수가 딜러 점수보다 낮으면 플레이어 패")
    void test_player_score_lower_than_dealer() {
        List<Card> fixCards = List.of(
                new Card(Suit.DIAMOND, Rank.SEVEN),
                new Card(Suit.HEART, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.CLOVER, Rank.TEN)
        );

        String result = run("pobi" + NEW_LINE + "1000" + NEW_LINE + "n" + NEW_LINE, fixCards);
        assertThat(result).contains("딜러: 1000");
        assertThat(result).contains("pobi: -1000");
    }

    @Test
    @DisplayName("플레이어 Hit 및 버스트 -> 딜러 승")
    void test_player_hit_and_bust_dealer_win() {
        List<Card> fixCards = List.of(
                new Card(Suit.DIAMOND, Rank.SEVEN),
                new Card(Suit.HEART, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.CLOVER, Rank.TEN),
                new Card(Suit.DIAMOND, Rank.SIX)
        );

        String result = run("pobi" + NEW_LINE + "1000" + NEW_LINE + "y" + NEW_LINE, fixCards);
        assertThat(result).contains("딜러: 1000");
        assertThat(result).contains("pobi: -1000");
    }

    @Test
    @DisplayName("딜러는 17점 미만일 경우 hit")
    void test_dealer_hit_when_score_before_17() {
        List<Card> fixCards = List.of(
                new Card(Suit.DIAMOND, Rank.SEVEN),
                new Card(Suit.HEART, Rank.EIGHT),
                new Card(Suit.SPADE, Rank.TEN),
                new Card(Suit.CLOVER, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.SIX),
                new Card(Suit.CLOVER, Rank.SIX)
        );

        String result = run("pobi" + NEW_LINE + "1000" + NEW_LINE + "n" + NEW_LINE, fixCards);
        assertThat(result).contains("딜러는 16이하라 한장의 카드를 더 받았습니다");
    }

}
