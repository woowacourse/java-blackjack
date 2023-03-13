package service;

import domain.bank.BettingMoney;
import domain.card.Card;
import domain.card.CardBox;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
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
        askPlayerMoreDrawCard(participants);
        drawDealerCardByMinimumCondition(participants.getDealer());
        calculateProfitMoneyOfPlayer(participants);

        printCardResult(participants);
        printGameResult(participants);
    }

    private void printGameResult(final Participants participants) {
        outputView.printDealerGameResult(participants.getDealerProfit());
        outputView.printPlayersGameResult(participants.getPlayerNames(), participants.getPlayerMonies());
    }

    private void calculateProfitMoneyOfPlayer(final Participants participants) {
        List<Player> players = participants.getPlayersToList();
        List<Double> profits = participants.getProfits();
        for (int index = 0; index < players.size(); index++) {
            players.get(index).multiplyInterestOfPlayer(profits.get(index));
        }
        participants.calculateDealerMoney();
    }

    private List<BettingMoney> askPlayersBettingMoney(final List<Name> names) {
        List<BettingMoney> bettingMonies = new ArrayList<>();
        for (Name name : names) {
            outputView.printPlayerNameForBetting(name.getName());
            bettingMonies.add(getBettingMoney());
        }
        return bettingMonies;
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
        List<BettingMoney> bettingMonies = askPlayersBettingMoney(names);
        Dealer dealer = makeDealer();
        Players players = initPlayers(dealer, names, bettingMonies);
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

    private Players initPlayers(final Dealer dealer, final List<Name> names, final List<BettingMoney> bettingMonies) {
        List<Player> players = new ArrayList<>();
        for (int index = 0; index < names.size(); index++) {
            Name name = names.get(index);
            BettingMoney bettingMoney = bettingMonies.get(index);
            players.add(makePlayer(dealer, name, bettingMoney));
        }
        Card drewInitLastCardOfDealer = dealer.draw();
        dealer.addCard(drewInitLastCardOfDealer);
        return new Players(players);
    }

    private void printInitGameMessage(final Participants participants) {
        outputView.printPlayerNames(participants.getPlayerNames());
        outputView.printCardsPerDealer(participants.getDealerFirstCard());
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
        outputView.printAllDealerCardResult(participants.getDealerCards(), participants.sumOfDealerCards());

        for (final Player player : participants.getPlayersToList()) {
            outputView.printAllCardResult(player.getName(), player.getCards().cardsToString(),
                    player.sumOfCards());
        }
    }

    private Player makePlayer(final Dealer dealer, final Name name, final BettingMoney bettingMoney) {
        Card firstDrewCard = dealer.draw();
        Card seconddrewCard = dealer.draw();
        Cards cards = makeInitCards(firstDrewCard, seconddrewCard);
        return new Player(name, cards, bettingMoney);
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
}
