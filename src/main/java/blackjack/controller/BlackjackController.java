package blackjack.controller;

import blackjack.domain.Card;
import blackjack.domain.Participants;
import blackjack.domain.ParticipantName;
import blackjack.dto.CardDTO;
import blackjack.dto.FinalResultDTO;
import blackjack.dto.StartCardsDTO;
import blackjack.dto.WinningResultDTO;
import blackjack.service.BlackjackGame;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Participants participants = createPlayers();
        final BlackjackGame blackjackGame = initGame(participants);

        if (blackjackGame.isNotDealerWin()) {
            playGame(blackjackGame);
        }

        finishGame(blackjackGame);
    }

    private Participants createPlayers() {
        try {
            List<String> playerNames = inputView.readPlayerNames();
            return Participants.from(playerNames);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return createPlayers();
        }
    }

    private BlackjackGame initGame(final Participants participants) {
        final BlackjackGame blackjackGame = new BlackjackGame(participants);

        final StartCardsDTO startCardsDTO = blackjackGame.start();
        outputView.printStartCards(startCardsDTO);

        return blackjackGame;
    }

    private void playGame(final BlackjackGame blackjackGame) {
        final List<ParticipantName> participantName = blackjackGame.getParticipantsName();

        for (ParticipantName name : participantName) {
            runPlayerTurn(blackjackGame, name);
        }

        final int count = blackjackGame.giveDealerMoreCard();
        outputView.printDealerMoreCard(count);
    }

    private void runPlayerTurn(final BlackjackGame blackjackGame, final ParticipantName name) {
        boolean isFirst = true;
        List<CardDTO> cards = convertToCardDTO(blackjackGame.getCardsOf(name));

        while (isContinue(blackjackGame, name)) {
            isFirst = false;
            blackjackGame.addCardToParticipant(name);
            cards = convertToCardDTO(blackjackGame.getCardsOf(name));
            outputView.printPlayerCard(name.getName(), cards);
        }
        showPlayerInitialCards(name, isFirst, cards);
    }

    private List<CardDTO> convertToCardDTO(final List<Card> cards) {
        return cards.stream()
                .map(CardDTO::from)
                .toList();
    }

    private boolean isContinue(final BlackjackGame blackjackGame, final ParticipantName name) {
        return blackjackGame.isPlayerAliveByName(name) && needMoreCard(name);
    }

    private boolean needMoreCard(final ParticipantName name) {
        try {
            return inputView.readNeedMoreCard(name.getName());
        } catch (IllegalArgumentException e){
            outputView.printError(e.getMessage());
            return needMoreCard(name);
        }
    }

    private void showPlayerInitialCards(final ParticipantName name,
                                        final boolean isFirst,
                                        final List<CardDTO> cards) {
        if (isFirst) {
            outputView.printPlayerCard(name.getName(), cards);
        }
    }

    private void finishGame(final BlackjackGame blackjackGame) {
        FinalResultDTO finalResultDTO = blackjackGame.getFinalResults();
        WinningResultDTO winningResults = blackjackGame.getWinningResults();
        outputView.printFinalResult(finalResultDTO, winningResults);
    }
}
