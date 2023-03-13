package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.Result;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    @Test
    void 카드를_뽑을_수_있다() {
        // given
        Dealer dealer = new Dealer();
        CardPack cardPack = new CardPack();

        // when
        dealer.drawCard(cardPack);

        // then
        assertThat(dealer.getCards())
                .containsExactly(new Card(CardNumber.KING, CardShape.CLOVER));
    }

    @Test
    void 자신이_16점_이하이면_만족하지_않는다() {
        // given
        Dealer dealer = new Dealer();
        CardPack cardPack = new CardPack(List.of(
                new Card(CardNumber.ACE, CardShape.SPADE)));

        // when
        dealer.drawCard(cardPack);

        // then
        assertThat(dealer.isSatisfiedMinScore())
                .isFalse();
    }

    @Test
    void 자신이_16점_이상이면_만족한다() {
        // given
        Dealer dealer = new Dealer();
        CardPack cardPack = new CardPack(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.ACE, CardShape.SPADE)));

        // when
        dealer.drawCard(cardPack);
        dealer.drawCard(cardPack);

        // then
        assertThat(dealer.isSatisfiedMinScore())
                .isTrue();
    }

    @Test
    void 딜러는_자신의_점수를_기반으로_플레이어의_결과를_계산한다() {
        // given
        Dealer dealer = new Dealer();
        Player player = new Player("dummy");
        CardPack cardPack1 = new CardPack(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.ACE, CardShape.SPADE)));
        CardPack cardPack2 = new CardPack(List.of(
                new Card(CardNumber.ACE, CardShape.CLOVER),
                new Card(CardNumber.ACE, CardShape.SPADE)));

        // when
        dealer.drawCard(cardPack1);
        dealer.drawCard(cardPack1);
        player.drawCard(cardPack2);
        player.drawCard(cardPack2);

        // then
        final Map<User, Result> result = dealer.getResult(new Players(List.of(player)));
        assertThat(result.get(player).getResult().get(GameResult.LOSE)).isEqualTo(1);
    }
}
