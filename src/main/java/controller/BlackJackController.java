package controller;

import controller.dto.CardScoreDto;
import domain.BettingMoney;
import domain.BlackJackGame;
import domain.Player;
import domain.Score;
import domain.TrumpCard;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJackGame blackJackGame;

    public BlackJackController(InputView inputView, OutputView outputView, BlackJackGame blackJackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackGame = blackJackGame;
    }

    public void run() {
        List<Player> players = executeInitializeCards();

        players.forEach(this::executePlayerHit);
        executeDealerHit();

        displayCardResult(players);
        displayGameResult(players);
    }

    private List<Player> executeInitializeCards() {
        List<Player> players = createPlayers();
        Map<String, List<TrumpCard>> playerCards = convertPlayerCards(players);
        TrumpCard dealerFirstCard = blackJackGame.retrieveDealerFirstCard();

        outputView.printInitialCards(playerCards, dealerFirstCard);

        return players;
    }

    private List<Player> createPlayers() {
        Map<String, BettingMoney> playerInfos = inputView.readPlayerNames().stream()
                .collect(Collectors.toMap(
                        playerName -> playerName,
                        playerName -> new BettingMoney(inputView.readBettingMoney(playerName)),
                        (oldValue, newValue) -> {
                            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");  // TODO. 추후 하위 계층으로 분리
                        }, LinkedHashMap::new));

        return blackJackGame.createPlayers(playerInfos);
    }

    private Map<String, List<TrumpCard>> convertPlayerCards(List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(Player::getName, Player::retrieveCards));
    }

    private void executePlayerHit(Player player) {
        while (blackJackGame.isPlayerHitAllowed(player) &&
                inputView.readProcessHit(player.getName())) {
            blackJackGame.processPlayerHit(player);
            outputView.printPlayerCards(player.getName(), player.retrieveCards());
        }
    }

    private void executeDealerHit() {
        int dealerHitCount = blackJackGame.processDealerHit();
        outputView.printDealerHitInfo(dealerHitCount);
    }

    private void displayCardResult(List<Player> players) {
        Map<String, CardScoreDto> playerCardScoreDto = convertPlayerCardScoreDto(players);
        CardScoreDto dealerCardScoreDto = new CardScoreDto(
                blackJackGame.retrieveDealerCards(), blackJackGame.calculateDealerScore());
        outputView.printCardsResult(playerCardScoreDto, dealerCardScoreDto);
    }

    private Map<String, CardScoreDto> convertPlayerCardScoreDto(List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(Player::getName, player -> {
                    List<TrumpCard> playerCards = player.retrieveCards();
                    Score score = blackJackGame.calculatePlayerScore(player);

                    return new CardScoreDto(playerCards, score);
                }));
    }

    private void displayGameResult(List<Player> players) {
        Map<String, Integer> playersRevenueAmount = blackJackGame.calculatePlayersRevenueAmount(players);
        int dealerRevenueAmount = calculateDealerRevenueAmount(playersRevenueAmount);
        outputView.printRevenueAmount(playersRevenueAmount, dealerRevenueAmount);
    }

    private int calculateDealerRevenueAmount(Map<String, Integer> playersRevenueAmount) {
        int dealerRevenueAmount = 0;

        for (Integer playerRevenueAmount : playersRevenueAmount.values()) {
            dealerRevenueAmount += playerRevenueAmount * -1;
        }

        return dealerRevenueAmount;
    }
}
