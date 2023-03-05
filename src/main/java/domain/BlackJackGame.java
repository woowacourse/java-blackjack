package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.ResultCalculator;
import view.InputView;
import view.OutputView;
import view.ResultView;

public class BlackJackGame {
    private final Players players;
    private final Dealer dealer;

    public BlackJackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        initSetting();
    }

    public void run() {
        askEachPlayers();
        dealerDistributeOrNot();
        printFinalGameStatus();
        printFinalFightResult();
    }

    public static void initSettingCards(Players players, Dealer dealer) {
        Deck.shuffle();
        distributeCard(dealer, 2);
        for (Player player : players.getPlayers()) {
            distributeCard(player, 2);
        }
    }

    public static void distributeCard(Participant participant, int num) {
        for (int generateIndex = 0; generateIndex < num; generateIndex++) {
            participant.drawCard(generateCard());
        }
    }

    private static Card generateCard() {
        String cardName = Deck.drawCard();
        int cardValue = Deck.extractCardNumber(cardName);
        return new Card(cardName, cardValue);
    }

    private void initSetting() {
        BlackJackGame.initSettingCards(players, dealer);
        ResultView.printInitMessage(players.getPlayerNames());

        printInitMemberCards();
    }

    private void printInitMemberCards() {
        ResultView.printParticipantResult(dealer.getName(), dealer.getCardNames());
        for (Player player : players.getPlayers()) {
            ResultView.printParticipantResult(player.getName(), player.getCardNames());
        }
    }

    private void askEachPlayers() {
        System.out.println();
        for (Player player : players.getPlayers()) {
            askPlayerDistribute(player);
        }
    }

    private void askPlayerDistribute(Player player) {
        try {
            checkAdditionalDistribute(player);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            askPlayerDistribute(player);
        }
    }

    private void checkAdditionalDistribute(Player player) {
        do {
            OutputView.printInputReceiveYesOrNotMessage(player.getName());
            ResultView.printParticipantResult(player.getName(), player.getCardNames());
        } while (player.isAbleToDraw() && isReceivable(player));
    }

    private boolean isReceivable(Player player) {
        if (InputView.inputReceiveOrNot()) {
            BlackJackGame.distributeCard(player, 1);
            return true;
        }
        return false;
    }

    private void dealerDistributeOrNot() {
        while (dealer.isAbleToDraw()) {
            BlackJackGame.distributeCard(dealer, 1);
            OutputView.printDealerReceivedMessage();
        }
    }

    private void printFinalGameStatus() {
        System.out.println();
        ResultView.printParticipantFinalResult(dealer.getName(), dealer.getCardNames(), dealer.getMaxSum());
        for (Player player : players.getPlayers()) {
            ResultView.printParticipantFinalResult(player.getName(), player.getCardNames(), player.getMaxSum());
        }
    }

    private void printFinalFightResult() {
        ResultCalculator resultCalculator = new ResultCalculator(players, dealer);
        resultCalculator.executeGame(players, dealer);
        ResultView.printFinalFightResult(resultCalculator.getFinalFightResults());
    }
}
