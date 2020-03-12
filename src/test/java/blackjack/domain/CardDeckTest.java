package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardDeckTest {
    @DisplayName("52장의 카드 한 벌을 생성하는지 확인")
    @Test
    void name() {
        int expected = 52;
        assertThat(new CardDeck().getSize()).isEqualTo(expected);
    }

    @DisplayName("카드덱이 카드를 하나씩 생성해서 전달하는지 확인")
    @Test
    void name2() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        int originSum = dealer.computeSum();

        cardDeck.getCard(dealer);

        assertThat(dealer.computeSum() > originSum).isTrue();
    }
}
