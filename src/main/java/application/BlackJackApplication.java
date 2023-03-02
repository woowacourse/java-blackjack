package application;

import static java.util.stream.Collectors.toList;

import domain.CardDeck;
import domain.CardDeckGenerator;
import domain.Dealer;
import domain.Name;
import domain.Participant;
import domain.ParticipantGenerator;
import domain.Players;
import dto.DrawnCardsInfo;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {

    private static final int NUMBER_OF_SPLIT_CARDS = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackApplication(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.create();

        Dealer dealer = ParticipantGenerator.createDealer();
        Players players = createPlayers();

        splitCards(dealer, cardDeck);
        players.stream()
                .forEach(player -> splitCards(player, cardDeck));

        outputView.printCardSplitMessage(createDrawnCardsInfos(dealer, players));

    }

    private Players createPlayers() {
        List<String> rawNames = inputView.readPlayerNames();

        List<Name> names = rawNames.stream()
                .map(Name::new)
                .collect(toList());
        return ParticipantGenerator.createPlayers(names);
    }

    private void splitCards(final Participant participant, final CardDeck cardDeck) {
        for (int i = 0; i < NUMBER_OF_SPLIT_CARDS; i++) {
            participant.pickCard(cardDeck.draw());
        }
    }

    private List<DrawnCardsInfo> createDrawnCardsInfos(final Dealer dealer, final Players players) {
        List<DrawnCardsInfo> cardInfos = new ArrayList<>();

        cardInfos.add(DrawnCardsInfo.toDto(dealer, dealer.openDrawnCards()));
        players.stream()
                .forEach(player -> cardInfos.add(DrawnCardsInfo.toDto(player, player.openDrawnCards())));

        return cardInfos;
    }
}
