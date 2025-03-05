package blackjack.controller;

import blackjack.domain.BlackJackManager;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        try {
            String input = inputView.readNames();
            List<String> names = InputParser.parseStringToList(input);
            BlackJackManager blackJackManager = BlackJackManager.createByPlayerNames(names);

            // 카드 2장 배부
            blackJackManager.initCardsToParticipants();
            outputView.printStartGame(blackJackManager.getPlayerNames());
            for (Participant participant : blackJackManager.getParticipants()) {
                outputView.printCardResult(participant);
            }

            // 카드 추가 배부 여부 입력
            for (Participant participant : blackJackManager.getParticipants()) {
                if (participant instanceof Dealer) {
                    continue;
                }
                while (participant.isPossibleToAdd()) {
                    Player player = (Player)participant;
                    String yesOrNo = inputView.readGetOneMore(player.getName());
                    if(yesOrNo.equals("n")) {
                        break;
                    }
                    blackJackManager.addExtraCard(player);
                    outputView.printCardResult(participant);
                }
            }

            // 딜러 추가 배부
            if (blackJackManager.addExtraCardToDealer()) {
                outputView.printAddExtraCardToDealer();
            }

            // 최종 카드 결과 출력
            for (Participant participant : blackJackManager.getParticipants()) {
                outputView.printFinalCardResult(participant);
            }

            } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
        }
    }
}
