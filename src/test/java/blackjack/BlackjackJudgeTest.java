package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings("NonAsciiCharacters")
public class BlackjackJudgeTest {

    @Test
    void 패배하는_경우_테스트() {
        Result result = BlackjackJudge.judge(new Cards("1다이아몬드", "2하트"), new Cards("3클로버", "4하트"));
        assertThat(result).isEqualTo(Result.LOSS);
    }

    @ParameterizedTest()
    @MethodSource("provideCards")
    void 이기는_경우_테스트(List<String> cards1, List<String> cards2) {
        Result result = BlackjackJudge.judge(new Cards(cards1.toArray(String[]::new)),
            new Cards(cards2.toArray(String[]::new)));
        assertThat(result).isEqualTo(Result.WIN);
    }

    private static Stream<Arguments> provideCards() {
        return Stream.of(
            Arguments.of(List.of("3클로버", "4하트"), List.of("1다이아몬드", "2하트")),
            Arguments.of(List.of("Q클로버", "J하트"), List.of("K다이아몬드", "2하트")),
            Arguments.of(List.of("Q클로버", "J하트"), List.of("A다이아몬드", "2하트"))
        );
    }

    @Test
    void 버스트가_발생하는_경우_테스트1() {
        Result result = BlackjackJudge
            .judge(new Cards("Q클로버", "J하트", "K클로버"), new Cards("A다이아몬드", "2하트"));
        assertThat(result).isEqualTo(Result.LOSS);
    }

    @Test
    void 버스트가_발생하는_경우_테스트2() {
        Result result = BlackjackJudge
            .judge(new Cards("A다이아몬드", "2하트"), new Cards("Q클로버", "J하트", "K클로버"));
        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    void 버스트가_발생하는_경우_테스트3() {
        Result result = BlackjackJudge
            .judge(new Cards("Q클로버", "J하트", "K클로버", "5하트"), new Cards("Q클로버", "J하트", "K클로버"));
        assertThat(result).isEqualTo(Result.DRAW);
    }

    @Test
    void ACE를_1로_처리해야_이기는_경우_테스트() {
        Result result = BlackjackJudge
            .judge(new Cards("A다이아몬드", "J하트", "Q클로버"), new Cards("Q클로버", "J하트"));
        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    void 서로_점수가_같아_무승부인_경우() {
        Result result = BlackjackJudge
            .judge(new Cards("8다이아몬드", "4클로버"), new Cards("6다이아몬드", "6하트"));
        assertThat(result).isEqualTo(Result.DRAW);
    }
}
