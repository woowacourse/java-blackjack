package controller;

import domain.CardDeck;
import domain.CardDistributor;
import domain.Dealer;
import domain.Name;
import domain.Participant;
import domain.Player;
import domain.Players;
import domain.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import util.CardDeckMaker;
import util.CardStatusConverter;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final String MORE_CARD = "y";
    private static final String CARD_STOP = "n";
    private static final String DELIMITER = ",";
    private static final int LIMIT_REMOVED = -1;
    private static final String INVALID_MORE_CARD_ERROR_MESSAGE = "y 나 n 만을 입력해주세요.";
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public BlackJackController() {
    }

    public void run() {
        List<String> playerNames = requestPlayerName();
        CardDistributor cardDistributor = new CardDistributor(CardDeckMaker.generate());
        Players players = Players.of(playerNames, distributeCardsForPlayers(playerNames.size(), cardDistributor));
        Dealer dealer = new Dealer(cardDistributor.distributeInitialCard());
        printInitialDistribution(players, dealer);
        progress(players, cardDistributor, dealer);
        end(players, dealer);
    }

    private void progress(Players players, CardDistributor cardDistributor, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            requestPlayerMoreCard(cardDistributor, player);
        }
        int dealerMoreCardCount = 0;
        while (dealer.isMoreCardAble() && cardDistributor.isCardLeft()) {
            dealer.pick(cardDistributor.distribute());
            dealerMoreCardCount++;
        }
        outputView.printDealerMoreCard(dealer.getName().getValue(), dealerMoreCardCount);
    }

    private void end(Players players, Dealer dealer) {
        printFinalCard(dealer);
        players.getPlayers().forEach(this::printFinalCard);
        outputView.printFinalResult(dealer.getName().getValue(),new Result(dealer, players).getResult());
    }

    private void printFinalCard(Participant participant) {
        outputView.printCardAndScore(participant.getName().getValue(),
                CardStatusConverter.convertToCardStatus(participant.getCardDeck().getCards()), participant.getTotalScore());
    }

    private void printInitialDistribution(Players players, Dealer dealer) {
        outputView.printFirstCardDistribution(dealer.getName().getValue(), getPlayerNames(players));

        outputView.printCardStatus(dealer.getName().getValue(), CardStatusConverter.convertToCardStatus(List.of(dealer.showOneCard())));
        for (Participant player : players.getPlayers()) {
            outputView.printCardStatus(player.getName().getValue(), CardStatusConverter.convertToCardStatus(player.getCardDeck().getCards()));
        }
    }

    private List<String> getPlayerNames(Players players) {
        List<String> playerNames;
        playerNames = players.getPlayers()
                .stream()
                .map(Participant::getName)
                .map(Name::getValue)
                .collect(Collectors.toList());
        return playerNames;
    }

    private void requestPlayerMoreCard(CardDistributor cardDistributor, Player player) {
        boolean isCardRequested = true;

        while (player.isMoreCardAble() && isCardRequested) {
            String answer = inputView.askMoreCard(player.getName().getValue());
            validate(answer);
            isCardRequested = isNoStop(cardDistributor, player, answer);
        }
    }

    private void validate(String answer) {
        if (!(answer.equals(MORE_CARD) || answer.equals(CARD_STOP))) {
            throw new IllegalArgumentException(INVALID_MORE_CARD_ERROR_MESSAGE);
        }
    }

    private boolean isNoStop(CardDistributor cardDistributor, Player player, String answer) {
        if (answer.equals(MORE_CARD) && cardDistributor.isCardLeft()) {
            player.pick(cardDistributor.distribute());
        }
        outputView.printCardStatus(player.getName().getValue(), CardStatusConverter.convertToCardStatus(player.getCardDeck().getCards()));
        return answer.equals(MORE_CARD);
    }

    private List<String> requestPlayerName() {
        return Arrays.asList(inputView.requestPlayerName().split(DELIMITER, LIMIT_REMOVED));
    }

    private List<CardDeck> distributeCardsForPlayers(int count, CardDistributor cardDistributor) {
        List<CardDeck> cardDecks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cardDecks.add(cardDistributor.distributeInitialCard());
        }
        return cardDecks;
    }

}
