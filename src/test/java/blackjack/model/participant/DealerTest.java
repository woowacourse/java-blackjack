package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.cardDeck.CardDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    CardDeck mustPickTen = CardDeck.of(cards -> Card.createOpenedCard(Rank.TEN, Suit.CLOVER));

    @Test
    @DisplayName("딜러가 뽑은 두 장의 카드 중 한 장만 오픈돼 있다.")
    void pickInitialCards() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.pickInitialCards(mustPickTen);

        //then
        List<Card> cards = dealer.getOpenedCards();

        assertThat(cards.size()).isEqualTo(1);
        assertThat(cards.getFirst().getDefaultScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("딜러의 점수가 16점을 초과하면 false를 반환한다.")
    void canPick() {
        //given
        Dealer dealer =  new Dealer();

        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickTen); // 10 + 10 = 20

        // when & then
        assertThat(dealer.canPick()).isFalse();
    }
}