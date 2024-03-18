package controller;

import model.BlackJack;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardSize;
import model.card.Cards;
import model.player.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.function.Supplier;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        BlackJack blackJack = createBlackJack();

        outputView.printPlayerNames(blackJack.findParticipantsName());
        outputView.printPlayerCards(blackJack.findDealerCards(), blackJack.matchParticipantsNameAndCards());

        offerMoreCards(blackJack);

        outputView.printBlackJackScore(blackJack.findDealerCards(), blackJack.matchParticipantsNameAndCards());
        outputView.printParticipantsRevenue(blackJack.findDealerRevenue(), blackJack.matchNameAndRevenues());
    }

    private BlackJack createBlackJack() {
        CardDeck cardDeck = new CardDeck(Card.createCardDeck());
        Participants participants = createParticipant(() -> cardDeck.selectRandomCards(CardSize.TWO));
        Dealer dealer = new Dealer(cardDeck.selectRandomCards(CardSize.TWO));
        return new BlackJack(new Users(participants, dealer), cardDeck);
    }

    private Participants createParticipant(Supplier<Cards> selectCard) {
        List<Name> names = inputView.requestParticipantNames()
                .stream()
                .map(Name::new)
                .toList();

        return new ParticipantsBuilder().names(names)
                .gameMoneys(names.stream()
                        .map(inputView::requestParticipantMoney)
                        .toList())
                .cards(selectCard)
                .build();
    }

    private void offerMoreCards(BlackJack blackJack) {
        blackJack.decideParticipantsPlay(inputView::isOneMoreCard, outputView::printPlayerCardMessage);
        blackJack.decideDealerPlay(outputView::printDealerAddCard);
    }
}
