package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.card.Hand;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("플레이어들의 승패를 결정한다")
    @Test
    void test() {
        //given
        Hand hand1 = Hand.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.THREE, CardShape.CLOVER)
                )
        );
        Hand hand2 = Hand.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.TWO, CardShape.CLOVER)
                )
        );
        Hand hand3 = Hand.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.FOUR, CardShape.CLOVER)
                )
        );
        Dealer dealer = Dealer.of(hand1, ArrayList::new);
        Player player1 = Player.from("플레이어", hand2);
        Player player2 = Player.from("플레이어2", hand3);
        Map<Player, GameResult> expected = Map.of(
                player1, GameResult.LOSE,
                player2, GameResult.WIN
        );

        //when
        Map<Player, GameResult> actual = dealer.getGameResult(new Players(List.of(player1, player2)));

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어에게 카드를 분배한다")
    @Test
    void test2() {
        //given
        Dealer dealer = Dealer.init(() -> new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.A, CardShape.CLOVER),
                new Card(CardNumber.FOUR, CardShape.CLOVER)
        )));
        Hand dealerHand = Hand.of(
                List.of(
                        new Card(CardNumber.FOUR, CardShape.CLOVER),
                        new Card(CardNumber.A, CardShape.CLOVER)
                )
        );
        Dealer expectedDealer = Dealer.of(dealerHand, ArrayList::new);
        Player player = Player.init("플레이어1");
        Players players = new Players(List.of(player));
        Player expectedPlayer = Player.from("플레이어1", Hand.of(List.of(
                new Card(CardNumber.TWO, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        )));
        Players expectedPlayers = new Players(List.of(expectedPlayer));
        //when
        dealer.handoutCards(players);

        //then
        assertThat(dealer).isEqualTo(expectedDealer);
        assertThat(players).isEqualTo(expectedPlayers);
    }

    @DisplayName("딜러가 플레이어에게 카드 1장은 준다")
    @Test
    void test3() {
        //given
        Dealer dealer = Dealer.init(() -> new ArrayList<>(List.of(
                new Card(CardNumber.A, CardShape.CLOVER)
        )));
        Player player = Player.init("플레이어1");
        Player expectedPlayer = Player.from("플레이어1", Hand.of(List.of(
                new Card(CardNumber.A, CardShape.CLOVER)
        )));
        //when
        dealer.giveCards(player, 1);
        //then
        assertThat(player).isEqualTo(expectedPlayer);
    }

    @DisplayName("딜러는 합이 17 이상이 될 때까지 카드를 뽑는다")
    @Test
    void test4() {
        //given
        Dealer dealer = Dealer.init(() -> new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardShape.CLOVER),
                new Card(CardNumber.SEVEN, CardShape.CLOVER)
        )));
        Dealer expectedDealer = Dealer.of(Hand.of(List.of(
                new Card(CardNumber.SEVEN, CardShape.CLOVER),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        )), ArrayList::new);
        //when
        dealer.drawUntilLimit();
        //then
        assertThat(dealer).isEqualTo(expectedDealer);
    }
}