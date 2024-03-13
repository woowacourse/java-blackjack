package domain.participant;

import domain.blackjack.WinStatus;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @DisplayName("딜러의 점수가 16이하이면 카드를 받는다.")
    @Test
    void shouldHit() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.KING));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SIX));

        Assertions.assertThat(dealer.canHit()).isTrue();
    }

    @DisplayName("딜러의 점수가 17이상이면 카드를 받을 수 없다.")
    @Test
    void ShoulNotdHit() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.KING));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SEVEN));

        Assertions.assertThat(dealer.canHit()).isFalse();
    }

    @DisplayName("참가자와 딜러 중 점수가 높은 사람이 승리한다.")
    @Test
    void isWinner() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));

        Player player = new Player(new Name("one"));
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.HEART, Rank.TEN));

        WinStatus winStatus = dealer.calculatePlayerWinStatus(player);
        assertThat(winStatus).isEqualTo(WinStatus.WIN);
    }

    @DisplayName("참가자와 딜러의 점수가 같은 경우 무승부(DRAW)로 처리한다.")
    @Test
    void isWinnerWhenAllBlackJack() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.QUEEN));
        dealer.receiveCard(new Card(Shape.HEART, Rank.ACE));

        Player player = new Player(new Name("one"));
        player.receiveCard(new Card(Shape.HEART, Rank.KING));
        player.receiveCard(new Card(Shape.DIA, Rank.ACE));

        WinStatus winStatus = dealer.calculatePlayerWinStatus(player);
        assertThat(winStatus).isEqualTo(WinStatus.PUSH);
    }
}
