package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.util.NoviceShuffleStrategy;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserTest {

    @Test
    void 유저는_카드팩에서_카드를_뽑는다() {

        // given
        User user = new Player("주노");
        CardPack cardPack = new CardPack();
        cardPack.shuffle(new NoviceShuffleStrategy());

        // when
        user.drawCard(cardPack);

        // then
        List<Card> userCards = user.getCards();
        Assertions.assertThat(userCards.get(0))
                .isEqualTo(Card.of(CardNumber.ACE, CardShape.SPADE));
    }
}
