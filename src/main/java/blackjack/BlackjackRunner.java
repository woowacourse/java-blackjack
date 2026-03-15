package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.Participants;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.dto.DrawResult;
import blackjack.dto.ParticipantResult;
import blackjack.dto.PlayerBettingRequest;
import blackjack.dto.PlayerNicknamesResult;
import blackjack.dto.PlayersBettingRequest;
import blackjack.dto.TotalGameResult;
import blackjack.util.PlayerNameParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
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
        outputView.askGameMembers();
        String playerNamesInput = inputView.readLine();
        List<String> playerNames = PlayerNameParser.parsePlayerNames(playerNamesInput);
        PlayersBettingRequest initialRequest = PlayersBettingRequest.createInitialRequest(playerNames);
        PlayersBettingRequest playersBettingRequest = betPlayers(initialRequest);
        Players players = Players.makePlayers(playersBettingRequest);
        Dealer dealer = Dealer.from();
        return new Participants(players, dealer);
    }

    private PlayersBettingRequest betPlayers(PlayersBettingRequest initialRequest) {
        List<PlayerBettingRequest> playerBettingRequests = new ArrayList<>();
        List<PlayerBettingRequest> value = initialRequest.value();
        for (PlayerBettingRequest playerBettingRequest : value) {
            String playerNickname = playerBettingRequest.playerNickname();
            outputView.askBetAmount(playerNickname);
            String amount = inputView.readLine();
            playerBettingRequests.add(PlayerBettingRequest.of(playerNickname, amount));
        }
        return PlayersBettingRequest.from(playerBettingRequests);
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
