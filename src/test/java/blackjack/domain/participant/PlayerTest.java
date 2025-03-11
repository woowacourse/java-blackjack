package blackjack.domain.participant;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;

import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("카드가 21이 초과하지 않는다면 카드를 더 뽑을 수 있다.")
    @Test
    void testPlayerCanDrawCard() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("user1", cardHand);

        // when
        boolean canHit = player.canHit();

        // then
        assertThat(canHit).isTrue();
    }

    @DisplayName("카드가 21이 초과한다면 카드를 더 뽑을 수 없다.")
    @Test
    void testPlayerCanDrawCard_false() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.EIGHT));

        Player player = new Player("user1", cardHand);

        // when
        boolean canHit = player.canHit();

        // then
        assertThat(canHit).isFalse();
    }

    @DisplayName("플레이어는 처음에 받은 2장의 카드를 모두 보여준다.")
    @Test
    void test_showStartCards() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Player player = new Player("user1", cardHand);

        // when
        List<Card> startCards = player.showStartCards();

        // then
        assertThat(startCards).hasSize(2);
    }

}