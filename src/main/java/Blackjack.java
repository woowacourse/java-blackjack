import domain.bet.Bet;
import domain.bet.BetProfit;
import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import dto.BetProfitDto;
import dto.DealerCardDto;
import dto.DealerCardWithScoreDto;
import dto.PlayerCardDto;
import dto.PlayerCardWithScoreDto;
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
        Bet bet = new Bet(askPlayerBets(players.getAllPlayerNames()));

        initialize(players, dealer, deck);

        playGame(players, dealer, deck);
        BetProfit betProfit = bet.calculateProfit(players.decidePlayerResults(dealer));

        printGameResult(betProfit, players, dealer);
    }

    private void initialize(Players players, Dealer dealer, Deck deck) {
        players.getAllPlayerNames().forEach(name ->
                players.initializeCards(name, initCards(deck))
        );
        dealer.receiveInitialCards(initCards(deck));

        outputView.printPlayers(DealerCardDto.from(dealer.getHand()), getPlayerCardDtos(players));
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

    private Map<Name, Long> askPlayerBets(List<Name> playerNames) {
        Map<Name, Long> bettingLog = new LinkedHashMap<>();
        for (Name name : playerNames) {
            String input = inputView.askPlayerBet(name.name());
            bettingLog.put(name, InputParser.parseMoney(input));
        }
        return bettingLog;
    }

    private List<PlayerCardDto> getPlayerCardDtos(Players players) {
        return players.getAllPlayerNames().stream()
                .map(name -> PlayerCardDto.from(name, players.getPlayerCards(name)))
                .toList();
    }

    private void playGame(Players players, Dealer dealer, Deck deck) {
        for (Name name : players.getAllPlayerNames()) {
            playPlayerTurn(players, name, deck);
        }
        playDealerTurn(dealer, deck);
    }

    private void playPlayerTurn(Players players, Name name, Deck deck) {
        while (players.canDraw(name) && isPlayerWantHit(name)) {
            players.distributeCard(name, deck.drawCard());
            outputView.printPlayerCard(PlayerCardDto.from(name, players.getPlayerCards(name)));
        }
    }

    private boolean isPlayerWantHit(Name name) {
        String input = inputView.askPlayerHit(name.name());
        return InputParser.parseHitAnswer(input);
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.drawCard());
            outputView.printDealerHit();
        }
    }

    private void printGameResult(BetProfit betProfit, Players players, Dealer dealer) {
        outputView.printDealerCardWithScore(DealerCardWithScoreDto.from(dealer.getHand(), dealer.getScore()));

        for (Name name : players.getAllPlayerNames()) {
            outputView.printPlayerCardWithScore(
                    PlayerCardWithScoreDto.from(name, players.getPlayerCards(name), players.getPlayerScore(name))
            );
        }

        outputView.printProfit(BetProfitDto.from(betProfit));
    }
}
