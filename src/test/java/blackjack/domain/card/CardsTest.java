package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardsTest {
    @DisplayName("Cards 생성하기")
    @Test
    void create() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.of(Suit.CLUB, Denomination.KING));
        cardList.add(Card.of(Suit.CLUB, Denomination.QUEEN));

        assertThatCode(() -> {
            Cards cards = Cards.of(cardList);
        }).doesNotThrowAnyException();
    }

    @DisplayName("ACE를 포함하고 있는지 확인하기")
    @Test
    void containsAce() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.of(Suit.CLUB, Denomination.ACE));
        cardList.add(Card.of(Suit.CLUB, Denomination.QUEEN));
        Cards cards = Cards.of(cardList);

        assertTrue(cards.containsAce());
    }

    @DisplayName("ACE를 제외한 합 구하기")
    @Test
    void sumWithoutAce() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.of(Suit.CLUB, Denomination.ACE));
        cardList.add(Card.of(Suit.CLUB, Denomination.QUEEN));
        Cards cards = Cards.of(cardList);
        assertThat(cards.sumWithoutAce()).isEqualTo(10);
    }

    @DisplayName("입력한 크기 만큼의 카드를 리스트로 가져오기")
    @Test
    void getCardsWithSize() {
        List<Card> cardList = new ArrayList<>();
        final Card card = Card.of(Suit.CLUB, Denomination.ACE);
        cardList.add(card);
        cardList.add(Card.of(Suit.CLUB, Denomination.QUEEN));
        Cards cards = Cards.of(cardList);

        assertThat(cards.getCardsWithSize(1))
                .contains(card);
    }

    @DisplayName("블랙잭인지 여부 판단하기")
    @Test
    void isBlackjack() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.of(Suit.CLUB, Denomination.ACE));
        cardList.add(Card.of(Suit.CLUB, Denomination.QUEEN));
        Cards cards = Cards.of(cardList);

        assertTrue(cards.isBlackjack());
    }

    @DisplayName("카드의 총 합 계산하기")
    @Test
    void calculate() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(Card.of(Suit.CLUB, Denomination.ACE));
        cardList.add(Card.of(Suit.CLUB, Denomination.QUEEN));
        Cards cards = Cards.of(cardList);

        assertThat(cards.calculate()).isEqualTo(21);
    }
}