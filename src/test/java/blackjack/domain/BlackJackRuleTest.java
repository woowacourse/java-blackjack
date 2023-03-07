package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestDeckGenerator;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackRuleTest {

    @Test
    @DisplayName("사용자의 점수 계산 테스트")
    void getScoreByRuleTest() {
        final User user = new Player("필립",
                new CardGroup(new Card(CardShape.HEART, CardNumber.ACE)
                        , new Card(CardShape.SPADE, CardNumber.ACE)));

        int score = BlackJackRule.getScore(user);

        assertThat(score).isEqualTo(12);
    }

    @Test
    @DisplayName("Bust 확인 테스트")
    void isBustTest() {
        final User user = new Player("필립",
                new CardGroup(new Card(CardShape.HEART, CardNumber.JACK)
                        , new Card(CardShape.SPADE, CardNumber.QUEEN)));

        user.drawCard(new Deck(new TestDeckGenerator(List.of(new Card(CardShape.HEART, CardNumber.FOUR)))));
        boolean isBust = BlackJackRule.isBust(user);

        assertThat(isBust).isTrue();
    }

    @Test
    @DisplayName("유저의 점수가 BlackJackNumber인지 확인하는 기능 테스트")
    void isBlackJackScoreTest() {
        final User user = new Player("필립", new CardGroup(
                new Card(CardShape.CLOVER, CardNumber.ACE), new Card(CardShape.HEART, CardNumber.TEN)));

        boolean isBlackJack = BlackJackRule.isBlackJackScore(user);

        assertThat(isBlackJack).isTrue();
    }
}
