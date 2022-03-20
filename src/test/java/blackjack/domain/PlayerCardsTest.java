package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;
import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerCardsTest {

    @Test
    @DisplayName("카드 한 장을 저장한다.")
    void saveCard() {
        PlayerCards playerCards = new PlayerCards();
        playerCards.save(new Card(Suit.CLOVER, Denomination.FIVE));
        assertThat(playerCards.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Ace가 있는 상태로 total point를 계산한다.")
    void sumTotalPointWithAce() {
        PlayerCards playerCards = new PlayerCards();

        playerCards.save(new Card(Suit.CLOVER, Denomination.ACE));
        playerCards.save(new Card(Suit.DIAMOND, Denomination.JACK));

        assertThat(playerCards.calculateTotalPoint()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace가 있는 상태로 total point가 21을 초과하는 경우 total point를 계산한다.")
    void sumTotalPointWithAceOverMax() {
        PlayerCards playerCards = new PlayerCards();

        playerCards.save(new Card(Suit.CLOVER, Denomination.ACE));
        playerCards.save(new Card(Suit.DIAMOND, Denomination.JACK));
        playerCards.save(new Card(Suit.DIAMOND, Denomination.ACE));

        assertThat(playerCards.calculateTotalPoint()).isEqualTo(12);
    }
}
