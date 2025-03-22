package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardGroup;
import domain.card.CardScore;
import domain.card.CardType;
import domain.gamer.Dealer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    void 딜러는_점수가_16점_이하인_경우_True를_반환한다() {
        List<Card> cards = List.of(new Card(CardType.DIAMOND, CardScore.JACK), new Card(CardType.DIAMOND, CardScore.TWO));
        CardGroup cardGroup = new CardGroup(cards);
        Dealer dealer = new Dealer(cardGroup);

        boolean status = dealer.canReceiveCard();

        assertThat(status).isTrue();
    }

    @Test
    void 딜러의_점수를_계산한다() {
        //given
        final Map<String, GameResult> playerResults = Map.of("윌슨", GameResult.DRAW, "가이온", GameResult.WIN);

        //when
        final Dealer dealer = new Dealer(new CardGroup(new ArrayList<>()));
        final Map<GameResult, Integer> result = dealer.calculateDealerGameResult(playerResults);

        //then
        assertThat(result).containsEntry(GameResult.DRAW, 1)
                .containsEntry(GameResult.LOSE, 1);
    }
}
