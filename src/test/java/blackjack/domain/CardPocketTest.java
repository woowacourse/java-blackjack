package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardPocketTest {

    private static final List<Card> cards = List.of(new Card(Shape.CLOVER, Symbol.ACE),
            new Card(Shape.HEART, Symbol.EIGHT));

    private CardPocket cardPocket;

    @BeforeEach
    void setCardPocket() {
        cardPocket = CardPocket.empty();
        cards.forEach(cardPocket::addCard);
    }

    @Test
    void 카드_포켓_안의_카드의_점수_계산() {
        assertThat(cardPocket.calculateScore())
                .isEqualTo(19);

    }

    @Test
    void 카드_포켓에_카드_추가한_후_카드의_점수_계산() {
        cardPocket.addCard(new Card(Shape.DIAMOND, Symbol.ACE));

        assertThat(cardPocket.calculateScore())
                .isEqualTo(20);

    }

    @Test
    void 카드_포켓에_카드_두번_추가한_후_카드의_점수_계산() {
        cardPocket.addCard(new Card(Shape.DIAMOND, Symbol.TEN));
        cardPocket.addCard(new Card(Shape.DIAMOND, Symbol.EIGHT));

        assertThat(cardPocket.calculateScore())
                .isEqualTo(27);
    }

    @Test
    void 카드_포켓에서_카드_가져오는_기능_추가() {
        assertThat(cardPocket.getCards())
                .isEqualTo(cards);
    }

    @Test
    void 카드_포켓에서_Ace_의_점수_계산이_11_과_1로_잘_계산된다() {
        CardPocket cardPocket = CardPocket.empty();
        cardPocket.addCard(new Card(Shape.DIAMOND, Symbol.ACE));
        cardPocket.addCard(new Card(Shape.DIAMOND, Symbol.TEN));

        assertThat(cardPocket.calculateScore())
                .isEqualTo(21);

        cardPocket.addCard(new Card(Shape.DIAMOND, Symbol.ACE));
        assertThat(cardPocket.calculateScore())
                .isEqualTo(12);
    }
}
