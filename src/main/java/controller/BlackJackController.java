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
        List<String> playerNames = inputView.askPlayerNames();
        Dealer dealer = Dealer.generate();
        List<Player> players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(Player.from(name));
        }

        ArrayList<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        GameBoard gameBoard = new GameBoard(participants);

        gameBoard.shufflePlayingCard();

        gameBoard.drawTwoCards();
        ParticipantResponse participantResponse = ParticipantResponse.of(dealer, players);
        outputView.printDrawTwoCardsMessage(participantResponse);

        CardDeckStatusResponse cardDeckStatusResponse = CardDeckStatusResponse.from(gameBoard.getCardDeckOfParticipant());
        outputView.printCardDeckStatus(cardDeckStatusResponse);

        for (Player player : players) {
            while (gameBoard.ableToDraw(player)) {
                Answer answer = Answer.from(inputView.askDrawOneMore(player.getNickname()));
                if (answer == Answer.NO) {
                    break;
                }
                // 주고 -> 출력
                gameBoard.drawCardTo(player);
                CardDeckStatusResponse singleCardDeckStatusResponse = CardDeckStatusResponse.from(player.getNickname(), gameBoard.getCardDeckOf(player));
                outputView.printCardDeckStatus(singleCardDeckStatusResponse);
            }
        }

        // 딜러 출력
        while (gameBoard.ableToDraw(dealer)) {
            gameBoard.drawCardTo(dealer);
            outputView.printDrawSingleCardToDealerMessage(dealer.getNickname(), Dealer.STAY_THRESHOLD);
        }

        // 전체 스코어 출력
        // DTO 생성
        List<BlackJackResultResponse> blackJackResultResponses = new ArrayList<>();
        String dealerNickname = dealer.getNickname();
        List<String> dealerCardNames = gameBoard.getCardDeckOf(dealer)
                                                .getCards().stream()
                                                .map(card -> card.getNumber() + card.getCardSymbol())
                                                .toList();

        BlackJackResultResponse dealerResultResponse = new BlackJackResultResponse(dealerNickname, dealerCardNames, gameBoard.getScoreOf(dealer));
        blackJackResultResponses.add(dealerResultResponse);

        for (Player player : players) {
            String playerNickname = player.getNickname();
            List<String> playerCardNames = gameBoard.getCardDeckOf(player)
                                    .getCards().stream()
                                    .map(card -> card.getNumber() + card.getCardSymbol())
                                    .toList();


            BlackJackResultResponse playerResultResponse = new BlackJackResultResponse(playerNickname, playerCardNames, gameBoard.getScoreOf(player));
            blackJackResultResponses.add(playerResultResponse);
        }

        outputView.printBlackJackResult(blackJackResultResponses);

        // 최종 승패
        gameBoard.calculateBattleResult();

        List<BattleResultResponse> battleResultResponses = new ArrayList<>();
        for (Participant participant : participants) {
            String participantNickname = participant.getNickname();
            Map<BattleResult, Integer> battleResult = participant.getBattleResult();

            BattleResultResponse battleResultResponse = new BattleResultResponse(participantNickname, battleResult);
            battleResultResponses.add(battleResultResponse);
        }

        outputView.printBattleResult(battleResultResponses);
    }
}
