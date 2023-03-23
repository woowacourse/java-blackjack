package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("이름 초기화 테스트")
    void nameInitTest() {
        final Name name = new TestName("test");
        assertThat(name.getValue()).isEqualTo("test");
    }

    @Test
    @DisplayName("이름과 똑같은 String이 들어오는지 확인")
    void isSameNameTest() {
        final Name name = new TestName("test");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(name.isSame(name)).isTrue();
            softly.assertThat(name.isSame(new TestName("홍실"))).isFalse();
        });
    }

    static class TestName extends Name {
        protected TestName(final String name) {
            super(name);
        }
    }
}
