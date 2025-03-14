package controller;

import domain.BettingTable;
import domain.CardDeck;
import domain.GameManger;
import domain.GameResult;
import domain.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackConsole {
    private final OutputView outputView;
    private final InputView inputView;

    public BlackjackConsole(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        List<String> playerNames = inputView.inputUsers();
        List<Player> users = playerNames.stream()
                .map(Player::new)
                .toList();

        // 블랙잭을 진행한다
        Dealer dealer = new Dealer();
        GameManger gameManger = new GameManger(users, dealer, new CardDeck());

        // 배팅 결과를 계산한다
        BettingTable bettingTable = bettingMoney(users, dealer);

        // 첫 카드를 공개한다
        distributionFirstCard(gameManger, playerNames);

        // 카드를 추가한다
        additionalPlayerCard(gameManger, playerNames);
        additionalDealerCard(gameManger);

        // 카드를 공개한다
        displayScore(gameManger, playerNames);

        // 게임 결과를 계산한다
        Map<User, GameResult> gameResult = gameManger.calculatePlayerScore();
        calculateGameResult(gameManger, gameResult);

        // 배팅 금액을 계산한다
        Map<User, Long> rewards = bettingTable.calculateRewards(gameResult, dealer);
        outputView.displayRewards(rewards);
        inputView.close();
    }

    private BettingTable bettingMoney(List<Player> users, Dealer dealer) {
        BettingTable bettingTable = new BettingTable();

        for (Player user : users) {
            Long bettingMoney = inputView.inputBettingMoney(user.getName());
            bettingTable.bettingMoney(user, bettingMoney);
        }
        bettingTable.bettingMoney(dealer, 0L);
        return bettingTable;
    }

    private void distributionFirstCard(GameManger gameManger, List<String> playerNames) {
        gameManger.firstHandOutCard();

        displayOpenCard(gameManger.getDealer().getName(), gameManger.getDealer());
        playerNames.forEach(playerName -> displayOpenCard(playerName, gameManger.findUserByUsername(playerName)));
    }

    private void additionalDealerCard(GameManger gameManger) {
        User dealer = gameManger.getDealer();
        while (!dealer.isDrawable()) {
            dealer.drawCard(gameManger.handOutCard());
            outputView.displayDealerAddCard();
        }
    }

    private void additionalPlayerCard(GameManger gameManger, List<String> playerNames) {
        playerNames.forEach(playerName ->
                addCardAllPlayer(gameManger, playerName));
    }

    private void addCardAllPlayer(GameManger gameManger, String playerName) {
        User player = gameManger.findUserByUsername(playerName);
        while (player.isDrawable()) {
            String yesOrNo = inputView.inputYesOrNo(playerName);
            if (yesOrNo.equalsIgnoreCase("N")) {
                return;
            }
            player.drawCard(gameManger.handOutCard());
            displayOpenCard(playerName, player);
        }
    }

    private void displayOpenCard(String playerName, User player) {
        List<Card> trumpCards = player.openInitialCard();
        outputView.displayOpenCards(playerName, trumpCards);
    }

    private void displayScore(GameManger gameManger, List<String> playerNames) {
        displayDealer(gameManger);
        displayPlayers(gameManger, playerNames);
    }

    private void displayDealer(GameManger gameManger) {
        Dealer dealer = (Dealer) gameManger.getDealer();
        List<Card> dealerCards = dealer.openAllCard();
        int score = dealer.calculateScore();
        outputView.displayOpenCardsResult(dealer.getName(), dealerCards, score);
    }

    private void displayPlayers(GameManger gameManger, List<String> playerNames) {
        playerNames.stream()
                .map(gameManger::findUserByUsername)
                .toList()
                .forEach(player -> outputView.displayOpenCardsResult(
                        player.getName(),
                        player.openInitialCard(),
                        player.calculateScore()));
    }

    private void calculateGameResult(GameManger gameManger, Map<User, GameResult> gameResult) {
        Map<GameResult, Integer> gameResultIntegerMap = gameManger.calculateDealerScore();
        outputView.displayDealerGameResult(gameResultIntegerMap.get(GameResult.WIN),
                gameResultIntegerMap.get(GameResult.LOSE), gameResultIntegerMap.get(GameResult.DRAW));

        outputView.displayGameResult(gameResult);
    }
}