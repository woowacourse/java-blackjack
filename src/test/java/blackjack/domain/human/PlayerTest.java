package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Fixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private final Fixture fx = new Fixture();

    @Test
    @DisplayName("참여자 객체 생성 기능 테스트")
    public void createTest() {
        assertThat(fx.POBI.getName())
                .isEqualTo("pobi");
    }

    @Test
    @DisplayName("참여자 객체 카드 추가 기능 테스트")
    public void addCardTest() {
        fx.POBI.addCard(fx.ACE);
        fx.POBI.addCard(fx.TEN);
        assertThat(fx.POBI.getPoint())
                .isEqualTo(21);
    }

    @Test
    @DisplayName("참여자 객체 카드 2개인지 확인 기능 테스트")
    public void isThatSize() {
        fx.HUNCH.addCard(fx.TEN);
        fx.HUNCH.addCard(fx.NINE);
        assertThat(fx.HUNCH.hasCardSizeOf(2))
                .isTrue();
    }

    @Test
    @DisplayName("카드모음 포인트 올바른지 테스트")
    public void equalPointTest() {
        fx.JASON.addCard(fx.EIGHT);
        fx.JASON.addCard(fx.ACE);
        assertThat(fx.JASON.getPoint())
                .isEqualTo(19);
    }
}
