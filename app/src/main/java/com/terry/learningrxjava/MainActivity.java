package com.terry.learningrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        staticFactory();

        basicOperators();

        combiningObservables();

        multicastRepalyingAndCaching();
    }

    private void staticFactory() {
//        Observable<Integer> just1 = Observable.just(1).delay(1000, TimeUnit.MILLISECONDS);
//        Observable<Integer> just2 = Observable.just(2).delay(200, TimeUnit.MILLISECONDS);
//        Observable<Integer> just3 = Observable.just(3).delay(300, TimeUnit.MILLISECONDS);
//        Observable<Integer> just4 = Observable.just(4).delay(400, TimeUnit.MILLISECONDS);
//        Observable<Integer> just5 = Observable.just(5).delay(500, TimeUnit.MILLISECONDS);
//        Observable<Integer> just6 = Observable.just(6).delay(600, TimeUnit.MILLISECONDS)
//                .flatMap(integer -> {
//                    if (false) return Observable.just(6);
//                    return Observable.error(new Exception());
//                });
//        Observable<Integer> just7 = Observable.just(7).delay(700, TimeUnit.MILLISECONDS);
//        Observable<Integer> just8 = Observable.just(8).delay(800, TimeUnit.MILLISECONDS);
//        Observable<Integer> just9 = Observable.just(9).delay(900, TimeUnit.MILLISECONDS);
//
//        List<Observable<Integer>> justIterable = new ArrayList<>();
//        justIterable.add(just1);
//        justIterable.add(just2);
//        justIterable.add(just3);
//        justIterable.add(just4);
//        justIterable.add(just5);
//        justIterable.add(just6);
//        justIterable.add(just7);
//        justIterable.add(just8);
//        justIterable.add(just9);

//        Observable<Long> interval1 = Observable.intervalRange(0, 10, 0, 100, TimeUnit.MILLISECONDS)
//                .flatMap(aLong -> {
//                    if (5 == aLong) return Observable.error(new Exception());
//                    return Observable.just(aLong);
//                });
//        Observable<Long> interval2 = Observable.intervalRange(0, 10, 0, 200, TimeUnit.MILLISECONDS);
//        Observable<Long> interval3 = Observable.intervalRange(10, 10, 0, 300, TimeUnit.MILLISECONDS);
//        Observable<Long> interval4 = Observable.interval(400, TimeUnit.MILLISECONDS);
//        Observable<Long> interval5 = Observable.interval(500, TimeUnit.MILLISECONDS);
//        Observable<Long> interval6 = Observable.interval(600, TimeUnit.MILLISECONDS);
//        Observable<Long> interval7 = Observable.interval(700, TimeUnit.MILLISECONDS);
//        Observable<Long> interval8 = Observable.interval(800, TimeUnit.MILLISECONDS);
//        Observable<Long> interval9 = Observable.interval(900, TimeUnit.MILLISECONDS);


//        Observable.combineLatestDelayError(Arrays.asList(interval1, interval2), (aLong) -> String.valueOf(aLong[0]) + "-" + String.valueOf(aLong[1]))
//                .forEach(s -> Log.d(TAG, "combineLatestDelayError: " + s));

//        Observable.concat(Arrays.asList(interval3, interval2))
//                .forEach(s -> Log.d(TAG, "concat: " + s));

//        Observable.concat(Observable.just(interval3, interval2))
//                .forEach(s -> Log.d(TAG, "concat: " + s));

//        Observable.concat(interval3, interval2)
//                .forEach(s -> Log.d(TAG, "concat: " + s));

//        //noinspection unchecked
//        Observable.concatArray(interval3, interval2)
//                .forEach(s -> Log.d(TAG, "concat: " + s));

//        Observable.concatArrayEager(interval3, interval2)
//                .forEach(s -> Log.d(TAG, "concat: " + s));

//        Observable.empty().subscribe(
//                o -> Log.d(TAG, "onNext"),
//                throwable -> Log.d(TAG, "onError"),
//                () -> Log.d(TAG, "onComplete"));

//        Observable.error(() -> new Exception()).subscribe(lifecycleObserver());

//        Observable.error(new Exception()).subscribe(lifecycleObserver());

//        Observable.fromArray(0, 1, 2).forEach(printConsumer());

//        Observable.fromCallable(() -> 1).forEach(printConsumer());

//        Observable.fromCallable(() -> 1).subscribe(printObserver());

//        FutureTask<String> future = new FutureTask<>(() -> {
//            log("Callable called on thread " + Thread.currentThread().getName());
//            Thread.sleep(5000);
//            return "hello";
//        });
//        Observable.fromFuture(future, Schedulers.io())
//                .doOnSubscribe(disposable -> future.run())
//                .subscribeOn(Schedulers.io())
//                .subscribe(printConsumer());

//        Callable<String> callable = () -> {
//            log("Callable called on thread " + Thread.currentThread().getName());
//            Thread.sleep(5000);
//            return "hello";
//        };
//        Future<String> submit = Executors.newFixedThreadPool(2).submit(callable);
//        Observable.fromFuture(submit, Schedulers.io()).subscribe(printConsumer());

//        Observable.fromFuture(Observable.just("hello").toFuture(), Schedulers.io()).subscribe(printConsumer());

//        Observable.fromIterable(Arrays.asList(0, 1)).forEach(printConsumer());

//        Observable.fromPublisher(Flowable.just(0)).subscribe(printConsumer());

//        Observable.generate(emitter -> {
//            emitter.onNext(1);
//            emitter.onComplete();
//        }).subscribe(printConsumer());

//        Observable.generate(() -> 2, (integer, emitter) -> {
//            emitter.onNext(integer);
//            emitter.onComplete();
//        }).subscribe(printConsumer());

//        Disposable disposable = Observable.generate(() -> 2, (integer, emitter) -> {
//            emitter.onNext(integer);
//            emitter.onComplete();
//        }, integer -> log("disposed value: " + String.valueOf(integer))).delay(3000, TimeUnit.MILLISECONDS).subscribe(printConsumer());

//        Disposable disposable = Observable.generate(() -> 2, (integer, emitter) -> {
//            emitter.onNext(integer);
//            emitter.onComplete();
//        }, integer -> log("disposed value: " + String.valueOf(integer))).delay(3000, TimeUnit.MILLISECONDS).subscribe(printConsumer());
//        Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(aLong -> disposable.dispose());

//        Disposable disposable = Observable.generate(() -> 2, (integer, emitter) -> {
//            emitter.onNext(integer);
//            emitter.onComplete();
//            return integer + 1;
//        }, integer -> log("disposed value: " + String.valueOf(integer))).delay(3000, TimeUnit.MILLISECONDS).subscribe(printConsumer());

//        Disposable disposable = Observable.generate(() -> 2, (integer, emitter) -> {
//            emitter.onNext(integer);
//            emitter.onComplete();
//            return integer + 1;
//        }, integer -> log("disposed value: " + String.valueOf(integer))).delay(3000, TimeUnit.MILLISECONDS).subscribe(printConsumer());
//        Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(aLong -> disposable.dispose());

//        Disposable disposable = Observable.timer(3000, TimeUnit.MILLISECONDS).flatMap(aLong ->
//                Observable.generate(() -> 2, (integer, emitter) -> {
//                    emitter.onNext(integer);
//                    emitter.onComplete();
//                    return integer + 1;
//                }, integer -> log("disposed value: " + String.valueOf(integer))))
//                .subscribe(printConsumer());

//        Disposable disposable = Observable.timer(3000, TimeUnit.MILLISECONDS).flatMap(aLong ->
//                Observable.generate(() -> 2, (integer, emitter) -> {
//                    emitter.onNext(integer);
//                    emitter.onComplete();
//                    return integer + 1;
//                }, integer -> log("disposed value: " + String.valueOf(integer))))
//                .subscribe(printConsumer());
//        Observable.timer(1000, TimeUnit.MILLISECONDS).subscribe(aLong -> disposable.dispose());

//        Observable.interval(0, 1000, TimeUnit.MILLISECONDS).takeUntil(aLong -> aLong >= 5).subscribe(printConsumer());

//        Observable.intervalRange(0, 5, 0, 1000, TimeUnit.MILLISECONDS).subscribe(printConsumer());

//        Observable.just(1).forEach(printConsumer());

//        Observable.just(1, 2).forEach(printConsumer());

//        Observable.never();

//        Observable.range(0, 5).forEach(printConsumer());

//        Observable.range(0, 5).forEach(printConsumer());

//        Observable.sequenceEqual(Observable.just(1).delay(2, TimeUnit.SECONDS), Observable.just(1)).subscribe(printConsumer());

//        Observable.switchOnNext(Observable.interval(0, 5000, TimeUnit.MILLISECONDS)
//                .map(aLong -> Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
//                        .map(aLong1 -> aLong + "-" + aLong1))).forEach(printConsumer());

//        Observable.switchOnNextDelayError(Observable.intervalRange(0, 3, 0, 5000, TimeUnit.MILLISECONDS)
//                .map(aLong -> {
//                    if (1 == aLong) return Observable.error(new Exception("switchOnNext error"));
//                    if (2 == aLong) return Observable.just("done");
//                    return Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
//                            .map(aLong1 -> aLong + "-" + aLong1);
//                })).subscribe(printConsumer(), errorConsumer());

//        Observable.using(() -> "resource",
//                url -> Observable.just("www.baidu.com")
//                        .delay(3000, TimeUnit.MILLISECONDS),
//                resource -> resource.toString())
//                .subscribeOn(Schedulers.io())
//                .subscribe(printConsumer(), errorConsumer());

//        Observable.wrap(Observable.just(0)).subscribe(printConsumer());

//        Observable<Long> zipObservable1 = Observable.intervalRange(0, 10, 0, 100, TimeUnit.MILLISECONDS);
//        Observable<Long> zipObservable2 = Observable.intervalRange(10, 10, 0, 100, TimeUnit.MILLISECONDS);

//        Observable.zip(zipObservable1, zipObservable2,
//                (aLong, aLong2) -> aLong + "-" + aLong2).forEach(printConsumer());

//        Observable.zipArray(objects -> objects[0] + "-" + objects[1], true, 128, zipObservable1, zipObservable2)
//                .forEach(printConsumer());

//        Observable.zipIterable(Arrays.asList(zipObservable1, zipObservable2), objects -> objects[0] + "-" + objects[1], true, 128)
//        .forEach(printConsumer());

//        Observable.just(0, 1, 2, 3, 4).all(integer -> integer < 5).subscribe(printConsumer());

//        Observable.just(0, 1, 2, 3, 4).any(integer -> integer < 1).subscribe(printConsumer());

//        log(Observable.just("0", "1",  "2").as(upstream -> upstream.blockingLast()));

        // 如其名，该方法是阻塞的
//        log(Observable.just("blockingFirst").blockingFirst());

//        log(Observable.timer(5000, TimeUnit.MILLISECONDS).blockingFirst().toString());

//        log(Observable.empty().blockingFirst("default value").toString());

//        Observable.just("ForEach1", "ForEach2").blockingForEach(printConsumer());

//        Iterable<String> blockingLast = Observable.just("Iterable1", "Iterable2").blockingIterable();
//        for (String s : blockingLast) {
//            log(s);
//        }

//        log(Observable.just("blockingLast").blockingLast());

//        log(Observable.empty().blockingLast("default value").toString());

//        Observable<Long> source = Observable.interval(1, TimeUnit.MICROSECONDS).take(1000);
//        Iterable<Long> iterable = source.blockingLatest();
//        for (Long i : iterable) {
//            log(i.toString());
//        }

//        Observable<Long> source = Observable.interval(10, TimeUnit.MILLISECONDS).take(5);
//        Iterable<Long> longs = source.blockingMostRecent(-1L);
//        for (Long i : longs) {
//            log(i.toString());
//        }

//        Observable<Long> source = Observable.interval(1, TimeUnit.MICROSECONDS).take(1000);
//        Iterable<Long> longs = source.blockingNext();
//        for (Long i : longs) {
//            log(i.toString());
//        }

//        log(Observable.just("blockingSingle1").blockingSingle());

//        try {
//            log(Observable.empty().blockingSingle().toString());
//        } catch (Exception e) {
//            log(e.getClass().getSimpleName());
//        }

//        try {
//            log(Observable.just("blockingSingle1", "blockingSingle2").blockingSingle());
//        } catch (Exception e) {
//            log(e.getClass().getSimpleName());
//        }

//        Observable.just("blockingSubscribe").blockingSubscribe(printConsumer());
//        Observable.timer(3000, TimeUnit.MILLISECONDS).blockingSubscribe(printConsumer());

//        try {
//            log(Observable.just("toFuture").delay(3000, TimeUnit.MILLISECONDS).toFuture().get());
//        } catch (InterruptedException e) {
//            log(e.getClass().getSimpleName());
//        } catch (ExecutionException e) {
//            log(e.getClass().getSimpleName());
//        }

//        Observable<Long> intervalRange = Observable.intervalRange(0, 10, 0, 100, TimeUnit.MILLISECONDS);
//        intervalRange.buffer(3).subscribe(printConsumer());
//        intervalRange.buffer(2, 3).subscribe(printConsumer());

//        Observable<Long> intervalRange = Observable.intervalRange(0, 10, 0, 100, TimeUnit.MILLISECONDS);
//        intervalRange.buffer(5, () -> new ArrayList<>()).subscribe(printConsumer());

//        Observable<Long> intervalRange = Observable.intervalRange(0, 10, 0, 100, TimeUnit.MILLISECONDS).map(aLong -> aLong % 3);
//        intervalRange.buffer(5, () -> new LinkedHashSet<>()).subscribe(printConsumer());

//        Observable<Long> intervalRange = Observable.intervalRange(0, 10, 0, 100, TimeUnit.MILLISECONDS).map(aLong -> aLong % 3);
//        intervalRange.buffer(5, 4, () -> new LinkedHashSet<>()).subscribe(printConsumer());
    }

    /**
     *
     */
    private void basicOperators() {
        suppressingOperators();
        transformingOperators();
        reducingOperators();
        collectionOperators();
        errorRecoveryOperators();
        actionOperators();
    }

    /**
     *
     */
    private void combiningObservables() {
        merging();
        concatenation();
        ambiguous();
        zipping();
        combineLatest();
        grouping();
    }

    private void merging() {
//        Observable<Integer> source1 = Observable.just(1);
//        Observable<Integer> source2 = Observable.just(2, 3);
//        Observable<Object> mergeError = Observable.error(new Exception("merge error"));
//
//        log("------分隔符------");
//
//        Observable.merge(source1, source2)
//                .forEach(printConsumer());
//
//        log("------分隔符------");
//
//        Observable.merge(Arrays.asList(source1, source2))
//                .forEach(printConsumer());
//
//        log("------分隔符------");
//
//        Observable.merge(Arrays.asList(source1, source2), Integer.MAX_VALUE)
//                .forEach(printConsumer());
//
//        log("------分隔符------");
//
//        Observable.merge(Observable.just(source1, source2))
//                .forEach(printConsumer());
//
//        log("------分隔符------");
//
//        Observable.merge(Observable.just(source1, source2), Integer.MAX_VALUE)
//                .forEach(printConsumer());
//
//        log("------分隔符------");
//
//        source1.mergeWith(source2)
//                .forEach(printConsumer());
//
//        log("------分隔符------");
//
//        Observable.mergeDelayError(mergeError, source1)
//                .subscribe(printConsumer(), errorConsumer());
//
//        log("------分隔符------");
//
//        //noinspection unchecked
//        Observable.mergeArray(source1, source2)
//                .forEach(printConsumer());
//
//        log("------分隔符------");
//
//        Observable.mergeArrayDelayError(mergeError, source2)
//                .subscribe(printConsumer(), errorConsumer());
//
//        log("------分隔符------");
//
//        Observable<String> sourceFlatmap = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")))
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")), true)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")), true, 1024)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")), true, 1024, 128)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(Observable::just, Observable::error, () -> Observable.just("onComplete"))
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(Observable::just, Observable::error, () -> Observable.just("onComplete"), 1024)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")), 1024)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")),
//                        (s, s2) -> s + "'s Child " + s2)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")),
//                        (s, s2) -> s + "'s Child " + s2, true)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")),
//                        (s, s2) -> s + "'s Child " + s2, true, 1024)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")),
//                        (s, s2) -> s + "'s Child " + s2, true, 1024, 128)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMap(s -> Observable.fromArray(s.split("")),
//                        (s, s2) -> s + "'s Child " + s2, 1024)
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMapIterable(s -> Arrays.asList(s.split("")))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMapIterable(s -> Arrays.asList(s.split("")),
//                        (s, s2) -> s + "-" + s2)
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMapMaybe(s -> Observable.fromArray(s.split("")).elementAt(1))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMapMaybe(s -> Observable.empty().firstElement())
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMapSingle(s -> Observable.fromArray(s.split("")).single("default"))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMapSingle(s -> Observable.empty().single("default"))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        sourceFlatmap
//                .flatMapCompletable(s -> Completable.complete())
//                .subscribe(successAction(), errorConsumer());
//
//        log("------分隔符------");
    }

    private void concatenation() {
//        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
//        Observable<String> source2 = Observable.just("Zeta", "Eta", "Theta");
//        Observable<Integer> source3 = Observable.just(1, 2, 0, 4, 5)
//                .map(integer -> 10 / integer);
//        Observable<Integer> source4 = Observable.just(6, 7, 8)
//                .map(integer -> 10 / integer);
//        log("------分隔符------");
//        Observable.concat(Arrays.asList(source1, source2)).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concat(Observable.just(source1, source2)).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concat(Observable.just(source1, source2), 128).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concat(source1, source2).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concat(source1, source2, source1).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concat(source1, source2, source1, source2).subscribe(printConsumer());
//        log("------分隔符------");
//        source1.concatWith(source2).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concatEager(Observable.just(source1, source2)).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concatEager(Observable.just(source1, source2), 128, 128).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concatEager(Arrays.asList(source1, source2)).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concatEager(Arrays.asList(source1, source2), 128, 128).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.concatDelayError(Arrays.ascList(source3, source4))
//                .subscribe(printConsumer(), errorConsumer());
//        log("------分隔符------");
//        Observable.concatDelayError(Observable.just(source3, source4))
//                .subscribe(printConsumer(), errorConsumer());
//        log("------分隔符------");
//        Observable.concatDelayError(Observable.just(source3, source4), 128, true)
//                .subscribe(printConsumer(), errorConsumer());
//        log("------分隔符------");
//        //noinspection unchecked
//        Observable.concatArray(source1, source2, source1, source2).subscribe(printConsumer());
//        log("------分隔符------");
//        //noinspection unchecked
//        Observable.concatArrayEager(source1, source2, source1, source2).subscribe(printConsumer());
//        log("------分隔符------");
//        //noinspection unchecked
//        Observable.concatArrayEager(128, 128, source1, source2, source1, source2).subscribe(printConsumer());
//        log("------分隔符------");
//        //noinspection unchecked
//        Observable.concatArrayDelayError(source3, source4, source3, source4)
//                .subscribe(printConsumer(), errorConsumer());
//        log("------分隔符------");
//        source1.concatMap(s -> Observable.fromArray(s.split(""))).subscribe(printConsumer());
//        log("------分隔符------");
//        source1.concatMap(s -> Observable.fromArray(s.split("")), 2).subscribe(printConsumer());
//        log("------分隔符------");
//        source1.concatMapEager(s -> Observable.fromArray(s.split(""))).subscribe(printConsumer());
//        log("------分隔符------");
//        source1.concatMapEager(s -> Observable.fromArray(s.split("")), 128, 2).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.just(source3, source4).concatMapDelayError(Functions.identity())
//                .subscribe(printConsumer(), errorConsumer());
//        log("------分隔符------");
//        Observable.just(source3, source4).concatMapDelayError(Functions.identity(), 2, true)
//                .subscribe(printConsumer(), errorConsumer());
//        log("------分隔符------");
//        Observable.just(source3, source4).concatMapEagerDelayError(Functions.identity(), true)
//                .subscribe(printConsumer(), errorConsumer());
//        log("------分隔符------");
//        Observable.just(source3, source4)
//                .concatMapEagerDelayError(Functions.identity(), 128, 2, true)
//                .subscribe(printConsumer(), errorConsumer());
//        log("------分隔符------");
//        source1.concatMapIterable(s -> Arrays.asList(s.split(""))).subscribe(printConsumer());
//        log("------分隔符------");
//        source1.concatMapIterable(s -> Arrays.asList(s.split("")), 2).subscribe(printConsumer());
//        log("------分隔符------");
//        source1.concatMapCompletable(s -> Completable.complete()).subscribe(successAction(), errorConsumer());
//        log("------分隔符------");
//        source1.concatMapCompletable(s -> Completable.complete(), 2).subscribe(successAction(), errorConsumer());
//        log("------分隔符------");
    }

    private void ambiguous() {
//        Observable<String> source1 = Observable.timer(500, TimeUnit.MILLISECONDS).map(aLong -> "source1");
//        Observable<String> source2 = Observable.timer(300, TimeUnit.MILLISECONDS).map(aLong -> "source2");
//
//        Observable.amb(Arrays.asList(source1, source2)).subscribe(printConsumer());
//
//        //noinspection unchecked
//        Observable.ambArray(source1, source2).subscribe(printConsumer());
//
//        source1.ambWith(source2).subscribe(printConsumer());
    }

    private void zipping() {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<Integer> source2 = Observable.range(1, 6);
//        Observable<Boolean> source3 = Observable.just(true, false, true, false, true);
//        log("------分隔符------");
//        Observable.zip(Arrays.asList(source1, source2), objects -> objects[0] + "-" + objects[1]).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.zip(Observable.just(source1, source2), objects -> objects[0] + "-" + objects[1]).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.zip(source1, source2, (s, i) -> s + "-" + i).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.zip(source1, source2, (s, i) -> s + "-" + i, true).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.zip(source1, source2, (s, i) -> s + "-" + i, true, 128).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.zip(source1, source2, source3, (s, i, b) -> s + "-" + i + "-" + b).subscribe(printConsumer());
//        log("------分隔符------");
//        Observable.zipIterable(Arrays.asList(source1, source2), objects -> objects[0] + "-" + objects[1], true, 128).subscribe(printConsumer());
//        log("------分隔符------");
//        //noinspection unchecked
//        Observable.zipArray(objects -> objects[0] + "-" + objects[1], true, 128, source1, source2).subscribe(printConsumer());
//        log("------分隔符------");
//        // 4, 5, 6, 7, 8, 9 的重载省略
//        source1.zipWith(Arrays.asList(1, 2, 3, 4, 5, 6), (s, i) -> s + "-" + i).subscribe(printConsumer());
//        log("------分隔符------");
//        source1.zipWith(source2, (s, i) -> s + "-" + i).subscribe(printConsumer());
//        log("------分隔符------");
//        source1.zipWith(source2, (s, i) -> s + "-" + i, true).subscribe(printConsumer());
//        log("------分隔符------");
//        source1.zipWith(source2, (s, i) -> s + "-" + i, true, 128).subscribe(printConsumer());
    }

    private void combineLatest() {
//        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS).take(10);
//        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS).take(5);
//        log("------分隔符------");
//        Observable.combineLatest(source1, source2,
//                (l1, l2) -> "SOURCE	1:	" + l1 + "		SOURCE	2:	" + l2)
//                .subscribe(printConsumer());
//        log("------分隔符------");
//        // 与上面不同，不会重复取值
//        source2.withLatestFrom(source1, (l1, l2) -> "SOURCE	2:	" + l1 + "		SOURCE	1:	" + l2)
//                .subscribe(printConsumer());
    }

    private void grouping() {
//        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
//        source.groupBy(String::length).flatMapSingle(Observable::toList).subscribe(printConsumer());
    }

    /**
     *
     */
    private void multicastRepalyingAndCaching() {
        understandingMulticasting();
        automaticConnection();
        replayingAndCaching();
        subjects();
    }

    private void understandingMulticasting() {
        // multicasting with operators
//        log("------分隔符------");
//        Observable<Integer> threeIntegers = Observable.range(1, 3);
//        threeIntegers.subscribe(i -> log("Observer	One:	" + i));
//        threeIntegers.subscribe(i -> log("Observer	Two:	" + i));
//        log("------分隔符------");
//        ConnectableObservable<Integer> hotThreeIntegers = Observable.range(1, 3).publish();
//        hotThreeIntegers.subscribe(i -> log("Observer	One:	" + i));
//        hotThreeIntegers.subscribe(i -> log("Observer	Two:	" + i));
//        hotThreeIntegers.connect();
//        log("------分隔符------");
//        Observable<Integer> threeRandoms1 = Observable.range(1, 3)
//                .map(i -> new Random().nextInt(100000));
//        threeRandoms1.subscribe(i -> log("Observer	1:	" + i));
//        threeRandoms1.subscribe(i -> log("Observer	2:	" + i));
//        log("------分隔符------");
//        ConnectableObservable<Integer> threeInts = Observable.range(1, 3).publish();
//        Observable<Integer> threeRandoms2 = threeInts.map(i -> new Random().nextInt(100000));
//        threeRandoms2.subscribe(i -> log("Observer	1:	" + i));
//        threeRandoms2.subscribe(i -> log("Observer	2:	" + i));
//        threeInts.connect();
//        log("------分隔符------");
//        ConnectableObservable<Integer> threeRandoms3 = Observable.range(1, 3)
//                .map(i -> new Random().nextInt(100000)).publish();
//        threeRandoms3.subscribe(i -> log("Observer	1:	" + i));
//        threeRandoms3.subscribe(i -> log("Observer	2:	" + i));
//        threeRandoms3.connect();
//        // when to multicast
//        log("------分隔符------");
//        ConnectableObservable<Integer> threeRandoms4 = Observable.range(1, 3)
//                .map(i -> new Random().nextInt(100000)).publish();
//        threeRandoms4.subscribe(i -> log("Observer	1:	" + i));
//        threeRandoms4.reduce(0, (total, next) -> total + next)
//                .subscribe(i -> log("Observer	2:	" + i));
//        threeRandoms4.connect();
    }

    private void automaticConnection() {
//        // autoConnect()
//        log("------分隔符------");
//        Observable<Integer> threeRandoms = Observable.range(1, 3)
//                .map(i -> new Random().nextInt(100000))
//                .publish().autoConnect(2);
//        threeRandoms.subscribe(i -> log("Observer	1:	" + i));
//        threeRandoms.reduce(0, (total, next) -> total + next)
//                .subscribe(i -> log("Observer	2:	" + i));
//        log("------分隔符------");
//        Observable<Integer> threeRandoms2 = Observable.range(1, 3)
//                .map(i -> new Random().nextInt(100000))
//                .publish().autoConnect(2);
//        threeRandoms2.subscribe(i -> log("Observer	1:	" + i));
//        threeRandoms2.reduce(0, (total, next) -> total + next)
//                .subscribe(i -> log("Observer	2:	" + i));
//        threeRandoms2.subscribe(i -> log("Observer	3:	" + i));
//        log("------分隔符------");
//        Observable<Integer> threeRandoms3 = Observable.range(1, 3)
//                .delay(300, TimeUnit.MILLISECONDS)
//                .map(i -> new Random().nextInt(100000))
//                .publish().autoConnect(2);
//        threeRandoms3.subscribe(i -> log("Observer	1:	" + i));
//        threeRandoms3.reduce(0, (total, next) -> total + next)
//                .subscribe(i -> log("Observer	2:	" + i));
//        threeRandoms3.subscribe(i -> log("Observer	3:	" + i));
//        log("------分隔符------");
//        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
//                .take(5).publish().autoConnect();
//        seconds.subscribe(i -> log("Observer	1:	" + i));
//        Observable.timer(3000, TimeUnit.MILLISECONDS).subscribe(aLong ->
//                seconds.subscribe(i -> log("Observer	2:	" + i)));
//        // refCount() and share()
//        log("------分隔符------");
//        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
//                .take(10).publish().refCount();
//        seconds.take(5).subscribe(l -> log("Observer	1:	" + l));
//        Observable.timer(3000, TimeUnit.MILLISECONDS).subscribe(aLong ->
//                seconds.take(2).subscribe(l -> log("Observer	2:	" + l)));
//        Observable.timer(6000, TimeUnit.MILLISECONDS).subscribe(aLong ->
//                seconds.take(2).subscribe(l -> log("Observer	3:	" + l)));
//        log("------分隔符------");
//        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
//                .take(10).share();
//        seconds.take(5).subscribe(l -> log("Observer	1:	" + l));
//        Observable.timer(3000, TimeUnit.MILLISECONDS).subscribe(aLong ->
//                seconds.take(2).subscribe(l -> log("Observer	2:	" + l)));
//        Observable.timer(6000, TimeUnit.MILLISECONDS).subscribe(aLong ->
//                seconds.take(2).subscribe(l -> log("Observer	3:	" + l)));
    }

    private void replayingAndCaching() {
//        log("------分隔符------");
//        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS)
//                .take(6).replay().autoConnect();
//        seconds.subscribe(l -> log("Observer	1:	" + l));
//        Observable.timer(3000, TimeUnit.MILLISECONDS).subscribe(aLong ->
//                seconds.subscribe(l -> log("Observer	2:	" + l)));
//        log("------分隔符------");
//        Observable<Long> seconds2 = Observable.interval(1, TimeUnit.SECONDS)
//                .take(6).replay(2).autoConnect();
//        seconds2.subscribe(l -> log("Observer	1:	" + l));
//        Observable.timer(3000, TimeUnit.MILLISECONDS).subscribe(aLong ->
//                seconds2.subscribe(l -> log("Observer	2:	" + l)));
//        log("------分隔符------");
//        Observable<String> source =
//                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                        .replay(1).autoConnect();
//        source.subscribe(l -> log("Observer	1:	" + l));
//        source.subscribe(l -> log("Observer	2:	" + l));
//        log("------分隔符------");
//        Observable<String> source2 =
//                Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                        .replay(1).refCount();
//        source2.subscribe(l -> log("Observer	1:	" + l));
//        source2.subscribe(l -> log("Observer	2:	" + l));
//        log("------分隔符------");
//        Observable<Long> seconds3 =
//                Observable.interval(300, TimeUnit.MILLISECONDS)
//                        .take(10).map(l -> (l + 1) * 300)
//                        .replay(1, TimeUnit.SECONDS)
//                        .autoConnect();
//        seconds3.subscribe(l -> log("Observer	1:	" + l));
//        Observable.timer(2000, TimeUnit.MILLISECONDS).subscribe(aLong ->
//                seconds3.subscribe(l -> log("Observer	2:	" + l)));
//        log("------分隔符------");
//        Observable<Long> seconds4 =
//                Observable.interval(300, TimeUnit.MILLISECONDS)
//                        .take(10).map(l -> (l + 1) * 300)
//                        .replay(1, 1, TimeUnit.SECONDS)
//                        .autoConnect();
//        seconds4.subscribe(l -> log("Observer	1:	" + l));
//        Observable.timer(2000, TimeUnit.MILLISECONDS).subscribe(aLong ->
//                seconds4.subscribe(l -> log("Observer	2:	" + l)));
//        log("------分隔符------");
//        Observable<Integer> cachedRollingTotals =
//                Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
//                        .scan(0, (total, next) -> total + next)
//                        .cache();
//        cachedRollingTotals.subscribe(printConsumer());
//        log("------分隔符------");
//        Observable<Integer> cachedRollingTotals2 =
//                Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
//                        .scan(0, (total, next) -> total + next)
//                        .cacheWithInitialCapacity(9);
//        cachedRollingTotals2.subscribe(printConsumer());
    }

    private void subjects() {
        publishSubjects();
        whenToUseSubjects();
        whenSubjectsGoWrong();
        serializingSubjects();
        behaviorSubject();
        replaySubject();
        asyncSubject();
        unicastSubject();
    }

    private void publishSubjects() {
//        log("------分隔符------");
//        Subject<String> subject = PublishSubject.create();
//        subject.map(String::length).subscribe(printConsumer());
//        subject.onNext("Alpha");
//        subject.onNext("Beta");
//        subject.onNext("Gamma");
//        subject.onComplete();
    }

    private void whenToUseSubjects() {
//        log("------分隔符------");
//        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
//                .take(5).map(l -> (l + 1) + "	seconds");
//        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
//                .map(l -> ((l + 1) * 300) + "	milliseconds");
//        Subject<String> subject = PublishSubject.create();
//        subject.subscribe(printConsumer());
//        source1.subscribe(subject);
//        source2.subscribe(subject);
    }

    private void whenSubjectsGoWrong() {
//        log("------分隔符------");
//        Subject<String> subject = PublishSubject.create();
//        subject.onNext("Alpha");
//        subject.onNext("Beta");
//        subject.onNext("Gamma");
//        subject.onComplete();
//        subject.map(String::length).subscribe(printConsumer());
    }

    private void serializingSubjects() {
//        log("------the thread safe Subject------");
//        Subject<String> subject = PublishSubject.<String>create().toSerialized();
    }

    private void behaviorSubject() {
//        log("------last event is onNext------");
//        Subject<String> subject = BehaviorSubject.create();
//        subject.subscribe(s -> log("Observer	1:	" + s), errorConsumer(), completeAction());
//        subject.onNext("Alpha");
//        subject.onNext("Beta");
//        subject.onNext("Gamma");
//        subject.subscribe(s -> log("Observer	2:	" + s), errorConsumer(), completeAction());
//        log("------last event is onComplete------");
//        Subject<String> subject2 = BehaviorSubject.create();
//        subject2.subscribe(s -> log("Observer	1:	" + s), errorConsumer(), completeAction());
//        subject2.onNext("Alpha");
//        subject2.onNext("Beta");
//        subject2.onNext("Gamma");
//        subject2.onComplete();
//        subject2.subscribe(s -> log("Observer	2:	" + s), errorConsumer(), completeAction());
    }

    private void replaySubject() {
//        log("------exclude onComplete------");
//        Subject<String> subject = ReplaySubject.create();
//        subject.subscribe(s -> log("Observer	1:	" + s), errorConsumer(), completeAction());
//        subject.onNext("Alpha");
//        subject.onNext("Beta");
//        subject.onNext("Gamma");
//        subject.subscribe(s -> log("Observer	2:	" + s), errorConsumer(), completeAction());
//        log("------include onComplete------");
//        Subject<String> subject2 = ReplaySubject.create();
//        subject2.subscribe(s -> log("Observer	1:	" + s), errorConsumer(), completeAction());
//        subject2.onNext("Alpha");
//        subject2.onNext("Beta");
//        subject2.onNext("Gamma");
//        subject2.onComplete();
//        subject2.subscribe(s -> log("Observer	2:	" + s), errorConsumer(), completeAction());
    }

    private void asyncSubject() {
//        log("------wrong------");
//        Subject<String> subject = AsyncSubject.create();
//        subject.subscribe(s -> log("Observer	1:	" + s), errorConsumer());
//        subject.onNext("Alpha");
//        subject.onNext("Beta");
//        subject.onNext("Gamma");
//        subject.subscribe(s -> log("Observer	2:	" + s), errorConsumer());
//        log("------correct out of onComplete invoked------");
//        Subject<String> subject2 = AsyncSubject.create();
//        subject2.subscribe(s -> log("Observer	1:	" + s), errorConsumer());
//        subject2.onNext("Alpha");
//        subject2.onNext("Beta");
//        subject2.onNext("Gamma");
//        subject2.onComplete();
//        subject2.subscribe(s -> log("Observer	2:	" + s), errorConsumer());
    }

    private void unicastSubject() {
//        log("------only work with one Observer------");
//        Subject<String> subject = UnicastSubject.create();
//        Observable.interval(300, TimeUnit.MILLISECONDS)
//                .take(15)
//                .map(l -> ((l + 1) * 300) + "	milliseconds")
//                .subscribe(subject);
//        Observable.timer(2000, TimeUnit.MILLISECONDS).subscribe(
//                aLong -> subject.subscribe(s -> log("Observer	1:	" + s)));
//        log("------work with multiple Observer and ignore missed emissions------");
//        Subject<String> subject2 = UnicastSubject.create();
//        Observable.interval(300, TimeUnit.MILLISECONDS)
//                .take(17)
//                .map(l -> ((l + 1) * 300) + "	milliseconds")
//                .subscribe(subject2);
//        Observable.timer(2000, TimeUnit.MILLISECONDS).subscribe(aLong -> {
//            Observable<String> multicast = subject2.publish().autoConnect();
//            multicast.subscribe(s -> log("Observer	1:	" + s));
//            Observable.timer(2000, TimeUnit.MILLISECONDS).subscribe(
//                    aLong1 -> multicast.subscribe(s -> log("Observer	2:	" + s)));
//        });
    }

    /**
     *
     */
    private void concurrencyAndParallelization() {

    }

    /**
     *
     */
    private void switchingThrottlingWindowingAndBuffering() {

    }

    /**
     *
     */
    private void flowablesAndBackpressure() {

    }

    /**
     *
     */
    private void transformersAndCustomOperators() {

    }

    /**
     *
     */
    private void testingAndDebuging() {

    }

    private void suppressingOperators() {
//        Observable.interval(300, TimeUnit.MILLISECONDS)
//                .take(3).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.interval(300, TimeUnit.MILLISECONDS)
//                .take(2, TimeUnit.SECONDS).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.intervalRange(0, 10, 0, 300, TimeUnit.MILLISECONDS)
//                .takeLast(3).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.intervalRange(0, 10, 0, 300, TimeUnit.MILLISECONDS)
//                .takeLast(2, TimeUnit.SECONDS).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.intervalRange(0, 10, 0, 300, TimeUnit.MILLISECONDS)
//                .takeLast(5, 2, TimeUnit.SECONDS).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.interval(300, TimeUnit.MILLISECONDS)
//                .takeUntil(aLong -> aLong >= 5)
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.interval(300, TimeUnit.MILLISECONDS)
//                .takeUntil(Observable.timer(1000, TimeUnit.MILLISECONDS))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.intervalRange(0, 10, 0, 300, TimeUnit.MILLISECONDS)
//                .takeWhile(aLong -> aLong <= 5)
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.range(0, 10)
//                .skip(5).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.intervalRange(0, 10, 0, 300, TimeUnit.MILLISECONDS)
//                .skip(1000, TimeUnit.MILLISECONDS).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.range(0, 10)
//                .skipLast(5).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.intervalRange(0, 10, 1000, 1000, TimeUnit.MILLISECONDS)
//                .skipLast(3000, TimeUnit.MILLISECONDS).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.intervalRange(0, 10, 300, 300, TimeUnit.MILLISECONDS)
//                .skipUntil(Observable.timer(2000, TimeUnit.MILLISECONDS)).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.range(0, 10)
//                .skipWhile(aLong -> aLong < 5).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(0, 1, 2).repeat(2)
//                .distinct().subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(0, 1, 2).repeat(2)
//                .distinct(integer -> integer).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(0, 1, 2).repeat(2)
//                .distinct(integer -> integer, () -> new HashSet<>()).subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(0, 1, 1, 2).repeat(2)
//                .distinctUntilChanged()
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(0, 1, 1, 2).repeat(2)
//                .distinctUntilChanged(integer -> integer)
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(0, 1, 1, 2).repeat(2)
//                .distinctUntilChanged((integer, integer2) -> integer.equals(integer2))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Single<Integer> first1 = Observable.range(0, 10).first(-1);
//        first1.subscribe(printConsumer());

//        Single<Integer> first2 = Observable.<Integer>empty().first(-1);
//        first2.subscribe(printConsumer());

//        Single<Integer> first3 = Observable.range(0, 10).firstOrError();
//        first3.subscribe(printConsumer(), errorConsumer());

//        Single<Integer> first4 = Observable.<Integer>empty().firstOrError();
//        first4.subscribe(printConsumer(), errorConsumer());

//        Maybe<Integer> first5 = Observable.range(0, 10).firstElement();
//        first5.subscribe(printConsumer(), errorConsumer(), completeAction());

//        Maybe<Integer> first6 = Observable.<Integer>empty().firstElement();
//        first6.subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(0, 1, 2, 3)
//                .elementAt(1)
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(0, 1, 2, 3)
//                .elementAt(4)
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(0, 1, 2, 3)
//                .elementAt(1, -1)
//                .subscribe(printConsumer(), errorConsumer());

//        Observable.just(0, 1, 2, 3)
//                .elementAt(4, -1)
//                .subscribe(printConsumer(), errorConsumer());

//        Observable.just(0, 1, 2, 3)
//                .flatMap(integer -> {
//                    if (integer > 2) return Observable.error(new Exception("error"));
//                    return Observable.just(integer);
//                })
//                .elementAt(3, -1)
//                .subscribe(printConsumer(), errorConsumer());

//        Observable.just(0, 1, 2, 3)
//                .elementAtOrError(1)
//                .subscribe(printConsumer(), errorConsumer());

//        Observable.just(0, 1, 2, 3)
//                .elementAtOrError(4)
//                .subscribe(printConsumer(), errorConsumer());

//        Single<Integer> last1 = Observable.range(0, 10).last(-1);
//        last1.subscribe(printConsumer());

//        Single<Integer> last2 = Observable.<Integer>empty().last(-1);
//        last2.subscribe(printConsumer());

//        Single<Integer> last3 = Observable.range(0, 10).lastOrError();
//        last3.subscribe(printConsumer(), errorConsumer());

//        Single<Integer> last4 = Observable.<Integer>empty().lastOrError();
//        last4.subscribe(printConsumer(), errorConsumer());

//        Maybe<Integer> last5 = Observable.range(0, 10).lastElement();
//        last5.subscribe(printConsumer(), errorConsumer(), completeAction());

//        Maybe<Integer> last6 = Observable.<Integer>empty().lastElement();
//        last6.subscribe(printConsumer(), errorConsumer(), completeAction());
    }

    private void transformingOperators() {
//        Observable.just("a", "ab", "abc", "abcd", "abcde")
//                .map(String::length).subscribe(printConsumer());

//        Observable.just("a", "ab", "abc", "abcd", "abcde")
//                .cast(Object.class).subscribe(printConsumer());

//        Observable.just("a", "ab", "abc", "abcd", "abcde")
//                .startWith("suffix a").subscribe(printConsumer());

//        Observable.just("a", "ab", "abc", "abcd", "abcde")
//                .startWith(Arrays.asList("suffix a")).subscribe(printConsumer());

//        Observable.just("a", "ab", "abc", "abcd", "abcde")
//                .startWith(Observable.just("suffix a")).subscribe(printConsumer());

//        Observable.just("a", "ab", "abc", "abcd", "abcde")
//                .startWithArray("suffix a", "suffix b").subscribe(printConsumer());

//        Observable.empty()
//                .defaultIfEmpty("default value")
//                .subscribe(printConsumer());

//        Observable.empty()
//                .switchIfEmpty(Observable.just("default value"))
//                .subscribe(printConsumer());

//        Observable.just(6,	2,	5,	7,	1,	4,	9,	8,	3)
//                .sorted()
//                .subscribe(printConsumer());

//        Observable.just(6,	2,	5,	7,	1,	4,	9,	8,	3)
//                .sorted((o1, o2) -> o1 - o2)
//                .subscribe(printConsumer());

//        Observable.just(0, 1, 2)
//                .delay(integer -> Observable.timer(integer * 3000, TimeUnit.MILLISECONDS))
//                .subscribe(printConsumer());

//        Observable.just(1)
//                .delay(3000, TimeUnit.MILLISECONDS)
//                .subscribe(printConsumer());

//        Observable.just(1)
//                .delay(Observable.timer(3000, TimeUnit.MILLISECONDS), integer -> Observable.timer(integer * 3000, TimeUnit.MILLISECONDS))
//                .subscribe(printObserver());

//        Observable.just(1)
//                .delay(3000, TimeUnit.MILLISECONDS)
//                .subscribe(printObserver());

//        Observable.just(2)
//                .delaySubscription(Observable.timer(3000, TimeUnit.MILLISECONDS))
//                .subscribe(printObserver());

//        Observable.just(1)
//                .delaySubscription(3000, TimeUnit.MILLISECONDS)
//                .subscribe(printObserver());

//        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                .repeat(2).subscribe(printConsumer());

//        final int[] index = {0};
//        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                .repeatUntil(() -> ++index[0] >= 2).subscribe(printConsumer());

//        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                .repeatUntil(new BooleanSupplier() {
//                    int index = 0;
//                    @Override
//                    public boolean getAsBoolean() throws Exception {
//                        return ++index >= 2;
//                    }
//                }).subscribe(printConsumer());

//        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                .repeatWhen(objectObservable -> Observable.range(0, 3)).subscribe(printConsumer());

//        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                .repeatWhen(objectObservable -> Observable.range(1, 3)
//                        .delay(integer -> Observable.timer(integer * 5000, TimeUnit.MILLISECONDS)))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                .repeatWhen(objectObservable -> Observable.range(1, 3)
//                        .flatMap(integer -> Observable.timer(integer * 5000, TimeUnit.MILLISECONDS)))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.range(0, 10)
//                .scan((integer, integer2) -> integer + integer2)
//                .subscribe(printConsumer());

//        Observable.range(0, 10)
//                .scan(5, (integer, integer2) -> integer + integer2)
//                .subscribe(printConsumer());
    }

    private void reducingOperators() {
//        Observable.range(0, 10)
//                .count().subscribe(printConsumer());

//        Observable.range(0, 10)
//                .reduce((integer, integer2) -> integer + integer2)
//                .subscribe(printConsumer());

//        Observable.range(0, 10)
//                .reduce(5, (integer, integer2) -> integer + integer2)
//                .subscribe(printConsumer());

//        Observable.range(0, 10)
//                .reduce("", (s, integer) -> (s.isEmpty() ? s : (s + ",")) + integer)
//                .subscribe(printConsumer());

//        Observable.range(0, 10)
//                .all(integer -> integer < 10)
//                .subscribe(printConsumer());

//        Observable.range(0, 10)
//                .any(integer -> integer < 10)
//                .subscribe(printConsumer());

//        Observable.range(0, 10)
//                .contains(5)
//                .subscribe(printConsumer());
    }

    private void collectionOperators() {
//        Observable.range(0, 10)
//                .toList().subscribe(printConsumer());

//        Observable.range(0, 500)
//                .toList(500).subscribe(printConsumer());

//        Observable.range(0, 500)
//                .toList(() -> new ArrayList<>(500)).subscribe(printConsumer());

//        Observable.just(4, 3, 7, 9, 5, 1, 2, 0, 6)
//                .toSortedList().subscribe(printConsumer());

//        Observable.just(4, 3, 7, 9, 5, 1, 2, 0, 6)
//                .toSortedList(9).subscribe(printConsumer());

//        Observable.just(4, 3, 7, 9, 5, 1, 2, 0, 6)
//                .toSortedList(Functions.naturalComparator()).subscribe(printConsumer());

//        Observable.just(4, 3, 7, 9, 5, 1, 2, 0, 6)
//                .toSortedList(Functions.naturalComparator(), 9).subscribe(printConsumer());

//        Observable.just(4, 3, 7, 9, 5, 1, 2, 0, 6)
//                .toMap(integer -> "key" + integer)
//                .subscribe(printConsumer());

//        Observable.just(4, 3, 7, 9, 5, 1, 2, 0, 6)
//                .toMap(integer -> "key" + integer, integer -> "value" + integer)
//                .subscribe(printConsumer());

//        Observable.just(4, 3, 7, 9, 5, 1, 2, 0, 6)
//                .toMap(integer -> "key" + integer, integer -> "value" + integer, HashMap::new)
//                .subscribe(printConsumer());

//        Observable.just("Alpha",	"Beta",	"Gamma",	"Delta",	"Epsilon")
//                .toMultimap(String::length)
//                .subscribe(printConsumer());

//        Observable.just("Alpha",	"Beta",	"Gamma",	"Delta",	"Epsilon")
//                .toMultimap(String::length, s -> s.charAt(0))
//                .subscribe(printConsumer());

//        Observable.just("Alpha",	"Beta",	"Gamma",	"Delta",	"Epsilon")
//                .toMultimap(String::length, s -> s.substring(0, 2), HashMapSupplier.asCallable())
//                .subscribe(printConsumer());

//        Observable.just("Alpha",	"Beta",	"Gamma",	"Delta",	"Epsilon")
//                .toMultimap(String::length, s -> s.substring(0, 2), HashMapSupplier.asCallable(), integer -> new ArrayList<>())
//                .subscribe(printConsumer());

//        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                .collect(ArrayList::new, ArrayList::add).subscribe(printConsumer());

//        Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                .collectInto(new ArrayList<>(), ArrayList::add).subscribe(printConsumer());
    }

    private void errorRecoveryOperators() {
//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i).subscribe(printConsumer(), errorConsumer());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i).onErrorReturnItem(-1)
//                .subscribe(printConsumer(), errorConsumer());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i).onErrorReturn(throwable -> -1)
//                .subscribe(printConsumer(), errorConsumer());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(integer -> {
//                    try {
//                        return 10 / integer;
//                    } catch (Exception e) {
//                        return -1;
//                    }
//                })
//                .subscribe(printConsumer(), errorConsumer());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .onErrorResumeNext(throwable -> {
//                    return Observable.just(-1);
//                })
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .onErrorResumeNext(Observable.just(-1))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .onExceptionResumeNext(Observable.just(-1))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .retry((integer, throwable) -> integer < 2)
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .retry(1)
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .retry(new Predicate<Throwable>() {
//                    private int index = 0;
//
//                    @Override
//                    public boolean test(Throwable throwable) throws Exception {
//                        return ++index < 3;
//                    }
//                })
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .retry(1, new Predicate<Throwable>() {
//                    private int index = 0;
//
//                    @Override
//                    public boolean test(Throwable throwable) throws Exception {
//                        return ++index < 3;
//                    }
//                })
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .retryUntil(new BooleanSupplier() {
//                    private int index = 0;
//
//                    @Override
//                    public boolean getAsBoolean() throws Exception {
//                        return ++index >= 2;
//                    }
//                })
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

        // 这是一个错误的使用方式，不要使用range，range发送完一个后立刻complete，会导致上一个retry检测isDispose为true就return了。
//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .subscribeOn(Schedulers.io())
//                .retryWhen(throwableObservable ->
//                        throwableObservable.zipWith(Observable.range(1, 1), (throwable, integer) -> integer))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

        // 这才是正确的使用姿势，使用takeWhile，此处会在第二次检测才complete，因此不会影响上一次的retry。
//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .retryWhen(throwableObservable -> {
//                    AtomicInteger counter = new AtomicInteger();
//                    return throwableObservable.takeWhile(e -> counter.getAndIncrement() < 1);
//                })
//                .subscribe(printConsumer(), errorConsumer(), completeAction());

//        Observable.just(5, 2, 4, 0, 3, 2, 8)
//                .map(i -> 10 / i)
//                .retryWhen(throwableObservable ->
//                        throwableObservable.zipWith(Observable.range(1, 3), (throwable, integer) -> integer)
//                                .delay(integer -> Observable.timer(integer * 3000, TimeUnit.MILLISECONDS)))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
    }

    private void actionOperators() {
//
//        Observable.just(1)
//                .doOnNext(integer -> log("doOnNext " + integer))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(3)
//                .doOnComplete(() -> log("doOnComplete"))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(4)
//                .doOnError(throwable -> log("doOnError"))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(8)
//                .doAfterTerminate(() -> log("doAfterTerminate"))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(6)
//                .doOnTerminate(() -> log("doOnTerminate"))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(7)
//                .doOnEach(integerNotification -> log("doOnEach isOnNext " + integerNotification.isOnNext()))
//                .doOnEach(integerNotification -> log("doOnEach isOnComplete" + integerNotification.isOnComplete()))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(77)
//                .doOnEach(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        log("doOnEach isOnNext true");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        log("doOnEach isOnError true");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        log("doOnEach isOnComplete true");
//                    }
//                })
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(2)
//                .doAfterNext(integer -> log("doAfterNext " + integer))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(0)
//                .doOnSubscribe(disposable -> log("doOnSubscribe"))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(5)
//                .doOnDispose(() -> log("doOnDispose"))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
//
//        log("------分隔符------");
//
//        Observable.just(10)
//                .doOnLifecycle(disposable -> log("doOnSubscribe"),() -> log("doOnDispose"))
//                .subscribe(printConsumer());
//
//        log("------分隔符------");
//
//        Observable.just(9)
//                .doFinally(() -> log("doFinally"))
//                .subscribe(printConsumer(), errorConsumer(), completeAction());
    }

    private <T> Consumer<? super T> printConsumer() {
        return integer -> log(String.valueOf(integer));
    }

    private Consumer<? super Throwable> errorConsumer() {
        return throwable -> log("error: " + (throwable.getMessage() != null ? throwable.getMessage() : throwable.getClass().getSimpleName()));
    }

    private Action successAction() {
        return () -> log("onSuccess");
    }

    private Action completeAction() {
        return () -> log("onComplete");
    }

    private <T> Observer<T> printObserver() {
        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                log("onSubscribe");
            }

            @Override
            public void onNext(T o) {
                log("onNext " + String.valueOf(o));
            }

            @Override
            public void onError(Throwable e) {
                log("onError");
            }

            @Override
            public void onComplete() {
                log("onComplete");
            }
        };
    }

    private Observer<Object> lifecycleObserver() {
        return new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                log("onNext");
            }

            @Override
            public void onError(Throwable e) {
                log("onError");
            }

            @Override
            public void onComplete() {
                log("onComplete");
            }
        };
    }

    private void log(String msg) {
        Log.d(TAG, msg);
    }

    private void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

