package controller;

import domain.GameBoard;
import domain.participant.BattleResult;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import dto.BattleResultResponse;
import dto.BlackJackResultResponse;
import dto.CardDeckStatusResponse;
import dto.ParticipantResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private GameBoard makeGameBoard(Dealer dealer, List<Player> players) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        return new GameBoard(participants);
    }

    public void run() {
        final Dealer dealer = Dealer.generate();
        final List<Player> players = getPlayers();
        final GameBoard gameBoard = makeGameBoard(dealer, players);

        startBlackJack(dealer, players, gameBoard);
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

    private void printBlackJackResult(Dealer dealer, List<Player> players, GameBoard gameBoard) {
        printBlackJackScore(dealer, players, gameBoard);

        printBattleResult(dealer, players, gameBoard);
    }

    private void printBattleResult(Dealer dealer, List<Player> players, GameBoard gameBoard) {
        gameBoard.calculateBattleResult();

        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        List<BattleResultResponse> battleResultResponses = new ArrayList<>();
        for (Participant participant : participants) {
            String participantNickname = participant.getNickname();
            Map<BattleResult, Integer> battleResult = participant.getBattleResult();

            BattleResultResponse battleResultResponse = new BattleResultResponse(participantNickname, battleResult);
            battleResultResponses.add(battleResultResponse);
        }

        outputView.printBattleResult(battleResultResponses);
    }

    private void printBlackJackScore(Dealer dealer, List<Player> players, GameBoard gameBoard) {
        List<BlackJackResultResponse> blackJackResultResponses = getBlackJackResultResponses();

        blackJackResultResponses.add(generateResultResponseOfDealer(dealer, gameBoard));
        for (Player player : players) {
            blackJackResultResponses.add(generateResultResponseOfPlayer(player, gameBoard));
        }

        outputView.printBlackJackResult(blackJackResultResponses);
    }

    private BlackJackResultResponse generateResultResponseOfPlayer(Player player, GameBoard gameBoard) {
        String playerNickname = player.getNickname();
        List<String> playerCardNames = gameBoard.getCardDeckOf(player)
                .getCards().stream()
                .map(card -> card.getName() + card.getCardSymbol())
                .toList();

        return new BlackJackResultResponse(playerNickname, playerCardNames, gameBoard.getScoreOf(player));

    }

    private BlackJackResultResponse generateResultResponseOfDealer(Dealer dealer, GameBoard gameBoard) {
        String dealerNickname = dealer.getNickname();
        List<String> dealerCardNames = gameBoard.getCardDeckOf(dealer)
                .getCards().stream()
                .map(card -> card.getName() + card.getCardSymbol())
                .toList();

        return new BlackJackResultResponse(dealerNickname, dealerCardNames, gameBoard.getScoreOf(dealer));
    }

    private static List<BlackJackResultResponse> getBlackJackResultResponses() {
        List<BlackJackResultResponse> blackJackResultResponses = new ArrayList<>();
        return blackJackResultResponses;
    }

    private void startDealerTurn(Dealer dealer, GameBoard gameBoard) {
        while (gameBoard.ableToDraw(dealer)) {
            gameBoard.drawCardTo(dealer);
            outputView.printDrawSingleCardToDealerMessage(dealer.getNickname(), Dealer.STAY_THRESHOLD);
        }
    }

    private void startTurnOf(Player player, GameBoard gameBoard) {
        while (gameBoard.ableToDraw(player)) {
            Answer answer = Answer.from(inputView.askDrawOneMore(player.getNickname()));
            if (answer == Answer.NO) {
                break;
            }
            gameBoard.drawCardTo(player);
            CardDeckStatusResponse singleCardDeckStatusResponse = CardDeckStatusResponse.from(player.getNickname(), gameBoard.getCardDeckOf(
                    player));
            outputView.printCardDeckStatus(singleCardDeckStatusResponse);
        }
    }

    private void drawTwoCards(Dealer dealer, List<Player> players, GameBoard gameBoard) {
        gameBoard.drawTwoCards();
        ParticipantResponse participantResponse = ParticipantResponse.of(dealer, players);
        outputView.printDrawTwoCardsMessage(participantResponse);
        CardDeckStatusResponse cardDeckStatusResponse = CardDeckStatusResponse.from(gameBoard.getCardDeckOfParticipant());
        outputView.printCardDeckStatus(cardDeckStatusResponse);
    }

    private List<Player> getPlayers() {
        final List<String> playerNames = inputView.askPlayerNames();

        return playerNames.stream()
                .map(Player::from)
                .collect(Collectors.toList());
    }
}
