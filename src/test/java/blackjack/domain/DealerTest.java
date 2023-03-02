package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

class DealerTest {

    private Player player1;
    private Player player2;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player1 = new Player("1");
        player2 = new Player("2");
        dealer = new Dealer(new Players(List.of(player1, player2)));
    }

    @Test
    @DisplayName("참가자들에게 2장의 카드를 나눠준다.")
    void distributeTwoCards() {
        dealer.distributeTwoCards();

        assertAll(
                () -> assertThat(player1.getCards().getCards().size()).isEqualTo(2),
                () -> assertThat(player2.getCards().getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("딜러가 자신의 카드 2장을 가져온다.")
    void drawSelfCards() {
        dealer.drawSelfCards();

        assertThat(dealer.getCards().getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어에게 카드 한 장을 더 준다.")
    void giveOneMoreCard() {
        dealer.giveOneMoreCard(player1);

        assertThat(player1.getCards().getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("최종 승패를 결정한다.")
    void decideResult() {
        Player player3 = new Player("3");
        dealer = new Dealer(new Players(List.of(player1, player2, player3)));

        dealer.receiveCard(new Card(Number.THREE, Pattern.DIAMOND));
        dealer.receiveCard(new Card(Number.NINE, Pattern.CLUB));
        dealer.receiveCard(new Card(Number.EIGHT, Pattern.DIAMOND));

        player1.receiveCard(new Card(Number.TWO, Pattern.HEART));
        player1.receiveCard(new Card(Number.EIGHT, Pattern.SPADE));
        player1.receiveCard(new Card(Number.ACE, Pattern.CLUB));

        player2.receiveCard(new Card(Number.SEVEN, Pattern.CLUB));
        player2.receiveCard(new Card(Number.K, Pattern.SPADE));

        player3.receiveCard(new Card(Number.THREE, Pattern.HEART));
        player3.receiveCard(new Card(Number.NINE, Pattern.SPADE));
        player3.receiveCard(new Card(Number.EIGHT, Pattern.CLUB));

        Map<Player, Result> result = dealer.decideResult();
        assertAll(
                () -> assertThat(result.get(player1)).isEqualTo(Result.WIN),
                () -> assertThat(result.get(player2)).isEqualTo(Result.LOSE),
                () -> assertThat(result.get(player3)).isEqualTo(Result.DRAW)
        );
    }
}
