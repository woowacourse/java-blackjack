import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackjackGame;
import domain.BlackjackGameResult;
import domain.Card;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.Result;
import domain.TrumpCardNumber;
import domain.TrumpCardType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlakcGameResultTest {
    private static final Card DIAMOND_TEN = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.TEN);
    private static final Card DIAMOND_NINE = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.NINE);
    private static final Card SPADE_NINE = new Card(TrumpCardType.SPADE, TrumpCardNumber.NINE);
    private static final Card DIAMOND_EIGHT = new Card(TrumpCardType.DIAMOND, TrumpCardNumber.EIGHT);

    @DisplayName("블랙잭 게임으로 게임의 결과가 생성된다.")
    @Test
    void createBlackjackGameResultSuccessTest() {
        BlackjackGame blackjackGame = TestDataManager.getShuffledBlackjackGame();
        giveCardsTo(blackjackGame.getDealer(), List.of(DIAMOND_TEN, DIAMOND_NINE)); // 19
        List<Player> players = blackjackGame.getPlayers().getPlayers();
        giveCardsTo(players.get(0), List.of(DIAMOND_TEN, SPADE_NINE)); // 19
        giveCardsTo(players.get(1), List.of(DIAMOND_TEN, DIAMOND_TEN)); // 20
        giveCardsTo(players.get(2), List.of(DIAMOND_TEN, DIAMOND_EIGHT)); // 18

        BlackjackGameResult blackjackGameResult = BlackjackGameResult.from(blackjackGame);

        assertThat(blackjackGameResult.getDealerResultsAgainstParticipants().values())
                .containsExactly(Result.DRAW, Result.LOSE, Result.WIN);
    }

    public void giveCardsTo(Participant participant, List<Card> cards) {
        for (Card card : cards) {
            participant.receive(card);
        }
    }
}
