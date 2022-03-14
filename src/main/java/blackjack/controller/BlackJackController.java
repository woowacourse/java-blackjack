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
        return PlayerDto.from(blackJackGame.getDealer());
    }

    public List<PlayerDto> getInitPlayerInfo() {
        return blackJackGame.getInitPlayers()
                .stream()
                .map(PlayerDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean isAllPlayersEnd() {
        return blackJackGame.isAllPlayersEnd();
    }

    public PlayerDto getCurrentTurnPlayerInfo() {
        return PlayerDto.from(blackJackGame.getCurrentTurnPlayer());
    }

    public PlayerDto drawNextPlayer() {
        return PlayerDto.from(blackJackGame.drawNextPlayer());
    }

    public PlayerDto drawCurrentPlayer() {
        return PlayerDto.from(blackJackGame.drawCurrentPlayer());
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
