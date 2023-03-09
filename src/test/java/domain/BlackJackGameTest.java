package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.deck.Deck;
import domain.game.BlackJackGame;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BlackJackGameTest {
    List<String> names;
    List<Integer> amounts;
    BlackJackGame blackJackGame;

    @BeforeEach
    void setup() {
        names = List.of("name1", "name2");
        amounts = List.of(1, 100);
        blackJackGame = new BlackJackGame(new Deck(), names, amounts);
    }

    @DisplayName("처음 카드를 나눠주면 플레이어의 패의 크기는 2이다.")
    @Test
    void distributeTwoCardsToPlayersTest() {
        int expectedCardSize = 2;
        for (String name : names) {
            assertEquals(expectedCardSize, blackJackGame.getPlayerCards(name).size());
        }
    }

    @DisplayName("처음 카드를 나눠받고 카드를 뽑으면 패의 크기는 3이다.")
    @Test
    void drawCardTest() {
        int expectedCardSize = 3;
        for (String name : names) {
            blackJackGame.drawCardPlayer(name);
            assertEquals(expectedCardSize, blackJackGame.getPlayerCards(name).size());
        }
    }

    @DisplayName("섞지 않은 덱에서 2장씩 뽑았을 때 Rank의 역순으로 카드가 나온다.")
    @ParameterizedTest
    @CsvSource(value = {"name1:20", "name2:17"}, delimiter = ':')
    void getScoreTest(String name, int score) {
        assertEquals(score, blackJackGame.getPlayerScore(name));
    }
}
