package blackjack.domain;

import blackjack.controller.dto.GameResultDto;
import blackjack.controller.dto.ParticipantResponseDto;
import blackjack.controller.dto.ParticipantsResponseDto;
import blackjack.controller.dto.PlayerRequestDto;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.ArrayList;
import java.util.List;

public class BlackjackManager {

    private final Dealer dealer;
    private final Players players;
    private int cursor = 0;

    public BlackjackManager(Players players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public BlackjackManager(List<PlayerRequestDto> playerRequestDtos) {
        this(Players.valueOf(playerRequestDtos));
    }

    public ParticipantsResponseDto initGame() {
        dealer.receiveFirstHand(dealer.drawCards());
        this.dealer.initHand(this.players);
        return createParticipantsResponseDto();
    }

    public boolean isDealerBlackjack() {
        return dealer.isBlackjack();
    }

    public boolean isFinishedAllOfPlayer() {
        return players.size() == cursor;
    }

    public boolean isCurrentPlayerBlackJack() {
        return currentPlayer().isBlackjack();
    }

    public void playerHitOrStay(UserAnswer userAnswer) {
        Player currentPlayer = currentPlayer();
        if (userAnswer.isStay()) {
            currentPlayer.stay();
            return;
        }
        currentPlayer.receiveCard(dealer.drawCard());
    }

    public ParticipantResponseDto getCurrentPlayerDto() {
        return ParticipantResponseDto.from(players.get(cursor));
    }

    public ParticipantsResponseDto createParticipantsResponseDto() {
        return ParticipantsResponseDto.of(this.dealer, this.players);
    }

    public List<GameResultDto> getGameResult() {
        List<GameResultDto> playersResultDtos = dealer.getPlayersResult(players);
        GameResultDto dealerResultDto = dealer.getDealerResult(playersResultDtos);

        List<GameResultDto> gameResultDtos = new ArrayList<>();
        gameResultDtos.add(dealerResultDto);
        gameResultDtos.addAll(playersResultDtos);
        return gameResultDtos;
    }

    public void nextTurn() {
        cursor++;
    }

    public boolean isCurrentPlayerFinished() {
        return currentPlayer().isFinished();
    }

    public boolean isCurrentPlayerBust() {
        return currentPlayer().isBust();
    }

    public boolean isDealerHit() {
        return dealer.isHit();
    }

    public void dealerDrawCard() {
        dealer.receiveCard(dealer.drawCard());
    }

    private Player currentPlayer() {
        return players.get(cursor);
    }
}
