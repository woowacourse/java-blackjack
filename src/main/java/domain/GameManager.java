package domain;

import domain.constant.Result;
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

    public int calculateScore(Player player) {
        return player.getScore();
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

    public List<GameFinalResultDto> getFinalResult() {
        List<GameFinalResultDto> results = new ArrayList<>();
        // 딜러 추가
        results.add(new GameFinalResultDto(dealer.getName()));

        // 플레이어 추가
        determineWinLose(results);

        return results;
    }

    private void drawInitialCards() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players.values()) {
                player.addCard(deck.draw());
            }
            dealer.addCard(deck.draw());
        }
    }

    private void determineWinLose(List<GameFinalResultDto> results) {
        for (Player player : players.values()) {
            int playerScore = player.getScore();
            int dealerScore = dealer.getScore();
            if (player.isBust() || playerScore < dealerScore) {
                results.add(new GameFinalResultDto(player.getName(), Result.LOSE));
                continue;
            }

            if (playerScore > dealerScore) {
                results.add(new GameFinalResultDto(player.getName(), Result.WIN));
                continue;
            }

            results.add(new GameFinalResultDto(player.getName(), Result.DRAW));
        }
    }
}
