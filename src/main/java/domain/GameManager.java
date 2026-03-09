package domain;

import domain.constant.Result;
import domain.dto.GameFinalResultDto;
import domain.dto.GameInitialInfoDto;
import domain.dto.GameScoreResultDto;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private final Deck deck = new Deck();
    private final Players players = new Players();
    private final Dealer dealer = new Dealer();

    public GameManager() {}

    public void startGame() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players.getPlayers()) {
                player.addCard(deck.draw());
            }
            dealer.addCard(deck.draw());
        }
    }


    public int calculateScore(Hand hand) {
        return hand.calculateScore();
    }

    public List<String> drawPlayerCard(Player player) {
        player.addCard(deck.draw());
        return player.getHandToString();
    }

    public void drawDealerCard() {
        dealer.addCard(deck.draw());
    }

    public void addPlayer(String name) {
        // TODO: 베팅 기능 추가 시 이름만이 아니라 베팅 금액도 함께 받도록 수정 필요
        players.add(new Player(name));
    }

    public List<Player> getPlayerSequence() {
        return players.getPlayers();
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
                dealer.getScore()
        ));
    }

    private void aggregatePlayerResult(List<GameScoreResultDto> results) {
        for (Player player : players.getPlayers()) {
            results.add(new GameScoreResultDto(
                    player.getName(),
                    player.getHandToString(),
                    player.getScore()
            ));
        }
    }

    public List<GameInitialInfoDto> getInitialInfo() {
        List<GameInitialInfoDto> results = new ArrayList<>();

        List<String> dealerOpenCard = new ArrayList<>();
        dealerOpenCard.add(dealer.getHandToString().getFirst());


        addDealerInfo(results, dealerOpenCard);

        addPlayersInfo(results);

        return results;
    }

    private void addPlayersInfo(List<GameInitialInfoDto> results) {
        for (Player player : players.getPlayers()) {
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
        // TODO: 베팅 기능 추가 시 승/패/무 뿐 아니라 정산 금액까지 포함한 결과 생성 필요
        List<GameFinalResultDto> results = new ArrayList<>();
        results.add(new GameFinalResultDto(dealer.getName()));

        determineWinLose(results);

        return results;
    }

    private void determineWinLose(List<GameFinalResultDto> results) {
        // TODO: 베팅 기능 추가 시 승패 판정 후 베팅 금액 기준 정산 로직 추가 필요
        for (Player player : players.getPlayers()) {
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
