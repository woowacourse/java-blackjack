package blackjack.domain.judgement;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardShape;
import blackjack.domain.common.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgementTest {

    private final Judgement judgement = new Judgement();

    @DisplayName("사용자와 딜러가 둘 다 버스트되면, 사용자가 진다.")
    @Test
    void allBust() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.FOUR, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

        Player player = new Player("pobi", new Money(1000));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.JACK, CardShape.DIAMOND));
        player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

        JudgementResult judgementResult = judgement.judgePlayer(dealer, player);

        assertThat(judgementResult).isEqualTo(JudgementResult.LOSE);
    }

    @DisplayName("사용자와 딜러가 둘 다 블랙잭이면, 무승부이다.")
    @Test
    void allBlackJack() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        Player player = new Player("pobi", new Money(1000));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        JudgementResult judgementResult = judgement.judgePlayer(dealer, player);

        assertThat(judgementResult).isEqualTo(JudgementResult.TIE);
    }

    @DisplayName("딜러만 블랙잭인 경우, 사용자가 진다.")
    @Test
    void onlyDealerBlackJack() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        Player player = new Player("pobi", new Money(1000));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));
        player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        JudgementResult judgementResult = judgement.judgePlayer(dealer, player);

        assertThat(judgementResult).isEqualTo(JudgementResult.LOSE);
    }

    @DisplayName("사용자만 블랙잭인 경우, 딜러가 진다.")
    @Test
    void onlyPlayerBlackJack() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.SIX, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.FIVE, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));

        Player player = new Player("pobi", new Money(1000));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        JudgementResult judgementResult = judgement.judgePlayer(dealer, player);

        assertThat(judgementResult).isEqualTo(JudgementResult.BLACKJACK_WIN);
    }

    @DisplayName("딜러만 버스트되면, 사용자가 이긴다.")
    @Test
    void onlyDealerBust() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.FOUR, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

        Player player = new Player("pobi", new Money(1000));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));

        JudgementResult judgementResult = judgement.judgePlayer(dealer, player);

        assertThat(judgementResult).isEqualTo(JudgementResult.WIN);
    }

    @DisplayName("사용자만 버스트되면, 딜러가 이긴다.")
    @Test
    void onlyPlayerBust() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

        Player player = new Player("pobi", new Money(1000));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.FOUR, CardShape.DIAMOND));
        player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

        JudgementResult judgementResult = judgement.judgePlayer(dealer, player);

        assertThat(judgementResult).isEqualTo(JudgementResult.LOSE);
    }

    @DisplayName("사용자와 딜러가 둘 다 버스트되지 않고, 딜러 점수가 더 낮으면 사용자가 이긴다.")
    @Test
    void whenDealerLose() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));

        Player player = new Player("pobi", new Money(1000));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        JudgementResult judgementResult = judgement.judgePlayer(dealer, player);

        assertThat(judgementResult).isEqualTo(JudgementResult.WIN);
    }

    @DisplayName("사용자와 딜러가 둘 다 버스트되지 않고, 딜러 점수가 더 높으면 사용자가 진다.")
    @Test
    void whenDealerWin() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        Player player = new Player("pobi", new Money(1000));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));

        JudgementResult judgementResult = judgement.judgePlayer(dealer, player);

        assertThat(judgementResult).isEqualTo(JudgementResult.LOSE);
    }

    @DisplayName("사용자와 딜러가 둘 다 버스트되지 않고, 같은 점수라면 무승부다.")
    @Test
    void whenTie() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

        Player player = new Player("pobi", new Money(1000));
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

        JudgementResult judgementResult = judgement.judgePlayer(dealer, player);

        assertThat(judgementResult).isEqualTo(JudgementResult.TIE);
    }
}
