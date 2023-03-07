package domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.type.Letter;
import domain.type.Suit;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드 생성 테스트")
    public void testCreate() {
        //given
        final Set<Card> cardSet = Arrays.stream(Letter.values())
            .map(i -> new Card(Suit.DIAMOND, i))
            .collect(Collectors.toSet());

        //when
        //then
        assertDoesNotThrow(() -> new Cards(cardSet));
    }

    @Test
    @DisplayName("")
    public void testMakeEmptyCards() {
        //given
        //when
        final Cards cards = Cards.makeEmptyCards();

        //then
        assertThat(cards.getCards().isEmpty()).isTrue();
    }

    @Test
    @DisplayName("카드를 추가하는 것을 테스트")
    public void testAddCard() {
        //given
        final Set<Card> cardSet = Arrays.stream(Letter.values())
            .map(i -> new Card(Suit.DIAMOND, i))
            .collect(Collectors.toSet());
        final Cards cards = new Cards(cardSet);
        final Card card = new Card(Suit.SPADE, Letter.ACE);

        //when
        cards.add(card);

        //then
        assertThat(cards.count(Letter.ACE)).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 문자 카드 보유 개수를 테스트")
    public void testCount() {
        //given
        final Set<Card> cardSet = Set.of(new Card(Suit.CLUB, Letter.ACE), new Card(Suit.DIAMOND, Letter.ACE),
            new Card(Suit.SPADE, Letter.ACE));
        final Cards cards = new Cards(cardSet);

        //when
        final int result1 = cards.count(Letter.ACE);
        final int result2 = cards.count(Letter.TWO);

        //then
        assertThat(result1).isEqualTo(3);
        assertThat(result2).isEqualTo(0);
    }
}
