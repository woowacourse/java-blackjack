package domain;

import static org.assertj.core.api.Assertions.assertThat;

import controller.dto.HandStatus;
import domain.constants.Score;
import domain.constants.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.OutputView;

class RoundTest {

    @DisplayName("참가자들에게 2장 씩 카드를 분배한다.")
    @Test
    void startBlackJack() {
        List<String> playerNames = Arrays.asList("pobi", "jason");
        BlackJackGame blackJackGame = BlackJackGame.from(playerNames);

        List<HandStatus> statuses = blackJackGame.initiateGameCondition();

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
        Players participants = new Players(dealer, players);

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Score.THREE, Shape.DIAMOND));

        BlackJackGame blackJackGame = new BlackJackGame(participants, new Deck(cards));

        // when
        blackJackGame.giveCardsToDealer(new OutputView());

        // then
        assertThat(blackJackGame.getDealer().getHand().size()).isEqualTo(3);
    }

    private List<Card> createNormalWithTwoCards() {
        return List.of(
                new Card(Score.TEN, Shape.CLOVER),
                new Card(Score.SIX, Shape.DIAMOND));
    }
}
