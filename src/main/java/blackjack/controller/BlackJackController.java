package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.OutComeResult;
import blackjack.domain.participant.Participant;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerFinalResultDto;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    private final BlackJackGame blackJackGame;

    public BlackJackController(final List<String> playerNames) {
        blackJackGame = BlackJackGame.init(playerNames);
    }

    public ParticipantDto getInitDealerInfo() {
        return ParticipantDto.from(blackJackGame.getDealer());
    }

    public List<ParticipantDto> getInitPlayerInfo() {
        return blackJackGame.getInitPlayers()
                .stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isAllPlayersEnd() {
        return blackJackGame.isAllPlayersEnd();
    }

    public ParticipantDto getCurrentTurnPlayerInfo() {
        return ParticipantDto.from(blackJackGame.getCurrentTurnPlayer());
    }

    public ParticipantDto drawNextPlayer() {
        return ParticipantDto.from(blackJackGame.drawNextPlayer());
    }

    public ParticipantDto drawCurrentPlayer() {
        return ParticipantDto.from(blackJackGame.drawCurrentPlayer());
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

    public List<PlayerFinalResultDto> getPlayerResultInfos() {
        final List<Participant> participants = blackJackGame.getParticipants();
        return participants.stream()
                .map(PlayerFinalResultDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public OutComeResult getWinningResult() {
        return blackJackGame.getWinningResult();
    }
}
