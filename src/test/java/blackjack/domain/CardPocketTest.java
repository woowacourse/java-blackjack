package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardPocketTest {
    private static List<Card> cards;

    private CardPocket cardPocket;

    @BeforeEach
    void setCardPocket() {
        cards = List.of(new Card(Shape.CLOVER, Symbol.ACE), new Card(Shape.HEART, Symbol.EIGHT));
        cardPocket = new CardPocket(cards);
    }

    @Test
    void 카드_포켓_생성시_null_입력시_예외() {
        assertThatThrownBy(() -> new CardPocket(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 입력하지 않았습니다");
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
}
