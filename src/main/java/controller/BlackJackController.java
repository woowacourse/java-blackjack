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

    public void run() {
        final Dealer dealer = Dealer.generate();
        final List<Player> players = getPlayers();
        final GameBoard gameBoard = GameBoard.generateOf(dealer, players);

        startBlackJack(gameBoard);
    }

    private void startBlackJack(final GameBoard gameBoard) {
        gameBoard.shufflePlayingCard();
        drawTwoCards(gameBoard);

        for (Participant player : gameBoard.getPlayers()) {
            startTurnOf(player, gameBoard);
        }
        startDealerTurn(gameBoard);
        printBlackJackResult(gameBoard);
    }

    private void drawTwoCards(GameBoard gameBoard) {
        gameBoard.drawTwoCards();

        ParticipantResponse participantResponse = ParticipantResponse.of(gameBoard.getDealer(), gameBoard.getPlayers());
        outputView.printDrawTwoCardsMessage(participantResponse);

        CardDeckStatusResponse cardDeckStatusResponse = CardDeckStatusResponse.from(gameBoard.getCardDeckOfParticipant());
        outputView.printCardDeckStatus(cardDeckStatusResponse);
    }

    private void startTurnOf(Participant player, GameBoard gameBoard) {
        while (gameBoard.ableToDraw(player)) {
            Answer answer = Answer.from(inputView.askDrawOneMore(player.getNickname()));
            if (answer == Answer.NO) {
                break;
            }

            gameBoard.drawCardTo(player);
            CardDeckStatusResponse singleCardDeckStatusResponse = CardDeckStatusResponse.from(
                    player.getNickname(),
                    gameBoard.getCardDeckOf(player)
            );

            outputView.printCardDeckStatus(singleCardDeckStatusResponse);
        }
    }

    private void startDealerTurn(GameBoard gameBoard) {
        Participant dealer = gameBoard.getDealer();
        while (gameBoard.ableToDraw(dealer)) {
            gameBoard.drawCardTo(dealer);
            outputView.printDrawSingleCardToDealerMessage(dealer.getNickname(), Dealer.STAY_THRESHOLD);
        }
    }

    private void printBlackJackResult(GameBoard gameBoard) {
        printBlackJackScore(gameBoard);
        printBattleResult(gameBoard);
    }

    private void printBlackJackScore(GameBoard gameBoard) {
        List<BlackJackResultResponse> blackJackResultResponses = new ArrayList<>();

        blackJackResultResponses.add(generateResultResponseOfDealer(gameBoard.getDealer(), gameBoard));
        for (Participant player : gameBoard.getPlayers()) {
            blackJackResultResponses.add(generateResultResponseOfPlayer(player, gameBoard));
        }

        outputView.printBlackJackResult(blackJackResultResponses);
    }

    private void printBattleResult(GameBoard gameBoard) {
        gameBoard.calculateBattleResult();

        List<Participant> participants = gameBoard.getParticipants();
        List<BattleResultResponse> battleResultResponses = new ArrayList<>();
        for (Participant participant : participants) {
            String participantNickname = participant.getNickname();
            Map<BattleResult, Integer> battleResult = participant.getGameRecord();

            BattleResultResponse battleResultResponse = new BattleResultResponse(participantNickname, battleResult);
            battleResultResponses.add(battleResultResponse);
        }

        outputView.printBattleResult(battleResultResponses);
    }

    private BlackJackResultResponse generateResultResponseOfPlayer(Participant player, GameBoard gameBoard) {
        String playerNickname = player.getNickname();
        List<String> playerCardNames = gameBoard.getCardDeckOf(player)
                .getCards().stream()
                .map(card -> card.getName() + card.getCardSymbol())
                .toList();

        return new BlackJackResultResponse(playerNickname, playerCardNames, gameBoard.getScoreOf(player).getScore());

    }

    private BlackJackResultResponse generateResultResponseOfDealer(Participant dealer, GameBoard gameBoard) {
        String dealerNickname = dealer.getNickname();
        List<String> dealerCardNames = gameBoard.getCardDeckOf(dealer)
                .getCards().stream()
                .map(card -> card.getName() + card.getCardSymbol())
                .toList();

        return new BlackJackResultResponse(dealerNickname, dealerCardNames, gameBoard.getScoreOf(dealer).getScore());
    }

    private List<Player> getPlayers() {
        final List<String> playerNames = inputView.askPlayerNames();

        return playerNames.stream()
                .map(Player::from)
                .collect(Collectors.toList());
    }
}
