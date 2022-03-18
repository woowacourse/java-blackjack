package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Deck;
import blackjack.domain.card.JustTenSpadeDeck;
import blackjack.domain.card.JustTwoSpadeDeck;
import blackjack.domain.card.RandomDeck;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("Dealer 클래스는 딜러라는 이름을 가지고 정상적으로 생성된다.")
    void create_dealer() {
        String name = "딜러";
        Deck deck = new RandomDeck();
        Dealer dealer = new Dealer(deck);

        assertThat(dealer.getName().get()).isEqualTo(name);
    }

    @Test
    @DisplayName("딜러는 isDealer가 참을 반환한다.")
    void check_dealer() {
        Deck deck = new RandomDeck();
        Player dealer = new Dealer(deck);

        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 총합이 16이하면 canHit가 참을 반환한다.")
    void validate_range_true() {
        Deck deck = new JustTwoSpadeDeck();
        Player dealer = new Dealer(deck);

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 총합이 16초과면 canHit가 거짓을 반환한다.")
    void validate_range_false() {
        Deck deck = new JustTenSpadeDeck();
        Player dealer = new Dealer(deck);

        assertThat(dealer.canHit()).isFalse();
    }
}
