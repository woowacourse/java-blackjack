package view;

import java.util.function.Supplier;

@FunctionalInterface
public interface InputUntilValid<T> {
    T validate(Supplier<T> supplier);

    static <T> T validatePlayerAnswer(Supplier<T> supplier){
        {
            while(true){
                try{
                    return supplier.get();
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
