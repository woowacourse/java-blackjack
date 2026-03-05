package blackjack.controller;

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

    public void run() {
        // 사람 이름 입력
        outputView.printPlayerNamesInputPrompt();
        List<String> names = inputView.inputPlayerNames();

        // 딜러, 플레이어 카드 초기 2장 나눠줌
        outputView.printCardDistributionCompletePrompt(names);

        // 딜러 1장 공개, 플레이어 2장 공개
        outputView.printDealerCards();
        outputView.printPlayersCards();

        // 각 플레이어 히트/스탠드 진행
        for () {
            do {
                outputView.printCardPickInputPrompt();
            } while (inputView.inputMoreCard());
        }

        // 딜러 카드 16초과할 때까지 추가
        while() {
            outputView.printDealerPicksCard();
        }
        outputView.printDealerDoesNotPickCard();

        // 딜러 카드 및 결과 공개
        outputView.printDealerCards();

        // 플레이어 카드 및 결과 공개
        for () {
            outputView.printPlayersCards();
        }

        // 최종 승패 출력
        outputView.printResult();
    }
}
