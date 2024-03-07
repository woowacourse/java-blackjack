package blackjack.controller;

import blackjack.domain.Participants;
import blackjack.domain.ParticipantsName;
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
        Participants participants = createPlayers();
        BlackjackGame blackjackGame = new BlackjackGame(participants);
        StartCardsDTO startCardsDTO = blackjackGame.start();
        outputView.printStartCards(startCardsDTO);

        if (!blackjackGame.isDealerBlackjack()) {
            List<ParticipantsName> participantsName = blackjackGame.getParticipantsName();

            for (ParticipantsName name : participantsName) {
                boolean needMoreCard = inputView.readNeedMoreCard(name.getName());
                if (needMoreCard && participants.isNotBlackjack(name)) {
                    blackjackGame.addCardToParticipant(name);
                }
                List<CardDTO> cards = blackjackGame.getCardsOf(name);
                outputView.printPlayerCard(name.getName(), cards);

                while (needMoreCard && blackjackGame.isAlive(name)) {
                    needMoreCard = inputView.readNeedMoreCard(name.getName());
                    blackjackGame.addCardToParticipant(name);
                    cards = blackjackGame.getCardsOf(name);
                    outputView.printPlayerCard(name.getName(), cards);
                }
            }

            int count = blackjackGame.giveDealerMoreCard();
            outputView.printDealerMoreCard(count);

        }

        FinalResultDTO finalResultDTO = blackjackGame.getFinalResults();
        WinningResultDTO winningResults = blackjackGame.getWinningResults();

        outputView.printFinalResult(finalResultDTO, winningResults);
    }

    private Participants createPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return Participants.from(playerNames);
    }
}
