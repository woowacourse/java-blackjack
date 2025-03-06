package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        try {
            String input = inputView.readNames();
            List<String> names = InputParser.parseStringToList(input);
            BlackjackGame blackjackGame = BlackjackGame.createByPlayerNames(names);

            // 카드 2장 배부
            blackjackGame.initCardsToParticipants();
            outputView.printStartGame(blackjackGame.getPlayerNames());
            for (Participant participant : blackjackGame.getParticipants()) {
                outputView.printCardResult(participant);
            }

            // 카드 추가 배부 여부 입력
            for (Participant participant : blackjackGame.getParticipants()) {
                if (participant instanceof Dealer) {
                    continue;
                }
                while (participant.isPossibleToAdd()) {
                    Player player = (Player)participant;
                    String yesOrNo = inputView.readGetOneMore(player.getName());
                    if(yesOrNo.equals("n")) {
                        break;
                    }
                    blackjackGame.addExtraCard(player);
                    outputView.printCardResult(participant);
                }
            }

            // 딜러 추가 배부
            while (blackjackGame.addExtraCardToDealer()) {
                outputView.printAddExtraCardToDealer();
            }

            // 최종 카드 결과 출력
            for (Participant participant : blackjackGame.getParticipants()) {
                outputView.printFinalCardResult(participant);
            }

            outputView.printResultTitle();
            for (Participant participant : blackjackGame.getParticipants()) {
                if (participant instanceof Dealer) {
                    outputView.printDealerResult(blackjackGame.calculateStatisticsForDealer());
                    continue;
                }
                outputView.printPlayerResult((Player) participant, blackjackGame.findDealer());
            }
            } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
        }
    }
}
