import domain.bet.Bet;
import domain.bet.BetProfit;
import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import service.ShuffledCardGenerator;
import util.InputParser;
import view.InputView;
import view.OutputView;

public class Blackjack {

    private static final int INITIAL_CARD_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public Blackjack(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Deck deck = Deck.create(new ShuffledCardGenerator());
        Players players = new Players(parsePlayerNames());
        Dealer dealer = new Dealer();
        final Bet bet = askPlayerBet(players.getAllPlayersName());

        initialize(players, dealer, deck);

        playGame(players, dealer, deck);
        printResult(players, dealer);
        printProfit(bet, players, dealer);
    }

    private void initialize(Players players, Dealer dealer, Deck deck) {
        players.getAllPlayersName().forEach(name ->
                players.initializeCards(name, initCards(deck))
        );
        dealer.receiveInitialCards(initCards(deck));

        outputView.printPlayers(dealer.getHand(), getPlayerCards(players));
    }

    private List<Card> initCards(Deck deck) {
        List<Card> initialCards = new ArrayList<>();
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            initialCards.add(deck.drawCard());
        }
        return initialCards;
    }

    private List<String> parsePlayerNames() {
        String input = inputView.askPlayerNames();
        return InputParser.parseNames(input);
    }

    private Bet askPlayerBet(List<Name> playersName) {
        Bet bet = new Bet(playersName);
        for (Name name : playersName) {
            String input = inputView.askPlayerBet(name);
            bet.bettingMoney(name, InputParser.parseMoney(input));
        }
        return bet;
    }

    private Map<Name, List<Card>> getPlayerCards(Players players) {
        Map<Name, List<Card>> playerCards = new LinkedHashMap<>();
        for (Name name : players.getAllPlayersName()) {
            playerCards.put(name, players.getPlayerCards(name));
        }
        return playerCards;
    }

    private void playGame(Players players, Dealer dealer, Deck deck) {
        for (Name name : players.getAllPlayersName()) {
            playPlayerTurn(players, name, deck);
        }
        playDealerTurn(dealer, deck);
    }

    private void playPlayerTurn(Players players, Name name, Deck deck) {
        while (players.canDraw(name) && isPlayerWantHit(name)) {
            players.distributeCard(name, deck.drawCard());
            outputView.printPlayerCard(name, players.getPlayerCards(name));
        }
    }

    private boolean isPlayerWantHit(Name name) {
        String input = inputView.askPlayerHit(name);
        return InputParser.parseHitAnswer(input);
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.drawCard());
            outputView.printDealerHit();
        }
    }

    private void printResult(Players players, Dealer dealer) {
        outputView.printDealerCardWithScore(dealer.getHand(), dealer.getScore());

        for (Name name : players.getAllPlayersName()) {
            outputView.printPlayerCardWithScore(name, players.getPlayerCards(name), players.getPlayerScore(name));
        }
    }

    private void printProfit(Bet bet, Players players, Dealer dealer) {
        BetProfit betProfit = bet.calculateProfit(players.decidePlayerResults(dealer));
        outputView.printProfit(betProfit.getDealerBetProfit(), betProfit.getPlayerBetProfit());
    }
}
