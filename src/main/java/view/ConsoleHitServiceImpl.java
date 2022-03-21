package view;

import domain.card.PlayingCard;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Player;
import domain.player.Players;
import domain.service.HitService;
import java.util.List;
import java.util.stream.Collectors;
import util.Console;
import util.NameMapper;

public class ConsoleHitServiceImpl implements HitService {
    private static final String INFO_FOR_INITIAL_SPREAD = "%s와 %s에게 2장을 나누었습니다.%n";
    private static final String INFO_FOR_DEALER_ADD_CARD = "%s는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String COLON_FOR_NAME_AND_REVENUE = ": ";
    private static final String CARD_NAME_JOIN_CHARACTER = ", ";
    private static final String GAMBLER_NAME_DELIMITER = ", ";
    private static final String HIT_CHARACTER = "y";
    private static final String INPUT_NEED_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";

    @Override
    public void showInitCards(Players players) {
        showSpreadAnnouncement(players);
        showInitialOpenCards(players);
    }

    private void showSpreadAnnouncement(Players players) {
        System.out.println();
        Dealer dealer = players.getDealer();
        Gamblers gamblers = players.getGamblers();

        System.out.printf(INFO_FOR_INITIAL_SPREAD,
                dealer.getName(),
                String.join(GAMBLER_NAME_DELIMITER, joinGamblerNames(gamblers)));
    }

    private String joinGamblerNames(Gamblers gamblers) {
        return gamblers.getGamblers()
                .stream()
                .map(Gambler::getName)
                .collect(Collectors.joining(GAMBLER_NAME_DELIMITER));
    }

    private void showInitialOpenCards(Players players) {
        Dealer dealer = players.getDealer();
        Gamblers gamblers = players.getGamblers();

        printInitOpenCards(dealer);
        gamblers.getGamblers()
                .forEach(this::printInitOpenCards);
    }

    private void printInitOpenCards(Player player) {
        System.out.println(player.getName() + "카드: " + player.getOpenCards());
    }

    @Override
    public boolean isHit(String name) {
        System.out.printf(INPUT_NEED_MORE_CARD, name);
        return HIT_CHARACTER.equalsIgnoreCase(Console.nextLine().trim());
    }

    @Override
    public void showGamblerHitStatus(Player gambler) {
        System.out.println(
                gambler.getName() + COLON_FOR_NAME_AND_REVENUE + getJoinedCardNames(gambler.getHoldingCards()));
    }

    private String getJoinedCardNames(List<PlayingCard> cards) {
        return cards.stream()
                .map(NameMapper::getCardName)
                .collect(Collectors.joining(CARD_NAME_JOIN_CHARACTER));
    }

    @Override
    public void showDealerHitStatus(Player dealer) {
        System.out.printf(INFO_FOR_DEALER_ADD_CARD, dealer.getName());
    }

}
