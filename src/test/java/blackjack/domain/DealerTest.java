package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    Card card1;
    Card card2;
    UserCards userCards;
    User dealer;

    @BeforeEach
    void setUp() {
        card1 = new Card(Suit.CLUB, Symbol.SIX);
        card2 = new Card(Suit.HEART, Symbol.KING);
        userCards = new UserCards(Arrays.asList(card1, card2));
        dealer = new Dealer(userCards);
    }

    @Test
    @DisplayName("딜러의 이름이 \"딜러\"인지 확인")
    void checkDealerName() {
        assertThat(dealer.getName()).isEqualTo("딜러");
    }
}
