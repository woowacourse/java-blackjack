package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Deck;
import blackjack.domain.card.RandomDeckGenerator;
import blackjack.domain.card.TestNonShuffledDeckGenerator;
import blackjack.domain.result.CardResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
            new Card(CardShape.CLOVER, CardNumber.TEN),
            new Card(CardShape.CLOVER, CardNumber.NINE),
            new Card(CardShape.HEART, CardNumber.JACK),
            new Card(CardShape.HEART, CardNumber.NINE),
            new Card(CardShape.DIAMOND, CardNumber.FOUR));

    @Test
    @DisplayName("플레이어의 이름과 카드목록 점수를 반환하는 기능 테스트")
    void getPlayerNameAndCardResultsTest() {
        final Players players = new Players(List.of("필립", "홍실"), new Deck(new TestNonShuffledDeckGenerator(testCards)));

        final Map<String, CardResult> playerNameAndResults = players.getPlayerNameAndCardResults();

        assertSoftly(softly -> {
            softly.assertThat(playerNameAndResults.get("필립").getCards().getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(0, 2));
            softly.assertThat(playerNameAndResults.get("필립").getScore().getValue())
                    .isEqualTo(21);
            softly.assertThat(playerNameAndResults.get("홍실").getCards().getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(2, 4));
            softly.assertThat(playerNameAndResults.get("홍실").getScore().getValue())
                    .isEqualTo(19);
        });
    }

    @Test
    @DisplayName("플레이어들의 이름에 중복이 있는경우 예외처리하는 기능 테스트")
    void throwExceptionIfPlayerNamesHasDuplicate() {
        final List<String> playerNames = List.of("필립", "필립");

        assertThatThrownBy(() -> new Players(playerNames, new Deck(new RandomDeckGenerator())));
    }
}
