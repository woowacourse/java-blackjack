package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    public void run() {
        // 1. 참가자를 세팅한다.
        List<Participant> participants = initParticipants();

        // 2. 카드분배

        // 3. 결과 추출 및 출력
    }

    private List<Participant> initParticipants() {
        PlayerNames playerNames = initPlayerNames();
        List<Participant> participants = new ArrayList<>();
        playerNames.values()
                .stream()
                .map(playerName -> participants.add(Player.of(playerName)));
        participants.add(Dealer.init());

        return participants;
    }

    private PlayerNames initPlayerNames() {
        try {
            List<String> inputPlayerNames = InputView.inputPlayerNames();
            return PlayerNames.of(inputPlayerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return initPlayerNames();
        }
    }
}
