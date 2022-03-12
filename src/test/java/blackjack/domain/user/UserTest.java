package blackjack.domain.user;

import blackjack.domain.card.Deck;
import blackjack.domain.strategy.ShuffledDeckGenerateStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @DisplayName("게임 시작시 User는 카드를 2장씩 받는다.")
    @Test
    public void testDrawInitCard() {
        //given
        Deck deck = new Deck(new ShuffledDeckGenerateStrategy());
        User user = Player.from("pobi");

        //when
        user.drawInitCards(deck);

        //then
        Assertions.assertThat(user.showCards().size()).isEqualTo(2);
    }
}
