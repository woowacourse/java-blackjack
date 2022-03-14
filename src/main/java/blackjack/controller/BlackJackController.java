package blackjack.controller;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.OutComeResult;
import blackjack.domain.participant.Participant;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayerFinalResultDto;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    private final BlackJackGame blackJackGame;

    public BlackJackController(final List<String> playerNames) {
        blackJackGame = BlackJackGame.init(playerNames);
    }

    public PlayerDto getInitDealerInfo() {
        return PlayerDto.dealerToInitInfo(blackJackGame.getDealer());
    }

    public List<PlayerDto> getInitPlayerInfo() {
        return blackJackGame.getInitPlayers()
                .stream()
                .map(PlayerDto::playerToInfo)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isAllPlayersEnd() {
        return blackJackGame.isAllPlayersEnd();
    }

    public PlayerDto getCurrentTurnPlayerInfo() {
        return PlayerDto.playerToInfo(blackJackGame.getCurrentTurnPlayer());
    }

    public PlayerDto drawNextPlayer() {
        return PlayerDto.playerToInfo(blackJackGame.drawNextPlayer());
    }

    public PlayerDto drawCurrentPlayer() {
        return PlayerDto.playerToInfo(blackJackGame.drawCurrentPlayer());
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
