package domain.card;

import static message.ErrorMessage.DECK_CAN_NOT_DUPLICATED;
import static message.ErrorMessage.DECK_OUT_OF_CARD_STOCK;

import domain.enums.Rank;
import domain.enums.Suit;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.CardGenerator;
import service.ShuffledCardGenerator;

public class DeckTest {

    @DisplayName("52장의 서로 다른 카드가 정상 생성된다.")
    @Test
    void _52장의_서로_다른_카드가_정상_생성된다() {
        Deck deck = Deck.create(new ShuffledCardGenerator());

        Set<Card> distinctCards = Set.copyOf(deck.getCards());

        Assertions.assertThat(distinctCards.size()).isEqualTo(deck.getCards().size());
        Assertions.assertThat(distinctCards.size()).isEqualTo(52);
    }

    public static class DuplicatedDeckGenerator implements CardGenerator {
        @Override
        public List<Card> generate() {
            return List.of(new Card(Rank.ACE, Suit.SPADE), new Card(Rank.ACE, Suit.SPADE));
        }
    }

    @DisplayName("중복된 카드가 들어있을 경우 예외가 발생한다.")
    @Test
    void 중복_카드_예외_발생_한다() {
        Assertions.assertThatThrownBy(() -> Deck.create(new DuplicatedDeckGenerator()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DECK_CAN_NOT_DUPLICATED.getMessage());
    }

    public static class EmptyDeckGenerator implements CardGenerator {
        @Override
        public List<Card> generate() {
            return List.of();
        }
    }

    @DisplayName("덱에 카드가 없는 경우 예외가 발생한다.")
    @Test
    void 덱_카드_X_드로우_예외() {
        //given
        Deck deck = Deck.create(new EmptyDeckGenerator());
        //when
        //then
        Assertions.assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(DECK_OUT_OF_CARD_STOCK.getMessage());
    }
}
