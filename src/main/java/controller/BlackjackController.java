package controller;

import domain.CardDeck;
import domain.GameManger;
import domain.GameResult;
import domain.TrumpCard;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;
import view.Parser;

public class BlackjackController {
    private final OutputView outputView;
    private final InputView inputView;

    public BlackjackController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        List<String> playerNames = Parser.parseStringToList(inputView.inputUsers());
        List<Player> users = playerNames.stream().map(Player::new).toList();
        GameManger gameManger = new GameManger(users, new Dealer(), new CardDeck());
        distributionFirstCard(gameManger, playerNames);

        additionalPlayerCard(gameManger, playerNames);
        additionalDealerCard(gameManger);

        displayScore(gameManger, playerNames);
        calculateGameResult(gameManger);

        inputView.close();
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
        List<TrumpCard> trumpCards = player.openInitialCard();
        outputView.displayOpenCards(playerName, trumpCards);
    }

    private void displayScore(GameManger gameManger, List<String> playerNames) {
        displayDealer(gameManger);
        displayPlayers(gameManger, playerNames);
    }

    private void displayDealer(GameManger gameManger) {
        Dealer dealer = (Dealer) gameManger.getDealer();
        List<TrumpCard> dealerCards = dealer.openAllCard();
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

    private void calculateGameResult(GameManger gameManger) {
        Map<GameResult, Integer> gameResultIntegerMap = gameManger.calculateDealerScore();
        outputView.displayDealerGameResult(gameResultIntegerMap.get(GameResult.WIN),
                gameResultIntegerMap.get(GameResult.LOSE), gameResultIntegerMap.get(GameResult.DRAW));

        Map<User, GameResult> gameResult = gameManger.calculatePlayerScore();
        outputView.displayGameResult(gameResult);
    }
}