package blackjack.domain.player;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_JACK;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.state.BlackJack;
import blackjack.domain.state.Bust;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblerTest {

    private static Gambler gambler;

    @BeforeEach
    void initGambler() {
        gambler = Gambler.of(Name.of("test"), 1000);
    }

    @Test
    @DisplayName("플레이어 생성 테스트")
    public void createGambler() {
        assertThat(gambler.getName()).isEqualTo("test");
        assertThat(gambler.getMoney()).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어 카드 추가 테스트")
    public void addCardGambler() {
        gambler.addCard(SPADE_ACE);
        gambler.addCard(SPADE_JACK);

        assertThat(gambler.getPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("카드 점수가 21점일 때 Hit가 아닌지 테스트")
    public void isNonHitTest() {
        gambler.addCard(SPADE_ACE);
        gambler.addCard(SPADE_JACK);

        assertThat(gambler.isHit()).isEqualTo(false);
    }

    @Test
    @DisplayName("카드 점수가 21점보다 적을 때 Hit인지 테스트")
    public void isHitTest() {
        gambler.addCard(SPADE_TWO);
        gambler.addCard(SPADE_JACK);

        assertThat(gambler.isHit()).isEqualTo(true);
    }

    @Test
    @DisplayName("카드 지급 여부 질문이 첫번째인지")
    public void isFirstQuestion() {
        gambler.addCard(SPADE_TWO);
        gambler.addCard(SPADE_JACK);

        assertThat(gambler.isFirstQuestion()).isEqualTo(true);
    }

    @Test
    @DisplayName("카드 지급 여부 질문이 첫번째가 아닌지 ")
    public void isNonFirstQuestion() {
        gambler.addCard(SPADE_TWO);
        gambler.addCard(SPADE_JACK);
        gambler.addCard(SPADE_ACE);

        assertThat(gambler.isFirstQuestion()).isEqualTo(false);
    }

    @Test
    @DisplayName("참여자 Hit일 경우 테스트")
    public void isHit() {
        gambler.addCard(SPADE_ACE);
        gambler.addCard(SPADE_JACK);

        assertThat(gambler.isHit()).isEqualTo(false);
    }

    @Test
    @DisplayName("참여자 Hit가 아닐 경우 테스트")
    public void isNonHit() {
        gambler.addCard(SPADE_TWO);
        gambler.addCard(SPADE_JACK);

        assertThat(gambler.isHit()).isEqualTo(true);
    }

    @Test
    @DisplayName("참여자 BlackJack 테스트")
    public void isBlackJack() {
        gambler.addCard(SPADE_ACE);
        gambler.addCard(SPADE_JACK);

        assertThat(gambler.getState()).isInstanceOf(BlackJack.class);
    }

    @Test
    @DisplayName("참여자 Bust 테스트")
    public void isBustJack() {
        gambler.addCard(SPADE_TWO);
        gambler.addCard(SPADE_JACK);
        gambler.addCard(SPADE_JACK);

        assertThat(gambler.getState()).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("참여자 BlackJack일 때 카드 뽑을 경우 예외 발생 테스트")
    public void isStayJack() {
        gambler.addCard(SPADE_ACE);
        gambler.addCard(SPADE_JACK);

        assertThatThrownBy(() -> gambler.addCard(SPADE_JACK))
            .isInstanceOf(IllegalStateException.class);
    }
}
