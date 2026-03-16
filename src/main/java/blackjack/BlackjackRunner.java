package blackjack;

import blackjack.domain.Amount;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Nicknames;
import blackjack.domain.Participants;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.dto.DrawResult;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerNicknamesResult;
import blackjack.dto.TotalGameResult;
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
        Deck deck = Deck.createShuffledDeck();
        deck = gameStart(participants, deck);

        printInitialSetup(participants);
        printInitialResult(participants);

        deck = playerTurn(participants, deck);
        dealerTurn(participants, deck);
        gameEnd(participants);
    }

    private Deck gameStart(Participants participants, Deck deck) {
        return participants.distributeCards(deck);
    }

    private void printInitialSetup(Participants participants) {
        PlayerNicknamesResult playerNicknamesResult = PlayerNicknamesResult.from(participants);
        outputView.printInitialSetUp(playerNicknamesResult);
    }

    private void gameEnd(Participants participants) {
        printGameResult(participants);
    }

    private void printInitialResult(Participants participants) {
        List<ParticipantResult> participantResults = participants.getInitialResult();
        outputView.printInitialResult(participantResults);
    }

    private Participants makeParticipants() {
        Nicknames nicknames = getPlayersNickname();
        Players players = betPlayers(nicknames);
        Dealer dealer = Dealer.from();
        return new Participants(players, dealer);
    }

    private Nicknames getPlayersNickname() {
        try {
            outputView.askGameMembers();
            String playerNamesInput = inputView.readLine();
            List<String> playerNames = PlayerNameParser.parsePlayerNames(playerNamesInput);
            return Nicknames.from(playerNames);
        } catch (IllegalArgumentException e) {
            outputView.printLine(e.getMessage());
            return getPlayersNickname();
        }
    }

    private Players betPlayers(Nicknames nicknames) {
        List<String> playerNicknames = nicknames.getRawNicknames();
        Players players = Players.makeEmptyPlayers();
        for (String nickname : playerNicknames) {
            players = addPlayerWithValidBet(nicknames, nickname, players);
        }
        return players;
    }

    private Players addPlayerWithValidBet(Nicknames nicknames, String nickname, Players players) {
        try {
            Amount playerRequest = readBettingRequest(nickname);
            return players.addPlayer(nicknames.findByNickname(nickname), playerRequest);
        } catch (IllegalArgumentException e) {
            outputView.printLine(e.getMessage());
            return addPlayerWithValidBet(nicknames, nickname, players);
        }
    }

    private Amount readBettingRequest(String nickname) {
        outputView.askBetAmount(nickname);
        String amount = inputView.readLine();
        return Amount.from(amount);
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
        while (participants.findDrawablePlayer() != null) {
            deck = drawCard(participants, deck);
        }
        return deck;
    }

    private Deck drawCard(Participants participants, Deck deck) {
        String drawablePlayerNickname = participants.findDrawablePlayer();
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
        return userCommand.equals("y");
    }
}
