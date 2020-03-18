package domain.player;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerTest {
    @DisplayName("생성자 테스트")
    @Test
    void constructorTest() {
        Assertions.assertThat(new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.TWO, CardSuitSymbol.DIAMOND)
        )), 10_000)).isInstanceOf(Player.class);
    }

    @DisplayName("플레이어가 21 미만의 카드 값을 갖고 있을 때 카드를 더 받겠다고 반환하는지 테스트")
    @Test
    void isHitUnderTwentyOneTest() {
        Player player = new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.TWO, CardSuitSymbol.DIAMOND)
        )), 10_000);

        Assertions.assertThat(player.isHit()).isTrue();
    }

    @DisplayName("플레이어가 21 이상의 카드 값을 갖고 있을 때 카드를 더 받지 않겠다고 반환하는지 테스트")
    @Test
    void isHitOverTwentyOneTest() {
        Player player = new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.DIAMOND),
                Card.of(CardNumber.JACK, CardSuitSymbol.DIAMOND)
        )), 10_000);

        Assertions.assertThat(player.isHit()).isTrue();
    }
}
