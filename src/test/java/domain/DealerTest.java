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
}