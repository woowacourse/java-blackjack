package domain;

import domain.dto.GameFinalResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;
import domain.shuffle.RandomShuffle;

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
        deck.shuffle(new RandomShuffle());
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

    public List<String> drawPlayerCard(String player) {
        players.get(player).addCard(deck.draw());
        return players.get(player).showHand();
    }

    public List<String> drawDealerCard() {
        dealer.addCard(deck.draw());
        return dealer.showHand();
    }

    public List<Player> getPlayerSequence() {
        return players.values().stream().toList();
    }

    public List<GameScoreResultDto> getScoreResults() {
        List<GameScoreResultDto> results = new ArrayList<>();
        aggregateDealerResult(results); // dealer
        aggregatePlayerResult(results); // players

        return results;
    }

    private void aggregateDealerResult(List<GameScoreResultDto> results) {
        results.add(GameScoreResultDto.from(dealer));
    }

    private void aggregatePlayerResult(List<GameScoreResultDto> results) {
        for (Player player : players.values()) {
            results.add(GameScoreResultDto.from(player));
        }
    }

    public boolean canReceiveCard(String player) {
        return players.get(player).canReceiveCard();
    }

    public boolean canReceiveCard() {
        return dealer.canReceiveCard();
    }

    public GameFinalResultDto getFinalResult() {
        GameResult gameResult = new GameResult(dealer, players.values());

        return gameResult.convertToDto();
    }

    private void drawInitialCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players.values()) {
                player.addCard(deck.draw());
            }
            dealer.addCard(deck.draw());
        }
    }
}
