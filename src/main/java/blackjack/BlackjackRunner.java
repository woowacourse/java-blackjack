package blackjack;

import blackjack.domain.Participants;
import blackjack.domain.Players;
import blackjack.domain.PlayingCards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.ParticipantResult;
import blackjack.dto.DrawResult;
import blackjack.dto.WinningResult;
import blackjack.util.PlayerNameParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackRunner {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        Participants participants = makeParticipants();
        PlayingCards deck = PlayingCards.createShuffledDeck();
        deck = gameStart(participants, deck);

        printInitialSetup(participants);
        printInitialResult(participants);

        deck = playerTurn(participants, deck);
        dealerTurn(participants, deck);
        gameEnd(participants);
    }

    private void gameEnd(Participants participants) {
        printGameResult(participants);
    }

    private void printInitialResult(Participants participants) {
        outputView.printInitialResult(participants);
    }

    private void printInitialSetup(Participants participants) {
        outputView.printInitialSetUp(participants);
    }

    private Participants makeParticipants() {
        outputView.askGameMembers();
        String playerNamesInput = inputView.readLine();
        List<String> playerNames = PlayerNameParser.parsePlayerNames(playerNamesInput);
        Players players = Players.makePlayers(playerNames);
        Dealer dealer = Dealer.from();
        return new Participants(players, dealer);
    }

    public void printGameResult(Participants participants) {
        List<ParticipantResult> participantResult = participants.getGameResult();
        outputView.printGameResult(participantResult);

        List<WinningResult> winningResults = participants.getWinningResult();
        outputView.printWinner(winningResults);
    }

    private void printWinner(ParticipantResult participantResult) {
    }

    private PlayingCards gameStart(Participants participants, PlayingCards deck) {
        return participants.distributeCards(deck);
    }

    private PlayingCards dealerTurn(Participants participants, PlayingCards deck) {
        boolean isDealerDraw = participants.isDealerDraw();
        if (isDealerDraw) {
            outputView.printDealerTurn();
            deck = participants.dealerDraw(deck);
        }
        return deck;
    }

    private PlayingCards playerTurn(Participants participants, PlayingCards deck) {
        while (participants.findDrawablePlayer() != null) {
            deck = drawCard(participants, deck);
        }
        return deck;
    }

    private PlayingCards drawCard(Participants participants, PlayingCards deck) {
        String drawablePlayerNickname = participants.findDrawablePlayer();
        boolean isPlayerDraw = isDraw(drawablePlayerNickname);
        if (isPlayerDraw) {
            DrawResult drawResult = participants.addCardToAvailablePlayer(deck);
            PlayingCards playerHand = drawResult.drewCard();
            printDrewResult(drawablePlayerNickname, playerHand);
            return drawResult.drewDeck();
        }
        participants.dontWandDraw();
        return deck;
    }

    private void printDrewResult(String drawablePlayerNickname, PlayingCards playerCards) {
        outputView.printPlayerStatus(drawablePlayerNickname, playerCards.getStatusByDisplayName());
    }

    private boolean isDraw(String nickname) {
        outputView.hitOrStand(nickname);
        return inputView.readLine().equals("y");
    }
}
