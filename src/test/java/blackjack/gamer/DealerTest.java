package blackjack.gamer;

import static blackjack.fixture.TestFixture.provideBlackjackCards;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Shape;
import blackjack.cardMachine.CardRandomMachine;
import blackjack.fixture.TestFixture.TestCardRandomMachine;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("52장이 곂치지 않게 뽑힌다.")
    @Test
    void drawEveryCard() {
        // given
        final Dealer dealer = new Dealer(new CardRandomMachine());

        // when
        dealer.initCardMachine();
        final List<Card> deck = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            deck.add(dealer.spreadOneCard());
        }
        final Set<Card> uniqueCards = new HashSet<>(deck);

        // then
        assertThat(uniqueCards.size() == deck.size()).isTrue();
    }

    @DisplayName("카드 한 장을 랜덤하게 뽑는다.")
    @Test
    void SpreadOneRandomCard() {
        // given
        final Dealer dealer = new Dealer(new TestCardRandomMachine());

        // when & then
        assertThat(dealer.spreadOneCard()).extracting("shape").isEqualTo(Shape.CLOB);
    }

    @DisplayName("딜러가 2장의 카드를 뽑는다.")
    @Test
    void spreadTwoCards() {
        // given
        final Dealer dealer = new Dealer(new CardRandomMachine());

        // when
        dealer.initCardMachine();

        // then
        assertThat(dealer.spreadTwoCards()).hasSize(2);
    }

    @DisplayName("딜러의 카드합이 16이하인지 확인한다.")
    @Test
    void isHit() {
        // given
        final Dealer dealer = new Dealer(new TestCardRandomMachine());

        // when
        final List<Card> cards = dealer.spreadTwoCards();
        dealer.receiveCards(cards);

        // then
        assertThat(dealer.isHit()).isTrue();
    }

    @DisplayName("딜러가 번 돈을 확인한다.")
    @Test
    void updateEarnenMoney() {
        // given
        final Dealer dealer = new Dealer(new CardRandomMachine());

        // when
        dealer.updateEarnedMoney(30_000);

        // then
        assertThat(dealer.getProfit()).isEqualTo(30_000);
    }

    @DisplayName("딜러가 초기에 받은 카드 1장을 공개한다.")
    @Test
    void showInitialCards() {
        // given
        final Dealer dealer = new Dealer(new CardRandomMachine());

        // when
        dealer.initCardMachine();
        final List<Card> cards = dealer.spreadTwoCards();
        dealer.receiveCards(cards);

        // then
        assertThat(dealer.showInitialCards()).hasSize(1);
    }

    @DisplayName("딜러의 닉네임을 확인한다.")
    @Test
    void getDealerNickName() {
        // given
        final Dealer dealer = new Dealer(new CardRandomMachine());

        // when & then
        assertThat(dealer.getNickName()).isEqualTo("딜러");
    }

    @DisplayName("딜러가 버스트인지 확인한다.")
    @Test
    void isBust() {
        // given
        final Dealer dealer = new Dealer(new TestCardRandomMachine());

        // when
        final List<Card> cards = dealer.spreadTwoCards();
        dealer.receiveCards(cards);

        // then
        assertThat(dealer.isBust(16)).isFalse();
    }

    @DisplayName("딜러가 블랙잭인지 확인한다.")
    @Test
    void isBlackjack() {
        // given
        final Dealer dealer = new Dealer(new TestCardRandomMachine());

        // when
        final List<Card> cards = provideBlackjackCards();
        dealer.receiveCards(cards);

        // then
        assertThat(dealer.isBlackjack(21, 2)).isTrue();
    }
}
