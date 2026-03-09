package domain.card;

import static message.ErrorMessage.DECK_CAN_NOT_DUPLICATED;

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
        CardGenerator cardGenerator = new ShuffledCardGenerator();
        Deck deck = new Deck(cardGenerator.generate());

        Set<Card> distinctCards = Set.copyOf(deck.getCards());

        Assertions.assertThat(distinctCards.size()).isEqualTo(deck.getCards().size());
        Assertions.assertThat(distinctCards.size()).isEqualTo(52);
    }

    @DisplayName("중복된 카드가 들어있을 경우 예외가 발생한다.")
    @Test
    void 중복_카드_예외_발생_한다() {
        CardGenerator cardGenerator = new ShuffledCardGenerator();
        List<Card> cards = cardGenerator.generate();
        cards.add(new Card(Rank.ACE, Suit.SPADE));

        Assertions.assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DECK_CAN_NOT_DUPLICATED.getMessage());
    }

    @DisplayName("덱에 카드가 없는 경우 예외가 발생한다.")
    @Test
    void 덱_카드_X_드로우_예외() {
        //given
        Deck deck = new Deck(List.of());
        //when
        //then
        Assertions.assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 덱에 드로우할 카드가 존재하지 않습니다.");
    }
}

