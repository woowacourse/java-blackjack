package domain;

import domain.constant.Result;
import domain.dto.GameFinalResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;
import domain.shuffle.RandomShuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {
    private Deck deck = new Deck();
    private ScoreCalculator scoreCalculator = new ScoreCalculator();
    private List<Player> players = new ArrayList<>();
    private Dealer dealer = new Dealer();

    public GameManager() {
        deck.shuffle(new RandomShuffle());
    }

    public void startGame() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.draw());
            }
            dealer.addCard(deck.draw());
        }
    }

    public void judgeBust(int score, Player currentPlayer) {
        if (scoreCalculator.isBust(score)) {
            currentPlayer.setBust();
        }
    }

    public int calculateScore(List<Card> hand) {
        return scoreCalculator.calculateScore(hand);
    }

    public List<String> drawPlayerCard(Player player) {
        player.addCard(deck.draw());
        judgeBust(calculateScore(player.getHand()), player);
        return player.getHandToString();
    }

    public List<String> drawDealerCard() {
        dealer.addCard(deck.draw());
        judgeBust(calculateScore(dealer.getHand()), dealer);
        return dealer.getHandToString();
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public List<Player> getPlayerSequence() {
        return Collections.unmodifiableList(players);
    }

    public List<GameScoreResultDto> getScoreResults() {
        List<GameScoreResultDto> results = new ArrayList<>();
        // dealer
        aggregateDealerResult(results);
        //players
        aggregatePlayerResult(results);

        return results;
    }

    private void aggregateDealerResult(List<GameScoreResultDto> results) {
        results.add(new GameScoreResultDto(
                dealer.getName(),
                dealer.getHandToString(),
                scoreCalculator.calculateScore(dealer.getHand())
        ));
    }

    private void aggregatePlayerResult(List<GameScoreResultDto> results) {
        for (Player player : players) {
            results.add(new GameScoreResultDto(
                    player.getName(),
                    player.getHandToString(),
                    scoreCalculator.calculateScore(player.getHand())
            ));
        }
    }

    public List<GameInitialInfoDto> getInitialInfo() {
        List<GameInitialInfoDto> results = new ArrayList<>();

        // 딜러 첫 카드 공개
        List<String> dealerOpenCard = new ArrayList<>();
        dealerOpenCard.add(dealer.getHandToString().getFirst());

        // dealer
        addDealerInfo(results, dealerOpenCard);
        //players
        addPlayersInfo(results);

        return results;
    }

    private void addPlayersInfo(List<GameInitialInfoDto> results) {
        for (Player player : players) {
            results.add(new GameInitialInfoDto(
                    player.getName(),
                    2,
                    player.getHandToString()
            ));
        }
    }

    private void addDealerInfo(List<GameInitialInfoDto> results, List<String> dealerOpenCard) {
        results.add(new GameInitialInfoDto(
                dealer.getName(),
                2,
                dealerOpenCard
        ));
    }

    public boolean isBlackjack(Player player) {
        return calculateScore(player.getHand()) == 21;
    }

    public boolean isDealerTurn() {
        return calculateScore(dealer.getHand()) <= 16;
    }

    public List<GameFinalResultDto> getFinalResult() {
        List<GameFinalResultDto> results = new ArrayList<>();
        // 딜러 추가
        results.add(new GameFinalResultDto(dealer.getName()));

        // 플레이어 추가
        determineWinLose(results);

        return results;
    }

    private void determineWinLose(List<GameFinalResultDto> results) {
        for (Player player : players) {
            int playerScore = calculateScore(player.getHand());
            int dealerScore = calculateScore(dealer.getHand());
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
