package domain;

import domain.deck.Deck;
import domain.dto.GameFinalResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;

public class GameManager {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public GameManager(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void registerPlayer(String name, String betAmount) {
        players.register(name, betAmount);
    }

    public void startGame() {
        deck.shuffle();
        drawInitialCards();
    }

    public GameInitialInfoDto getInitialInfo() {
        return GameInitialInfoDto.of(dealer, players);
    }

    public boolean canPlayerReceiveCard(String playerName) {
        return players.canReceiveCard(playerName);
    }

    public List<String> drawPlayerCard(String playerName) {
        return players.drawCardTo(playerName, deck.draw());
    }

    public boolean canDealerReceiveCard() {
        return dealer.canReceiveCard();
    }

    public void drawDealerCard() {
        dealer.receiveCard(deck.draw());
    }

    public List<GameScoreResultDto> getScoreResults() {
        return GameScoreResultDto.createGameScoreResults(dealer, players);
    }

    public GameFinalResultDto getFinalResult() {
        GameResult gameResult = GameResult.of(dealer, players);
        return GameFinalResultDto.of(dealer, gameResult);
    }

    private void drawInitialCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players.getAll()) {
                player.receiveCard(deck.draw());
            }
            dealer.receiveCard(deck.draw());
        }
    }
}
