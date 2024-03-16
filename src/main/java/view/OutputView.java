package view;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.card.Cards;
import model.player.Name;
import view.dto.ParticipantNamesDto;
import view.dto.UserCardDto;
import view.dto.UserProfitDto;
import view.dto.UserResultDto;

public class OutputView {

    private static final String DIVIDE_CARD_MESSAGE = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String RECEIVED_CARD_MESSAGE = "%s 카드 : %s";
    private static final String GAME_RESULT_MESSAGE = "%s : %s";
    private static final String PLAYER_CARD_SUM_MESSAGE = " - 결과: %d";
    private static final String DEALER_ADD_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_PROMPT_MESSAGE = "## 최종 승패";

    public void printPlayerNames(ParticipantNamesDto participantNamesDto) {
        System.out.println(System.lineSeparator() +
                DIVIDE_CARD_MESSAGE.formatted(String.join(", ",participantNamesDto.getNames())));
    }

    public void printPlayerCards(UserCardDto userCardDto) {
        Map<String, List<String>> dealerNameAndCards = userCardDto.getDealerCards();
        Map<String, List<String>> usersNameAndCards = userCardDto.getParticipantCards();
        for (Entry<String, List<String>> dealerNameAndCard : dealerNameAndCards.entrySet()) {
            String name = dealerNameAndCard.getKey();
            System.out.println(RECEIVED_CARD_MESSAGE.formatted(name, String.join(", ", dealerNameAndCard.getValue())));
        }
        for (Entry<String, List<String>> usersNameAndCard : usersNameAndCards.entrySet()) {
            String name = usersNameAndCard.getKey();
            System.out.println(RECEIVED_CARD_MESSAGE.formatted(name, String.join(", ", usersNameAndCard.getValue().get(0))));
        }
    }

    public void printPlayerCardMessage(Name name, Cards cards) {
        System.out.println(cardsToString(name, cards, cards.getCards().size()));
    }


    public void printBlackJackScores(List<UserResultDto> userResultDto) {
        userResultDto.forEach(this::printBlackJackScore);
    }

    private void printBlackJackScore(UserResultDto userResultDto) {
        System.out.print(RECEIVED_CARD_MESSAGE
                .formatted(userResultDto.getName(), String.join(", ", userResultDto.getCards())));
        System.out.println(PLAYER_CARD_SUM_MESSAGE.formatted(userResultDto.getScore()));
    }

    private String cardsToString(Name name, Cards userCards, int cardCountToPrint) {
        List<Card> cards = userCards.getCards();
        int cardCountNotToPrint = cards.size() - cardCountToPrint;
        String cardNames = String.join(", ", cards.stream()
                .skip(cardCountNotToPrint)
                .map(this::cardToString)
                .toList());
        return RECEIVED_CARD_MESSAGE.formatted(name.getValue(), cardNames);
    }

    private String cardToString(Card card) {
        String cardNumber = cardNumberToString(card.getNumber());
        String cardShape = cardShapeToString(card.getShape());
        return cardNumber + cardShape;
    }

    public void printDealerAddCard() {
        System.out.println(System.lineSeparator() + DEALER_ADD_CARD_MESSAGE);
    }

    public void printResults(List<UserProfitDto> userProfitDtos) {
        System.out.println(System.lineSeparator() + GAME_RESULT_PROMPT_MESSAGE);
        userProfitDtos
                .forEach(dto -> System.out.println(GAME_RESULT_MESSAGE.formatted(dto.getName(), dto.getProfit())));
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
}
