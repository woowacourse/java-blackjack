package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.deck.Deck;
import domain.game.BlackJackGame;
import domain.player.Amount;
import domain.player.Name;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BlackJackGameTest {
    @Nested
    @DisplayName("카드 분배 테스트")
    class distributeTest {
        List<Name> names;
        List<Amount> amounts;
        BlackJackGame blackJackGame;

        @BeforeEach
        void setup() {
            names = Stream.of("name1", "name2").map(Name::new).collect(Collectors.toList());
            amounts = Stream.of(1, 100).map(Amount::new).collect(Collectors.toList());
            blackJackGame = new BlackJackGame(new Deck(), names, amounts);
        }

        @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
        @Test
        void distributeTwoCardsToPlayersTest() {
            // given
            final int expectedCardSize = 2;

            // when
            for (final Name name : names) {
                // then
                assertEquals(expectedCardSize, blackJackGame.getPlayerCards(name).size());
            }
        }

        @DisplayName("처음 카드를 나눠받고 카드를 뽑으면 패의 크기는 3이다.")
        @Test
        void drawCardTest() {
            // given
            final int expectedCardSize = 3;

            //when
            for (final Name name : names) {
                blackJackGame.drawCardPlayer(name);

                //then
                assertEquals(expectedCardSize, blackJackGame.getPlayerCards(name).size());
            }
        }

        @DisplayName("섞지 않은 덱에서 2장씩 뽑았을 때 Rank의 역순으로 카드가 나온다.")
        @ParameterizedTest
        @CsvSource(value = {"name1:20", "name2:17"}, delimiter = ':')
        void getScoreTest(final Name name, final int score) {
            assertEquals(score, blackJackGame.getPlayerScore(name));
        }

    }

    @Nested
    @DisplayName("수익 계산 테스트")
    class profitTest {
        Deck deck;
        Name name = new Name("player");
        List<Name> names = List.of(name);
        List<Amount> amounts = List.of(new Amount(100));

        @BeforeEach
        void setup() {
            deck = new Deck();
        }

        @DisplayName("플레이어가 블랙잭인 경우 베팅 금액의 1.5배 수익이 난다.")
        @Test
        void blackjackTest() {
            // given
            final int blackjackOffset = 10;
            final int expectProfit = 150;
            for (int i = 0; i < blackjackOffset; i++) {
                deck.popCard();
            }

            // when
            final BlackJackGame blackJackGame = new BlackJackGame(deck, names, amounts);
            final Map<Name, Integer> profits = blackJackGame.calculateProfits();

            // then
            assertEquals(expectProfit, profits.get(name));
        }

        @DisplayName("플레이어가 이긴 경우 베팅 금액만큼 수익이 난다.")
        @Test
        void winTest() {
            // given
            final int winOffset = 11;
            final int expectProfit = 100;
            for (int i = 0; i < winOffset; i++) {
                deck.popCard();
            }

            // when
            final BlackJackGame blackJackGame = new BlackJackGame(deck, names, amounts);
            final Map<Name, Integer> profits = blackJackGame.calculateProfits();

            // then
            assertEquals(expectProfit, profits.get(name));
        }

        @DisplayName("플레이어가 비긴 경우 수익이 0이다.")
        @Test
        void drawTest() {
            // given
            final int expectProfit = 0;

            // when
            final BlackJackGame blackJackGame = new BlackJackGame(deck, names, amounts);
            final Map<Name, Integer> profits = blackJackGame.calculateProfits();

            // then
            assertEquals(expectProfit, profits.get(name));
        }

        @DisplayName("플레이어가 진 경우 베팅 금액만큼 손실이 난다.")
        @Test
        void loseTest() {
            // given
            final int loseOffset = 2;
            final int expectProfit = -100;
            for (int i = 0; i < loseOffset; i++) {
                deck.popCard();
            }

            // when
            final BlackJackGame blackJackGame = new BlackJackGame(deck, names, amounts);
            final Map<Name, Integer> profits = blackJackGame.calculateProfits();

            // then
            assertEquals(expectProfit, profits.get(name));
        }
    }
}
