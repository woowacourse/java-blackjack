package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BlackJackRuleTest {
    @DisplayName("유저와 대답에 따라 반환하는 Boolean 값 테스트")
    @Test
    void isHitTest() {
        Player blackJackPlayer = new Player("pobi", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB))));
        Player overBlackJackPlayer = new Player("json", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.SEVEN, CardSuitSymbol.CLUB),
                Card.of(CardNumber.KING, CardSuitSymbol.SPACE),
                Card.of(CardNumber.KING, CardSuitSymbol.DIAMOND))));
        Player hitPlayer = new Player("lavine", new ArrayList<>(Arrays.asList(
                Card.of(CardNumber.KING, CardSuitSymbol.SPACE),
                Card.of(CardNumber.TWO, CardSuitSymbol.SPACE))));
        BlackJackRule blackJackRule = new BlackJackRule();

        Assertions.assertThat(blackJackRule.isHit(blackJackPlayer)).isFalse();
        Assertions.assertThat(blackJackRule.isHit(overBlackJackPlayer)).isFalse();
        Assertions.assertThat(blackJackRule.isHit(hitPlayer)).isTrue();
    }
}
