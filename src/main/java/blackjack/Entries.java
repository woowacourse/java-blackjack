package blackjack;

import java.util.List;
import java.util.stream.Collectors;

public class Entries {
    private static final String ERROR_NO_ENTRY = "[ERROR] 더 이상 Entry가 없습니다.";

    private final List<Entry> entries;
    private int currentIndex = 0;

    private Entries(List<Entry> entries) {
        this.entries = List.copyOf(entries);
    }

    public static Entries from(List<String> names) {
        List<Entry> entries = names.stream()
                .map(Entry::new)
                .collect(Collectors.toList());
        return new Entries(entries);
    }

    public void toNextEntry() {
        if (hasNoNext()) {
            throw new RuntimeException(ERROR_NO_ENTRY);
        }
        this.currentIndex++;
    }

    private boolean hasNoNext() {
        return entries.size() <= currentIndex + 1;
    }

    public String getCurrentEntryName() {
        return entries.get(currentIndex).getName();
    }
}
