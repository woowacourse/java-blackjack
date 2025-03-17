package blackjack;

import bet.BetManager;
import card.DeckGenerator;
import player.Player;
import result.PlayerResult;
import view.InputView;
import view.OutputView;

public class ConsoleBlackjackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public ConsoleBlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Blackjack blackjack = new Blackjack(inputView.inputParticipantName(), DeckGenerator.generateDeck());
        BetManager betManager = new BetManager();

        addInitialBet(blackjack, betManager);
        blackjack.distributeInitialCards();
        openInitialCards(blackjack);
        addMoreCards(blackjack);

        PlayerResult playerResult = new PlayerResult(blackjack.getDealer(), blackjack.getParticipants());
        outputView.printPlayersCardsAndSum(blackjack.getDealer(),
                blackjack.getParticipants(), blackjack.getNameAndSumOfAllPlayers());
        calculateBettingResult(playerResult, betManager);
    }

    private void addInitialBet(Blackjack blackjack, BetManager betManager) {
        for (Player participant : blackjack.getParticipants()) {
            betManager.addInitialBet(participant, inputView.inputBetAmount(participant.getName()));
        }
    }

    private void openInitialCards(Blackjack blackjack) {
        outputView.printOpenInitialCards(blackjack.getDealer(), blackjack.getParticipants());
        outputView.printInitialCards(blackjack.openInitialCards());
    }

    private void addMoreCards(Blackjack blackjack) {
        for (Player participant : blackjack.getParticipants()) {
            addMoreCardsIfNotBust(blackjack, participant);
        }
        boolean isAdded = blackjack.addCardToDealerIfLowScore();
        if (isAdded) {
            outputView.printAddCardToDealer();
        }
    }

    private void addMoreCards(Blackjack blackjack, Player participant) {
        YesOrNo yesOrNo;
        do {
            yesOrNo = YesOrNo.from(inputView.inputWantOneMoreCard(participant.getName()));
            addOneCardIfYes(blackjack, participant, yesOrNo);
            outputView.printPlayerCards(participant.getName(), participant.getCards());
        } while (yesOrNo.equals(YesOrNo.YES) && !participant.isBust());
    }

    private void addOneCardIfYes(Blackjack blackjack, Player participant, YesOrNo yesOrNo) {
        if (yesOrNo.equals(YesOrNo.YES)) {
            blackjack.addCard(participant);
        }
    }

    private void addMoreCardsIfNotBust(Blackjack blackjack, Player participant) {
        addMoreCards(blackjack, participant);
    }

    private void calculateBettingResult(PlayerResult playerResult, BetManager betManager) {
        betManager.calculateParticipantBetResults(playerResult.getMatchResults());
        outputView.printBettingResult(betManager.calculateDealerBetResultAmount(), betManager.getBetResults());
    }
}
