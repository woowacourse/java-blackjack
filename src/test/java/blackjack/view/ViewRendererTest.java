package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.CardNumber;
import blackjack.domain.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ViewRendererTest {

    @Test
    @DisplayName("Status를 출력 형식에 맞게 렌더링 테스트")
    void renderStatusTest() {
        final Map<String, List<Card>> status = Map.of("홍실", List.of(new Card(CardShape.SPADE, CardNumber.ACE)));

        final Map<String, List<String>> renderedStatus = ViewRenderer.renderStatus(status);

        assertThat(renderedStatus.get("홍실")).contains("A스페이드");
    }

}
