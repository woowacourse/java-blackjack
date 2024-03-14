package controller;

import model.BlackJack;
import model.GameMoney;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardSize;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import model.player.ParticipantsBuilder;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        List<String> names = inputView.requestParticipantNames();
        List<Integer> gameMoneys = names.stream().map(inputView::requestParticipantMoney).toList();
        BlackJack blackJack = createBlackJack(names, gameMoneys);

        outputView.printPlayerNames(blackJack.findParticipantsName());
        outputView.printPlayerCards(blackJack.mapToUsersNameAndCards(), blackJack.mapToDealerNameAndCards());

        offerMoreCards(blackJack);

        outputView.printBlackJackScore(blackJack.mapToUsersNameAndCards(), blackJack.mapToDealerNameAndCards());
        outputView.printPlayersOutcome(blackJack.getDealerOutCome(), blackJack.matchParticipantsOutcome());
    }

    private BlackJack createBlackJack(List<String> names, List<Integer> gameMoneys) {
        CardDeck cardDeck = new CardDeck(Card.createCardDeck());
        Participants participants = createParticipants(names, gameMoneys, () -> cardDeck.selectRandomCards(CardSize.TWO));
        Dealer dealer = new Dealer(cardDeck.selectRandomCards(CardSize.TWO));
        return new BlackJack(participants, dealer, cardDeck);
    }

    private Participants createParticipants(List<String> names, List<Integer> gameMoneys, Supplier<Cards> selectCard) {
        List<Cards> cards = Stream.generate(selectCard)
                .limit(names.size())
                .toList();

        List<Participant> participants = IntStream.range(0, names.size())
                .mapToObj(i -> new Participant(names.get(i), cards.get(i), new GameMoney(gameMoneys.get(i))))
                .toList();

        return new Participants(participants);
    }

    private void offerMoreCards(BlackJack blackJack) {
        blackJack.decideParticipantsPlay(inputView::isOneMoreCard, outputView::printPlayerCardMessage);
        blackJack.decideDealerPlay(outputView::printDealerAddCard);
    }
}
