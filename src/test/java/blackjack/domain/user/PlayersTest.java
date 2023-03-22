package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.CardResult;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    private static final PlayerName TEST_PLAYER_NAME_1 = new PlayerName("필립");
    private static final PlayerName TEST_PLAYER_NAME_2 = new PlayerName("홍실");

    final Queue<CardGroup> cardGroups = new LinkedList<>();

    final List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
            new Card(CardShape.CLOVER, CardNumber.TEN),
            new Card(CardShape.CLOVER, CardNumber.NINE),
            new Card(CardShape.HEART, CardNumber.JACK),
            new Card(CardShape.HEART, CardNumber.NINE),
            new Card(CardShape.DIAMOND, CardNumber.FOUR));

    @BeforeEach
    void setUp() {
        for (int index = 0; index < testCards.size(); index = index + 2) {
            cardGroups.add(new CardGroup(testCards.get(index), testCards.get(index + 1)));
        }
    }

    @Test
    @DisplayName("플레이어의 이름과 카드목록 점수를 반환하는 기능 테스트")
    void getPlayerNameAndCardResultsTest() {
        final Players players = new Players(List.of(TEST_PLAYER_NAME_1.getValue(), TEST_PLAYER_NAME_2.getValue()),
                cardGroups);

        final Map<Name, CardResult> playerNameAndResults = players.getPlayerNameAndCardResults();

        assertSoftly(softly -> {
            softly.assertThat(playerNameAndResults.get(TEST_PLAYER_NAME_1).getCards().getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(0, 2));
            softly.assertThat(playerNameAndResults.get(TEST_PLAYER_NAME_1).getScore().getValue())
                    .isEqualTo(21);
            softly.assertThat(playerNameAndResults.get(TEST_PLAYER_NAME_2).getCards().getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(2, 4));
            softly.assertThat(playerNameAndResults.get(TEST_PLAYER_NAME_2).getScore().getValue())
                    .isEqualTo(19);
        });
    }

    @Test
    @DisplayName("플레이어들의 이름에 중복이 있는경우 예외처리하는 기능 테스트")
    void throwExceptionIfPlayerNamesHasDuplicate() {
        final List<String> playerNames = List.of(TEST_PLAYER_NAME_1.getValue(), TEST_PLAYER_NAME_1.getValue());

        assertThatThrownBy(() -> new Players(playerNames, new LinkedList<>()));
    }

    @Test
    @DisplayName("플레이어들의 이름과 수익률을 반환하는 기능 테스트")
    void getPlayerNameAndProfitRates() {
        final Players players = new Players(List.of(TEST_PLAYER_NAME_1.getValue(), TEST_PLAYER_NAME_2.getValue()),
                cardGroups);
        final Dealer dealer = new Dealer(new CardGroup(
                new Card(CardShape.SPADE, CardNumber.TEN),
                new Card(CardShape.DIAMOND, CardNumber.TEN)));

        final Map<Name, Double> playerNameAndProfitRates = players.getPlayerNameAndProfitRates(dealer);

        assertSoftly(softly -> {
            softly.assertThat(playerNameAndProfitRates.get(TEST_PLAYER_NAME_1)).isEqualTo(1.5);
            softly.assertThat(playerNameAndProfitRates.get(TEST_PLAYER_NAME_2)).isEqualTo(-1);
        });
    }
}
