package blackjack.domain.card;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
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

    @DisplayName("카드덱이 첫 카드를 2장 전달하는지 확인")
    @Test
    void name2() {
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        Player player = new Player("포비");

        cardDeck.dealFirstCards(dealer);
        cardDeck.dealFirstCards(player);

        assertThat(dealer.getCards().getCards().size()).isEqualTo(2);
        assertThat(player.getCards().getCards().size()).isEqualTo(2);
    }
}
