package object.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import object.card.Card;
import object.participant.Dealer;
import object.participant.Participant;
import object.participant.Player;
import object.view.Answer;
import object.view.InputView;
import object.view.OutputView;
import response.BattleResultResponse;
import response.BlackJackResultResponse;
import response.CardDeckStatusResponse;
import response.ParticipantResponse;

public class BlackJackManager {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackManager(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Dealer dealer = Dealer.generate();
        final List<Player> players = getBetPlayers();
        final BlackJackBoard blackJackBoard = BlackJackBoard.generateOf(dealer, players);

        startBlackJack(blackJackBoard);
    }

    private void startBlackJack(final BlackJackBoard blackJackBoard) {
        blackJackBoard.shufflePlayingCard();

        processInitCardDraws(blackJackBoard);

        for (Participant player : blackJackBoard.getPlayers()) {
            processTurnOf(player, blackJackBoard);
        }
        processDealerTurn(blackJackBoard);
        processGameResult(blackJackBoard);
    }

    private void processInitCardDraws(BlackJackBoard blackJackBoard) {
        blackJackBoard.drawTwoCards();

        ParticipantResponse participantResponse = ParticipantResponse.of(blackJackBoard.getDealer(), blackJackBoard.getPlayers());
        outputView.printDrawTwoCardsMessage(participantResponse);

        CardDeckStatusResponse cardDeckStatusResponse = CardDeckStatusResponse.generateInitCardResponse(blackJackBoard.getCardDeckOfParticipant());
        outputView.printCardDeckStatus(cardDeckStatusResponse);
    }

    private void processTurnOf(Participant player, BlackJackBoard blackJackBoard) {
        while (blackJackBoard.ableToDraw(player)) {
            Answer answer = Answer.from(inputView.askDrawOneMore(player.getNickname()));
            if (answer == Answer.NO) {
                break;
            }

            blackJackBoard.drawCardTo(player);
            CardDeckStatusResponse singleCardDeckStatusResponse = CardDeckStatusResponse.of(
                    player.getNickname(),
                    blackJackBoard.getCardDeckOf(player)
            );

            outputView.printCardDeckStatus(singleCardDeckStatusResponse);
        }
    }

    private void processDealerTurn(BlackJackBoard blackJackBoard) {
        Participant dealer = blackJackBoard.getDealer();
        while (blackJackBoard.ableToDraw(dealer)) {
            blackJackBoard.drawCardTo(dealer);
            outputView.printDrawSingleCardToDealerMessage(dealer.getNickname(), Dealer.STAY_THRESHOLD);
        }
    }

    private void processGameResult(BlackJackBoard blackJackBoard) {
        blackJackBoard.calculateBattleResult();
        printBlackJackScore(blackJackBoard);
        printBattleResult(blackJackBoard);
        printBetResult(blackJackBoard);
    }

    private void printBlackJackScore(BlackJackBoard blackJackBoard) {
        List<BlackJackResultResponse> blackJackResultResponses = new ArrayList<>();

        blackJackResultResponses.add(generateResultResponseOfDealer(blackJackBoard.getDealer(), blackJackBoard));
        for (Participant player : blackJackBoard.getPlayers()) {
            blackJackResultResponses.add(generateResultResponseOfPlayer(player, blackJackBoard));
        }

        outputView.printBlackJackResult(blackJackResultResponses);
    }

    private void printBetResult(BlackJackBoard blackJackBoard) {
        blackJackBoard.calculateDealerProfit();
        outputView.printFinalProfit(blackJackBoard.getParticipants());
    }

    private void printBattleResult(BlackJackBoard blackJackBoard) {
        List<Participant> participants = blackJackBoard.getParticipants();
        List<BattleResultResponse> battleResultResponses = new ArrayList<>();
        for (Participant participant : participants) {
            String participantNickname = participant.getNickname();
            Map<GameResult, Integer> battleResult = participant.getGameRecord();

            BattleResultResponse battleResultResponse = new BattleResultResponse(participantNickname, battleResult);
            battleResultResponses.add(battleResultResponse);
        }

        outputView.printBattleResult(battleResultResponses);
    }

    private BlackJackResultResponse generateResultResponseOfPlayer(Participant player, BlackJackBoard blackJackBoard) {
        String playerNickname = player.getNickname();
        List<String> playerCardNames = blackJackBoard.getCardDeckOf(player)
                .getCards().stream()
                .map(Card::getName)
                .toList();

        return new BlackJackResultResponse(playerNickname, playerCardNames, blackJackBoard.getScoreOf(player).getScore());

    }

    private BlackJackResultResponse generateResultResponseOfDealer(Participant dealer, BlackJackBoard blackJackBoard) {
        String dealerNickname = dealer.getNickname();
        List<String> dealerCardNames = blackJackBoard.getCardDeckOf(dealer)
                .getCards().stream()
                .map(Card::getName)
                .toList();

        return new BlackJackResultResponse(dealerNickname, dealerCardNames, blackJackBoard.getScoreOf(dealer).getScore());
    }

    private List<Player> getBetPlayers() {
        final List<String> playerNames = inputView.askPlayerNames();

        return playerNames.stream()
                .map(name -> {
                    Player player = Player.from(name);
                    player.bet(inputView.askBetMoney(name));
                    return player;
                })
                .toList();
    }
}
