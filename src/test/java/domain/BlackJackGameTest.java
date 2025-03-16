package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Hand;
import domain.card.StaticCardGenerator;
import domain.participant.Dealer;
import domain.participant.Money;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    void 플레이어들의_수익을_조회한다() {
        // given
        Hand playerHand1 = Hand.of(List.of(
                new Card(CardNumber.NINE, CardShape.CLOVER),
                new Card(CardNumber.A, CardShape.CLOVER)
        ));
        Hand playerHand2 = Hand.of(List.of(
                new Card(CardNumber.FIVE, CardShape.CLOVER),
                new Card(CardNumber.A, CardShape.CLOVER)
        ));
        Hand dealerHand = Hand.of(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.SEVEN, CardShape.CLOVER)
        ));
        Player player1 = Player.of(playerHand1, "player1", Money.of(100000));
        Player player2 = Player.of(playerHand2, "player2", Money.of(100000));
        Players players = Players.of(List.of(player1, player2));

        Dealer dealer = Dealer.of(dealerHand, new StaticCardGenerator());

        BlackJackGame game = BlackJackGame.of(dealer, players);

        Map<Player, Money> expected = Map.of(player1, Money.of(100000), player2, Money.of(-100000));
        // when
        Map<Player, Money> actual = game.calculateRevenue();

        //then
        assertThat(actual).isEqualTo(expected);
    }
}