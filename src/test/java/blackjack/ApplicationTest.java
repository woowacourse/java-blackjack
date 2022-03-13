package blackjack;

import static blackjack.domain.card.CardNumber.A;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.FOUR;
import static blackjack.domain.card.CardNumber.JACK;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardPattern.HEART;
import static blackjack.domain.card.CardPattern.SPADE;
import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

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
    void 딜러는_첫드로우를_한장만_보여준다() {
        assertShuffleTest(
                () -> {
                    run("ori", "n");
                    assertThat(output()).contains("딜러와 ori에게 2장을 나누었습니다.\n"
                            + "딜러: K하트\n"
                            + "ori 카드: K스페이드, Q스페이드");
                },
                Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN),
                        Card.of(HEART, KING), Card.of(HEART, SEVEN))
        );
    }

    @Test
    void 유저가_버스트면_항상_진다() {
        assertShuffleTest(
                () -> {
                    run("ori", "y");
                    assertThat(output()).contains("## 최종 승패\n"
                            + "딜러: 1승\n"
                            + "ori: 패");
                },
                Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, QUEEN),
                        Card.of(HEART, KING), Card.of(HEART, SIX),
                        Card.of(SPADE, JACK),
                        Card.of(HEART, QUEEN))
        );
    }

    @Test
    void 유저와_딜러_모두_블랙잭이면_무승부한다() {
        assertShuffleTest(
                () -> {
                    run("ori", "n");
                    assertThat(output()).contains("## 최종 승패\n"
                            + "딜러: 1무\n"
                            + "ori: 무");
                },
                Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A),
                        Card.of(HEART, KING), Card.of(HEART, A))
        );
    }

    @Test
    void 유저만_블랙잭이면_유저가_이긴다() {
        assertShuffleTest(
                () -> {
                    run("ori", "n");
                    assertThat(output()).contains("## 최종 승패\n"
                            + "딜러: 1패\n"
                            + "ori: 승");
                },
                Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, A),
                        Card.of(HEART, KING), Card.of(HEART, SIX),
                        Card.of(HEART, FIVE))
        );
    }

    @Test
    void 딜러만_버스트면_유저가_이긴다() {
        assertShuffleTest(
                () -> {
                    run("ori", "n");
                    assertThat(output()).contains("## 최종 승패\n"
                            + "딜러: 1패\n"
                            + "ori: 승");
                },
                Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, SEVEN),
                        Card.of(HEART, KING), Card.of(HEART, SIX),
                        Card.of(HEART, JACK))
        );
    }

    @Test
    void 버스트와_블랙잭_모두_아닌_경우_21에_가까운_사람이_이긴다() {
        assertShuffleTest(
                () -> {
                    run("ori", "y", "n");
                    assertThat(output()).contains("## 최종 승패\n"
                            + "딜러: 1승\n"
                            + "ori: 패");
                },
                Arrays.asList(Card.of(SPADE, KING), Card.of(SPADE, SEVEN),
                        Card.of(HEART, KING), Card.of(HEART, SIX),
                        Card.of(SPADE, TWO), Card.of(HEART, FOUR))
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}