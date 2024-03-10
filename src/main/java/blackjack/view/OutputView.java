package blackjack.view;

import blackjack.model.blackjackgame.BlackJackGame;
import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import blackjack.model.participants.Dealer;
import blackjack.model.participants.Player;
import blackjack.model.results.DealerResult;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import blackjack.view.symbol.CardNumberSymbol;
import blackjack.view.symbol.CardShapeSymbol;
import blackjack.view.symbol.ResultSymbol;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";
    private static final String MULTIPLE_OUTPUTS_DELIMITER = ", ";

    public void printDistributedCardsInfo(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        System.out.println();
        System.out.printf(DEALER_NAME + "와 %s에게 2장을 나누었습니다.%n", getPlayersNames(players));

        System.out.print(getDealerCardFormat(DEALER_NAME, getDealerCard(dealer)));
        players.forEach(player ->
                System.out.print(getParticipantCardsFormat(player.getName(), player.getCards())));
        System.out.println();
    }

    public void printPlayerCardsInfo(BlackJackGame blackJackGame, int index) {
        Player player = blackJackGame.getPlayers().get(index);
        System.out.print(getParticipantCardsFormat(player.getName(), player.getCards()));
    }

    public void printDealerChange() {
        System.out.println();
        System.out.println(DEALER_NAME + "는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScore(BlackJackGame blackJackGame) {
        System.out.println();
        List<Player> players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        System.out.print(getParticipantScoreFormat(DEALER_NAME, dealer.getCards(), dealer.getCards().getScore()));
        players.forEach(player -> System.out.printf(
                getParticipantScoreFormat(player.getName(), player.getCards(),
                        player.getCards().getScore())));
    }

    public void printGameResults(PlayerResult playerResult, DealerResult dealerResult) {
        System.out.println();
        System.out.println("### 최종 승패");
        System.out.printf("%s: %s%n", DEALER_NAME, getDealerResultFormat(dealerResult));
        Map<Player, Result> gameResult = playerResult.getResult();
        printPlayerResultsFormat(gameResult);
    }

    public void printBustState() {
        System.out.println("카드 합계가 21을 초과하였습니다. 턴을 종료합니다.");
    }

    private String getDealerCardFormat(String name, Card card) {
        return String.format("%s: %s%n", name, convertCardText(card));
    }

    private String getParticipantCardsFormat(String name, Cards cards) {
        return String.format("%s: %s%n", name, getCardsText(cards));
    }

    private String getParticipantScoreFormat(String name, Cards cards, int score) {
        return String.format("%s: %s - 결과: %d%n", name, getCardsText(cards), score);
    }

    private void printPlayerResultsFormat(Map<Player, Result> gameResult) {
        gameResult.entrySet()
                .stream()
                .map(entry -> getPlayerResultFormat(entry.getKey().getName(),
                        ResultSymbol.convertToSymbol(entry.getValue())))
                .forEach(System.out::print);
    }

    private String getPlayerResultFormat(String name, String result) {
        return String.format("%s: %s%n", name, result);
    }

    private String getDealerResultFormat(DealerResult dealerResult) {
        return dealerResult.getDealerResult().entrySet()
                .stream()
                .map(entry -> String.format("%d%s", entry.getValue(), ResultSymbol.convertToSymbol(entry.getKey())))
                .collect(Collectors.joining(" "));
    }

    private String getPlayersNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(MULTIPLE_OUTPUTS_DELIMITER));
    }

    private Card getDealerCard(Dealer dealer) {
        return dealer.getCards()
                .getCards()
                .get(0);
    }

    private String getCardsText(Cards cards) {
        return cards.getCards()
                .stream()
                .map(this::convertCardText)
                .collect(Collectors.joining(MULTIPLE_OUTPUTS_DELIMITER));
    }

    private String convertCardText(Card card) {
        CardNumber cardNumber = card.getCardNumber();
        CardShape cardShape = card.getCardShape();
        String cardNumberSymbol = CardNumberSymbol.convertToSymbol(cardNumber);
        String cardShapeSymbol = CardShapeSymbol.convertToSymbol(cardShape);
        return cardNumberSymbol + cardShapeSymbol;
    }
}
