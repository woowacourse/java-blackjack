package view;

import model.Outcome;
import model.card.Card;
import model.card.Cards;
import model.card.Denomination;
import model.card.Suit;
import model.player.Participant;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String DIVIDE_CARD_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String RECEIVED_CARD_MESSAGE = "%s 카드 : %s";
    private static final String GAME_RESULT_MESSAGE = "%s : %s";
    private static final String PLAYER_CARD_SUM_MESSAGE = " - 결과: %d";
    private static final String DEALER_ADD_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_PROMPT_MESSAGE = "## 최종 수익";
    private static final String GAME_RESULT_REVENUE_MESSAGE = "%s : %d";

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
        System.out.println(cardsToString(name, cards, cards.cards().size()));
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

    public void printParticipantsRevenue(Map<String, Integer> revenue) {
        System.out.println(System.lineSeparator() + GAME_RESULT_PROMPT_MESSAGE);
        for (Entry<String, Integer> participant : revenue.entrySet()) {
            System.out.println(GAME_RESULT_REVENUE_MESSAGE.formatted(participant.getKey(), participant.getValue()));
        }
    }

    private String cardsToString(Entry<String, Cards> userNameAndCards) {
        int size = userNameAndCards.getValue().cards().size();
        return cardsToString(userNameAndCards.getKey(), userNameAndCards.getValue(), size);
    }

    private String cardsToString(String name, Cards userCards, int cardCountToPrint) {
        List<Card> cards = userCards.cards();
        int cardCountNotToPrint = cards.size() - cardCountToPrint;
        String cardNames = String.join(", ", cards.stream()
                .skip(cardCountNotToPrint)
                .map(this::cardToString)
                .toList());
        return RECEIVED_CARD_MESSAGE.formatted(name, cardNames);
    }

    private String cardToString(Card card) {
        String denomination = denominationToString(card.getDenomination());
        String suit = suitToString(card.getSuit());
        return denomination + suit;
    }

    public void printDealerAddCard() {
        System.out.println(System.lineSeparator() + DEALER_ADD_CARD_MESSAGE);
    }

    private String denominationToString(Denomination denomination) {
        if (denomination == Denomination.ACE) {
            return "A";
        }
        if (denomination == Denomination.QUEEN) {
            return "Q";
        }
        if (denomination == Denomination.KING) {
            return "K";
        }
        if (denomination == Denomination.JACK) {
            return "J";
        }
        return String.valueOf(denomination.minimumNumber());
    }

    private String suitToString(Suit suit) {
        if (suit == Suit.SPACE) {
            return "스페이드";
        }
        if (suit == Suit.CLOVER) {
            return "클로버";
        }
        if (suit == Suit.HEART) {
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
