package schedulers;

import model.Beer;
import model.BeerStocks;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ParallelStreams {

    public static void main(String[] args) {

        Observable<Beer> observableBeers = Observable.from(BeerStocks.create());

        observableBeers
                .flatMap(beer -> Observable.just(beer)
                        .subscribeOn(Schedulers.computation())  // new thread for each observable
                        .map(ParallelStreams::matureBeer)
                )
                .subscribe(beer -> System.out.println("Subscriber got " +
                        beer.getName() + " on  " +
                        Thread.currentThread().getName())
                );

        // Just to keep the program running
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Beer matureBeer(Beer beer) {
        try {
            System.out.println("** Maturing " + beer.getName() +
                    " on " + Thread.currentThread().getName());

            Thread.sleep((int) (Math.random() * 500));
            return beer;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
