package blackjack.service;

import blackjack.domain.RecordFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.RecordDto;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerService {

    private final Deck deck;
    private final Players players;

    public PlayerService(Deck deck, final List<String> playerNames) {
        this.deck = deck;
        this.players = new Players(playerNames);
    }

    public void prepare() {
        players.prepareGame(deck);
    }

    public List<String> findAllNames() {
        return players.getNames();
    }

    public List<ParticipantDto> findAllPlayers() {
        return players.getValue().stream()
                .map(ParticipantDto::of)
                .collect(Collectors.toList());
    }

    public boolean isPlayerTurn() {
        return players.isDrawablePlayerExist();
    }

    public String findDrawablePlayerName() {
        return players.findHitPlayer().getName();
    }

    public ParticipantDto progressTurn(final String playerName, final Status status) {
        final Player player = players.findByName(playerName);
        if (status == Status.HIT) {
            player.hit(deck);
        }
        if (status == Status.STAY) {
            player.stay();
        }

        return ParticipantDto.of(player);
    }

    public List<ParticipantResultDto> findAllResult() {
        return players.getValue().stream()
                .map(ParticipantResultDto::of)
                .collect(Collectors.toList());
    }

    public RecordDto getAllRecord(final int dealerScore) {
        final Map<String, Integer> playerResult = players.getValue().stream()
                .collect(Collectors.toMap(
                        Player::getName,
                        Player::getScore,
                        (a, b) -> b,
                        LinkedHashMap::new
                ));

        final RecordFactory factory = new RecordFactory(dealerScore, playerResult);
        return RecordDto.of(factory.getDealerRecord(), factory.getAllPlayerRecord());
    }
}
