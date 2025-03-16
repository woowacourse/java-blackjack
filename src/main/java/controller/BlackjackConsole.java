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
        List<String> playerNames = inputView.inputUsers();
        Dealer dealer = new Dealer();
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();

        BlackjackGame blackjackGame = BlackjackGame.of(players, dealer, new CardDeck());

        BettingTable bettingTable = setUpBets(players, dealer);

        blackjackGame.firstHandOutCard();
        openFirstCards(blackjackGame);

        // 카드를 추가한다
        for (Player player : players) {

        }

        for (String playerName : playerNames) {
            User player = blackjackGame.findUserByUsername(playerName);
            while (player.isDrawable()) {
                YesOrNo yesOrNo = inputView.inputYesOrNo(playerName);
                if (yesOrNo == YesOrNo.NO) {
                    return;
                }
                player.drawCard(blackjackGame.handOutCard());
                displayOpenCard(playerName, player);
            }
        }

        while (!dealer.isDrawable()) {
            dealer.drawCard(blackjackGame.handOutCard());
            outputView.displayDealerAddCard();
        }

        // 카드를 공개한다
        List<Card> dealerCards = dealer.openAllCard();
        int score = dealer.calculateScore();
        outputView.displayOpenCardsResult(dealer.getName(), dealerCards, score);

        playerNames.stream()
                .map(blackjackGame::findUserByUsername)
                .toList()
                .forEach(player -> outputView.displayOpenCardsResult(
                        player.getName(),
                        player.openInitialCard(),
                        player.calculateScore()));

        // 게임 결과를 계산한다
        Map<User, GameResult> gameResult = blackjackGame.calculatePlayerScore();
        Map<GameResult, Integer> gameResultIntegerMap = blackjackGame.calculateDealerScore();
        outputView.displayDealerGameResult(gameResultIntegerMap.get(GameResult.WIN),
                gameResultIntegerMap.get(GameResult.LOSE), gameResultIntegerMap.get(GameResult.DRAW));

        outputView.displayGameResult(gameResult);

        // 배팅 금액을 계산한다
        Map<User, Long> rewards = bettingTable.calculateRewards(gameResult, dealer);
        outputView.displayRewards(rewards);
        inputView.close();
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

    private void displayOpenCard(String playerName, User user) {
        List<Card> cards = user.openInitialCard();
        outputView.displayOpenCards(playerName, cards);
    }
}