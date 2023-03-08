package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ViewRendererTest {

    @Test
    @DisplayName("Status를 출력 형식에 맞게 렌더링 테스트")
    void renderStatusTest() {
        final Map<String, List<Card>> status = Map.of("홍실", List.of(new Card(CardShape.SPADE, CardNumber.ACE)));

        final Map<String, List<String>> renderedStatus = ViewRenderer.renderStatus(status);

        assertThat(renderedStatus.get("홍실")).contains("A스페이드");
    }

    @Test
    @DisplayName("카드 렌더링 테스트")
    void renderCardToStringTest() {
        final List<Card> cards = List.of(new Card(CardShape.SPADE, CardNumber.ACE));

        List<String> renderedCardNames = ViewRenderer.renderCardsToString(cards);

        assertThat(renderedCardNames).contains("A스페이드");
    }

    @Test
    @DisplayName("승리 결과 렌더링 테스트")
    void renderWinningResultTest() {
        Map<String, GameResult> winningResult = Map.of(
                "필립", GameResult.WIN,
                "홍실", GameResult.TIE);

        Map<String, String> renderedWinningResult = ViewRenderer.renderWinningResult(winningResult);

        assertSoftly(softly -> {
            softly.assertThat(renderedWinningResult.get(Dealer.DEALER_NAME_CODE)).isEqualTo("1무 1패 ");
            softly.assertThat(renderedWinningResult.get("필립")).isEqualTo("승 ");
            softly.assertThat(renderedWinningResult.get("홍실")).isEqualTo("무 ");
        });
    }
}
