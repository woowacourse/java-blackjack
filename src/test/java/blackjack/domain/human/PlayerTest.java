package blackjack.domain.human;

import static blackjack.domain.Fixtures.ACE;
import static blackjack.domain.Fixtures.EIGHT;
import static blackjack.domain.Fixtures.NINE;
import static blackjack.domain.Fixtures.TEN;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Fixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private final Fixtures fx = new Fixtures();

    @Test
    @DisplayName("참여자 객체 생성 기능 테스트")
    public void createTest() {
        assertThat(fx.POBI.getName())
                .isEqualTo("pobi");
    }

    @Test
    @DisplayName("참여자 객체 카드 추가 기능 테스트")
    public void addCardTest() {
        fx.POBI.addCard(ACE);
        fx.POBI.addCard(TEN);
        assertThat(fx.POBI.getPoint())
                .isEqualTo(21);
    }

    @Test
    @DisplayName("참여자 객체 카드 2개인지 확인 기능 테스트")
    public void isThatSize() {
        fx.HUNCH.addCard(TEN);
        fx.HUNCH.addCard(NINE);
        assertThat(fx.HUNCH.isInitState())
                .isTrue();
    }

    @Test
    @DisplayName("카드모음 포인트 올바른지 테스트")
    public void equalPointTest() {
        fx.JASON.addCard(EIGHT);
        fx.JASON.addCard(ACE);
        assertThat(fx.JASON.getPoint())
                .isEqualTo(19);
    }
}
