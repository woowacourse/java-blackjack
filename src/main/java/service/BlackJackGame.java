package service;

import domain.Card;
import domain.CardBox;
import domain.Cards;
import domain.Dealer;
import domain.Name;
import domain.Participant;
import domain.Participants;
import domain.Player;
import domain.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = makeParticipants();
        printInitGameMessage(participants);

        askPlayer(participants);

        dealerDrawCardByMinimumCondition(participants.getDealer());

        printCardResult(participants);
        printWinningResult(participants);
    }

    private Participants makeParticipants() {
        List<Name> names = getNames();
        Dealer dealer = makeDealer();
        List<Participant> participantsByPlayer = initPlayers(dealer, names);
        participantsByPlayer.add(0, dealer);
        return new Participants(participantsByPlayer);
    }

    private List<Name> getNames() {
        List<String> playerNames = inputView.getPlayer();
        return readNames(playerNames);
    }

    private Dealer makeDealer() {
        CardBox cardBox = new CardBox();
        List<Card> cards = new ArrayList<>();
        cards.add(cardBox.get());
        Cards cardss = new Cards(cards);
        return new Dealer(cardBox, cardss);
    }

    private List<Participant> initPlayers(final Dealer dealer, final List<Name> names) {
        List<Participant> participants = new ArrayList<>();
        for (Name name : names) {
            participants.add(makePlayer(dealer, name));
        }
        Card drewInitLastCardOfDealer = dealer.draw();
        dealer.addCard(drewInitLastCardOfDealer);
        return participants;
    }

    private void printInitGameMessage(final Participants participants) {
        outputView.printPlayerNames(participants.getPlayerNames());
        outputView.printCardsPerDealer(participants.getDealerName(), participants.getDealer().printInitCards());
        outputView.printCardsPerPlayer(participants.getPlayerNames(), copiedBoxedCards(participants.getPlayers()));
    }

    private void askPlayer(final Participants participants) {
        for (Player player : participants.getPlayersToList()) {
            drawCard(player, participants.getDealer());
        }
    }

    private void drawCard(final Player player, final Dealer dealer) {
        while (inputView.addOrStop(player.getName()) && player.isNotBurst()) {
            Card drewCard = dealer.draw();
            player.addCard(drewCard);
            outputView.printCurrentPlayerResult(player.getName(), player.getCards().cardsToString());
        }
        outputView.printCurrentPlayerResult(player.getName(), player.getCards().cardsToString());
    }

    private void dealerDrawCardByMinimumCondition(final Dealer dealer) {
        outputView.newLine();
        while (dealer.isSumUnderStandard()) {
            outputView.noticeDealerUnderStandard();
            dealer.addCard(dealer.draw());
        }
    }

    private void printCardResult(final Participants participants) {
        outputView.newLine();
        Dealer dealer = participants.getDealer();
        outputView.printAllCardResult(dealer.getName(), dealer.getCards().cardsToString(), dealer.sumOfCards());

        for (final Player player : participants.getPlayersToList()) {
            outputView.printAllCardResult(player.getName(), player.getCards().cardsToString(),
                    player.sumOfCards());
        }
    }

    private Player makePlayer(final Dealer dealer, final Name name) {
        Card firstDrewCard = dealer.draw();
        Card seconddrewCard = dealer.draw();
        Cards cards = makeInitCards(firstDrewCard, seconddrewCard);
        return new Player(name, cards);
    }

    private Cards makeInitCards(final Card firstDrewCard, final Card seconddrewCard) {
        List<Card> cards = new ArrayList<>();
        cards.add(firstDrewCard);
        cards.add(seconddrewCard);
        return new Cards(cards);
    }

    private List<Name> readNames(final List<String> playerNames) {
        return playerNames.stream()
                .map(Name::new)
                .collect(Collectors.toList());
    }

    private List<List<String>> copiedBoxedCards(final Players players) {
        return players.cardsToString();
    }

    private void printWinningResult(final Participants participants) {
        outputView.printWinningResult(participants.getWinningResult(), participants.getPlayerNames());
    }
}
