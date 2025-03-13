package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exceptions.BlackjackArgumentException;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DeckTest {

  @Nested
  @DisplayName("덱 생성")
  class CreateDecks {

    @ParameterizedTest
    @CsvSource({"1, 52", "2, 104"})
    @DisplayName("요청한 수에 비례하여, 52개의 트럼프카드 덱을 생성한다.")
    void test_createShuffledDecks(final int numberOfDeck, final int expected) {
      // given
      final var deck = Deck.createShuffledDecks(numberOfDeck);
      // when & then
      assertThat(deck.getNumberOfCards()).isEqualTo(expected);
    }
  }

  @Nested
  @DisplayName("카드를 순서대로 뽑는다.")
  class drawCard {

    @Test
    @DisplayName("카드를 올바르게 뽑아온다.")
    void test_draw() {
      // given
      final var expected = new TrumpCard(Rank.TWO, Suit.CLUB);
      final var cards = new ArrayDeque<>(List.of(expected));
      final var deck = new Deck(cards);
      // when&then
      assertThat(deck.draw()).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드를 모두뽑았다면, 예외가 발생한다.")
    void error_deckEmpty() {
      // given
      final var deck = new Deck(new ArrayDeque<>());
      // when&then
      assertThatThrownBy(deck::draw)
          .isInstanceOf(BlackjackArgumentException.class)
          .hasMessageContaining("덱에 남아있는 카드가 없습니다.");
    }
  }
}
