package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Participants;
import blackjack.dto.DrawResult;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerNicknamesResult;
import blackjack.dto.TotalGameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackRunner {

    private final ParticipantsCreator participantsCreator;
    private final OutputView outputView;
    private final InputView inputView;

    public BlackjackRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.participantsCreator = new ParticipantsCreator(inputView, outputView);
    }

    public void execute() {
        Participants participants = participantsCreator.create();
        Deck deck = Deck.createShuffledDeck();
        deck = participants.distributeCards(deck);

        printInitialSetup(participants);
        printInitialResult(participants);
        deck = playerTurn(participants, deck);
        dealerTurn(participants, deck);
        printGameResult(participants);
    }

    private void printInitialSetup(Participants participants) {
        PlayerNicknamesResult playerNicknamesResult = PlayerNicknamesResult.from(participants);
        outputView.printInitialSetUp(playerNicknamesResult);
    }

    private void printInitialResult(Participants participants) {
        List<ParticipantResult> participantResults = participants.getInitialResult();
        outputView.printInitialResult(participantResults);
    }

    public void printGameResult(Participants participants) {
        List<ParticipantResult> participantResult = participants.getGameResult();
        outputView.printGameResult(participantResult);
        TotalGameResult gameResult = participants.getWinningResult();
        outputView.printTotalProfitResult(gameResult);
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
            DrawResult drawResult = participants.addCardToAvailablePlayer(deck);
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
        outputView.hitOrStand(nickname);
        String userCommand = inputView.readLine();
        boolean isDraw = userCommand.equals("y");
        boolean isNotDraw = userCommand.equals("n");
        if (!(isDraw || isNotDraw)) {
            throw new IllegalArgumentException("유효한 커맨드(예는 y, 아니오는 n)를 입력해 주세요.");
        }
        return isDraw;
    }
}
