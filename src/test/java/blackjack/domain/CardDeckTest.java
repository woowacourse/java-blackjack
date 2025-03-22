package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.game.CardDeck;
import blackjack.exception.ErrorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    @DisplayName("52장 미만의 Card로 CardDeck 생성 시 예외 발생")
    void throwIfSizeUnder52() {
        // given
        List<Card> cards = new ArrayList<>();
        Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values()).map(suit -> new Card(rank, suit)))
                .forEach(cards::add);
        cards.removeFirst();
        // when & then
        assertThatThrownBy(() -> new CardDeck(cards))
                .isInstanceOf(ErrorException.class)
                .hasMessage("[ERROR] 카드는 총 52장이어야 합니다.");
    }

    @Test
    @DisplayName("Card 1장 맨 앞에서 뽑기 기능 테스트")
    void pickCardTest() {
        // given
        List<Card> cards = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values()).map(suit -> new Card(rank, suit)))
                .toList();
        CardDeck cardDeck = new CardDeck(cards);
        // when
        Card card = cardDeck.pickCard();
        // then
        assertThat(card).isNotNull();
        assertThat(card.rank()).isEqualTo(Rank.values()[0]);
        assertThat(card.suit()).isEqualTo(Suit.values()[0]);
    }
}
