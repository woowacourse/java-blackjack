package blackjack;

import static blackjack.domain.card.Denomination.A;
import static blackjack.domain.card.Denomination.EIGHT;
import static blackjack.domain.card.Denomination.FIVE;
import static blackjack.domain.card.Denomination.JACK;
import static blackjack.domain.card.Denomination.KING;
import static blackjack.domain.card.Denomination.NINE;
import static blackjack.domain.card.Denomination.QUEEN;
import static blackjack.domain.card.Denomination.SEVEN;
import static blackjack.domain.card.Denomination.SIX;
import static blackjack.domain.card.Denomination.TEN;
import static blackjack.domain.card.Denomination.THREE;
import static blackjack.domain.card.Denomination.TWO;
import static blackjack.domain.card.Suit.CLUBS;
import static blackjack.domain.card.Suit.DIAMONDS;
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
                assertThatThrownBy(() -> runException("ori, ori"))
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
                    run("ori, huni", "1000", "1000", "n", "n");
                    assertThat(output()).contains(String.format("%s%n%s%n%s%n%s",
                            "딜러와 ori, huni에게 2장을 나누었습니다.", "딜러: K하트", "ori 카드: K스페이드, Q스페이드",
                            "huni 카드: J스페이드, 10스페이드"));
                },
                Arrays.asList(new Card(HEARTS, KING), new Card(HEARTS, SEVEN),
                        new Card(SPADES, KING), new Card(SPADES, QUEEN),
                        new Card(SPADES, JACK), new Card(SPADES, TEN))
        );
    }

    @Test
    void 유저가_버스트면_수익이_마이너스_1이다() {
        assertShuffleTest(
                () -> {
                    run("ori, huni", "10000", "1000", "y", "y");
                    assertThat(output()).contains(String.format("%s%n%s%n%s%n%s",
                            "## 최종 수익", "딜러: 11000", "ori: -10000", "huni: -1000"));
                },
                Arrays.asList(new Card(HEARTS, KING), new Card(HEARTS, SIX),
                        new Card(SPADES, KING), new Card(SPADES, QUEEN),
                        new Card(CLUBS, KING), new Card(CLUBS, QUEEN),
                        new Card(SPADES, JACK),
                        new Card(CLUBS, JACK),
                        new Card(HEARTS, QUEEN))
        );
    }

    @Test
    void 유저와_딜러_모두_블랙잭이면_수익이_0이다() {
        assertShuffleTest(
                () -> {
                    run("ori, huni", "10000", "1000", "n", "n");
                    assertThat(output()).contains(String.format("%s%n%s%n%s%n%s",
                            "## 최종 수익", "딜러: 0", "ori: 0", "huni: 0"));
                },
                Arrays.asList(new Card(SPADES, KING), new Card(SPADES, A),
                        new Card(HEARTS, KING), new Card(HEARTS, A),
                        new Card(CLUBS, KING), new Card(CLUBS, A))
        );
    }

    @Test
    void 유저만_블랙잭이면_유저가_수익이_1_5이다() {
        assertShuffleTest(
                () -> {
                    run("ori, huni", "10000", "1000", "n", "n");
                    assertThat(output()).contains(String.format("%s%n%s%n%s%n%s",
                            "## 최종 수익", "딜러: -16500", "ori: 15000", "huni: 1500"));
                },
                Arrays.asList(new Card(HEARTS, KING), new Card(HEARTS, SIX),
                        new Card(SPADES, KING), new Card(SPADES, A),
                        new Card(CLUBS, KING), new Card(CLUBS, A),
                        new Card(HEARTS, FIVE))
        );
    }

    @Test
    void 딜러만_버스트면_유저가_수익이_1이다() {
        assertShuffleTest(
                () -> {
                    run("ori, huni", "10000", "1000", "n", "n");
                    assertThat(output()).contains(String.format("%s%n%s%n%s%n%s",
                            "## 최종 수익", "딜러: -11000", "ori: 10000", "huni: 1000"));
                },
                Arrays.asList(new Card(HEARTS, KING), new Card(HEARTS, SIX),
                        new Card(SPADES, KING), new Card(SPADES, SEVEN),
                        new Card(SPADES, TWO), new Card(SPADES, THREE),
                        new Card(HEARTS, JACK))
        );
    }

    @Test
    void 버스트와_블랙잭_모두_아닌_경우_21에_가까운_사람이_수익이_1이다() {
        assertShuffleTest(
                () -> {
                    run("pobi, json", "10000", "20000", "y", "n", "n");
                    assertThat(output()).contains("딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20");
                },
                Arrays.asList(new Card(DIAMONDS, THREE), new Card(CLUBS, NINE),
                        new Card(HEARTS, TWO), new Card(SPADES, EIGHT),
                        new Card(CLUBS, SEVEN), new Card(SPADES, KING),
                        new Card(CLUBS, A),
                        new Card(DIAMONDS, EIGHT))
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
