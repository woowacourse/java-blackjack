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

    public int calculateScore(Player player) {
        return player.getScore();
    }

    public List<String> drawPlayerCard(Player player) {
        player.addCard(deck.draw());
        return player.showHand();
    }

    public List<String> drawDealerCard() {
        dealer.addCard(deck.draw());
        return dealer.showHand();
    }

    public void addPlayer(String name) {
        players.add(new Player(name, new Hand()));
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
                dealer.showHand(),
                dealer.getScore()
        ));
    }

    private void aggregatePlayerResult(List<GameScoreResultDto> results) {
        for (Player player : players) {
            results.add(new GameScoreResultDto(
                    player.getName(),
                    player.showHand(),
                    player.getScore()
            ));
        }
    }

    public List<GameInitialInfoDto> getInitialInfo() {
        List<GameInitialInfoDto> results = new ArrayList<>();

        // 딜러 첫 카드 공개
        List<String> dealerOpenCard = new ArrayList<>();
        dealerOpenCard.add(dealer.showHand().getFirst());

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
                    player.showHand()
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

    public boolean canReceiveCard(Player player) {
        return player.canReceiveCard();
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

    private void determineWinLose(List<GameFinalResultDto> results) {
        for (Player player : players) {
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
