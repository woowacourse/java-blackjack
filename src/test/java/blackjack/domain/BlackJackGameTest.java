package blackjack.domain;

import blackjack.domain.betting.BettingAmount;
import blackjack.domain.betting.BettingAreas;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BlackJackGameTest {

    private static BlackJackGame blackJackGame;

    @BeforeEach
    void setUp() {
        Participants participants = new Participants(List.of("IO", "Bada"));
        Map<Player, BettingAmount> bettingAmountMap = Map.of(
                participants.getPlayers().get(0), new BettingAmount(1000),
                participants.getPlayers().get(1), new BettingAmount(3000)
        );

        BettingAreas bettingAreas = new BettingAreas(bettingAmountMap);
        blackJackGame = new BlackJackGame(participants, bettingAreas);
    }

    @Test
    @DisplayName("시작 시 모든 참가자에게 카드를 두장씩 드로우 기능 테스트")
    void drawInitialCardsTest() {
        blackJackGame.drawInitialCards();

        List<Participant> allParticipants = blackJackGame.getParticipants().getAllParticipants();
        allParticipants.forEach(participant ->
                assertThat(participant.getCards().size()).isEqualTo(2));
    }

    @Test
    @DisplayName("참가자에게 카드 한장 드로우 기능 테스트")
    void drawNewCardToDealerTest() {
        Participant participant = blackJackGame.getParticipants().getAllParticipants().get(0);
        int oldCardCount = participant.getCards().size();

        blackJackGame.drawNewCard(participant);

        assertThat(participant.getCards().size()).isEqualTo(oldCardCount + 1);
    }
}
