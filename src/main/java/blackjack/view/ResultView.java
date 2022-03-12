package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.PlayerDto;
import java.util.List;
import java.util.StringJoiner;

public class ResultView {
    private static final String FORMAT_MESSAGE_DEALER = "\n%s와 ";
    private static final String FORMAT_MESSAGE_ENTRIES = "%s에게 2장의 카드를 나누었습니다.\n";
    private static final String FORMAT_PLAYER_NAME_AND_CARDS = "%s : %s%n";
    private static final String FORMAT_MESSAGE_BUST = "%s의 점수 합이 21을 넘어, 다음 참가자로 넘어갑니다.%n";
    private static final String FORMAT_MESSAGE_DEALER_HIT = "%n딜러는 16이하라 %d장의 카드를 더 받았습니다.%n";

    public static final String DELIMITER_NAME = ", ";
    public static final String FORMAT_SCORE = "%s 카드: %s - 결과: %d%n";

    public void printInitGameResult(PlayerDto dealerDto, List<PlayerDto> entriesDtos) {
        printDealer(dealerDto);
        printEntries(entriesDtos);
        printPlayersCards(dealerDto, entriesDtos);
    }

    private void printDealer(PlayerDto dealerDto) {
        System.out.printf(FORMAT_MESSAGE_DEALER, dealerDto.getName());
    }

    private void printEntries(List<PlayerDto> entriesDtos) {
        StringJoiner joiner = new StringJoiner(", ");
        for (PlayerDto entryDto : entriesDtos) {
            joiner.add(entryDto.getName());
        }
        System.out.printf(FORMAT_MESSAGE_ENTRIES, joiner);
    }

    private void printPlayersCards(PlayerDto dealerDto, List<PlayerDto> entriesDtos) {
        printDealerCards(dealerDto);
        for (PlayerDto entryDto : entriesDtos) {
            printEntriesCards(entryDto);
        }
    }

    private void printDealerCards(PlayerDto dealerDto) {
        CardDto cardDto = dealerDto.getCardDto();
        System.out.printf(FORMAT_PLAYER_NAME_AND_CARDS, dealerDto.getName(), cardDto.getCard());
    }

    private void printEntriesCards(PlayerDto playerDto) {
        StringJoiner joiner = new StringJoiner(", ");
        for (CardDto cardDto : playerDto.getCardsDto()) {
            joiner.add(cardDto.getCard());
        }
        System.out.printf(FORMAT_PLAYER_NAME_AND_CARDS, playerDto.getName(), joiner);
    }

    public void printDeck(PlayerDto playerDto) {
        StringJoiner joiner = new StringJoiner(", ");
        for (CardDto cardDto : playerDto.getCardsDto()) {
            joiner.add(cardDto.getCard());
        }
        System.out.printf(FORMAT_PLAYER_NAME_AND_CARDS, playerDto.getName(), joiner);
    }

    public void printBustMessage(PlayerDto playerDto) {
        System.out.printf(FORMAT_MESSAGE_BUST, playerDto.getName());
    }

    public void printDealerHitCount(int count) {
        System.out.printf(FORMAT_MESSAGE_DEALER_HIT, count);
    }

    public void printScores(List<String> names, List<List<String>> decks, List<Integer> scores) {
        for (int index = 0; index < names.size(); index++) {
            printScore(names.get(index), decks.get(index), scores.get(index));
        }
    }

    private void printScore(String name, List<String> deck, int score) {
        String joinedCards = String.join(DELIMITER_NAME, deck);
        System.out.printf(FORMAT_SCORE, name, joinedCards, score);
    }
}
