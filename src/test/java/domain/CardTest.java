package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest extends AbstractTestFixture{

    @ParameterizedTest(name = "점수를 알 수 있다")
    @CsvSource({"ACE,1", "TWO,2", "THREE,3", "FOUR,4", "FIVE,5", "SIX,6", "SEVEN,7",
            "EIGHT,8", "NINE,9", "TEN,10", "JACK,10", "QUEEN,10", "KING,10"})
    void test_card_score(Letter letter, int expectedScore) {
        var card = createCard(letter);
        assertThat(card.score()).isEqualTo(expectedScore);
    }
}
