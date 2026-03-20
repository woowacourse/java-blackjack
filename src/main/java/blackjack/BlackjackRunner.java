package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.DrawCommand;
import blackjack.domain.Hand;
import blackjack.domain.Participants;
import blackjack.dto.DrawResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackRunner {

    private final ParticipantsCreator participantsCreator;
    private final BlackjackGameView blackjackGameView;
    private final OutputView outputView;
    private final InputView inputView;

    public BlackjackRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.participantsCreator = new ParticipantsCreator(inputView, outputView);
        this.blackjackGameView = new BlackjackGameView(outputView);
    }

    public void execute() {
        Participants participants = participantsCreator.create();
        Deck deck = Deck.createShuffledDeck();
        deck = participants.dealInitialCards(deck);

        blackjackGameView.printInitialState(participants);
        deck = playerTurn(participants, deck);
        dealerTurn(participants, deck);
        blackjackGameView.printFinalResult(participants);
    }

    private void dealerTurn(Participants participants, Deck deck) {
        while (participants.isDealerDraw()) {
            outputView.printDealerTurn();
            deck = participants.dealerDraw(deck);
        }
    }

    private Deck playerTurn(Participants participants, Deck deck) {
        while (participants.hasDrawablePlayer()) {
            deck = drawCard(participants, deck);
        }
        return deck;
    }

    private Deck drawCard(Participants participants, Deck deck) {
        String drawablePlayerNickname = participants.getDrawablePlayerNickname();
        boolean isPlayerDraw = isDraw(drawablePlayerNickname);
        if (isPlayerDraw) {
            DrawResult drawResult = participants.hitPlayer(deck);
            Hand playerHand = drawResult.drewCard();
            printDrewResult(drawablePlayerNickname, playerHand);
            return drawResult.drewDeck();
        }
        participants.dontWantDraw();
        return deck;
    }

    private void printDrewResult(String drawablePlayerNickname, Hand playerCards) {
        outputView.printPlayerStatus(drawablePlayerNickname, playerCards.getStatusByDisplayName());
    }

    private boolean isDraw(String nickname) {
        try {
            outputView.hitOrStand(nickname);
            String userCommand = inputView.readLine();
            DrawCommand drawCommand = DrawCommand.from(userCommand);
            return drawCommand.isDraw();
        } catch (IllegalArgumentException e) {
            outputView.printLine(e.getMessage());
            return isDraw(nickname);
        }
    }
}
