package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardPocketTest {

    private static final List<Card> cards = List.of(Card.from(Shape.CLOVER, Denomination.ACE),
            Card.from(Shape.HEART, Denomination.EIGHT));

    private Hand cardPocket;

    @BeforeEach
    void setCardPocket() {
        cardPocket = new Hand();
        cards.forEach(cardPocket::addCard);
    }

    @Test
    void 카드_포켓_안의_카드의_점수_계산() {
        assertThat(cardPocket.getScore())
                .isEqualTo(19);

    }

    @Test
    void 카드_포켓에_카드_추가한_후_카드의_점수_계산() {
        cardPocket.addCard(Card.from(Shape.DIAMOND, Denomination.ACE));

        assertThat(cardPocket.getScore())
                .isEqualTo(20);

    }

    @Test
    void 카드_포켓에_카드_두번_추가한_후_카드의_점수_계산() {
        cardPocket.addCard(Card.from(Shape.DIAMOND, Denomination.TEN));
        cardPocket.addCard(Card.from(Shape.DIAMOND, Denomination.EIGHT));

        assertThat(cardPocket.getScore())
                .isEqualTo(27);
    }

    @Test
    void 카드_포켓에서_카드_가져오는_기능_추가() {
        assertThat(cardPocket.getPossessedCards())
                .isEqualTo(cards);
    }
}
