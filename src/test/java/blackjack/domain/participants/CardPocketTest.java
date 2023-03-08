package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardPocketTest {

    private static final List<Card> cards = List.of(new Card(Shape.CLOVER, Symbol.ACE),
            new Card(Shape.HEART, Symbol.EIGHT));

    private CardPocket cardPocket;

    @BeforeEach
    void setCardPocket() {
        cardPocket = new CardPocket();
        cards.forEach(cardPocket::addCard);
    }

    @Test
    void 카드_포켓_안의_카드의_점수_계산() {
        assertThat(cardPocket.getScore())
                .isEqualTo(19);

    }

    @Test
    void 카드_포켓에_카드_추가한_후_카드의_점수_계산() {
        cardPocket.addCard(new Card(Shape.DIAMOND, Symbol.ACE));

        assertThat(cardPocket.getScore())
                .isEqualTo(20);

    }

    @Test
    void 카드_포켓에_카드_두번_추가한_후_카드의_점수_계산() {
        cardPocket.addCard(new Card(Shape.DIAMOND, Symbol.TEN));
        cardPocket.addCard(new Card(Shape.DIAMOND, Symbol.EIGHT));

        assertThat(cardPocket.getScore())
                .isEqualTo(27);
    }

    @Test
    void 카드_포켓에서_카드_가져오는_기능_추가() {
        assertThat(cardPocket.getPossessedCards())
                .isEqualTo(cards);
    }
}
