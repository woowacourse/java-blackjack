package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.TestUtil;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {
    
    @DisplayName("참가자의 카드 덱의 최종 점수를 계산한다")
    @Test
    void testPlayerTotalCardScore() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.EIGHT)); //17

        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        int totalScore = player.getScore().intValue();
        assertThat(totalScore).isEqualTo(17);
    }

    @DisplayName("참가자의 카드 덱에 에이스가 있고, 버스트가 아닐 때, 최대 점수를 계산한다")
    @Test
    void testPlayerTotalCardScore_hasAce_noBust() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.ACE)); //11 -> 20

        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        int totalScore = player.getScore().intValue();
        assertThat(totalScore).isEqualTo(20);
    }

    @DisplayName("참가자의 카드 덱에 에이스가 있고, 버스트일 때, 에이스를 1로 계산한다")
    @Test
    void testPlayerTotalCardScore_hasAce_Bust() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); //16
        cardHand.add(new Card(CardSuit.CLUB, CardRank.ACE)); //1선택 -> 17

        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        int totalScore = player.getScore().intValue();
        assertThat(totalScore).isEqualTo(17);
    }

    @DisplayName("참가자의 카드 덱에 에이스가 여러 개인 있는 경우 알맞은 합을 반환")
    @Test
    void testPlayerTotalCardScore_hasMultipleAce() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.ACE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));

        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        int totalScore = player.getScore().intValue();
        assertThat(totalScore).isEqualTo(21);
    }

    @DisplayName("참가자는 자신의 카드 덱에 카드를 추가할 수 있다")
    @Test
    void testPlayerAddCard() {
        // given
        CardHand cardHand = new CardHand();
        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        player.receiveCard(new Card(CardSuit.CLUB, CardRank.ACE));

        // then
        assertThat(cardHand.deckSize()).isEqualTo(1);
    }

    @DisplayName("참가자의 점수가 21이 초과될 시 버스트상태이다.")
    @Test
    void test_isBust() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.KING));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.TWO));
        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        boolean bust = player.isBust();

        // then
        assertThat(bust).isTrue();
    }

    @DisplayName("참가자의 점수가 21이하면 버스트가 아니다.")
    @Test
    void test_isBust_false() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.KING));
        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        boolean bust = player.isBust();

        // then
        assertThat(bust).isFalse();
    }

    @DisplayName("참가자의 점수가 2장으로 이루어진 21이면 블랙잭이다.")
    @Test
    void test_isBlackjack() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.ACE));

        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        boolean blackjack = player.isBlackjack();

        // then
        assertThat(blackjack).isTrue();
    }

    @DisplayName("참가자의 점수가 21이지만 2장으로 이루어져있지 않으면 블랙잭이 아니다.")
    @Test
    void test_isBlackjack_false() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.FIVE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SIX));

        Player player = TestUtil.createPlayerOf("player", cardHand);

        // when
        boolean blackjack = player.isBlackjack();
        // then
        assertThat(blackjack).isFalse();
    }
}