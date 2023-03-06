package domain;

import domain.card.CardRepository;
import domain.gameresult.GameResult;
import domain.gameresult.GameResultReadOnly;
import domain.player.PlayerReadOnly;
import domain.player.Players;
import domain.player.PlayersReadOnly;

import java.util.List;

public class BlackJack {
    private final Players players;
    private final CardRepository cardRepository;

    public BlackJack(Players players, CardRepository cardRepository) {
        this.players = players;
        this.cardRepository = cardRepository;
    }

    public void initializeCardsOfPlayers() {
        for (int count = 0; count < 2; count++) {
            giveCardToAllPlayers();
        }
    }

    public void giveCardToAllPlayers() {
        for (String name : players.getAllPlayerNames()) {
            players.giveCardByName(name, cardRepository.findAnyOneCard());
        }
    }

    public void giveCard(PlayerReadOnly participant) {
        players.giveCardByName(participant.getName(), cardRepository.findAnyOneCard());
    }

    public boolean shouldDealerGetCard() {
        return players.shouldDealerGetCard();
    }

    public void giveCardToDealer() {
        players.giveCardToDealer(cardRepository.findAnyOneCard());
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
