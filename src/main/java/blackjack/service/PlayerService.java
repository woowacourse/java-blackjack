package blackjack.service;

import blackjack.domain.Record;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.PlayerRecordDto;
import blackjack.view.PlayerAnswer;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerService {

    private final Deck deck;
    private final Players players;

    public PlayerService(Deck deck, final List<String> playerNames) {
        this.deck = deck;
        this.players = new Players(playerNames);
    }

    public void prepareGame() {
        players.prepareGame(deck);
    }

    public List<String> findAllNames() {
        return players.getNames();
    }

    public List<ParticipantDto> findAllPlayers() {
        return players.getValue().stream()
                .map(ParticipantDto::from)
                .collect(Collectors.toList());
    }

    public boolean isPlayerTurn() {
        return players.isDrawablePlayerExist();
    }

    public String findDrawablePlayerName() {
        return players.findHitPlayer().getName();
    }

    public ParticipantDto progressTurn(final String playerName, final PlayerAnswer playerAnswer) {
        final Player player = players.findByName(playerName);
        if (playerAnswer.isDraw()) {
            player.hit(deck);
        }
        if (!playerAnswer.isDraw()) {
            player.stay();
        }

        return ParticipantDto.from(player);
    }

    public List<ParticipantResultDto> findAllResult() {
        return players.getValue().stream()
                .map(ParticipantResultDto::from)
                .collect(Collectors.toList());
    }

    public List<PlayerRecordDto> getRecord(final int dealerScore) {
        return players.getValue().stream()
                .map(player -> PlayerRecordDto.of(player.getName(), Record.of(dealerScore, player.getScore())))
                .collect(Collectors.toList());
    }
}
