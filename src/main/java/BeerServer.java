import model.Beer;
import model.BeerStocks;
import rx.Observable;

import java.util.List;

public class BeerServer {

    public static Observable<Beer> getData() {

        List<Beer> beerStock = BeerStocks.create();
        System.out.println("*** Getting beers from the main data source ***");

        // Create an observable passing subscriber (implements Observer)
        // provided by the client

        return Observable.create(subscriber -> {
            for (Beer beer : beerStock) {
                subscriber.onNext(beer);
                try {
                    Thread.sleep(500); // Emulating delay in getting data
                } catch (InterruptedException e) {
                    subscriber.onError(new Throwable("Error in getting beer info"));
                }
            }
            subscriber.onCompleted();
        });
    }
}
