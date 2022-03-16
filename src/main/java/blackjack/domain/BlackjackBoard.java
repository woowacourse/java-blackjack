package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.HoldingCard;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.ParticipantDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackBoard {
    private final Players players;
    private final Participant dealer;
    private final CardDeck cardDeck;

    public BlackjackBoard(CardDeck cardDeck, List<Player> players) {
        this.cardDeck = cardDeck;
        this.dealer = new Dealer();
        this.players = new Players(players);
        distributeCards();
    }

    private void distributeCards() {
        dealer.receiveCard(cardDeck.drawCard());
        players.getPlayers().forEach(player -> player.receiveCards(cardDeck.drawCard(2)));
    }

    public boolean isAllPlayerFinished() {
        return players.isFinished();
    }

    public HoldingCard drawCurrentPlayer(DrawCommand drawCommand) {
        Participant currentPlayer = players.getCurrentPlayer();
        if (drawCommand.isHit()) {
            currentPlayer.receiveCard(cardDeck.drawCard());
        }
        if (!drawCommand.isHit() || currentPlayer.isBust()) {
            players.skipTurn();
        }
        return currentPlayer.getHoldingCard();
    }

    public int dealerFinishGame() {
        int dealerGainCard = 0;
        dealer.receiveCard(cardDeck.drawCard());
        while (!dealer.isFinished()) {
            dealer.receiveCard(cardDeck.drawCard());
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
        for (Participant player : players.getPlayers()) {
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
