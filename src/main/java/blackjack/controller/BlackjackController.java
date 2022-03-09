package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
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

    public void decideMoreCard(final Players players, final Deck deck) {
        for (Participant participant : players.getParticipants()) {
            decideOneMoreCard(participant, deck);
        }
        decideOneMoreCard(players.getDealer(), deck);
    }

    private void decideOneMoreCard(Participant participant, Deck deck) {
        while (isNotOverMaxScore(participant) && oneMoreCard(participant)) {
            participant.addCard(deck.draw());
            OutputView.printParticipantCards(participant);
        }
    }

    private boolean oneMoreCard(Participant participant) {
        String input = InputView.inputSelectMoreCard(participant.getName());
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("[ERROR] y 또는 n으로 입력하세요.");
        }
        return input.equals("y");
    }

    private boolean isNotOverMaxScore(Participant participant) {
        if (participant.isOverMaxScore()) {
            OutputView.printParticipantOverMaxScore(participant.getName());
        }
        return !participant.isOverMaxScore();
    }

    private void decideOneMoreCard(Dealer dealer, Deck deck) {
        if (dealer.acceptableCard()) {
            dealer.addCard(deck.draw());
            OutputView.printDealerAcceptCard();
            return;
        }
        OutputView.printDealerDenyCard();
    }

    public void announcePlayersFinishInfo(Players players) {
        OutputView.printFinishDealerInfo(players.getDealer());
        OutputView.printFinishParticipantInfo(players.getParticipants());
    }

    public void announcePlayersResult(Players players) {
        OutputView.printResult(players);
    }

    public void competeWithDealer(Players players) {
        players.getParticipants().forEach(participant -> players.getDealer().compete(participant));
    }
}
