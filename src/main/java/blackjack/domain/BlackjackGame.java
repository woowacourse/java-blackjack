package blackjack.domain;

import static blackjack.domain.DrawCommand.*;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.HoldingCard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private final Participants players;
    private final Participant dealer;

    public BlackjackGame(List<String> playersNames) {
        this.dealer = new Dealer(List.of(CardDeck.drawCard()));
        this.players = new Participants(createPlayers(playersNames));
    }

    private List<Participant> createPlayers(List<String> playersNames) {
        return playersNames.stream()
                .map(playerName -> new Player(playerName.trim(), CardDeck.drawTwoCards()))
                .collect(Collectors.toList());
    }

    public boolean isAllPlayerFinished() {
        return players.isFinished();
    }

    public HoldingCard drawCurrentPlayer(DrawCommand drawCommand) {
        Participant currentPlayer = players.getCurrentPlayer();
        if (drawCommand == YES) {
            currentPlayer.receiveCard(CardDeck.drawCard());
        }
        if (drawCommand == NO || currentPlayer.isBust()) {
            players.skipTurn();
        }
        return currentPlayer.getHoldingCard();
    }

    public int dealerFinishGame() {
        int dealerGainCard = 0;
        dealer.receiveCard(CardDeck.drawCard());
        while (!dealer.isFinished()) {
            dealer.receiveCard(CardDeck.drawCard());
            dealerGainCard++;
        }
        return dealerGainCard;
    }

    public String getCurrentPlayerName() {
        return players.getCurrentPlayerName();
    }

    public List<ParticipantDto> getParticipantsDto() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(ParticipantDto.of(dealer));
        for (Participant player : players.getParticipants()) {
            participantDtos.add(ParticipantDto.of(player));
        }
        return participantDtos;
    }

    public Map<GameResult, Integer> getDealerResult() {
        int score = dealer.calculateScore();
        return players.getDealerGameResult(score);
    }

    public Map<String, GameResult> getPlayersResult() {
        int score = dealer.calculateScore();
        return players.getPlayersGameResult(score);
    }
}
