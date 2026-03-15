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

    CardDeck mustPickFive = CardDeck.of(cards -> Card.createOpenedCard(Rank.FIVE, Suit.CLOVER));
    CardDeck mustPickTen = CardDeck.of(cards -> Card.createOpenedCard(Rank.TEN, Suit.CLOVER));
    CardDeck mustPickAce = CardDeck.of(cards -> Card.createOpenedCard(Rank.ACE, Suit.CLOVER));

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

    @Test
    @DisplayName("점수가 21 점을 초과하면 true를 반환한다.")
    void isBustTrue() {
        // given
        Dealer dealer =  new Dealer();

        // when
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickTen); // pick 30

        // then
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("점수가 21 점 이하이면 false를 반환한다.")
    void isBustFalse() {
        // given
        Dealer dealer =  new Dealer();

        // when
        dealer.pickAdditionalCard(mustPickTen); // pick 10

        // then
        assertThat(dealer.isBust()).isFalse();
    }

    @Test
    @DisplayName("초기 두 장의 카드가 21점이면 true를 반환한다.")
    void isBlackjackTrue() {
        // given
        Dealer dealer =  new Dealer();

        // when
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickAce);     // 10 + 11 = 21

        // then
        assertThat(dealer.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("초기 두 장의 카드가 21점이 아니몀ㄴ false를 반환한다.")
    void isBlackjackFalse() {
        // given
        Dealer dealer =  new Dealer();

        // when
        dealer.pickAdditionalCard(mustPickTen);
        dealer.pickAdditionalCard(mustPickFive);    //10 + 5 = 15

        // then
        assertThat(dealer.isBlackjack()).isFalse();
    }

    @Test
    @DisplayName("카드 덱에서 추가 1장을 더 가지고 온다.")
    void pickAdditionalCard() {
        // given
        Dealer dealer =  new Dealer();

        // when
        dealer.pickAdditionalCard(mustPickAce);

        // then
        assertThat(dealer.getAllCard().size()).isEqualTo(1);
        assertThat(dealer.getAllCard().getFirst().isAce()).isTrue();
    }
}