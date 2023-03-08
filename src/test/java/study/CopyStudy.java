package study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CopyStudy {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("깃짱", "오잉"));
        names.add("이리내");

        List<String> unmodifiableNames = Collections.unmodifiableList(names);
        // unmodifiableNames.add("이리내"); // UnsupportedOperationException
        System.out.println(unmodifiableNames); // [깃짱, 오잉, 이리내]

        List<String> copyNames = List.copyOf(names);
        names.add("에코");
        System.out.println(unmodifiableNames); // [깃짱, 오잉, 이리내, 에코]
        System.out.println(copyNames); // [깃짱, 오잉, 이리내]

        // copyNames.add("푸우"); // UnsupportedOperationException
    }
}
