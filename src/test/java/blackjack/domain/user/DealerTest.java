package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.strategy.DealerHitStrategy;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("딜러 생성 검증")
    @Test
    public void createDealer() {
        //given & when
        Dealer dealer = new Dealer();

        //then
        assertThat(dealer).isNotNull();
    }

    @DisplayName("딜러는 카드를 뽑을 수 있다.")
    @Test
    public void testDrawCard() {
        //given
        Deck deck = new Deck();
        Dealer dealer = new Dealer();

        //when
        dealer.drawCard(deck);
        List<Card> cards = dealer.getHandCards();

        //then
        assertThat(cards.size()).isEqualTo(1);
    }

    @DisplayName("딜러는 처음에 카드를 한 장만 보여준다.")
    @Test
    public void testShowInitCards() {
        //given
        Deck deck = new Deck();
        Dealer dealer = new Dealer();

        dealer.drawCard(deck);
        dealer.drawCard(deck);
        //when
        List<Card> cards = dealer.showInitCards();
        //then
        assertThat(cards.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러는 17점 미만이면 카드를 한장 더 받는다.")
    public void testDealerHitWithScoreLessThan17() {
        // given
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        DealerHitStrategy strategy = new DealerHitStrategy(dealer.getScore());
        // when
        boolean isHit = dealer.hitOrStay(deck, strategy);
        // then
        assertThat(isHit).isTrue();
    }
}
