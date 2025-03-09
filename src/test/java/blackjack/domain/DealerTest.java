package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPack;
import blackjack.domain.player.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    @DisplayName("딜러는 한장의 카드만 공개한다")
    void dealerRevealsOnlyOneCard() {
        CardPack cardPack = new CardPack(new SortShuffle());
        Dealer dealer = new Dealer();

        dealer.pushDealCard(cardPack, 2);
        List<Card> cards = dealer.getOpenedCards();

        assertThat(cards.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 카드가 없는 경우 빈 리스트를 반환한다")
    void returnsEmptyListWhenDealerHasNoCards() {
        Dealer dealer = new Dealer();
        List<Card> result = dealer.getOpenedCards();

        assertThat(result)
                .isEmpty();
    }
}
