package blackjack.service;

import blackjack.domain.RecordFactory;
import blackjack.domain.card.CardFactory;
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

public class Casino {

    private final CardFactory cardFactory;
    private final Dealer dealer;
    private final Players players;

    public Casino(final List<String> playerNames) {
        this.cardFactory = CardFactory.create();
        this.dealer = new Dealer();
        this.players = new Players(playerNames);
    }

    public void prepareParticipants() {
        dealer.prepareGame(cardFactory);
        players.prepareGame(cardFactory);
    }

    public List<String> getPlayersNames() {
        return players.getNames();
    }

    public CardDto findDealerFirstCard() {
        return CardDto.of(dealer.openFirstCard());
    }

    public List<ParticipantDto> findAllParticipants() {
        final List<ParticipantDto> list = players.getValue().stream()
                .map(ParticipantDto::of)
                .collect(Collectors.toList());
        list.add(0, ParticipantDto.of(dealer));

        return list;
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
            player.hit(cardFactory);
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
            dealer.hit(cardFactory);
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
