package application;

import domain.CardDeck;
import domain.CardDeckGenerator;
import domain.Dealer;
import domain.Name;
import domain.Participant;
import domain.ParticipantGenerator;
import domain.Players;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;

public class BlackJackApplication {

    private static final int NUMBER_OF_SPLIT_CARDS = 2;

    private final InputView inputView;

    public BlackJackApplication(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.create();

        Dealer dealer = ParticipantGenerator.createDealer();
        Players players = createPlayers();

        splitCards(dealer, cardDeck);
        players.stream()
                .forEach(player -> splitCards(player, cardDeck));


    }

    private Players createPlayers() {
        List<String> rawNames = inputView.readPlayerNames();

        List<Name> names = rawNames.stream()
                .map(Name::new)
                .collect(Collectors.toList());
        return ParticipantGenerator.createPlayers(names);
    }

    private void splitCards(final Participant participant, final CardDeck cardDeck) {
        for (int i = 0; i < NUMBER_OF_SPLIT_CARDS; i++) {
            participant.pickCard(cardDeck.draw());
        }
    }
}
