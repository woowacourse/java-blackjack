package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import fixture.HandFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 16 이하이면 히트가 가능하다.")
    @Test
    void testCanHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal16();

        Dealer dealer = new Dealer(hand);

        // when
        boolean actual = dealer.canHit();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("딜러는 17점 이상이면 히트가 불가능하다.")
    @Test
    void testCanNotHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal21();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Dealer dealer = new Dealer(hand);

        // when
        boolean actual = dealer.canHit();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("플레이어의 승리는 딜러의 패배이고 플레이어의 패배는 딜러의 승리")
    @Test
    void testJudge() {
        // given
        Hand pobiHand = new Hand();
        pobiHand.append(new Card(CardRank.TWO, CardSuit.HEART));
        pobiHand.append(new Card(CardRank.EIGHT, CardSuit.SPADE));
        pobiHand.append(new Card(CardRank.ACE, CardSuit.CLUB));
        Player pobi = new Player(pobiHand, new PlayerName("pobi"));

        Hand jasonHand = new Hand();
        jasonHand.append(new Card(CardRank.SEVEN, CardSuit.CLUB));
        jasonHand.append(new Card(CardRank.KING, CardSuit.SPADE));
        Player jason = new Player(jasonHand, new PlayerName("jason"));

        Players players = new Players(List.of(pobi, jason));

        Hand dealerHand = new Hand();
        dealerHand.append(new Card(CardRank.THREE, CardSuit.DIAMOND));
        dealerHand.append(new Card(CardRank.NINE, CardSuit.CLUB));
        dealerHand.append(new Card(CardRank.EIGHT, CardSuit.DIAMOND));
        Dealer dealer = new Dealer(dealerHand);

        // when
        Result judge = dealer.judge(players);

        // then
        assertAll(
                () -> assertThat(judge.getWinCount()).isEqualTo(1),
                () -> assertThat(judge.getLoseCount()).isEqualTo(1)
        );
    }
}
