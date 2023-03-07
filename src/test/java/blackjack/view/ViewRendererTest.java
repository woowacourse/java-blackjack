package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.CardGroup;
import blackjack.domain.CardNumber;
import blackjack.domain.CardShape;
import blackjack.domain.WinningStatus;
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
        final Map<String, CardGroup> status = Map.of("홍실", new CardGroup(
                new Card(CardShape.SPADE, CardNumber.ACE),
                new Card(CardShape.DIAMOND, CardNumber.TWO)
        ));

        final Map<String, List<String>> renderedStatus = ViewRenderer.renderStatus(status);

        assertThat(renderedStatus.get("홍실")).contains("A스페이드", "2다이아몬드");
    }


    @Test
    @DisplayName("카드 렌더링 테스트")
    void renderCardToStringTest() {
        final CardGroup cardGroup = new CardGroup(new Card(CardShape.SPADE, CardNumber.ACE),
                new Card(CardShape.DIAMOND, CardNumber.TWO));

        List<String> renderedCardNames = ViewRenderer.renderCardsToString(cardGroup);

        assertThat(renderedCardNames).contains("A스페이드", "2다이아몬드");
    }

    @Test
    @DisplayName("승리 결과 렌더링 테스트")
    void renderWinningResultTest() {
        Map<String, WinningStatus> winningResult = Map.of(
                "필립", WinningStatus.WIN,
                "홍실", WinningStatus.TIE);

        Map<String, String> renderedWinningResult = ViewRenderer.renderWinningResult(winningResult);

        assertSoftly(softly -> {
            softly.assertThat(renderedWinningResult.get("딜러")).isEqualTo("1무 1패 ");
            softly.assertThat(renderedWinningResult.get("필립")).isEqualTo("승 ");
            softly.assertThat(renderedWinningResult.get("홍실")).isEqualTo("무 ");
        });
    }
}
