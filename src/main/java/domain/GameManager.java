package domain;

import domain.deck.Deck;
import domain.dto.GameFinalResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.*;

public class GameManager {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public GameManager(Deck deck, Dealer dealer, Players players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = players;
    }

    public void registerPlayer(String name) {
        players.register(name);
    }

    public void startGame() {
        deck.shuffle();
        drawInitialCards();
    }

    public GameInitialInfoDto getInitialInfo() {
        return new GameInitialInfoDto(
                dealer.getName(),
                dealer.getOpenCard(),
                players.getScoreResults()
        );
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
        List<GameScoreResultDto> results = new ArrayList<>();
        results.add(createDealerScoreResult());
        results.addAll(createPlayerScoreResults());

        return results;
    }

    public GameFinalResultDto getFinalResult() {
        GameResult gameResult = new GameResult(dealer, players.getAll());

        return gameResult.convertToDto();
    }

    private void drawInitialCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players.getAll()) {
                player.receiveCard(deck.draw());
            }
            dealer.receiveCard(deck.draw());
        }
    }

    private GameScoreResultDto createDealerScoreResult() {
        return GameScoreResultDto.from(dealer);
    }

    private List<GameScoreResultDto> createPlayerScoreResults() {
        return players.getScoreResults();
    }
}
