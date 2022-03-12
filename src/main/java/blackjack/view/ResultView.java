package blackjack.view;

import blackjack.Dealer;
import blackjack.dto.BlackJackGameDto;
import blackjack.dto.CardDto;
import blackjack.dto.PlayerDto;
import java.util.List;
import java.util.StringJoiner;

public class ResultView {
    private static final String DEALER_MESSAGE_FORMAT = "\n%s와 ";
    private static final String GAMERS_MESSAGE_FORMAT = "%s에게 2장의 카드를 나누었습니다.\n";
    private static final String PLAYER_CARDS_MESSAGE_FORMAT = "%s : %s%n";
    private static final String DEALER_RECEIVE_MESSAGE_FORMAT = "\n%s는 16이하라 %d장의 카드를 더 받았습니다.%n";

    public static final String DELIMITER_NAME = ", ";
    public static final String FORMAT_SCORE = "%s 카드: %s - 결과: %d%n";
    public static final String BUST_MESSAGE = "카드 합이 21을 넘어 순서가 종료 됩니다.";

    public void printStartingCardsInGame(BlackJackGameDto gameDto) {
        printDealer(gameDto.getDealer());
        printGamers(gameDto.getGamers());
        printPlayersCards(gameDto);
    }

    private void printDealer(PlayerDto dealerDto) {
        System.out.printf(DEALER_MESSAGE_FORMAT, dealerDto.getName());
    }

    private void printGamers(List<PlayerDto> gamerDtos) {
        StringJoiner joiner = new StringJoiner(", ");
        for (PlayerDto gamerDto : gamerDtos) {
            joiner.add(gamerDto.getName());
        }
        System.out.printf(GAMERS_MESSAGE_FORMAT, joiner);
    }

    private void printPlayersCards(BlackJackGameDto gameDto) {
        printDealerCards(gameDto.getDealer());
        for (PlayerDto gamerDto : gameDto.getGamers()) {
            printGamerCards(gamerDto);
        }
    }

    private void printDealerCards(PlayerDto dealerDto) {
        CardDto cardDto = dealerDto.getCardDto();
        System.out.printf(PLAYER_CARDS_MESSAGE_FORMAT, dealerDto.getName(), cardDto.getCard());
    }

    private void printGamerCards(PlayerDto gamerDto) {
        StringJoiner joiner = new StringJoiner(", ");
        for (CardDto cardDto : gamerDto.getCardsDto()) {
            joiner.add(cardDto.getCard());
        }
        System.out.printf(PLAYER_CARDS_MESSAGE_FORMAT, gamerDto.getName(), joiner);
    }

    public void printDeck(PlayerDto playerDto) {
        StringJoiner joiner = new StringJoiner(", ");
        for (CardDto cardDto : playerDto.getCardsDto()) {
            joiner.add(cardDto.getCard());
        }
        System.out.printf(PLAYER_CARDS_MESSAGE_FORMAT, playerDto.getName(), joiner);
    }

    public void printBust() {
        System.out.println(BUST_MESSAGE);
    }

    public void printDealerAddCardCount(BlackJackGameDto game) {
        int addCount = game.getDealerAddCardCount();
        if (addCount > 0) {
            System.out.printf(DEALER_RECEIVE_MESSAGE_FORMAT, game.getDealerName(), addCount);
        }
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
