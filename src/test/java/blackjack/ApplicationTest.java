package blackjack;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.FOUR;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.HEARTS;
import static blackjack.domain.card.Suit.SPADES;
import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends NsTest {

    @Test
    void 잘못된_명령어에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("ori, rio", "g"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 중복된_사람_이름에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("ori, ori", "1000", "1000"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 사람_수가_0에_대한_예외_처리() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(","))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 딜러는_첫드로우를_한장만_보여준다() {
        assertShuffleTest(
                () -> {
                    run("ori", "1000", "n");
                    assertThat(output()).contains("딜러와 ori에게 2장을 나누었습니다.\n"
                            + "딜러: K하트\n"
                            + "ori 카드: K스페이드, Q스페이드");
                },
                Arrays.asList(Card.of(HEARTS, KING), Card.of(HEARTS, SEVEN),
                        Card.of(SPADES, KING), Card.of(SPADES, QUEEN))
        );
    }

    @Test
    void 유저가_버스트면_수익이_마이너스_1이다() {
        assertShuffleTest(
                () -> {
                    run("ori", "10000", "y");
                    assertThat(output()).contains("## 최종 수익\n"
                            + "딜러: 10000\n"
                            + "ori: -10000");
                },
                Arrays.asList(Card.of(HEARTS, KING), Card.of(HEARTS, SIX),
                        Card.of(SPADES, KING), Card.of(SPADES, QUEEN),
                        Card.of(SPADES, JACK),
                        Card.of(HEARTS, QUEEN))
        );
    }

    @Test
    void 유저와_딜러_모두_블랙잭이면_수익이_0이다() {
        assertShuffleTest(
                () -> {
                    run("ori", "10000", "n");
                    assertThat(output()).contains("## 최종 수익\n"
                            + "딜러: 0\n"
                            + "ori: 0");
                },
                Arrays.asList(Card.of(SPADES, KING), Card.of(SPADES, A),
                        Card.of(HEARTS, KING), Card.of(HEARTS, A))
        );
    }

    @Test
    void 유저만_블랙잭이면_유저가_수익이_1_5이다() {
        assertShuffleTest(
                () -> {
                    run("ori", "10000", "n");
                    assertThat(output()).contains("## 최종 수익\n"
                            + "딜러: -15000\n"
                            + "ori: 15000");
                },
                Arrays.asList(Card.of(HEARTS, KING), Card.of(HEARTS, SIX),
                        Card.of(SPADES, KING), Card.of(SPADES, A),
                        Card.of(HEARTS, FIVE))
        );
    }

    @Test
    void 딜러만_버스트면_유저가_수익이_1이다() {
        assertShuffleTest(
                () -> {
                    run("ori", "10000", "n");
                    assertThat(output()).contains("## 최종 수익\n"
                            + "딜러: -10000\n"
                            + "ori: 10000");
                },
                Arrays.asList(Card.of(HEARTS, KING), Card.of(HEARTS, SIX),
                        Card.of(SPADES, KING), Card.of(SPADES, SEVEN),
                        Card.of(HEARTS, JACK))
        );
    }

    @Test
    void 버스트와_블랙잭_모두_아닌_경우_21에_가까운_사람이_수익이_1이다() {
        assertShuffleTest(
                () -> {
                    run("ori", "10000", "y", "n");
                    assertThat(output()).contains("## 최종 수익\n"
                            + "딜러: 10000\n"
                            + "ori: -10000");
                },
                Arrays.asList(Card.of(HEARTS, KING), Card.of(HEARTS, SIX),
                        Card.of(SPADES, KING), Card.of(SPADES, SEVEN),
                        Card.of(SPADES, TWO), Card.of(HEARTS, FOUR))
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
