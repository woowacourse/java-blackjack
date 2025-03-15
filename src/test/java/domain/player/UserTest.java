package domain.player;

import domain.card.Deck;
import domain.card.DeckGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void 초기_카드_분배_시_유저는_2장을_받는다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        User user = User.of("새로이", 1000);

        // when
        user.drawInitialCards(deck);

        // when & then
        Assertions.assertThat(user.cards().size())
                .isEqualTo(2);
    }

    @Test
    void 초기_카드_공개_시_딜러는_카드_한장만_공개한다() {
        // given
        Deck deck = DeckGenerator.generateDeck();
        User user = User.of("라젤", 2000);
        user.drawInitialCards(deck);

        // when
        user.openInitialCards();

        // when & then
        Assertions.assertThat(user.cards().openedCards().size())
                .isEqualTo(2);
    }
}
