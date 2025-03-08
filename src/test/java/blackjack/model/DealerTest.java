package blackjack.model;

import static blackjack.model.card.CardFixtures.NO_SHUFFLER;
import static blackjack.model.card.CardFixtures.SPADE_ACE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_SIX_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TWO_CARD;
import static blackjack.model.card.CardFixtures.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.card.Card;
import blackjack.model.card.CardValue;
import blackjack.model.card.Deck;
import blackjack.model.card.Suit;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("딜러 테스트")
class DealerTest {

    @DisplayName("카드를 한장 뽑는다.")
    @Test
    void drawCardTest() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(SPADE_ACE_CARD);
        Deck deck = Deck.createDeckByCards(cards, NO_SHUFFLER);
        Dealer dealer = new Dealer(deck);

        // when
        Card card = dealer.drawCard();

        // then
        assertThat(card)
                .isEqualTo(SPADE_ACE_CARD);
    }

    @DisplayName("카드를 한장 받는다.")
    @Test
    void receiveHandTest() {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));

        // when
        dealer.receiveHand(SPADE_ACE_CARD);

        // then
        assertThat(dealer.getHand())
                .contains(SPADE_ACE_CARD);
    }

    @DisplayName("가진 패의 총합을 계산한다.")
    @Test
    void calculateHandTotalTest() {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        dealer.receiveHand(SPADE_TEN_CARD);
        dealer.receiveHand(SPADE_SIX_CARD);

        // when
        int total = dealer.getTotal();

        // then
        assertThat(total)
                .isEqualTo(16);
    }

    @DisplayName("ACE를 가진 채, ACE를 제외한 총합이 11 이하인 경우 ACE를 11로 간주한다.")
    @Test
    void calculateHandTotalWithAceTest() {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        dealer.receiveHand(SPADE_ACE_CARD);
        dealer.receiveHand(SPADE_TEN_CARD);

        // when
        int total = dealer.getTotal();

        // then
        assertThat(total)
                .isEqualTo(21);
    }

    @DisplayName("ACE를 가진 채, ACE를 제외한 총합이 11 초과인 경우 ACE를 1로 간주한다.")
    @Test
    void calculateHandTotalWithAceTestOver11() {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        dealer.receiveHand(SPADE_ACE_CARD);
        dealer.receiveHand(SPADE_TWO_CARD);
        dealer.receiveHand(SPADE_TEN_CARD);

        // when
        int total = dealer.getTotal();

        // then
        assertThat(total)
                .isEqualTo(13);
    }

    @DisplayName("패가 2장만 있고, 합이 21이면 블랙잭이다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, ACE, true",
            "TEN, TEN, false",
    })
    void isBlackjackTest(CardValue cardValue1, CardValue cardValue2, boolean expected) {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        dealer.receiveHand(createCard(Suit.SPADES, cardValue1));
        dealer.receiveHand(createCard(Suit.SPADES, cardValue2));

        // when
        boolean isBlackjack = dealer.isBlackjack();

        // then
        assertThat(isBlackjack)
                .isSameAs(expected);
    }

    @DisplayName("21이 초과하면 버스트이다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, TEN, TEN, true",
            "TWO, TWO, ACE, false",
    })
    void isBustTest(CardValue cardValue1, CardValue cardValue2, CardValue cardValue3, boolean expected) {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        dealer.receiveHand(createCard(Suit.SPADES, cardValue1));
        dealer.receiveHand(createCard(Suit.SPADES, cardValue2));
        dealer.receiveHand(createCard(Suit.SPADES, cardValue3));

        // when
        boolean isBust = dealer.isBust();

        // then
        assertThat(isBust)
                .isSameAs(expected);
    }

    @DisplayName("보여줄 때 첫 번째 카드 한장을 반환한다.")
    @Test
    void getVisibleCardTest() {
        // give
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        Card firstCard = dealer.drawCard();
        dealer.receiveHand(firstCard);
        Card secondCard = dealer.drawCard();
        dealer.receiveHand(secondCard);

        // when
        Card visibleCard = dealer.getVisibleCard();

        // then
        assertThat(visibleCard)
                .isEqualTo(firstCard);
        assertThat(visibleCard)
                .isNotEqualTo(secondCard);
    }

    @DisplayName("보여줄 때 가진 패가 없는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenNoHandGetVisibleCardTest() {
        // given
        Dealer dealer = new Dealer(Deck.createDeckByCards(new ArrayList<>(), NO_SHUFFLER));

        // when, then
        assertThatCode(dealer::getVisibleCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러가 가진 패가 없습니다.");
    }

    @DisplayName("가진 패의 총합이 16이하인 경우 히트한다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, SIX, true",
            "TEN, SEVEN, false"
    })
    void shouldHitTrueTest(CardValue cardValue1, CardValue cardValue2, boolean expected) {
        // given
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        dealer.receiveHand(createCard(Suit.SPADES, cardValue1));
        dealer.receiveHand(createCard(Suit.SPADES, cardValue2));

        // when
        boolean shouldHit = dealer.shouldHit();

        // then
        assertThat(shouldHit)
                .isSameAs(expected);
    }
}
