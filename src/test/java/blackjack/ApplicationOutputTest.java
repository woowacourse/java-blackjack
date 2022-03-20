package blackjack;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import camp.nextstep.edu.missionutils.test.NsTest;

@SuppressWarnings("unchecked")
public class ApplicationOutputTest extends NsTest {

    private final String NEXT_LINE = System.lineSeparator();

    @Test
    @DisplayName("딜러는 처음 카드를 한 장만 보여준다.")
    void dealerOpenCard() {
        assertShuffleTest(
            () -> {
                run("pobi", "10000", "n");
                assertThat(output()).contains("딜러와 pobi에게 2장의 카드를 나누어주었습니다." + NEXT_LINE
                    + "딜러: K스페이드" + NEXT_LINE
                    + "pobi카드: K하트, 7하트");
            },
            List.of(new Card(HEART, SEVEN), new Card(HEART, KING),
                new Card(SPADE, QUEEN), new Card(SPADE, KING))
        );
    }

    @Test
    @DisplayName("딜러 BUST, 플레이어 NOT BUST이면 플레이어의 수익")
    void dealerBustPlayerNotBust() {
        assertShuffleTest(
            () -> {
                run("woni", "10000", "n");
                assertThat(output()).contains("## 최종 수익" + NEXT_LINE
                    + "딜러: -10000" + NEXT_LINE
                    + "woni: 10000");
            },
            List.of(new Card(SPADE, QUEEN),
                new Card(HEART, KING), new Card(SPADE, SIX),
                new Card(SPADE, JACK), new Card(HEART, SIX))
        );
    }

    @Test
    @DisplayName("플레이어 BUST, 딜러 NOT BUST인 경우 딜러의 수익")
    void playBustDealerNotBust() {
        assertShuffleTest(
            () -> {
                run("woni", "10000", "y");
                assertThat(output()).contains("## 최종 수익" + NEXT_LINE
                    + "딜러: 10000" + NEXT_LINE
                    + "woni: -10000");
            },
            List.of(new Card(SPADE, QUEEN),
                new Card(HEART, KING), new Card(HEART, SIX),
                new Card(SPADE, JACK), new Card(HEART, SEVEN))
        );
    }

    @Test
    @DisplayName("플레이어만 블랙잭인 경우 카드를 뽑지 않고 수익률이 1.5배이다.")
    void playerOnlyBlackjack() {
        assertShuffleTest(
            () -> {
                run("woni", "10000");
                assertThat(output()).contains("## 최종 수익" + NEXT_LINE
                        + "딜러: -15000" + NEXT_LINE
                        + "woni: 15000")
                    .doesNotContain("woni은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
            },
            List.of(new Card(HEART, KING), new Card(HEART, ACE),
                new Card(SPADE, JACK), new Card(HEART, SEVEN))
        );
    }

    @Test
    @DisplayName("모두 블랙잭인 경우 수익은 0이다.")
    void AllBlackjack() {
        assertShuffleTest(
            () -> {
                run("woni", "10000");
                assertThat(output()).contains("## 최종 수익" + NEXT_LINE
                    + "딜러: 0" + NEXT_LINE
                    + "woni: 0");
            },
            List.of(
                new Card(SPADE, ACE), new Card(SPADE, KING),
                new Card(HEART, ACE), new Card(HEART, KING))
        );
    }

    @Test
    @DisplayName("같은 21인 경우 블랙잭인 경우 수익을 가져간다.")
    void battleBetween21() {
        assertShuffleTest(
            () -> {
                run("woni", "10000", "y");
                assertThat(output()).contains("## 최종 수익" + NEXT_LINE
                    + "딜러: 10000" + NEXT_LINE
                    + "woni: -10000");
            },
            List.of(
                new Card(SPADE, SIX), new Card(SPADE, JACK), new Card(HEART, FIVE),
                new Card(HEART, ACE), new Card(HEART, KING))
        );
    }

    @Test
    @DisplayName("모두 BUST 아님 && BLACKJACK 아님 이면 높은 점수가 이긴다.")
    void allNotBustNotBlackjack() {
        assertShuffleTest(
            () -> {
                run("woni", "10000", "n");
                assertThat(output()).contains("## 최종 수익" + NEXT_LINE
                    + "딜러: 10000" + NEXT_LINE
                    + "woni: -10000");
            },
            List.of(new Card(HEART, KING), new Card(HEART, SIX),
                new Card(SPADE, SEVEN), new Card(HEART, TEN))
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[] {});
    }
}
