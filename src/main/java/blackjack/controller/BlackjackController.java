package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import blackjack.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {

    public BlackjackController() {
    }

    public List<Participant> createParticipants(String nameInput, Deck deck) {
        validateName(nameInput);
        List<String> names = convertNameInput(nameInput);
        return names.stream()
                .map(name -> new Participant(deck.initDistributeCard(), name))
                .collect(Collectors.toList());
    }

    private void validateName(String nameInput) {
        if (nameInput == null || nameInput.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참여자의 이름을 입력해주세요.");
        }
    }

    private List<String> convertNameInput(String nameInput) {
        return Arrays.stream(nameInput.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void announcePlayersInitCardInfo(Players players) {
        OutputView.printPlayersInitCardInfo(players);
    }
}
