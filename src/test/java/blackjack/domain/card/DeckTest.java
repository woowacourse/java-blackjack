package blackjack.domain.card;

import blackjack.manager.CardsGenerator;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void 생성되는_시점에_카드의_개수는_52장이어야_한다() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();

        // when & then
        assertAll(
                () -> assertThat(cards).hasSize(52),
                () -> assertThatCode(() -> new Deck(cards))
                        .doesNotThrowAnyException()
        );
    }

    @Test
    void 생성되는_시점에_카드의_개수가_52장이_아니면_예외를_던진다() {
        // given
        List<Card> cards = List.of(new Card(CardSuit.CLUB, CardRank.ACE));

        // when & then
        assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드 개수는 52개여야 합니다.");
    }

    @Test
    void 중복되는_카드가_존재하면_예외를_던진다() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();

        ArrayList<Card> copiedCards = new ArrayList<>(cards);
        copiedCards.removeLast();
        Card card = copiedCards.getLast();
        copiedCards.add(card);

        // when & then
        assertThatThrownBy(() -> new Deck(copiedCards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 카드가 존재합니다.");
    }

    @Test
    void 카드는_섞일_수_있다() {
        // given
        CardsGenerator cardsGenerator = new CardsGenerator();
        List<Card> cards = cardsGenerator.generate();
        Deck deck = new Deck(cards);

        // then
        assertThat(deck.getCards())
                .containsExactlyInAnyOrderElementsOf(cards);
    }

    @Nested
    class 카드뽑기 {

        @Test
        void 카드가_소진되면_예외를_던진다() {
            CardsGenerator cardsGenerator = new CardsGenerator();
            List<Card> cards = cardsGenerator.generate();
            Deck deck = new Deck(cards);

            for (int i = 0; i < 52; i++) {
                deck.takeSingleCard();
            }

            assertThatThrownBy(deck::takeSingleCard)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("모든 카드를 소진하였습니다.");
        }
    }
}
