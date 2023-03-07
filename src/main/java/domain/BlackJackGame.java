package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;
import domain.result.ResultCalculator;
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
        players.askEachPlayers();
        dealerDistributeOrNot();
        printFinalGameStatus();
        printFinalFightResult();
    }

    public static void distributeCard(Participant participant, int num) {
        for (int generateIndex = 0; generateIndex < num; generateIndex++) {
            participant.drawCard(generateCard());
        }
    }

    private static Card generateCard() {
        String card = Deck.drawCard();
        return new Card(card, Deck.extractCardNumber(card));
    }

    private void initSetting() {
        initSettingCards(players, dealer);
        ResultView.printInitMessage(players.getPlayerNames());

        printInitMemberCards();
    }

    private void initSettingCards(Players players, Dealer dealer) {
        distributeCard(dealer, 2);
        players.initDistribute();
    }

    private void printInitMemberCards() {
        ResultView.printParticipantResult(dealer.getName(), dealer.getCardNames());
        players.printInitPlayerCards();
    }

    private void dealerDistributeOrNot() {
        while (dealer.dealerMustDraw()) {
            BlackJackGame.distributeCard(dealer, 1);
            OutputView.printDealerReceivedMessage();
        }
    }

    private void printFinalGameStatus() {
        System.out.println();
        ResultView.printParticipantFinalResult(dealer.getName(), dealer.getCardNames(), dealer.calculateScore());
        players.printFinalPlayerResults();
    }

    private void printFinalFightResult() {
        ResultCalculator resultCalculator = new ResultCalculator(players, dealer);
        resultCalculator.executeGame(players, dealer);
        ResultView.printFinalFightResult(resultCalculator.getFinalFightResults());
    }
}
