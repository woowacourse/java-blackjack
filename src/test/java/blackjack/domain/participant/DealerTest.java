package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class DealerTest {

    private Player player1;
    private Player player2;
    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        players = new Players(List.of("1", "2", "3"));
        dealer = new Dealer();
        player1 = players.getPlayers().get(0);
        player2 = players.getPlayers().get(1);
    }

    @Test
    @DisplayName("참가자들과 본인에게 2장의 카드를 나눠준다.")
    void distributeTwoCards() {
        dealer.settingCards(players);

        assertAll(
                () -> assertThat(dealer.getCards().size()).isEqualTo(2),
                () -> assertThat(player1.getCards().size()).isEqualTo(2),
                () -> assertThat(player2.getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("플레이어에게 카드 한 장을 더 준다.")
    void giveOneMoreCard() {
        dealer.giveCard(player1);

        assertThat(player1.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 점수가 16점 이하이면 16점을 초과할 때까지 계속해서 카드를 뽑는다.")
    void canDrawTest() {
        dealer.receiveCard(new Card(Number.TWO, Suit.HEART));
        dealer.receiveCard(new Card(Number.THREE, Suit.HEART));
        int initSize = dealer.getCards().size();

        while (dealer.canDraw()) {
            dealer.receiveCard(dealer.drawCard());
        }

        assertAll(
                () -> assertThat(initSize).isNotEqualTo(dealer.getCards().size()),
                () -> assertThat(dealer.totalScore()).isGreaterThan(16)
        );
    }


}
