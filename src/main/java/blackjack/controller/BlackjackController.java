package blackjack.controller;

import blackjack.domain.result.GameResult;
import blackjack.domain.result.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.PlayersFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = makeParticipants();
        Deck deck = new Deck();

        setInitCards(deck, participants);

        turnOfPlayers(participants.getPlayers(), deck);
        turnOfDealer(participants.getDealer(), deck);

        finishGame(participants);
    }

    private Participants makeParticipants() {
        List<String> playersName = inputView.receivePlayersName();
        Players players = PlayersFactory.from(playersName);

        return new Participants(new Dealer(), players);
    }

    private void setInitCards(final Deck deck, final Participants participants) {
        for (Participant participant : participants.getPlayers()) {
            distributeTwoCards(deck, participant);
        }
        distributeTwoCards(deck, participants.getDealer());

        printParticipantsInitCards(participants);
    }

    private void distributeTwoCards(final Deck deck, final Participant participant) {
        for (int i = 0; i < 2; i++) {
            Card drawnCard = deck.drawCard();
            participant.receiveCard(drawnCard);
        }
    }

    private void printParticipantsInitCards(final Participants participants) {
        outputView.printDistributeCardsMessage(getPlayerNames(participants.getPlayers()));

        Card card = participants.getDealer().getOneCardToShow();
        outputView.printDealerInitCards(card.toString());

        outputView.printPlayersInitCards(getPlayersCards(participants.getPlayers()));
    }

    private List<String> getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private void turnOfPlayers(final List<Player> players, final Deck deck) {
        for (Player player : players) {
            play(player, deck);
        }
    }

    private void play(final Player player, final Deck deck) {
        boolean proceed = true;

        while (proceed && !player.isBust()) {
            proceed = ask(player, deck);
        }
    }

    private boolean ask(final Player player, final Deck deck) {
        String answer = inputView.askReceiveMoreCard(player.getName());

        if (answer.equals("n")) {
            outputView.printCurrentCards(player.getName(), getCurrentCards(player.getCards()));
            return false;
        }

        giveOneMoreCard(player, deck);
        outputView.printCurrentCards(player.getName(), getCurrentCards(player.getCards()));
        return true;
    }

    private void turnOfDealer(final Dealer dealer, final Deck deck) {
        while (dealer.canDraw()) {
            giveOneMoreCard(dealer, deck);
            outputView.printDealerDrawOneMoreCard();
        }
    }

    private void giveOneMoreCard(final Participant participant, final Deck deck) {
        participant.receiveCard(deck.drawCard());
    }

    private void finishGame(final Participants participants) {
        List<Player> players = participants.getPlayers();
        List<Integer> scores = getPlayersScore(players);
        Dealer dealer = participants.getDealer();

        outputView.printDealerFinalCards(getCurrentCards(dealer.getCards()), dealer.calculateTotalScore());
        outputView.printPlayerFinalCards(getPlayersCards(players), scores);

        showResult(participants);
    }

    private void showResult(final Participants participants) {
        GameResult gameResult = new GameResult(participants);
        Map<Player, Result> playerResult = gameResult.decidePlayersResult();
        List<Integer> dealerResult = gameResult.getDealerResult();

        Map<String, String> playerResultWithName = getPlayerResult(playerResult);

        outputView.printGameResult(dealerResult, playerResultWithName);
    }

    private Map<String, List<String>> getPlayersCards(final List<Player> players) {
        Map<String, List<String>> playersCards = new HashMap<>();

        for (Player player : players) {
            playersCards.put(player.getName(), player.getCards().getCards().stream()
                    .map(Card::toString)
                    .collect(Collectors.toList()));
        }

        return playersCards;
    }

    private List<Integer> getPlayersScore(final List<Player> players) {
        return players.stream()
                .map(Player::calculateTotalScore)
                .collect(Collectors.toList());
    }

    private List<String> getCurrentCards(final Cards cards) {
        return cards.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.toList());
    }

    private Map<String, String> getPlayerResult(final Map<Player, Result> playerResult) {
        Map<String, String> playerResultWithName = new HashMap<>();

        for(Player player: playerResult.keySet()) {
            playerResultWithName.put(player.getName(), playerResult.get(player).getState());
        }
        return playerResultWithName;
    }
}
