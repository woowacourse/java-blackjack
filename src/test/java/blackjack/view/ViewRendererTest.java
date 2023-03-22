package blackjack.view;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.money.Money;
import blackjack.domain.result.CardResult;
import blackjack.domain.user.DealerName;
import blackjack.domain.user.Name;
import blackjack.domain.user.PlayerName;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ViewRendererTest {

    @Test
    @DisplayName("카드 렌더링 테스트")
    void renderCardToStringTest() {
        final CardGroup cardGroup = new CardGroup(new Card(CardShape.SPADE, CardNumber.ACE),
                new Card(CardShape.DIAMOND, CardNumber.TWO));

        final List<String> renderedCardNames = ViewRenderer.renderCardGroup(cardGroup);

        assertThat(renderedCardNames).contains("A스페이드", "2다이아몬드");
    }

    @Test
    @DisplayName("유저의 이름과 카드결과를 렌더링하는 기능 테스트")
    void renderUserNameAndCardResultsTest() {
        final CardGroup cardGroup = new CardGroup(
                new Card(CardShape.SPADE, CardNumber.ACE),
                new Card(CardShape.DIAMOND, CardNumber.NINE)
        );
        final Map<Name, CardResult> userNameAndCardResults = Map.of(new DealerName(),
                new CardResult(cardGroup, cardGroup.getScore()));
        final Map<String, String> renderedUserNameAndCardResults = ViewRenderer.renderUserNameAndCardResults(
                userNameAndCardResults);

        assertThat(renderedUserNameAndCardResults)
                .containsExactly(entry("딜러", "A스페이드, 9다이아몬드 - 결과: 20"));
    }

    @Test
    @DisplayName("플레이어와 딜러의 수익금을 렌더링하는 기능 테스트")
    void renderUserNameAndProfitsTest() {
        final Map<Name, Money> playerNameAndProfit = Map.of(new PlayerName("제이미"), new Money(100_000));
        final Money dealerProfit = new Money(-100_000);

        final Map<String, Integer> renderUserNameAndProfit = ViewRenderer.renderUserNameAndProfit(playerNameAndProfit,
                dealerProfit);

        assertThat(renderUserNameAndProfit)
                .containsExactly(
                        entry(DealerName.DEALER_NAME, -100_000),
                        entry("제이미", 100_000));
    }
}
