package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.Outcome;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.card.Cards;
import model.player.Participant;

public class OutputView {

    private static final String DIVIDE_CARD_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String RECEIVED_CARD_MESSAGE = "%s 카드 : %s";
    private static final String GAME_RESULT_MESSAGE = "%s : %s";
    private static final String PLAYER_CARD_SUM_MESSAGE = " - 결과: %d";
    private static final String DEALER_ADD_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_PROMPT_MESSAGE = "## 최종 승패";

    public void printPlayerNames(List<String> names) {
        System.out.println(System.lineSeparator() + DIVIDE_CARD_MESSAGE.formatted(String.join(", ", names)));
    }

    public void printPlayerCards(Map<String, Cards> usersNameAndCards, Map<String, Cards> dealerNameAndCards) {
        Entry<String, Cards> dealer = mapToDealerEntry(dealerNameAndCards);
        System.out.println(cardsToString(dealer.getKey(), dealer.getValue(), 1));
        for (Entry<String, Cards> entry : usersNameAndCards.entrySet()) {
            System.out.println(cardsToString(entry));
        }
    }

    private Entry<String, Cards> mapToDealerEntry(Map<String, Cards> dealerNameAndCards) {
        return dealerNameAndCards.entrySet().stream()
                .findFirst()
                .orElseThrow();
    }

    public void printPlayerCardMessage(String name, Cards cards) {
        System.out.println(cardsToString(name, cards, cards.getCards().size()));
    }

    public void printBlackJackScore(Map<String, Cards> usersNameAndCards, Map<String, Cards> dealerNameAndCards) {
        Entry<String, Cards> dealer = mapToDealerEntry(dealerNameAndCards);
        System.out.println(System.lineSeparator() +
                cardsToString(dealer) + PLAYER_CARD_SUM_MESSAGE.formatted(dealer.getValue().calculateScore()));
        for (Entry<String, Cards> participant : usersNameAndCards.entrySet()) {
            System.out.println(cardsToString(participant) + PLAYER_CARD_SUM_MESSAGE.formatted(
                    participant.getValue().calculateScore()));
        }
    }

    private String cardsToString(Entry<String, Cards> userNameAndCards) {
        int size = userNameAndCards.getValue().getCards().size();
        return cardsToString(userNameAndCards.getKey(), userNameAndCards.getValue(), size);
    }

    private String cardsToString(String name, Cards userCards, int cardCountToPrint) {
        List<Card> cards = userCards.getCards();
        int cardCountNotToPrint = cards.size() - cardCountToPrint;
        String cardNames = String.join(", ", cards.stream()
                .skip(cardCountNotToPrint)
                .map(this::cardToString)
                .toList());
        return RECEIVED_CARD_MESSAGE.formatted(name, cardNames);
    }

    private String cardToString(Card card) {
        String cardNumber = cardNumberToString(card.getCardNumber());
        String cardShape = cardShapeToString(card.getCardShape());
        return cardNumber + cardShape;
    }

    public void printDealerAddCard() {
        System.out.println(System.lineSeparator() + DEALER_ADD_CARD_MESSAGE);
    }

    public void printPlayersOutcome(Map<Outcome, Long> dealerOutcomes, Map<Participant, Outcome> results) {
        printDealerOutcome(dealerOutcomes);
        printParticipantOutcome(results);
    }

    private void printDealerOutcome(Map<Outcome, Long> dealerOutcomes) {
        System.out.println(System.lineSeparator() + GAME_RESULT_PROMPT_MESSAGE);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Outcome, Long> dealerOutcome : dealerOutcomes.entrySet()) {
            stringBuilder.append(dealerOutcome.getValue()).append(gameResultToString(dealerOutcome.getKey()));
        }
        System.out.println(GAME_RESULT_MESSAGE.formatted("딜러", stringBuilder.toString()));
    }

    private void printParticipantOutcome(Map<Participant, Outcome> results) {
        for (Map.Entry<Participant, Outcome> result : results.entrySet()) {
            System.out.println(
                    GAME_RESULT_MESSAGE.formatted(result.getKey().getName(), gameResultToString(result.getValue())));
        }
    }

    private String cardNumberToString(CardNumber cardNumber) {
        if (cardNumber == CardNumber.ACE) {
            return "A";
        }
        if (cardNumber == CardNumber.QUEEN) {
            return "Q";
        }
        if (cardNumber == CardNumber.KING) {
            return "K";
        }
        if (cardNumber == CardNumber.JACK) {
            return "J";
        }
        return String.valueOf(cardNumber.minimumNumber());
    }

    private String cardShapeToString(CardShape cardShape) {
        if (cardShape == CardShape.SPACE) {
            return "스페이드";
        }
        if (cardShape == CardShape.CLOVER) {
            return "클로버";
        }
        if (cardShape == CardShape.HEART) {
            return "하트";
        }
        return "다이아몬드";
    }

    private String gameResultToString(Outcome outcome) {
        if (outcome == Outcome.WIN) {
            return "승 ";
        }
        if (outcome == Outcome.LOSE) {
            return "패 ";
        }
        return "무 ";
    }

}
