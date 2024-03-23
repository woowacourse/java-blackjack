package blackjackgame.model.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjackgame.model.card.Card;
import blackjackgame.model.card.CardNumber;
import blackjackgame.model.card.CardShape;
import blackjackgame.model.card.Cards;
import blackjackgame.model.card.StaticCardDispenser;
import blackjackgame.model.participants.player.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어의 카드 합계가 21점 이하이면 true를 반환한다")
    @Test
    void testCanAddCard() {
        Cards cards = createCanAddCardTestCards();
        Player player = new Player("lily", cards);
        assertThat(player.isNotBust()).isTrue();
    }

    private Cards createCanAddCardTestCards() {
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        StaticCardDispenser cardJackHeart = new StaticCardDispenser(CardNumber.JACK, CardShape.HEART);
        return new Cards(List.of(new Card(cardAceHeart), new Card(cardJackHeart)));
    }

    @DisplayName("플레이어의 카드 합계가 22점 이상이면 false를 반환한다")
    @Test
    void testCanNotAddCard() {
        Cards cards = createCanNotAddCardTestCards();
        Player player = new Player("lily", cards);
        assertThat(player.isNotBust()).isFalse();
    }

    private Cards createCanNotAddCardTestCards() {
        StaticCardDispenser cardTenHeart = new StaticCardDispenser(CardNumber.TEN, CardShape.HEART);
        StaticCardDispenser cardJackHeart = new StaticCardDispenser(CardNumber.JACK, CardShape.HEART);
        StaticCardDispenser cardTwoHeart = new StaticCardDispenser(CardNumber.TWO, CardShape.HEART);
        return new Cards(
                List.of(new Card(cardTenHeart), new Card(cardJackHeart), new Card(cardTwoHeart))
        );
    }

    @DisplayName("카드를 추가로 받을 수 있으면 카드 1장 획득")
    @Test
    void shouldAddCardWhenAllowed() {
        StaticCardDispenser cardTwoDia = new StaticCardDispenser(CardNumber.TWO, CardShape.DIAMOND);
        Cards cards = createShouldAddCardWhenAllowedTestCards();
        Player player = new Player("lily", cards);
        Card card = new Card(cardTwoDia);
        Player updatedPlayer = player.withAdditionalCard(card);
        assertThat(updatedPlayer.cardsSize()).isEqualTo(3);
    }

    private Cards createShouldAddCardWhenAllowedTestCards() {
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        StaticCardDispenser cardJackHeart = new StaticCardDispenser(CardNumber.JACK, CardShape.HEART);
        return new Cards(
                List.of(new Card(cardAceHeart), new Card(cardJackHeart))
        );
    }
}
