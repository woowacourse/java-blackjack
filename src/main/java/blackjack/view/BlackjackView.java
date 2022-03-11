package blackjack.view;

import static blackjack.view.output.OutputMessage.MESSAGE_OF_DEALER_DREW_CARD;
import static blackjack.view.output.OutputMessage.MESSAGE_OF_MATCH_RESULT_TITLE;
import static blackjack.view.output.OutputMessage.MESSAGE_OF_REQUEST_PLAYER_NAMES;

import java.util.List;

import blackjack.dto.MatchResultDto;
import blackjack.dto.ParticipantDto;
import blackjack.view.input.InputView;
import blackjack.view.output.OutputView;

public class BlackjackView {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public List<String> requestPlayerNames() {
        outputView.printMessage(MESSAGE_OF_REQUEST_PLAYER_NAMES);
        return inputView.requestPlayerNames();
    }

    public void printInitiallyDistributedCards(final String dealerFirstCardName, final List<ParticipantDto> playerDtos) {
        outputView.printEmptyLine();
        outputView.printMessageOfInitiallyDistributeCards(playerDtos);
        outputView.printFirstCardOfDealer(dealerFirstCardName);
        playerDtos.forEach(outputView::printDistributedCardsOfPlayer);
        outputView.printEmptyLine();
    }

    public boolean requestDrawingCardChoice(final String playerName) {
        outputView.printMessageOfRequestDrawingCardChoice(playerName);
        return inputView.requestDrawingCardChoice();
    }

    public void printCurrentCardsOfPlayer(final String playerName, final List<String> cardNames) {
        outputView.printCurrentCardsOfPlayer(playerName, cardNames);
    }

    public void printMessageOfDealerDrewCard() {
        outputView.printMessage(MESSAGE_OF_DEALER_DREW_CARD);
    }

    public void printFinalScoresOfParticipants(final ParticipantDto dealerDto, final List<ParticipantDto> playerDtos) {
        outputView.printEmptyLine();
        outputView.printFinalScoreOfParticipant(dealerDto);
        outputView.printFinalScoreOfParticipants(playerDtos);
    }

    public void printMatchResult(final MatchResultDto matchResultDto) {
        outputView.printEmptyLine();
        outputView.printMessage(MESSAGE_OF_MATCH_RESULT_TITLE);
        outputView.printMatchResult(matchResultDto);
    }

}
