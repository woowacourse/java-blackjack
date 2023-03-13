package controller;

import domain.BlackjackGame;
import domain.card.Deck;
import domain.card.Hand;
import domain.card.ShuffledDeck;
import domain.user.BettingAmount;
import domain.user.Dealer;
import domain.user.Participants;
import domain.user.Player;
import domain.user.PlayerName;
import domain.user.Players;
import domain.user.UserInformation;
import domain.user.UserName;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import ui.InputView;
import ui.OutputView;
import util.ExceptionCounter;

public class BlackjackController {
    private final BlackjackGame blackjackGame;

    public BlackjackController() {
        this.blackjackGame = generateBlackjackGame();
    }

    public void run() {
        initialize();
        play();
        announceResult();
    }

    private void initialize() {
        this.blackjackGame.initializeGame();
        OutputView.printCardsStatus(this.blackjackGame.getDealer(), this.blackjackGame.getPlayers());
    }

    private void play() {
        this.blackjackGame.getPlayers().forEach(player -> giveCardUntilImpossible(player, this.blackjackGame));
        OutputView.announceAddCardToDealerIfHit(this.blackjackGame.hitOrStayByDealer());
    }

    private void announceResult() {
        OutputView.printCardsStatusWithScore(this.blackjackGame.getDealer(), this.blackjackGame.getPlayers());
        OutputView.printWinningAmountsOfAllPlayers(this.blackjackGame.calculateAllWinningAmounts());
    }

    private BlackjackGame generateBlackjackGame() {
        Participants participants = new Participants(generatePlayers(), new Dealer());
        Deck shuffledDeck = repeat(() -> ShuffledDeck.createByCount(InputView.readDeckCount()));
        return new BlackjackGame(participants, shuffledDeck);
    }

    private Players generatePlayers() {
        List<UserInformation> userInformations = generatePluralUserInformation();
        List<Player> players = userInformations.stream()
                .map(userInformation -> new Player(userInformation, new Hand()))
                .collect(Collectors.toList());
        return new Players(players);
    }

    private List<UserInformation> generatePluralUserInformation() {
        List<UserName> userNames = repeat(() -> InputView.readPlayerNames().stream()
                .map(PlayerName::new)
                .collect(Collectors.toList()));
        return userNames.stream()
                .map(this::generateUserInformation)
                .collect(Collectors.toList());
    }

    private UserInformation generateUserInformation(UserName userName) {
        return repeat(() -> UserInformation.from(
                userName, InputView.readPlayerBettingAmountOf(userName.getValue()))
        );
    }

    private void giveCardUntilImpossible(Player player, BlackjackGame blackjackGame) {
        while (judgeWhetherDrawCard(player)) {
            blackjackGame.hitBy(player);
            OutputView.printCardsStatusOfUser(player);
        }
        if (player.canAdd()) {
            OutputView.printCardsStatusOfUser(player);
        }
    }

    private static boolean judgeWhetherDrawCard(Player player) {
        return player.canAdd() && InputView.readWhetherDrawCardOrNot(player);
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            ExceptionCounter.addCountHandledException();
            return repeat(supplier);
        }
    }
}
