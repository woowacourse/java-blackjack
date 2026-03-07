package blackjack;

import java.util.List;

public class BlackJack {

    private static final int INITIAL_CARD_COUNT = 2;

    private final domain.deck.CardDeck cardDeck;

    public BlackJack(domain.deck.CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void start() {
        domain.player.Dealer dealer = new domain.player.Dealer();
        domain.player.Gamblers gamblers = new domain.player.Gamblers(getPlayerNames());
        initialDeal(dealer, gamblers);
        printInitialDealInfo(dealer, gamblers);

        gamblersTurn(gamblers);
        dealerTurn(dealer);

        printFinalPlayerInfo(dealer, gamblers);
        printFinalResult(dealer, gamblers);
    }

    private List<String> getPlayerNames() {
        view.OutputView.printStartMessage();
        return parser.PlayNameParser.splitNames(view.InputView.readLine());
    }

    private void initialDeal(domain.player.Dealer dealer, domain.player.Gamblers gamblers) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealer.deal(cardDeck);
            gamblers.dealAll(cardDeck);
        }
    }

    private void printInitialDealInfo(domain.player.Dealer dealer, domain.player.Gamblers gamblers) {
        view.OutputView.printInitMessage(gamblers.getNames());
        view.OutputView.printDealerFirstCard(dealer.firstCard());

        gamblers.forEach(this::printPlayerCardInfo);
    }

    private void printPlayerCardInfo(domain.player.Player player) {
        view.OutputView.printPlayerCards(dto.PlayerCardInfo.from(player));
    }

    private void gamblersTurn(domain.player.Gamblers gamblers) {
        gamblers.forEach(this::gamblerTurn);
    }

    private void gamblerTurn(domain.player.Gambler gambler) {
        while (askHit(gambler.getName())) {
            gambler.deal(cardDeck);

            if (gambler.isBust()) {
                view.OutputView.printPlayerBust(gambler.getName());
                break;
            }
            view.OutputView.printPlayerCards(dto.PlayerCardInfo.from(gambler));
        }
    }

    private boolean askHit(String name) {
        view.OutputView.askHit(name);
        return parser.AnswerParser.parse(view.InputView.readLine());
    }

    private void dealerTurn(domain.player.Dealer dealer) {
        while (!dealer.canStand()) {
            view.OutputView.printDealerHit();
            dealer.deal(cardDeck);
        }
    }

    private void printFinalPlayerInfo(domain.player.Dealer dealer, domain.player.Gamblers gamblers) {
        printFinalPlayerCardInfo(dealer);
        gamblers.forEach(this::printFinalPlayerCardInfo);
    }

    private void printFinalPlayerCardInfo(domain.player.Player player) {
        view.OutputView.printFinalPlayer(dto.PlayerCardInfo.from(player));
    }

    private void printFinalResult(domain.player.Dealer dealer, domain.player.Gamblers gamblers) {
        view.OutputView.printFinalResultHeader();
        view.OutputView.printResult(gamblers.getResult(dealer.score()));
    }

}
