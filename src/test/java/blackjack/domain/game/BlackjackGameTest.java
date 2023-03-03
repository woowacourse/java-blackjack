package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.user.Dealer;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackjackGameTest {

    @Test
    void 게임은_딜러가_16점_이하인지_검사한다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(new CardPack());
        Dealer dealer = new Dealer();

        // when
        blackjackGame.draw(dealer);

        // then
        final boolean dealerEnd = blackjackGame.isDealerEnd(dealer);
        Assertions.assertThat(dealerEnd).isFalse();
    }

    @Test
    void 게임은_딜러가_16점_초과인지_검사한다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(new CardPack());
        Dealer dealer = new Dealer();

        // when
        blackjackGame.draw(dealer);
        blackjackGame.draw(dealer);

        // then
        final boolean dealerEnd = blackjackGame.isDealerEnd(dealer);
        Assertions.assertThat(dealerEnd).isTrue();
    }

    @Test
    void 게임은_딜러가_21점_초과이면_True() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(new CardPack());
        Dealer dealer = new Dealer();

        // when
        blackjackGame.draw(dealer);
        blackjackGame.draw(dealer);
        blackjackGame.draw(dealer);

        // then
        final boolean dealerEnd = blackjackGame.isDealerEnd(dealer);
        Assertions.assertThat(dealerEnd).isTrue();
    }


    @Test
    void 게임은_딜러가_16이면_false() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(new CardPack(
                List.of(new Card(CardNumber.QUEEN, CardShape.SPADE), new Card(CardNumber.SIX, CardShape.SPADE))));
        Dealer dealer = new Dealer();

        // when
        blackjackGame.draw(dealer);
        blackjackGame.draw(dealer);

        // then
        final boolean dealerEnd = blackjackGame.isDealerEnd(dealer);
        Assertions.assertThat(dealerEnd).isFalse();
    }
}
