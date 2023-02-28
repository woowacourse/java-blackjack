import java.util.List;

public class Deck {
    private static final String KOREAN_REGEX = "[가-힣]+";

    public static final List<String> cards = List.of(
            "A스페이드", "2스페이드", "3스페이드", "4스페이드", "5스페이드", "6스페이드", "7스페이드", "8스페이드", "9스페이드", "10스페이드", "J스페이드", "Q스페이드", "K스페이드",
            "A하트", "2하트", "3하트", "4하트", "5하트", "6하트", "7하트", "8하트", "9하트", "10하트", "J하트", "Q하트", "K하트",
            "A클로버", "2클로버", "3클로버", "4클로버", "5클로버", "6클로버", "7클로버", "8클로버", "9클로버", "10클로버", "J클로버", "Q클로버", "K클로버",
            "A다이아몬드", "2다이아몬드", "3다이아몬드", "4다이아몬드", "5다이아몬드", "6다이아몬드", "7다이아몬드", "8다이아몬드", "9다이아몬드", "10다이아몬드", "J다이아몬드", "Q다이아몬드", "K다이아몬드");


    public static int extractCardNumber(String card) {
        return Integer.parseInt(card.replaceAll(KOREAN_REGEX, ""));
    }
}
