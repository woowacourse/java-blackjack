package domain;

import domain.card.CardRepository;
import domain.gameresult.GameResult;
import domain.gameresult.GameResultReadOnly;
import domain.player.PlayerReadOnly;
import domain.player.Players;
import domain.strategy.IndexGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {
    private final Players players;
    private final GameResult gameResult;
    private final CardRepository cardRepository;

    public BlackJack(String participantNames, IndexGenerator indexGenerator) {
        this.players = Players.with(initParticipantNames(participantNames));
        this.gameResult = new GameResult();
        this.cardRepository = CardRepository.create(indexGenerator);
    }

    private List<String> initParticipantNames(String participantNames) {
        return Arrays.stream(participantNames.split(","))
                .collect(Collectors.toUnmodifiableList());
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

    public List<PlayerReadOnly> getPlayers() {
        return players.getAllPlayers();
    }

    public List<PlayerReadOnly> getParticipants() {
        return players.getParticipants();
    }

    public GameResultReadOnly getGameResult() {
        return GameResultReadOnly.from(gameResult);
    }

    public void battle() {
        players.battleAll(gameResult);
    }
}
