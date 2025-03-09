package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import constant.Suit;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.TrumpCard;
import java.util.ArrayDeque;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DeckTest {

  @Nested
  @DisplayName("덱 생성")
  class CreateDeck {

    @DisplayName("Deck에 동일한 카드가 들어오면, 예외가 발생한다.")
    @Test
    public void validateDuplicate() {
      // given
      final TrumpCard club = new TrumpCard(Rank.TWO, Suit.CLUB);
      final var q = new ArrayDeque<>(List.of(club, club));

      // when & then
      assertThatThrownBy(() -> new Deck(q))
          .isInstanceOf(IllegalArgumentException.class);
    }

  }

  @Nested
  @DisplayName("카드를 순서대로 뽑는다.")
  class pickTrumpCard {

    @DisplayName("카드를 올바르게 뽑아온다.")
    @Test
    public void pickCard() {
      // given
      final Rank rank = Rank.TWO;
      final var d = new ArrayDeque<>(List.of(new TrumpCard(rank, Suit.CLUB)));
      final var deck = new Deck(d);
      final var expected = new TrumpCard(rank, Suit.CLUB);

      // when
      final var actual = deck.draw();

      // then
      assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드를 모두뽑았다면, 예외가 발생한다.")
    @Test
    public void pickCardEmpty() {
      // given
      final var deck = new Deck(new ArrayDeque<>());
      // when&then
      assertThatThrownBy(deck::draw)
          .isInstanceOf(NullPointerException.class)
          .hasMessageContaining("덱에 남아있는 카드가 없습니다.");
    }
  }
}
