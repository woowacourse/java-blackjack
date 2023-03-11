package service;

import domain.bank.Bank;
import domain.bank.BettingMoney;
import domain.card.Card;
import domain.card.CardBox;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.bank.Money;
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
        Bank bank = makeBank(participants);

        printInitGameMessage(participants);
        askPlayerMoreDrawCard(participants);
        drawDealerCardByMinimumCondition(participants.getDealer());
        calculateProfitMoneyOfPlayer(bank, participants.getPlayersToList(), participants.getProfits());

        printCardResult(participants);
        printGameResult(bank, participants);
    }

    private void printGameResult(final Bank bank, final Participants participants) {
        outputView.printGameResult(participants.getPlayerNames(), bank.getCalculatedProfitMonies());
    }

    private void calculateProfitMoneyOfPlayer(final Bank bank, final List<Player> players, final List<Double> profits) {
        for (int index = 0; index < players.size(); index++) {
            bank.multiplyInterestOfPlayer(players.get(index), profits.get(index));
        }
    }

    private Bank makeBank(final Participants participants) {
        List<BettingMoney> bettingMonies = askPlayersBettingMoney(participants);
        List<Money> monies = bettingMonies.stream()
                .map(bettingMoney -> (Money) bettingMoney)
                .collect(Collectors.toList());
        return new Bank(participants.getPlayersToList(), monies);
    }

    private List<BettingMoney> askPlayersBettingMoney(final Participants participants) {
        List<BettingMoney> bettingMonies = new ArrayList<>();
        for (final Player player : participants.getPlayersToList()) {
            outputView.printPlayerNameForBetting(player.getName());
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
}
