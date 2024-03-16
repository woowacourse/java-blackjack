package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.card.Denomination;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.Profit;
import blackjack.domain.participant.ProfitDetails;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String SPLIT_DELIMITER = ", ";
    private static final String RESULT_FORMAT = " - 결과: ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printPlayersInitialHand(Players players, Dealer dealer) {
        List<Player> resultPlayers = players.getPlayers();
        List<Name> playerNames = getPlayerNames(resultPlayers);
        System.out.println(String.format(LINE_SEPARATOR + "딜러와 %s에게 2장을 나누었습니다.", convertNameToString(playerNames)));
        List<Card> visibleCards = dealer.getVisibleCards();
        System.out.println("딜러: " + convertCardsToString(visibleCards));
        resultPlayers.forEach(player -> System.out.println(convertParticipantHandToString(player)));
        System.out.println();
    }

    private List<Name> getPlayerNames(List<Player> resultPlayers) {
        return resultPlayers.stream()
                .map(Player::getName)
                .toList();
    }

    private String convertNameToString(List<Name> playerNames) {
        return playerNames.stream()
                .map(Name::getName)
                .collect(Collectors.joining(SPLIT_DELIMITER));
    }

    private String convertCardsToString(List<Card> visibleCards) {
        return visibleCards.stream()
                .map(this::convertCardToString)
                .collect(Collectors.joining(SPLIT_DELIMITER));
    }

    private String convertCardToString(Card card) {
        return OutputDenomination.convertDenominationToString(card.getDenomination()) +
                OutputSuit.convertSuitToString(card.getSuit());
    }

    public String convertParticipantHandToString(Participant participant) {
        return participant.getName().getName() + "카드: " + convertCardsToString(participant.getCards());
    }

    public void printParticipantHand(Participant participant) {
        System.out.println(convertParticipantHandToString(participant));
    }

    public void printDealerDraw() {
        System.out.println(LINE_SEPARATOR + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printParticipantResult(Players players, Dealer dealer) {
        List<Player> resultPlayers = players.getPlayers();
        List<Card> cards = dealer.getCards();
        System.out.println(
                LINE_SEPARATOR + "딜러: " + convertCardsToString(cards) + RESULT_FORMAT + dealer.calculateHand()
                        .getValue());
        resultPlayers.forEach(player -> System.out.println(
                convertParticipantHandToString(player) + RESULT_FORMAT + player.calculateHand().getValue()));
    }

    public void printProfitDetails(ProfitDetails profitDetails) {
        Profit dealerProfit = profitDetails.calculateDealerProfit();
        Map<Name, Profit> playerProfit = profitDetails.getProfitDetails();

        System.out.println(LINE_SEPARATOR + "## 최종 수익");
        System.out.println(String.format("딜러: %d", dealerProfit.getValue()));
        playerProfit.forEach(
                (name, profit) -> System.out.println(String.format("%s:  %d", name.getName(), profit.getValue())));
    }

    private enum OutputSuit {
        HEART("하트"),
        CLOVER("클로버"),
        DIAMOND("다이아몬드"),
        SPADE("스페이드"),
        ;

        private final String name;

        OutputSuit(String name) {
            this.name = name;
        }

        private String getName() {
            return this.name;
        }

        private static String convertSuitToString(Suit suit) {
            return Arrays.stream(OutputSuit.values())
                    .filter(outputSuit -> outputSuit.name().equals(suit.name()))
                    .findAny()
                    .map(OutputSuit::getName)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 무늬입니다."));
        }
    }

    private enum OutputDenomination {
        ACE("A"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ;

        private final String value;

        OutputDenomination(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private static String convertDenominationToString(Denomination denomination) {
            return Arrays.stream(OutputDenomination.values())
                    .filter(outputDenomination -> outputDenomination.name().equals(denomination.name()))
                    .findAny()
                    .map(OutputDenomination::getValue)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 숫자입니다."));
        }
    }
}
