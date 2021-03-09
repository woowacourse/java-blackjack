package blackjack.domain.participant;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CompeteResult {
    WIN("승"), DRAW("무"), DEFEAT("패");
    
    private static final String RESULT_DELIMITER = " ";
    
    private final String competeResult;
    
    CompeteResult(String competeResult) {
        this.competeResult = competeResult;
    }
    
    public static String getCompeteResultAgainstPlayers(Dealer dealer, List<Player> players) {
        final Map<CompeteResult, Long> competeResultLongEnumMap = competeAgainstPlayers(dealer, players);
        
        return competeResultLongEnumMap.entrySet()
                                       .stream()
                                       .map(entry -> entry.getValue() + entry.getKey().competeResult)
                                       .collect(Collectors.joining(RESULT_DELIMITER));
    }
    
    public static Map<CompeteResult, Long> competeAgainstPlayers(Dealer dealer, List<Player> players) {
        return players.stream()
                      .map(player -> competeAgainstPlayer(dealer, player))
                      .collect(Collectors.groupingBy(Function.identity(), () -> new EnumMap<>(CompeteResult.class),
                              Collectors.counting()));
    }
    
    private static CompeteResult competeAgainstPlayer(Dealer dealer, Player player) {
        if (isDealerWin(dealer, player)) {
            return CompeteResult.WIN;
        }
        
        if (isDealerDefeat(dealer, player)) {
            return CompeteResult.DEFEAT;
        }
        
        return CompeteResult.DRAW;
    }
    
    private static boolean isDealerWin(Dealer dealer, Player player) {
        return ((dealer.sumCardHand() > player.sumCardHand()) && !dealer.isBurst()) || player.isBurst();
    }
    
    private static boolean isDealerDefeat(Dealer dealer, Player player) {
        return (dealer.sumCardHand() < player.sumCardHand()) && !player.isBurst();
    }
    
    public static String getCompeteResultAgainstDealer(Dealer dealer, Player player) {
        return competeAgainstDealer(dealer, player).competeResult;
    }
    
    private static CompeteResult competeAgainstDealer(Dealer dealer, Player player) {
        CompeteResult result = competeAgainstPlayer(dealer, player);
        
        if (result == CompeteResult.WIN) {
            return CompeteResult.DEFEAT;
        }
        
        if (result == CompeteResult.DEFEAT) {
            return CompeteResult.WIN;
        }
        
        return CompeteResult.DRAW;
    }
}
