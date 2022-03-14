package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackJackTest {

    @Test
    @DisplayName("개별 플레이어에게 카드를 전달한다.")
    public void playGameOnePlayerTest() {
        BlackJack blackJack = new BlackJack(new String[]{"기영이", "기철이"});
        int beforeUserCardSum = getUserCardSum(blackJack, "기영이");
        blackJack.playGameOnePlayer("기영이");
        int afterUserCardSum = getUserCardSum(blackJack, "기영이");
        assertThat(beforeUserCardSum < afterUserCardSum).isTrue();
    }

    @Test
    @DisplayName("개별 플레이어에게 카드를 전달하더라도 기존 플레이어는 영향이 없다.")
    public void playGameOnePlayerNotAffectAnotherPlayerTest() {
        BlackJack blackJack = new BlackJack(new String[]{"기영이", "기철이"});
        int beforeUserCardSum = getUserCardSum(blackJack, "기철이");
        blackJack.playGameOnePlayer("기영이");
        int afterUserCardSum = getUserCardSum(blackJack, "기철이");
        assertThat(beforeUserCardSum == afterUserCardSum).isTrue();
    }

    @Test
    @DisplayName("점수의 합이 21을 넘지 않는 경우를 확인한다.")
    public void checkLimitInnerTest() {
        BlackJack blackJack = new BlackJack(new String[]{"기영이", "기철이"});
        assertThat(blackJack.checkLimit("기영이")).isTrue();
    }

    @Test
    @DisplayName("점수의 합이 21을 넘는 경우를 확인한다.")
    public void checkLimitExceedTest() {
        BlackJack blackJack = new BlackJack(new String[]{"기영이", "기철이"});
        for (int i = 0; i < 20; i++) {
            blackJack.playGameOnePlayer("기영이");
        }
        assertThat(blackJack.checkLimit("기영이")).isFalse();
    }

    private int getUserCardSum(BlackJack blackJack, String name) {
        return blackJack.getCardResults().stream()
                .filter(result -> result.getName().equals(name))
                .findAny()
                .get()
                .getCardSum();
    }
}
