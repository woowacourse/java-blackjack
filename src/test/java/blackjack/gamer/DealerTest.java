package blackjack.gamer;

import static blackjack.fixture.TestFixture.provideBlackjackCards;
import static blackjack.fixture.TestFixture.provideSum15Cards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Denomination;
import blackjack.card.Shape;
import blackjack.cardMachine.CardMachine;
import blackjack.cardMachine.CardRandomMachine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(new CardRandomMachine());
    }

    public static class TestCardRandomMachine implements CardMachine {

        private final List<Card> deck = List.of(
                new Card(Shape.CLOB, Denomination.ACE),
                new Card(Shape.CLOB, Denomination.TWO),
                new Card(Shape.CLOB, Denomination.THREE)
        );

        @Override
        public void receiveDeck(final List<Card> deck) {

        }

        @Override
        public Card drawOneCard() {
            final List<Card> mutableDeck = new ArrayList<>(deck);
            Collections.shuffle(mutableDeck);
            return mutableDeck.getFirst();
        }

    }

    @DisplayName("카드 한 장을 랜덤하게 뽑는다.")
    @Test
    void SpreadOneRandomCard() {
        // given
        final Dealer dealer = new Dealer(new TestCardRandomMachine());

        // when & then
        assertThat(dealer.spreadOneCard()).extracting("shape").isEqualTo(Shape.CLOB);
    }

    @DisplayName("52장이 곂치지 않게 뽑힌다.")
    @Test
    void drawEveryCard() {
        // given

        // when
        final List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            deck.add(dealer.spreadOneCard());
        }
        final Set<Card> uniqueCards = new HashSet<>(deck);

        // then
        assertThat(uniqueCards.size() == deck.size()).isTrue();
    }

    @DisplayName("딜러가 2장의 카드를 뽑는다.")
    @Test
    void spreadTwoCards() {
        // given

        // when & then
        assertThat(dealer.spreadTwoCards()).hasSize(2);
    }

    @DisplayName("딜러의 카드합이 16이하인지 확인한다.")
    @Test
    void isHit() {
        // given

        // when
        dealer.receiveCards(provideSum15Cards());

        // then
        assertThat(dealer.isHit()).isTrue();
    }

    @DisplayName("딜러가 번 돈을 확인한다.")
    @Test
    void updateEarnedMoney() {
        // given

        // when
        dealer.updateEarnedMoney(30_000);

        // then
        assertThat(dealer.getProfit()).isEqualTo(30_000);
    }

    @DisplayName("딜러가 초기에 받은 카드 1장을 공개한다.")
    @Test
    void showInitialCards() {
        // given

        // when
        final List<Card> cards = dealer.spreadTwoCards();
        dealer.receiveCards(cards);

        // then
        assertThat(dealer.showInitialCards()).hasSize(1);
    }

    @DisplayName("딜러의 닉네임을 확인한다.")
    @Test
    void getDealerNickName() {
        // given

        // when & then
        assertThat(dealer.getNickName()).isEqualTo("딜러");
    }

    @DisplayName("딜러가 버스트인지 확인한다.")
    @Test
    void isBust() {
        // given

        // when
        dealer.receiveCards(provideSum15Cards());

        // then
        assertThat(dealer.isBust(16)).isFalse();
    }

    @DisplayName("딜러가 블랙잭인지 확인한다.")
    @Test
    void isBlackjack() {
        // given

        // when
        dealer.receiveCards(provideBlackjackCards());

        // then
        assertThat(dealer.isBlackjack(21, 2)).isTrue();
    }
}
