package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardpack.CardPack;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {

    @Test
    void 카드를_뽑을_수_있다() {
        // given
        Player player = new Player("주노");
        CardPack cardPack = new CardPack();

        // when
        player.drawCard(cardPack);

        // then
        Assertions.assertThat(player.getCards())
                .containsExactly(Card.of(CardNumber.KING, CardShape.CLOVER));
    }
}
