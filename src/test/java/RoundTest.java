import static org.assertj.core.api.Assertions.assertThat;

import controller.Round;
import domain.Card;
import domain.Dealer;
import domain.Deck;
import domain.Gamer;
import domain.Participant;
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
        List<String> playerNames = List.of("pobi");
        Dealer dealer = new Dealer();

        List<Gamer> gamers = playerNames.stream()
                .map(Gamer::new)
                .toList();
        Participant participant = new Participant(dealer, gamers);

        Deck fourCards = createNormalWithFourCards();

        Round round = new Round(participant, fourCards);
        round.initiateGameCondition();

        assertThat(dealer.getHandSize()).isEqualTo(2);
        assertThat(gamers.get(0).getHandSize()).isEqualTo(2);
    }

    @DisplayName("딜러의 점수가 16 이하인동안 반복해서 카드를 받는다.")
    @Test
    void giveCardsUntilDealerScoreOverThreshold() {
        // given
        Deck twoCards = createNormalWithTwoCards();

        Dealer dealer = new Dealer();
        for (int i = 0; i < 2; i++) {
            dealer.pickOneCard(twoCards);
        }

        List<Gamer> gamers = List.of(new Gamer("pobi"));
        Participant participant = new Participant(dealer, gamers);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Score.THREE, Shape.DIAMOND));

        Round round = new Round(participant, new Deck(cards));

        // when
        int count = round.giveCardsToDealer();

        // then
        assertThat(count).isEqualTo(1);
    }

    private Deck createNormalWithTwoCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND)
        )));
    }

    private Deck createNormalWithFourCards() {
        return new Deck(new ArrayList<>(Arrays.asList(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND),
                new Card(Score.THREE, Shape.DIAMOND),
                new Card(Score.FIVE, Shape.DIAMOND)
        )));
    }
}
