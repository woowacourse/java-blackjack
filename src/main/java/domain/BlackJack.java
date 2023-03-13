package domain;

import domain.card.DeckOfCards;
import domain.gameresult.GameResult;
import domain.player.Dealer;
import domain.player.PlayerReadOnly;
import domain.player.Players;
import domain.player.PlayersReadOnly;

import java.util.List;

public class BlackJack {
    public static final int INITIALIZING_CARD_COUNT = 2;

    private final Players players;
    private final DeckOfCards deckOfCards;

    public BlackJack(Players players, DeckOfCards deckOfCards) {
        this.players = players;
        this.deckOfCards = deckOfCards;
    }

    public void initializeCardsOfPlayers() {
        for (int count = 0; count < INITIALIZING_CARD_COUNT; count++) {
            giveCardToAllPlayers();
        }
    }

    public void giveCardToAllPlayers() {
        for (String name : players.getAllPlayerNames()) {
            players.giveCardByName(name, deckOfCards.findAnyOneCard());
        }
    }

    public void giveCard(PlayerReadOnly participant) {
        players.giveCardByName(participant.getName(), deckOfCards.findAnyOneCard());
    }

    public boolean shouldDealerGetCard() {
        return players.shouldDealerGetCard();
    }

    public void giveCardToDealer() {
        Dealer dealer = players.getDealer();
        giveCard(PlayerReadOnly.from(dealer));
    }

    public PlayersReadOnly getPlayers() {
        return PlayersReadOnly.from(players);
    }

    public List<PlayerReadOnly> getParticipants() {
        return PlayersReadOnly.from(players)
                .getParticipants();
    }

    public GameResult battle() {
        return GameResult.from(players.battleAll());
    }
}
