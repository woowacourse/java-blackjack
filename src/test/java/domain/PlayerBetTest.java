package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerBetTest {

    @Test
    @DisplayName("플레이어 카드가 블랙잭인 경우 배팅 금액의 1.5배를 받는다.")
    void player_blackjack_receives_one_and_half_times_bet() {
        List<Card> blackjackCards = List.of(Card.of(Rank.ACE, Suit.SPADE), Card.of(Rank.J, Suit.HEART));
        String userName = "pobi";
        Player player = Player.of(userName);
        PlayerBet playerBet = PlayerBet.of(player, 10000);

        player.addInitialCards(blackjackCards);
        playerBet.applyProfitIfBlackjack();

        assertThat(playerBet.amount()).isEqualTo(15000);
    }

}