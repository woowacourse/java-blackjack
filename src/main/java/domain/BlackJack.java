package domain;

import domain.card.CardRepository;
import domain.gameresult.GameResult;
import domain.gameresult.GameResultReadOnly;
import domain.player.Name;
import domain.player.PlayerReadOnly;
import domain.player.Players;
import domain.player.PlayersReadOnly;
import domain.strategy.IndexGenerator;

import java.util.List;

public class BlackJack {
    private final Players players;
    private final CardRepository cardRepository;

    public BlackJack(List<Name> participantNames, IndexGenerator indexGenerator) {
        this.players = Players.with(participantNames);
        this.cardRepository = CardRepository.create(indexGenerator);
    }

    public void giveTwoCardToPlayers() {
        players.giveCardToAll(cardRepository);
        players.giveCardToAll(cardRepository);
    }

    public void giveCard(PlayerReadOnly participant) {
        players.giveCardByName(participant.getName(), cardRepository.findAnyOneCard());
    }

    public boolean shouldDealerGetCard() {
        return players.shouldDealerGetCard();
    }

    public void giveCardToDealer() {
        players.giveCardToDealer(cardRepository);
    }

    public PlayersReadOnly getPlayers() {
        return PlayersReadOnly.from(players);
    }

    public List<PlayerReadOnly> getParticipants() {
        return PlayersReadOnly.from(players)
                .getParticipants();
    }

    public GameResultReadOnly battle() {
        GameResult gameResult = new GameResult();
        players.battleAll(gameResult);
        return GameResultReadOnly.from(gameResult);
    }
}
