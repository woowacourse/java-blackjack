package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {
    private final Participants players;
    private final Participant dealer;

    public BlackJackGame(List<String> playersNames) {
        this.dealer = new Dealer(List.of(CardDeck.drawCard()));
        this.players = new Participants(createPlayers(playersNames));
    }

    private List<Participant> createPlayers(List<String> playersNames) {
        return playersNames.stream()
                .map(playerName -> new Player(playerName.trim(), CardDeck.drawTwoCards()))
                .collect(Collectors.toList());
    }

    public List<ParticipantDto> getParticipantsDto() {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        participantDtos.add(ParticipantDto.of(dealer));
        for (Participant player : players.getParticipants()) {
            participantDtos.add(ParticipantDto.of(player));
        }
        return participantDtos;
    }

    public boolean isAllPlayerFinished() {
        return players.isFinished();
    }

    public String getCurrentPlayerName() {
        return players.getCurrentPlayer().getName();
    }

    public HoldingCard drawCurrentPlayer(DrawCommand drawCommand) {
        Participant currentPlayer = players.getCurrentPlayer();
        if (drawCommand == DrawCommand.YES) {
            currentPlayer.receiveCard(CardDeck.drawCard());
        }
        if (drawCommand == DrawCommand.NO || currentPlayer.isBust()) {
            players.skipTurn();
        }
        return currentPlayer.getHoldingCard();
    }

    public void dealerFinishGame() {
        dealer.receiveCard(CardDeck.drawCard());
        while (!dealer.isFinished()) {
            dealer.receiveCard(CardDeck.drawCard());
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
        System.out.println();
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
