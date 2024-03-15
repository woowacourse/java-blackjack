package controller;

import java.util.ArrayList;
import java.util.List;
import model.BettingMoney;
import model.BlackJack;
import model.card.CardDeck;
import model.card.CardSize;
import model.player.Dealer;
import model.player.Name;
import model.player.Participant;
import model.player.Participants;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startGame() {
        List<String> names = inputView.askParticipantNames();
        BlackJack blackJack = createBlackJack(names);

        outputView.printPlayerNames(blackJack.findParticipantsName());
        outputView.printPlayerCards(blackJack.mapToUsersNameAndCards(), blackJack.mapToDealerNameAndCards());

        offerMoreCards(blackJack);

        outputView.printBlackJackScore(blackJack.mapToUsersNameAndCards(), blackJack.mapToDealerNameAndCards());
        outputView.printResult(blackJack.getDealerProfit(), blackJack.getParticipantProfits());
    }

    private BlackJack createBlackJack(List<String> names) {
        CardDeck cardDeck = new CardDeck(CardDeck.createCards());
        Participants participants = createParticipants(names, cardDeck);
        Dealer dealer = new Dealer(cardDeck, () -> cardDeck.selectRandomCards(CardSize.TWO));

        return new BlackJack(participants, dealer);
    }

    private Participants createParticipants(List<String> names, CardDeck cardDeck) {
        return new Participants(new ArrayList<>(names.stream()
                .map(name -> new Participant(new Name(name), cardDeck.selectRandomCards(CardSize.TWO),
                        inputView.askBettingAmount(name))).toList()));
    }

    private void offerMoreCards(BlackJack blackJack) {
        blackJack.decideParticipantsPlay(inputView::isOneMoreCard, outputView::printPlayerCardMessage);
        blackJack.decideDealerPlay(outputView::printDealerAddCard);
    }
}
