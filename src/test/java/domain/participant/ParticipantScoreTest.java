package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParticipantScoreTest {

    @Test
    @DisplayName("defaultOf()는 호출하면 점수가 0인 ParticipantScore를 반환한다")
    void defaultOf_whenCall_thenReturnScoreZeroParticipantScore() {
        // when
        final ParticipantScore actual = ParticipantScore.defaultOf();

        // then
        final ParticipantScore expected = ParticipantScore.scoreOf(0);

        assertThat(expected)
                .isEqualTo(actual);
    }

    @Test
    @DisplayName("scoreOf()는 점수를 건네주면 점수를 관리하는 ParticipantScore를 생성한다")
    void scoreOf_givenScore_thenReturnParticipantScore() {
        final ParticipantScore participantScore = assertDoesNotThrow(() -> ParticipantScore.scoreOf(1));

        assertThat(participantScore)
                .isExactlyInstanceOf(ParticipantScore.class);
    }

    @Test
    @DisplayName("add()는 더할 점수를 건네주면 더한 점수는 관리하는 ParticipantScore를 생성한다")
    void add_givenOtherParticipantScore_thenReturnAddResult() {
        // given
        final ParticipantScore one = ParticipantScore.scoreOf(1);
        final ParticipantScore two = ParticipantScore.scoreOf(2);

        // when
        final ParticipantScore actual = two.add(one);

        // then
        final ParticipantScore expected = ParticipantScore.scoreOf(3);

        assertThat(actual)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "processIncludeAce()는 호출하면 점수에 맞춰 Ace의 값을 1 또는 11로 설정한다")
    @CsvSource(value = {"11:21", "12:12"}, delimiter = ':')
    void processIncludeAce_whenCall_thenReturnAfterProcessAceNumber(final int score, final int expectedScore) {
        // given
        final ParticipantScore target = ParticipantScore.scoreOf(score);

        // when
        final ParticipantScore actual = target.processIncludeAce();

        // then
        final ParticipantScore expected = ParticipantScore.scoreOf(expectedScore);

        assertThat(actual)
                .isEqualTo(expected);
    }

    @ParameterizedTest(name = "checkGreaterThan()은 비교할 점수를 건네주면 점수보다 큰지 여부를 반환한다")
    @CsvSource(value = {"10:11:false", "11:10:true"}, delimiter = ':')
    void checkGreaterThan_givenTarget_thenReturnGreater(final int firstScore, final int secondScore, final boolean expected) {
        // given
        final ParticipantScore first = ParticipantScore.scoreOf(firstScore);
        final ParticipantScore second = ParticipantScore.scoreOf(secondScore);

        // when
        final boolean actual = first.checkGreaterThan(second);

        // then
        assertThat(actual)
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "checkBust()는 점수의 합이 21을 초과하는지 여부를 반환한다")
    @CsvSource(value = {"21:false", "22:true"}, delimiter = ':')
    void checkBust_whenCall_thenReturnIsBust(final int score, final boolean expected) {
        ParticipantScore participantScore = ParticipantScore.scoreOf(score);

        boolean actual = participantScore.checkBust();

        assertThat(actual)
                .isSameAs(expected);
    }

    @ParameterizedTest(name = "checkBlackJack()은 점수의 합이 21인지 여부를 반환한다")
    @CsvSource(value = {"21:true", "5:false"}, delimiter = ':')
    void checkBlackJack_whenCall_thenReturnIsBlackJack(final int score, final boolean expected) {
        ParticipantScore participantScore = ParticipantScore.scoreOf(score);

        boolean actual = participantScore.checkBlackJack();

        assertThat(actual)
                .isSameAs(expected);
    }
}
