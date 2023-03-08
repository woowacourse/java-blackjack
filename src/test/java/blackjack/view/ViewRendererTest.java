package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.CardResult;
import blackjack.domain.result.WinningStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
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

        List<String> renderedCardNames = ViewRenderer.renderCardGroupToString(cardGroup);

        assertThat(renderedCardNames).contains("A스페이드", "2다이아몬드");
    }

    @Test
    @DisplayName("플레이어의 승리 결과 렌더링 테스트")
    void renderPlayersWinningResultsTest() {
        final Map<String, WinningStatus> winningResult = Map.of(
                "필립", WinningStatus.WIN,
                "홍실", WinningStatus.TIE);

        final Map<String, String> renderedWinningResult = ViewRenderer.renderPlayersWinningResults(winningResult);

        assertSoftly(softly -> {
            softly.assertThat(renderedWinningResult.get("필립")).isEqualTo("승 ");
            softly.assertThat(renderedWinningResult.get("홍실")).isEqualTo("무 ");
        });
    }

    @Test
    @DisplayName("딜러의 승리 결과 렌더링 테스트")
    void renderDealerWinningResultTest() {
        final Map<WinningStatus, Long> winningResult = Map.of(WinningStatus.WIN, (long) 3, WinningStatus.LOSE, (long) 2,
                WinningStatus.TIE, (long) 4);

        final Map<String, Long> renderedWinningResult = ViewRenderer.renderDealerWinningResult(winningResult);

        assertSoftly(softly -> {
            softly.assertThat(renderedWinningResult.get("승 ")).isEqualTo(3);
            softly.assertThat(renderedWinningResult.get("무 ")).isEqualTo(4);
            softly.assertThat(renderedWinningResult.get("패")).isEqualTo(2);
        });
    }

    @Test
    @DisplayName("유저의 이름과 카드결과를 렌더링하는 기능 테스트")
    void renderUserNameAndCardResultsTest() {
        final CardGroup cardGroup = new CardGroup(
                new Card(CardShape.SPADE, CardNumber.ACE),
                new Card(CardShape.DIAMOND, CardNumber.NINE)
        );
        final Map<String, CardResult> userNameAndCardResults = Map.of("딜러",
                new CardResult(cardGroup, cardGroup.getScore()));
        final Map<String, String> renderedUserNameAndCardResults = ViewRenderer.renderUserNameAndCardResults(
                userNameAndCardResults);

        assertThat(renderedUserNameAndCardResults)
                .containsExactly(entry("딜러", "A스페이드, 9다이아몬드 - 결과: 20"));
    }
}
