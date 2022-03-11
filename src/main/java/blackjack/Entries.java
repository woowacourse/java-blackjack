package blackjack;

import java.util.List;
import java.util.stream.Collectors;

public class Entries {
    private static final String ERROR_DUPLICATE_NAME = "[ERROR] 중복된 이름이 있습니다.";
    private static final String ERROR_NO_ENTRY = "[ERROR] 더 이상 Entry가 없습니다.";

    private final List<Entry> values;
    private int currentIndex = -1;

    private Entries(List<Entry> values) {
        this.values = List.copyOf(values);
    }

    public static Entries from(List<String> names) {
        checkDuplicate(names);
        List<Entry> entries = names.stream()
                .map(Entry::new)
                .collect(Collectors.toList());
        return new Entries(entries);
    }

    private static void checkDuplicate(List<String> names) {
        if (countDistinct(names) != names.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NAME);
        }
    }

    private static int countDistinct(List<String> names) {
        return (int) names.stream()
                .distinct()
                .count();
    }

    public void giveFirstCards(TrumpCardPack trumpCardPack) {
        for (Entry entry : values) {
            entry.addCard(trumpCardPack.draw());
            entry.addCard(trumpCardPack.draw());
        }
    }

    public void toNextEntry() {
        if (hasNoNext()) {
            throw new RuntimeException(ERROR_NO_ENTRY);
        }
        this.currentIndex++;
    }

    public boolean hasNoNext() {
        return values.size() <= currentIndex + 1;
    }

    public void hitCurrentEntry(TrumpCard card) {
        getCurrentEntry().hit(card);
    }

    public boolean isCurrentEntryBust() {
        return getCurrentEntry().isBust();
    }

    public Entry getCurrentEntry() {
        return this.values.get(currentIndex);
    }

    public String getCurrentEntryName() {
        return getCurrentEntry().getName();
    }

    public List<Entry> getValues() {
        return this.values;
    }
}
