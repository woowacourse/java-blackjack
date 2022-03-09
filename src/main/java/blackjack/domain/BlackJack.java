package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {

    public void run() {
        String[] names = InputView.inputName();
        Deck deck = new Deck();

        List<Player> players = Arrays.stream(names)
                .map(n -> new Player(n,deck.initialDraw()))
                .collect(Collectors.toList());
        Dealer dealer = new Dealer(deck.initialDraw());

        OutputView.printInitialCards(getInitialParticipantDTOS(players, dealer));
    }

    private List<InitialParticipantDTO> getInitialParticipantDTOS(List<Player> players, Dealer dealer) {
        List<InitialParticipantDTO> dtos = new ArrayList<>();
        dtos.add(new InitialParticipantDTO(dealer));
        dtos.addAll(players.stream()
                .map(InitialParticipantDTO::new)
                .collect(Collectors.toList()));
        return dtos;
    }

}
