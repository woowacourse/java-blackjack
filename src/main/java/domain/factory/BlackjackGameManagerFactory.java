package domain.factory;

import domain.card.CardMachine;
import domain.game.BlackjackGameManager;
import domain.game.BlackjackJudge;
import domain.participant.BetAmount;
import domain.participant.Participants;
import domain.participant.PlayerName;
import java.util.List;

public class BlackjackGameManagerFactory {

    public BlackjackGameManager blackjackGameManager(List<PlayerName> playerNames, List<BetAmount> betAmounts) {
        return new BlackjackGameManager(new CardMachine(), new BlackjackJudge(), Participants.of(playerNames, betAmounts));
    }
}
