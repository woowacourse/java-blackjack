package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.game.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters"})
class HandTest {

    private static final List<Card> cards = List.of(Card.from(Shape.CLOVER, Denomination.ACE),
            Card.from(Shape.HEART, Denomination.EIGHT));

    private Hand hand;

    @BeforeEach
    void setCardPocket() {
        hand = new Hand();
        for (Card card : cards) {
            hand = hand.addCard(card);
        }
    }

    @Test
    void 카드_포켓_안의_카드의_점수_계산() {
        assertThat(hand.getScore())
                .isEqualTo(new Score(19));

    }

    @Test
    void 카드_포켓에_카드_추가한_후_카드의_점수_계산() {
        hand = hand.addCard(Card.from(Shape.DIAMOND, Denomination.ACE));

        assertThat(hand.getScore())
                .isEqualTo(new Score(20));

    }

    @Test
    void 카드_포켓에_카드_두번_추가한_후_카드의_점수_계산() {
        Hand handAddOne = hand.addCard(Card.from(Shape.DIAMOND, Denomination.TEN));
        Hand handAddTwo = handAddOne.addCard(Card.from(Shape.DIAMOND, Denomination.EIGHT));

        assertThat(handAddTwo.getScore())
                .isEqualTo(new Score(27));
    }

    @Test
    void 카드_포켓에서_카드_가져오는_기능_추가() {
        assertThat(hand.getCards())
                .isEqualTo(cards);
    }
}
