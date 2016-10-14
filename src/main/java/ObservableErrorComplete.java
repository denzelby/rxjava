/**
 * Created by yfain11 on 4/9/16.
 */

import model.Beer;
import model.BeerStocks;
import rx.Observable;

public class ObservableErrorComplete {

    public static void main(String[] args) {

        System.out.println("== Observable creation from an Iterable");
        Observable<Beer> observableBeer = Observable.from(BeerStocks.create());

        observableBeer.subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Streaming is over")
        );
    }
}
