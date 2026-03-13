package domain;

import domain.card.CardDistributor;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CardDistributorTest {

    @Test
    @DisplayName("딜러와 모든 플레이어는 게임 시작 시, 2장의 카드를 받는다.")
    void 게임_시작시_2장_카드() {
        // given
        Players players = new Players(List.of(new Player("pobi"), new Player("james")));
        Dealer dealer = new Dealer();
        CardDistributor cardDistributor = new CardDistributor();

        // when
        cardDistributor.dealInitialCardsToParticipants(dealer, players);

        // then
        Assertions.assertEquals(dealer.getCards().size(), 2);
        for (Player player : players.getPlayers()) {
            Assertions.assertEquals(player.getCards().size(), 2);
        }
    }

    @Test
    @DisplayName("딜러가 카드를 받는다.")
    void dealCardToDealerTest() {
        // given
        Dealer dealer = new Dealer();
        CardDistributor cardDistributor = new CardDistributor();

        // when
        cardDistributor.dealCardTo(dealer);

        // then
        Assertions.assertEquals(dealer.getCards().size(), 1);
    }

    @Test
    @DisplayName("플레이어가 카드를 받는다.")
    void dealCardToPlayerTest() {
        // given
        Player player = new Player("pobi");
        CardDistributor cardDistributor = new CardDistributor();

        // when
        cardDistributor.dealCardTo(player);

        // then
        Assertions.assertEquals(player.getCards().size(), 1);
    }
}
