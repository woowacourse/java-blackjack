package controller;

import domain.BettingTable;
import domain.CardDeck;
import domain.BlackjackGame;
import domain.GameResult;
import domain.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.InputView;
import view.OutputView;
import view.YesOrNo;

public class BlackjackConsole {
    private final OutputView outputView;
    private final InputView inputView;

    public BlackjackConsole(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        Dealer dealer = new Dealer();
        List<Player> players = getPlayers();

        BlackjackGame blackjackGame = BlackjackGame.of(players, dealer, new CardDeck());

        BettingTable bettingTable = setUpBets(players, dealer);

        blackjackGame.firstHandOutCard();
        openFirstCards(blackjackGame);

        controlTurn(players, blackjackGame, dealer);

        openAllCards(players, dealer, blackjackGame);

        calculateBettingReward(blackjackGame, bettingTable, dealer);
        inputView.close();
    }

    private void calculateBettingReward(BlackjackGame blackjackGame, BettingTable bettingTable, Dealer dealer) {
        Map<User, GameResult> gameResult = blackjackGame.calculatePlayerScore();
        calculateBettingReward(bettingTable, gameResult, dealer);
    }

    private List<Player> getPlayers() {
        List<String> playerNames = inputView.inputUsers();
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void openAllCards(List<Player> players, Dealer dealer, BlackjackGame blackjackGame) {
        openAllCardFor(dealer, blackjackGame);

        for (Player player : players) {
            openAllCardFor(player, blackjackGame);
        }
    }

    private void openAllCardFor(User user, BlackjackGame blackjackGame) {
        List<Card> cards = user.openAllCard();
        int score = blackjackGame.calculateScore(user);
        outputView.displayOpenCardsResult(user.getName(), cards, score);
    }

    private void calculateBettingReward(BettingTable bettingTable, Map<User, GameResult> gameResult, Dealer dealer) {
        Map<User, Long> rewards = bettingTable.calculateRewards(gameResult, dealer);
        outputView.displayRewards(rewards);
    }

    private void controlTurn(List<Player> players, BlackjackGame blackjackGame, Dealer dealer) {
        for (Player player : players) {
            while (blackjackGame.isDrawable(player)) {
                YesOrNo yesOrNo = inputView.inputYesOrNo(player.getName());
                blackjackGame.controlTurn(player, yesOrNo);
                displayOpenCard(player);
            }
        }
        while (blackjackGame.isDrawable(dealer)) {
            blackjackGame.controlTurn(dealer, YesOrNo.YES);
            outputView.displayDealerAddCard();
        }
    }

    private void openFirstCards(BlackjackGame blackjackGame) {
        List<Card> dealerCards = blackjackGame.openFirstDealerCard();
        outputView.displayOpenCards(Dealer.DEALER_NAME, dealerCards);

        Map<String, List<Card>> playersCard = blackjackGame.openFirstPlayersCard();
        for (Entry<String, List<Card>> openCardSet : playersCard.entrySet()) {
            outputView.displayOpenCards(openCardSet.getKey(), openCardSet.getValue());
        }
    }

    private BettingTable setUpBets(List<Player> users, Dealer dealer) {
        BettingTable bettingTable = new BettingTable();

        for (Player user : users) {
            Long bettingMoney = inputView.inputBettingMoney(user.getName());
            bettingTable.betMoney(user, bettingMoney);
        }
        bettingTable.betMoney(dealer, 0L);
        return bettingTable;
    }

    private void displayOpenCard(User user) {
        List<Card> cards = user.openInitialCard();
        outputView.displayOpenCards(user.getName(), cards);
    }
}