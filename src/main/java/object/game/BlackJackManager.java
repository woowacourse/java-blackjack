package object.game;

import java.util.ArrayList;
import java.util.List;
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

        ParticipantResponse participantResponse = ParticipantResponse.makeResponseFrom(blackJackBoard);
        outputView.printDrawTwoCardsMessage(participantResponse);

        CardDeckStatusResponse cardDeckStatusResponse = CardDeckStatusResponse.makeInitCardsResponseFrom(blackJackBoard);
        outputView.printCardDeckStatus(cardDeckStatusResponse);
    }

    private void processTurnOf(Participant player, BlackJackBoard blackJackBoard) {
        while (blackJackBoard.ableToDraw(player)) {
            Answer answer = Answer.from(inputView.askDrawOneMore(player.getNickname()));
            if (answer == Answer.NO) {
                break;
            }

            blackJackBoard.drawCardTo(player);
            CardDeckStatusResponse singleCardDeckStatusResponse = CardDeckStatusResponse.makeResponseOf(player, blackJackBoard);

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

        BlackJackResultResponse resultResponseOfDealer = BlackJackResultResponse.makeResponseOf(blackJackBoard.getDealer(), blackJackBoard);
        blackJackResultResponses.add(resultResponseOfDealer);

        for (Participant player : blackJackBoard.getPlayers()) {
            BlackJackResultResponse resultResponse = BlackJackResultResponse.makeResponseOf(player, blackJackBoard);
            blackJackResultResponses.add(resultResponse);
        }

        outputView.printBlackJackResult(blackJackResultResponses);
    }

    private void printBetResult(BlackJackBoard blackJackBoard) {
        blackJackBoard.calculateDealerProfit();
        outputView.printFinalProfit(blackJackBoard.getParticipants());
    }

    private void printBattleResult(BlackJackBoard blackJackBoard) {
        List<BattleResultResponse> battleResultResponses = new ArrayList<>();
        for (Participant participant : blackJackBoard.getParticipants()) {
            battleResultResponses.add(BattleResultResponse.makeResponseFrom(participant));
        }

        outputView.printBattleResult(battleResultResponses);
    }

    private List<Player> getBetPlayers() {
        final List<String> playerNames = inputView.askPlayerNames();
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어를 입력해주세요. 최소 1명은 입력해야 합니다.");
        }
        if (playerNames.size() > 18) {
            throw new IllegalArgumentException("18명을 초과해서 참가할 수 없습니다. 18명 이하로 입력해주세요.");
        }
        return playerNames.stream()
                .map(name -> {
                    Player player = Player.from(name);
                    player.bet(inputView.askBetMoney(name));
                    return player;
                })
                .toList();
    }
}
