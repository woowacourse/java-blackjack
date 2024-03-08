import static org.assertj.core.api.Assertions.assertThat;

import controller.Round;
import controller.dto.HandStatus;
import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.Participant;
import domain.Player;
import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoundTest {

    @DisplayName("참가자들에게 2장 씩 카드를 분배한다.")
    @Test
    void startBlackJack() {
        List<String> playerNames = Arrays.asList("pobi", "jason");
        Round round = new Round(playerNames);

        List<HandStatus> statuses = round.initiateGameCondition();

        for (HandStatus status : statuses) {
            assertThat(status.hand().getCards()).hasSize(2);
        }
    }

    @DisplayName("딜러의 점수가 16 이하인동안 반복해서 카드를 받는다.")
    @Test
    void giveCardsUntilDealerScoreOverThreshold() {
        // given
        Dealer dealer = new Dealer();
        dealer.drawCards(createNormalWithTwoCards());

        List<Player> players = List.of(new Player("pobi"));
        Participant participant = new Participant(dealer, players);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Score.THREE, Shape.DIAMOND));

        Round round = new Round(participant, new Deck(cards));

        // when
        int count = round.giveCardsToDealer();

        // then
        assertThat(count).isEqualTo(1);
    }

    private List<Card> createNormalWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND));
    }
}
