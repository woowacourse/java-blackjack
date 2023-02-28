package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    @Test
    @DisplayName("비교 대상 플레이어의 카드 값보다 크면 양수이다.")
    void compareToTestWhenBigger() {
        CardPool cardPool = new CardPool(List.of(
                new Card(CardType.HEART, CardNumber.SEVEN)
        ));
        CardPool otherCardPool = new CardPool(List.of(
                new Card(CardType.HEART, CardNumber.SIX)
        ));

        Player player = new Player("maco", cardPool);
        Player otherPlayer = new Player("teo", otherCardPool);

        assertThat(player.compareTo(otherPlayer)).isGreaterThan(0);
    }

    @Test
    @DisplayName("비교 대상 플레이어의 카드 값보다 작으면 음수이다.")
    void hasBiggerSumThanTestWhenSmall() {
        CardPool cardPool = new CardPool(List.of(
                new Card(CardType.HEART, CardNumber.FIVE)
        ));
        CardPool otherCardPool = new CardPool(List.of(
                new Card(CardType.HEART, CardNumber.SIX)
        ));

        Player player = new Player("maco", cardPool);
        Player otherPlayer = new Player("teo", otherCardPool);

        assertThat(player.compareTo(otherPlayer)).isLessThan(0);
    }

    @Test
    @DisplayName("비교 대상 플레이어의 카드 값보다 같으면 0이다.")
    void hasSameSumWithTestWhenSmall() {
        CardPool cardPool = new CardPool(List.of(
                new Card(CardType.HEART, CardNumber.FIVE)
        ));
        CardPool otherCardPool = new CardPool(List.of(
                new Card(CardType.HEART, CardNumber.FIVE)
        ));

        Player player = new Player("maco", cardPool);
        Player otherPlayer = new Player("teo", otherCardPool);

        assertThat(player.compareTo(otherPlayer)).isEqualTo(0);
    }
}