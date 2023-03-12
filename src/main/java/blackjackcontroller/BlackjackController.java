package blackjackcontroller;

import java.util.List;

import betting.PlayersBettingTable;
import betting.Reward;
import blackjackgame.BlackjackGame;
import deck.CardsGenerator;
import deck.Deck;
import dto.BettingResultsDto;
import dto.DealerFirstOpenDto;
import dto.DealerWinningDto;
import dto.PlayerOpenDto;
import dto.PlayerResultDto;
import dto.PlayerWinningDto;
import participants.Dealer;
import participants.Name;
import participants.Participants;
import participants.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackGame blackjackGame;

    public BlackjackController(InputView inputView, OutputView outputView, CardsGenerator cardsGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGame = initializeGame(cardsGenerator);
    }

    private BlackjackGame initializeGame(CardsGenerator cardsGenerator) {
        Participants participants = new Participants(new Dealer(), new Players());
        Deck deck = new Deck(cardsGenerator);
        PlayersBettingTable playersBettingTable = new PlayersBettingTable();
        return new BlackjackGame(participants, deck, playersBettingTable);
    }

    public void run() {
        setGame();
        showFirstDraw();
        hitPlayers();
        dealerHit();
        printFinalCards();
        printRewardResults();
        printWinningResult();
    }

    private void setGame() {
        List<String> names = recruitParticipants();
        getBetAmountFromPlayers(names);
        supplyCards(names);
    }

    private void getBetAmountFromPlayers(List<String> names) {
        for (String name : names) {
            saveBet(name);
        }
    }

    private void saveBet(String name) {
        try {
            int betAmount = inputView.readBetAmount(name);
            blackjackGame.saveBetAmount(name, betAmount);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            saveBet(name);
        }
    }

    private List<String> recruitParticipants() {
        try {
            List<String> names = inputView.readPlayerNames();
            blackjackGame.addPlayers(names);
            return names;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            return recruitParticipants();
        }
    }

    private void supplyCards(List<String> names) {
        blackjackGame.supplyCardsToDealer();
        blackjackGame.supplyCardsToPlayers();
        outputView.printFirstDrawMessage(names);
    }

    private void showFirstDraw() {
        DealerFirstOpenDto dealerFirstOpen = blackjackGame.getDealerFirstOpen();
        List<PlayerOpenDto> playersCards = blackjackGame.getPlayersCards();
        outputView.printFirstOpenCards(dealerFirstOpen, playersCards);
    }


    private void hitPlayers() {
        for (int i = 0; i < blackjackGame.countPlayer(); i++) {
            Name userName = blackjackGame.findUserNameByIndex(i);
            hitPlayer(i, userName);
        }
    }

    private void hitPlayer(int playerIndex, Name userName) {
        try {
            hitByCommand(playerIndex, userName);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception);
            hitPlayer(playerIndex, userName);
        }
    }

    private void hitByCommand(int playerIndex, Name userName) {
        String hitCommand = inputView.readHitCommand(userName);
        HitCommand.validateCommand(hitCommand);
        while (HitCommand.isYes(hitCommand) && !blackjackGame.isBust(playerIndex)) {
            blackjackGame.supplyAdditionalCard(playerIndex);
            PlayerOpenDto playerCard = blackjackGame.getPlayerCardsByIndex(playerIndex);
            outputView.printPlayerCard(playerCard);
            if (blackjackGame.isBust(playerIndex)) {
                break;
            }
            hitCommand = inputView.readHitCommand(userName);
        }
    }

    private void dealerHit() {
        while (blackjackGame.canDealerHit()) {
            blackjackGame.supplyAdditionalCardToDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void printFinalCards() {
        PlayerResultDto dealerResult = blackjackGame.getDealerResult();
        List<PlayerResultDto> playerResults = blackjackGame.getPlayerResults();

        outputView.printFinalResults(dealerResult, playerResults);
    }


    private void printRewardResults() {
        blackjackGame.calculateWinning();
        Reward dealerRewardResult = blackjackGame.getDealerRewardResult();
        BettingResultsDto playersResultDto = blackjackGame.getPlayersResultDto();
        outputView.printRewardResults(dealerRewardResult, playersResultDto);
    }


    private void printWinningResult() {
        DealerWinningDto dealerWinningResult = blackjackGame.getDealerWinningResult();
        List<PlayerWinningDto> playerWinningResults = blackjackGame.getPlayerWinningResults();
        outputView.printWinningResults(dealerWinningResult, playerWinningResults);
    }
}
