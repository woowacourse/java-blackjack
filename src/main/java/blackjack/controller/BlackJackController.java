package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.OutComeResult;
import blackjack.domain.participant.Participant;
import blackjack.dto.CurrentTurnParticipant;
import blackjack.dto.GameResult;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    private final BlackJackGame blackJackGame;

    public BlackJackController(final List<String> playerNames) {
        blackJackGame = new BlackJackGame(playerNames);
    }

    public CurrentTurnParticipant getInitDealerInfo() {
        return new CurrentTurnParticipant(blackJackGame.getDealer());
    }

    public List<CurrentTurnParticipant> getInitPlayerInfo() {
        return blackJackGame.getInitPlayers()
                .stream()
                .map(CurrentTurnParticipant::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isAllPlayersEnd() {
        return blackJackGame.isAllPlayersEnd();
    }

    public CurrentTurnParticipant getCurrentTurnPlayerInfo() {
        return new CurrentTurnParticipant(blackJackGame.getCurrentTurnPlayer());
    }

    public CurrentTurnParticipant drawNextPlayer() {
        return new CurrentTurnParticipant(blackJackGame.drawNextPlayer());
    }

    public CurrentTurnParticipant drawCurrentPlayer() {
        return new CurrentTurnParticipant(blackJackGame.drawCurrentPlayer());
    }

    public boolean isDealerTurnEnd() {
        return blackJackGame.isDealerTurnEnd();
    }

    public void drawDealer() {
        blackJackGame.drawDealer();
    }

    public void stayDealer() {
        blackJackGame.stayDealer();
    }

    public List<GameResult> getPlayerResultInfos() {
        final List<Participant> participants = blackJackGame.getParticipants();
        return participants.stream()
                .map(GameResult::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public OutComeResult getWinningResult() {
        return blackJackGame.getWinningResult();
    }
}
