package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.game.GameResult;
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

    @Test
    void 유저는_상대유저가_BUST_이고_자신이_BUST가_아니면_승리한다() {

        // given
        User user = new Player("주노");
        User user2 = new Player("블랙캣");

        CardPack cardPack = new CardPack(List.of(
                Card.of(CardNumber.ACE, CardShape.SPADE),
                Card.of(CardNumber.SIX, CardShape.SPADE)));
        CardPack cardPack2 = new CardPack(List.of(
                Card.of(CardNumber.JACK, CardShape.SPADE),
                Card.of(CardNumber.QUEEN, CardShape.SPADE),
                Card.of(CardNumber.TEN, CardShape.SPADE)));

        // when
        user.drawCard(cardPack);
        user.drawCard(cardPack);
        user2.drawCard(cardPack2);
        user2.drawCard(cardPack2);
        user2.drawCard(cardPack2);

        // then
        Assertions.assertThat(user.getResultByUser(user2)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 유저는_상대유저가_BLACKJACK_이고_자신이_BLACKJACK이면_무승부이다() {

        // given
        User user = new Player("주노");
        User user2 = new Player("블랙캣");

        CardPack cardPack = new CardPack(List.of(
                Card.of(CardNumber.ACE, CardShape.SPADE),
                Card.of(CardNumber.TEN, CardShape.SPADE)));
        CardPack cardPack2 = new CardPack(List.of(
                Card.of(CardNumber.JACK, CardShape.SPADE),
                Card.of(CardNumber.ACE, CardShape.SPADE)));

        // when
        user.drawCard(cardPack);
        user.drawCard(cardPack);
        user2.drawCard(cardPack2);
        user2.drawCard(cardPack2);

        // then
        Assertions.assertThat(user.getResultByUser(user2)).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 유저는_상대유저가_BLACKJACK_이아니고_자신이_BLACKJACK이면_BLACKJACK이다() {

        // given
        User user = new Player("주노");
        User user2 = new Player("블랙캣");

        CardPack cardPack = new CardPack(List.of(
                Card.of(CardNumber.ACE, CardShape.SPADE),
                Card.of(CardNumber.TEN, CardShape.SPADE)));
        CardPack cardPack2 = new CardPack(List.of(
                Card.of(CardNumber.JACK, CardShape.SPADE),
                Card.of(CardNumber.QUEEN, CardShape.SPADE)));

        // when
        user.drawCard(cardPack);
        user.drawCard(cardPack);
        user2.drawCard(cardPack2);
        user2.drawCard(cardPack2);

        // then
        Assertions.assertThat(user.getResultByUser(user2)).isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    void 유저는_상대유저보다_점수가_낮으면_패배한다() {

        // given
        User user = new Player("주노");
        User user2 = new Player("블랙캣");

        CardPack cardPack = new CardPack(List.of(
                Card.of(CardNumber.ACE, CardShape.SPADE),
                Card.of(CardNumber.SIX, CardShape.SPADE)));
        CardPack cardPack2 = new CardPack(List.of(
                Card.of(CardNumber.JACK, CardShape.SPADE),
                Card.of(CardNumber.QUEEN, CardShape.SPADE)));

        // when
        user.drawCard(cardPack);
        user.drawCard(cardPack);
        user2.drawCard(cardPack2);
        user2.drawCard(cardPack2);

        // then
        Assertions.assertThat(user.getResultByUser(user2)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 유저와_상대유저_모두_BUST가_아니고_자신의_점수가_더_높으면_승리한다() {

        // given
        User user = new Player("주노");
        User user2 = new Player("블랙캣");

        CardPack cardPack = new CardPack(List.of(
                Card.of(CardNumber.SIX, CardShape.SPADE),
                Card.of(CardNumber.QUEEN, CardShape.SPADE)));
        CardPack cardPack2 = new CardPack(List.of(
                Card.of(CardNumber.JACK, CardShape.SPADE)));

        // when
        user.drawCard(cardPack);
        user.drawCard(cardPack);
        user2.drawCard(cardPack2);

        // then
        Assertions.assertThat(user.getResultByUser(user2)).isEqualTo(GameResult.WIN);
    }

    @Test
    void 유저와_상대유저_모두_BUST이면_자신은_패배이다() {

        // given
        User user = new Player("주노");
        User user2 = new Player("블랙캣");

        CardPack cardPack = new CardPack(List.of(
                Card.of(CardNumber.JACK, CardShape.SPADE),
                Card.of(CardNumber.QUEEN, CardShape.SPADE),
                Card.of(CardNumber.TEN, CardShape.SPADE)));
        CardPack cardPack2 = new CardPack(List.of(
                Card.of(CardNumber.JACK, CardShape.SPADE),
                Card.of(CardNumber.QUEEN, CardShape.SPADE),
                Card.of(CardNumber.TEN, CardShape.SPADE)));

        // when
        user.drawCard(cardPack);
        user.drawCard(cardPack);
        user.drawCard(cardPack);
        user2.drawCard(cardPack2);
        user2.drawCard(cardPack2);
        user2.drawCard(cardPack2);

        // then
        Assertions.assertThat(user.getResultByUser(user2)).isEqualTo(GameResult.LOSE);
    }
}
