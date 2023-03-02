package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackJackRuleTest {

    @Test
    @DisplayName("사용자의 점수 계산 테스트")
    void getScoreByRuleTest() {
        final User user = new Player("필립",
                new CardGroup(new Card(CardShape.HEART, CardNumber.ACE)
                        , new Card(CardShape.SPADE, CardNumber.ACE)));

        int score = BlackJackRule.getScore(user);

        Assertions.assertThat(score).isEqualTo(12);
    }

    @Test
    @DisplayName("Bust 확인 테스트")
    void isBustTest() {
        final User user = new Player("필립",
                new CardGroup(new Card(CardShape.HEART, CardNumber.JACK)
                        , new Card(CardShape.SPADE, CardNumber.QUEEN)));

        user.drawCard(new Deck(new TestDeckGenerator(List.of(new Card(CardShape.HEART, CardNumber.FOUR)))));
        boolean isBust = BlackJackRule.isBust(user);

        Assertions.assertThat(isBust).isTrue();
    }

}
