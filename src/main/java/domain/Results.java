package domain;

import java.util.List;

public class Results {
    private List<Result> results;

    public Results(List<Result> results) {
        this.results = results;
    }

    public void printAll(){
        for(Result result : results){
            System.out.println(result.toString());
        }
    }
}
