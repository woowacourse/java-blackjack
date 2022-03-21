package view;

import domain.card.PlayingCard;
import domain.player.Player;
import domain.service.HitService;
import java.util.List;
import java.util.stream.Collectors;
import util.Console;
import util.NameMapper;

public class ConsoleHitServiceImpl implements HitService {
    private static final String HIT_CHARACTER = "y";
    private static final String CARD_NAME_JOIN_CHARACTER = ", ";
    private static final String COLON_FOR_NAME_AND_REVENUE = ": ";
    private static final String INFO_FOR_DEALER_ADD_CARD = "%n%s는 16이하라 한장의 카드를 더 받았습니다.%n";
    private static final String INPUT_NEED_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";

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
