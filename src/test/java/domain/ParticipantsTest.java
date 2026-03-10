package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import util.Validator;

public class ParticipantsTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Test
    void 참가자_수_초과_예외_테스트() {
        // given
        String participantsName = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q";

        // when & then
        assertThatThrownBy(() ->
                new Participants(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    void 빈_참가자_이름_예외_테스트() {
        // given
        String participantsName = "";

        // when & then
        assertThatThrownBy(() ->
                new Participants(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    void 참가자_이름_숫자_예외_테스트() {
        // given
        String participantsName = "123";

        // when & then
        assertThatThrownBy(() ->
                new Participants(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    void 쉼표_외의_특수문자_예외_테스트() {
        // given
        String participantsName = "영기:라이";

        // when & then
        assertThatThrownBy(() ->
                new Participants(participantsName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }
}
