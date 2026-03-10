package domain;

import domain.deck.Deck;
import domain.dto.GameFinalResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;
import domain.participant.Dealer;
import domain.participant.Player;

import java.util.*;

public class GameManager {
    private final Deck deck;
    private final Dealer dealer;
    private final Map<String, Player> players;

    public GameManager(Deck deck, Dealer dealer) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = new LinkedHashMap<>();
    }

    public void registerPlayer(String name) {
        players.put(name, new Player(name, new Hand()));
    }

    public void startGame() {
        deck.shuffle();
        drawInitialCards();
    }

    public GameInitialInfoDto getInitialInfo() {
        return new GameInitialInfoDto(
                dealer.getName(),
                dealer.showHand().getFirst(),
                players.values().stream()
                        .map(GameScoreResultDto::from)
                        .toList()
        );
    }

    public boolean canPlayerReceiveCard(String player) {
        return players.get(player).canReceiveCard();
    }

    public List<String> drawPlayerCard(String player) {
        players.get(player).receiveCard(deck.draw());
        return players.get(player).showHand();
    }

    public boolean canDealerReceiveCard() {
        return dealer.canReceiveCard();
    }

    public void drawDealerCard() {
        dealer.receiveCard(deck.draw());
    }

    public List<GameScoreResultDto> getScoreResults() {
        List<GameScoreResultDto> results = new ArrayList<>();
        aggregateDealerResult(results);
        aggregatePlayerResult(results);

        return results;
    }

    public GameFinalResultDto getFinalResult() {
        GameResult gameResult = new GameResult(dealer, players.values());

        return gameResult.convertToDto();
    }

    private void drawInitialCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players.values()) {
                player.receiveCard(deck.draw());
            }
            dealer.receiveCard(deck.draw());
        }
    }

    private void aggregateDealerResult(List<GameScoreResultDto> results) {
        results.add(GameScoreResultDto.from(dealer));
    }

    private void aggregatePlayerResult(List<GameScoreResultDto> results) {
        for (Player player : players.values()) {
            results.add(GameScoreResultDto.from(player));
        }
    }
}
