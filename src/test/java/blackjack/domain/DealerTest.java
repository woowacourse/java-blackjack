package blackjack.domain;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 유일한 카드들을 만들 수 있어야 한다.")
    void createUniqueCards_success() {
        // given
        Dealer dealer = new Dealer();

        // when
        List<Card> cards = dealer.createUniqueCards().getCards();
        List<Card> uniqueCards = cards.stream()
                .distinct()
                .collect(toList());

        // then
        assertThat(uniqueCards)
                .hasSize(48);
    }

    @Test
    @DisplayName("딜러가 카드를 더 뽑을 수 있으면 참을 반환해야 한다.")
    void canDrawCard_true() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        dealer.addCard(new Card(Suit.DIAMOND, Rank.FIVE));

        // expect
        assertThat(dealer.canDrawCard())
                .isTrue();
    }

    @Test
    @DisplayName("딜러가 카드를 더 뽑을 수 없으면 거짓을 반환해야 한다.")
    void canDrawCard_false() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Suit.DIAMOND, Rank.KING));
        dealer.addCard(new Card(Suit.DIAMOND, Rank.FIVE));
        dealer.addCard(new Card(Suit.DIAMOND, Rank.QUEEN));

        // expect
        assertThat(dealer.canDrawCard())
                .isFalse();
    }
}
