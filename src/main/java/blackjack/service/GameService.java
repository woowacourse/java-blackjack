package blackjack.service;

import blackjack.domain.RecordFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Status;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.CardDto;
import blackjack.dto.DealerTurnResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantResultDto;
import blackjack.dto.RecordDto;
import java.util.List;
import java.util.stream.Collectors;

public class GameService {

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public GameService(final List<String> playerNames) {
        this.deck = Deck.create();
        this.dealer = new Dealer();
        this.players = new Players(playerNames);
    }

    public void prepareParticipants() {
        dealer.prepareGame(deck);
        players.prepareGame(deck);
    }

    public List<String> getPlayersNames() {
        return players.getNames();
    }

    public CardDto findDealerFirstCard() {
        return CardDto.of(dealer.openFirstCard());
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

    public ParticipantDto progressPlayerTurn(final String playerName, final Status status) {
        final Player player = players.findByName(playerName);
        if (status == Status.HIT) {
            player.hit(deck);
        }
        if (status == Status.STAY) {
            player.stay();
        }

        return ParticipantDto.of(player);
    }

    public DealerTurnResultDto progressDealerTurn() {
        if (!dealer.canDrawCard()) {
            return new DealerTurnResultDto(0);
        }

        int count = 0;
        do {
            dealer.hit(deck);
            count++;
        } while (dealer.canDrawCard());

        return new DealerTurnResultDto(count);
    }

    public List<ParticipantResultDto> getAllResult() {
        final List<ParticipantResultDto> list = players.getValue().stream()
                .map(ParticipantResultDto::of)
                .collect(Collectors.toList());
        list.add(0, ParticipantResultDto.of(dealer));

        return list;
    }

    public RecordDto getAllRecord() {
        final RecordFactory factory = new RecordFactory(dealer.getScore(), players.getValue());
        return RecordDto.of(factory.getDealerRecord(), factory.getAllPlayerRecord());
    }
}
