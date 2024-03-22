package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.card.deck.Deck;
import blackjack.domain.card.deck.ShuffledDeckCardGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    private Deck deck;

    @BeforeEach
    void init() {
        ShuffledDeckCardGenerator deckCardGenerator = ShuffledDeckCardGenerator.getInstance();

        deck = new Deck(deckCardGenerator.generate());
    }

    @DisplayName("딜러는 0장의 카드를 갖고 생성 된다.")
    @Test
    void hasDeckTest() {
        // given & when
        Dealer dealer = new Dealer();

        // then
        assertThat(dealer.cards()).hasSize(0);
    }

    @DisplayName("딜러는 초기 카드를 2장 받는다")
    @Test
    void dealInitCardsTest() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.receiveInitCards(deck.drawInitCards());

        // then
        assertThat(dealer.cards()).hasSize(2);
    }

    @DisplayName("딜러는 카드 1장을 분배한다.")
    @Test
    void dealCardTest() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.receiveCard(deck.drawCard());

        // then
        assertThat(dealer.cards()).hasSize(1);
    }

    @DisplayName("딜러 카드 패의 총 합이 16 이하라면 Hit 한다.")
    @Test
    void dealerHitTest() {
        // given
        Dealer dealer = new Dealer(
                new ArrayList<>(List.of(Card.of(Suit.HEART, Rank.TEN), Card.of(Suit.HEART, Rank.SIX))));

        // then
        Assertions.assertAll(
                () -> assertThat(dealer.getScore()).isLessThanOrEqualTo(16),
                () -> assertThat(dealer.hasHitScore()).isTrue()
        );
    }

    @DisplayName("딜러 카드 패의 총 합이 16 초과라면 Stand 한다.")
    @Test
    void dealerStandTest() {
        // given
        Dealer dealer = new Dealer(
                new ArrayList<>(List.of(Card.of(Suit.HEART, Rank.TEN), Card.of(Suit.HEART, Rank.SEVEN))));

        // then
        Assertions.assertAll(
                () -> assertThat(dealer.getScore()).isGreaterThan(16),
                () -> assertThat(dealer.hasHitScore()).isFalse()
        );
    }
}
