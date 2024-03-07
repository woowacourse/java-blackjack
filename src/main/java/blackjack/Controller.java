package blackjack;

import java.util.ArrayList;
import java.util.List;
import blackjack.domain.Card;
import blackjack.domain.CardRank;
import blackjack.domain.CardShape;
import blackjack.domain.Deck;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.view.InputView;
import blackjack.view.OutputView;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = createParticipants();
        Deck deck = new Deck(createCards());

        initialDeal(participants, deck);
        outputView.printInitialDeal(participants.getDealer(), participants.getPlayers());
    }

    private void initialDeal(Participants participants, Deck deck) {
        for (Participant participant : participants.getParticipants()) {
            participant.hit(deck.draw());
            participant.hit(deck.draw());
        }
    }

    private Participants createParticipants() {
        List<String> playerNames = inputView.readPlayerNames();

        return new Participants(playerNames);
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (CardRank rank : CardRank.values()) {
            for (CardShape shape : CardShape.values()) {
                cards.add(new Card(rank, shape));
            }
        }

        return cards;
    }
}
