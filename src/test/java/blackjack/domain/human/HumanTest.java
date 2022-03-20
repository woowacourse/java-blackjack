package blackjack.domain.human;

import static blackjack.domain.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Fixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HumanTest {
    Fixtures fx = new Fixtures();

    @Test
    @DisplayName("카드 추가 기능 테스트")
    void addCard() {
        fx.POBI.addCard(TEN);
        assertThat(fx.POBI.getRawCards().size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("버스트 여부 확인 기능 true 테스트")
    public void isBustTest() {
        fx.POBI.addCard(TEN);
        fx.POBI.addCard(EIGHT);
        fx.POBI.addCard(NINE);

        assertThat(fx.POBI.isBust())
                .isTrue();
    }

    @Test
    @DisplayName("첫 카드 한장 리턴하는 기능 테스트")
    void getInitCardTest() {
        // when
        fx.POBI.addCard(TEN);
        fx.POBI.addCard(NINE);

        // then
        assertThat(fx.POBI.getInitCard())
                .isEqualTo(TEN);
    }

    @Test
    @DisplayName("버스트 여부 확인 기능 false 테스트")
    public void isBustFalseTest() {
        fx.POBI.addCard(TEN);
        fx.POBI.addCard(ACE);
        assertThat(fx.POBI.isBust())
                .isFalse();
    }

    @Test
    @DisplayName("득점한 포인트 리턴 기능 테스트")
    void getPointTest() {
        fx.POBI.addCard(TEN);
        fx.POBI.addCard(NINE);
        assertThat(fx.POBI.getPoint())
                .isEqualTo(19);
    }

    @Test
    @DisplayName("이름 리턴 기능 테스트")
    void getName() {
        assertThat(fx.POBI.getName())
                .isEqualTo("pobi");
    }

    @Test
    @DisplayName("보유카드 리턴 기능 테스트")
    void getCards() {
        fx.POBI.addCard(ACE);
        fx.POBI.addCard(NINE);
        assertThat(fx.POBI.getRawCards())
                .containsOnly(ACE, NINE);
    }
}
