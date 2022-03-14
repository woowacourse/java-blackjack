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
        blackJackGame = BlackJackGame.init(playerNames);
    }

    public CurrentTurnParticipant getInitDealerInfo() {
        return CurrentTurnParticipant.from(blackJackGame.getDealer());
    }

    public List<CurrentTurnParticipant> getInitPlayerInfo() {
        return blackJackGame.getInitPlayers()
                .stream()
                .map(CurrentTurnParticipant::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isAllPlayersEnd() {
        return blackJackGame.isAllPlayersEnd();
    }

    public CurrentTurnParticipant getCurrentTurnPlayerInfo() {
        return CurrentTurnParticipant.from(blackJackGame.getCurrentTurnPlayer());
    }

    public CurrentTurnParticipant drawNextPlayer() {
        return CurrentTurnParticipant.from(blackJackGame.drawNextPlayer());
    }

    public CurrentTurnParticipant drawCurrentPlayer() {
        return CurrentTurnParticipant.from(blackJackGame.drawCurrentPlayer());
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
                .map(GameResult::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public OutComeResult getWinningResult() {
        return blackJackGame.getWinningResult();
    }
}
