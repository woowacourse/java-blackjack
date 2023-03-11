package service;

import domain.BettingMoney;
import domain.Card;
import domain.CardBox;
import domain.Cards;
import domain.Dealer;
import domain.Name;
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

        askPlayersBettingMoney(participants);

        printInitGameMessage(participants);

        askPlayerMoreDrawCard(participants);

        drawDealerCardByMinimumCondition(participants.getDealer());

        printCardResult(participants);
        printWinningResult(participants);
    }

    private void askPlayersBettingMoney(final Participants participants) {
        Dealer dealer = participants.getDealer();
        for (final Player player : participants.getPlayersToList()) {
            outputView.printPlayerNameForBetting(player.getName());
            BettingMoney bettingMoney = getBettingMoney();
        }
    }

    private BettingMoney getBettingMoney() {
        try {
            return new BettingMoney(inputView.getBettingMoney());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return getBettingMoney();
        }
    }

    private Participants makeParticipants() {
        List<Name> names = getNames();
        Dealer dealer = makeDealer();
        Players players = initPlayers(dealer, names);
        return new Participants(dealer, players);
    }

    private List<Name> getNames() {
        List<String> playerNames = inputView.getPlayer();
        return readNames(playerNames);
    }

    private Dealer makeDealer() {
        CardBox cardBox = new CardBox();
        List<Card> cards = new ArrayList<>();
        cards.add(cardBox.get());
        return new Dealer(cardBox, new Cards(cards));
    }

    private Players initPlayers(final Dealer dealer, final List<Name> names) {
        List<Player> players = new ArrayList<>();
        for (Name name : names) {
            players.add(makePlayer(dealer, name));
        }
        Card drewInitLastCardOfDealer = dealer.draw();
        dealer.addCard(drewInitLastCardOfDealer);
        return new Players(players);
    }

    private void printInitGameMessage(final Participants participants) {
        outputView.printPlayerNames(participants.getPlayerNames());
        outputView.printCardsPerDealer(participants.getDealerName(), participants.getDealerFirstCard());
        outputView.printCardsPerPlayer(participants.getPlayerNames(), participants.copiedPlayersCardsToList());
    }

    private void askPlayerMoreDrawCard(final Participants participants) {
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

    private void drawDealerCardByMinimumCondition(final Dealer dealer) {
        outputView.newLine();
        while (dealer.isSumUnderStandard()) {
            outputView.noticeDealerUnderStandard();
            dealer.addCard(dealer.draw());
        }
    }

    private void printCardResult(final Participants participants) {
        outputView.newLine();
        outputView.printAllCardResult(participants.getDealerName(), participants.getDealerCards(),
                participants.sumOfDealerCards());

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

    private void printWinningResult(final Participants participants) {
        outputView.printWinningResult(participants.getWinningResult(), participants.getPlayerNames());
    }
}
