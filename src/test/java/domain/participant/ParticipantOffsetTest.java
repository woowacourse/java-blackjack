package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParticipantOffsetTest {

    @ParameterizedTest(name = "mapToIndexFromOrder()는 순서를 건네주면 인덱스로 변환해 반환한다")
    @CsvSource(value = {"DEALER:0:0", "PLAYER:0:1"}, delimiter = ':')
    void mapToIndexFromOrder_givenOrder_thenReturnIndex(final ParticipantOffset offset,
            final int participantOrder, final int expected) {
        // when
        final int actual = offset.mapToIndexFromOrder(participantOrder);

        // then
        assertThat(actual)
                .isSameAs(expected);
    }
}
