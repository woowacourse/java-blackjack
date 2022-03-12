package blackjack.view;

import blackjack.dto.BlackJackGameDto;
import blackjack.dto.CardDto;
import blackjack.dto.PlayerDto;
import java.util.List;
import java.util.StringJoiner;

public class ResultView {
    private static final String DEALER_MESSAGE_FORMAT = "\n%s와 ";
    private static final String GAMERS_MESSAGE_FORMAT = "%s에게 2장의 카드를 나누었습니다.\n";
    private static final String PLAYER_CARDS_MESSAGE_FORMAT = "%s : %s%n";
    private static final String DEALER_RECEIVE_MESSAGE_FORMAT = "\n%s는 16이하라 %d장의 카드를 더 받았습니다.\n";
    public static final String PLAYER_SCORE_MESSAGE_FORMAT = "%s 카드: %s - 결과: %d\n";

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
        CardDto cardDto = dealerDto.getCardDtoInDeck();
        String cardInfo = cardDto.getNumber() + cardDto.getSymbol();
        System.out.printf(PLAYER_CARDS_MESSAGE_FORMAT, dealerDto.getName(), cardInfo);
    }

    private void printGamerCards(PlayerDto gamerDto) {
        StringJoiner joiner = new StringJoiner(", ");
        for (CardDto cardDto : gamerDto.getCardsDto()) {
            String cardInfo = cardDto.getNumber() + cardDto.getSymbol();
            joiner.add(cardInfo);
        }
        System.out.printf(PLAYER_CARDS_MESSAGE_FORMAT, gamerDto.getName(), joiner);
    }

    public void printDeck(PlayerDto playerDto) {
        StringJoiner joiner = new StringJoiner(", ");
        for (CardDto cardDto : playerDto.getCardsDto()) {
            String cardInfo = cardDto.getNumber() + cardDto.getSymbol();
            joiner.add(cardInfo);
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

    public void printScoreResultOfGame(BlackJackGameDto gameDto) {
        printDealerScore(gameDto.getDealer());
        printGamerScores(gameDto.getGamers());
    }

    private void printDealerScore(PlayerDto dealer) {
        printScore(dealer);
    }

    private void printGamerScores(List<PlayerDto> gamers) {
        for (PlayerDto gamer : gamers) {
            printScore(gamer);
        }
    }

    public void printScore(PlayerDto player) {
        StringJoiner joiner = new StringJoiner(", ");
        for (CardDto cardDto : player.getCardsDto()) {
            String cardInfo = cardDto.getNumber() + cardDto.getSymbol();
            joiner.add(cardInfo);
        }
        System.out.printf(PLAYER_SCORE_MESSAGE_FORMAT, player.getName(), joiner, player.getTotalScore());
    }

    public void printWinningResultOfGame(BlackJackGameDto gameDto) {
    }
}
