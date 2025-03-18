package controller;

import controller.dto.CardScoreDto;
import domain.BlackJackGame;
import domain.participant.state.hand.Score;
import domain.TrumpCard;
import domain.participant.BettingMoney;
import domain.participant.Name;
import domain.participant.PlayerRoster;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PlayerRoster playerRoster = createPlayerRoster();
        BlackJackGame blackJackGame = createBlackJackGame(playerRoster);
        displayInitializeCards(blackJackGame, playerRoster);

        playerRoster.getPlayerNames().forEach(playerName ->
                executePlayerHit(blackJackGame, playerName));
        executeDealerHit(blackJackGame);

        displayCardResult(blackJackGame, playerRoster);
        displayGameResult(blackJackGame, playerRoster);
    }

    private PlayerRoster createPlayerRoster() {
        List<Name> PlayerNames = inputView.readPlayerNames().stream()
                .map(Name::new)
                .toList();

        return new PlayerRoster(PlayerNames);
    }

    private BlackJackGame createBlackJackGame(PlayerRoster playerRoster) {
        Map<Name, BettingMoney> playerInfos = playerRoster.getPlayerNames().stream()
                .collect(Collectors.toMap(
                        playerName -> playerName,
                        playerName -> new BettingMoney(inputView.readBettingMoney(playerName.getText())),
                        (oldValue, newValue) -> {
                            throw new IllegalStateException("플레이어의 이름의 중복 처리를 못했습니다.");
                        }, LinkedHashMap::new));

        return BlackJackGame.create(playerInfos);
    }

    private void displayInitializeCards(BlackJackGame blackJackGame, PlayerRoster playerRoster) {
        Map<String, List<TrumpCard>> playerCards = playerRoster.getPlayerNames().stream()
                .collect(Collectors.toMap(Name::getText, blackJackGame::retrievePlayerCards));
        TrumpCard dealerFirstCard = blackJackGame.retrieveDealerFirstCard();

        outputView.printInitialCards(playerCards, dealerFirstCard);
    }

    private void executePlayerHit(BlackJackGame blackJackGame, Name playerName) {
        while (blackJackGame.isPlayerHitAllowed(playerName)
                && inputView.readProcessHit(playerName.getText())) {
            blackJackGame.processPlayerHit(playerName);

            outputView.printPlayerCards(playerName.getText(),
                    blackJackGame.retrievePlayerCards(playerName));
        }
    }

    private void executeDealerHit(BlackJackGame blackJackGame) {
        int dealerHitCount = blackJackGame.processDealerHit();

        outputView.printDealerHitInfo(dealerHitCount);
    }

    private void displayCardResult(BlackJackGame blackJackGame, PlayerRoster playerRoster) {
        Map<String, CardScoreDto> playerCardScoreDto = convertPlayerCardScoreDto(blackJackGame, playerRoster);
        CardScoreDto dealerCardScoreDto = new CardScoreDto(
                blackJackGame.retrieveDealerCards(), blackJackGame.calculateDealerScore());

        outputView.printCardsResult(playerCardScoreDto, dealerCardScoreDto);
    }

    private Map<String, CardScoreDto> convertPlayerCardScoreDto(BlackJackGame blackJackGame,
                                                                PlayerRoster playerRoster) {
        return playerRoster.getPlayerNames().stream()
                .collect(Collectors.toMap(Name::getText, playerName -> {
                    List<TrumpCard> cards = blackJackGame.retrievePlayerCards(playerName);
                    Score score = blackJackGame.calculatePlayerScore(playerName);

                    return new CardScoreDto(cards, score);
                }));
    }

    private void displayGameResult(BlackJackGame blackJackGame, PlayerRoster playerRoster) {
        Map<String, Integer> playersRevenueAmount = playerRoster.getPlayerNames().stream()
                .collect(Collectors.toMap(Name::getText, blackJackGame::calculatePlayerRevenueAmount));
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
