package controller;

import domain.ScoreResult.BattleResults;
import domain.game.GameBoard;
import domain.ScoreResult.ScoreBoard;
import domain.game.GameRule;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import dto.BattleResultResponse;
import dto.BlackJackResultResponse;
import dto.CardDeckStatusResponse;
import dto.ParticipantResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import view.Answer;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Dealer dealer = new Dealer();
        final List<Player> players = getPlayers();

        final Participants participants = makeParticipants(dealer, players);
        final GameBoard gameBoard = new GameBoard(participants);

        startBlackJack(dealer, players, gameBoard);

        inputView.closeScanner();
    }

    private void startBlackJack(final Dealer dealer, final List<Player> players, final GameBoard gameBoard) {
        gameBoard.shufflePlayingCard();
        drawTwoCards(dealer, players, gameBoard);

        for (Player player : players) {
            startTurnOf(player, gameBoard);
        }
        startDealerTurn(dealer, gameBoard);

        printBlackJackResult(dealer, players, gameBoard);
    }

    private void printBlackJackResult(final Dealer dealer, final List<Player> players, final GameBoard gameBoard) {
        printBlackJackScore(dealer, players);

        printBattleResult(gameBoard);
    }

    private void printBattleResult(final GameBoard gameBoard) {
        final ScoreBoard scoreBoard = new ScoreBoard(gameBoard.getParticipants());
        scoreBoard.calculateScoreBoard();
        Map<Participant, BattleResults> battleResultsMap = scoreBoard.getScoreBoard();

        List<BattleResultResponse> battleResultResponses = new ArrayList<>();
        for (Map.Entry<Participant, BattleResults> entryBattleResult : battleResultsMap.entrySet()) {
            String participantNickname = entryBattleResult.getKey().getNickname();
            Map<String, Integer> battleResult = entryBattleResult.getValue().getResults().entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> entry.getKey().getName(),
                            Entry::getValue
                    ));
            BattleResultResponse battleResultResponse = new BattleResultResponse(participantNickname, battleResult);
            battleResultResponses.add(battleResultResponse);
        }

        outputView.printBattleResult(battleResultResponses);
    }

    private void printBlackJackScore(final Dealer dealer, final List<Player> players) {
        List<BlackJackResultResponse> blackJackResultResponses = new ArrayList<>();

        blackJackResultResponses.add(generateResultResponseOfDealer(dealer));
        for (Player player : players) {
            blackJackResultResponses.add(generateResultResponseOfPlayer(player));
        }

        outputView.printBlackJackResult(blackJackResultResponses);
    }

    private BlackJackResultResponse generateResultResponseOfPlayer(final Player player) {
        String playerNickname = player.getNickname();
        List<String> playerCardNames = player.getCardDeck()
                .getCards().stream()
                .map(card -> card.getName() + card.getCardSymbol())
                .toList();

        return new BlackJackResultResponse(playerNickname, playerCardNames, player.getScore());

    }

    private BlackJackResultResponse generateResultResponseOfDealer(final Dealer dealer) {
        String dealerNickname = dealer.getNickname();
        List<String> dealerCardNames = dealer.getCardDeck()
                .getCards().stream()
                .map(card -> card.getName() + card.getCardSymbol())
                .toList();

        return new BlackJackResultResponse(dealerNickname, dealerCardNames, dealer.getScore());
    }

    private void startDealerTurn(final Dealer dealer, final GameBoard gameBoard) {
        while (gameBoard.ableToDraw(dealer)) {
            gameBoard.drawCardTo(dealer);
            outputView.printDrawSingleCardToDealerMessage(dealer.getNickname(), GameRule.DEALER_STAY.getValue());
        }
    }

    private void startTurnOf(final Player player, final GameBoard gameBoard) {
        while (gameBoard.ableToDraw(player)) {
            Answer answer = inputView.askDrawOneMore(player.getNickname());
            if (answer == Answer.NO) {
                break;
            }
            gameBoard.drawCardTo(player);
            CardDeckStatusResponse singleCardDeckStatusResponse = CardDeckStatusResponse.from(player.getNickname(), player.getCardDeck());
            outputView.printCardDeckStatus(singleCardDeckStatusResponse);
        }
    }

    private void drawTwoCards(final Dealer dealer, final List<Player> players, final GameBoard gameBoard) {
        gameBoard.drawTwoCards();
        ParticipantResponse participantResponse = ParticipantResponse.of(dealer, players);
        outputView.printDrawTwoCardsMessage(participantResponse);
        List<Participant> originParticipants = gameBoard.getParticipants().getParticipants();
        CardDeckStatusResponse cardDeckStatusResponse = CardDeckStatusResponse.from(originParticipants);
        outputView.printCardDeckStatus(cardDeckStatusResponse);
    }

    private Participants makeParticipants(final Dealer dealer, final List<Player> players) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        return new Participants(participants);
    }

    private List<Player> getPlayers() {
        final List<String> playerNames = inputView.askPlayerNames();
        final List<Player> players = new ArrayList<>();

        for (String name : playerNames) {
            players.add(new Player(name));
        }

        return players;
    }
}
