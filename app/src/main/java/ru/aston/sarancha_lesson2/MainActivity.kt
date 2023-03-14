package ru.aston.sarancha_lesson2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.aston.sarancha_lesson2.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val editTextSubject = PublishSubject.create<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        with(binding) {

            btnObservable.setOnClickListener {
                btnObservableAction()
            }

            btnFlowable.setOnClickListener {
                btnFlowableAction()
            }

            btnCompletable.setOnClickListener {
                btnCompletableAction()
            }

            btnMaybe.setOnClickListener {
                btnMaybeAction()
            }

            btnSingle.setOnClickListener {
                btnSingleAction()
            }

            editText.doOnTextChanged { text, _, _, _ ->
                editTextSubject.onNext(text.toString())
            }

            showTextWithDebounce()

            btnParityCheck.setOnClickListener {
                threeSubscribers()
            }

            btnConcatAndMerge.setOnClickListener {
                concatAndMerge()
            }

            btnDivision.setOnClickListener {
                Log.d("@@@", "result: ${divisionAndZip(84, 2)}")
            }
        }
    }

    private fun btnObservableAction() {
        val observableSource = Observable.just(1, 2, 3, 4, 5, 6)
        observableSource
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "Observable: Next int: $it")
                },
                {
                    Log.d("@@@", "Observable: Error")
                },
                {
                    Log.d("@@@", "Observable: Complete")
                }
            )
    }

    private fun btnFlowableAction() {
        val randomBackpressureStrategy = when ((0..4).random()) {
            0 -> BackpressureStrategy.MISSING
            1 -> BackpressureStrategy.BUFFER
            2 -> BackpressureStrategy.LATEST
            3 -> BackpressureStrategy.DROP
            else -> BackpressureStrategy.ERROR
        }
        Flowable.create({ subscriber ->
            for (i in 0..500) {
                subscriber.onNext(i)
            }
            subscriber.onComplete()
        }, randomBackpressureStrategy)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "Flowable: Next int: $it")
                },
                {
                    Log.d("@@@", "Flowable ($randomBackpressureStrategy): Error")
                },
                {
                    Log.d("@@@", "Flowable ($randomBackpressureStrategy): Complete")
                }
            )
    }

    private fun btnCompletableAction() {
        val completableSource = Completable.timer(1500, TimeUnit.MILLISECONDS)
        completableSource
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "Completable: Success")
                },
                {
                    Log.d("@@@", "Completable: Error")
                }
            )
    }

    private fun btnMaybeAction() {
        val obs1 =
            Maybe.just("The Answer to the Ultimate Question of Life, the Universe, and Everything is ")
        val obs2 = Maybe.just(42)
        Maybe.merge(obs1, obs2)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "$it")
                },
                {
                    Log.d("@@@", "Maybe: Error")
                },
                {
                    Log.d("@@@", "Maybe: Complete")
                }
            )
    }

    private fun btnSingleAction() {
        val singleSource = Single.timer(1, TimeUnit.SECONDS)
        singleSource
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "Single: Success")
                },
                {
                    Log.d("@@@", "Single: Error")
                }
            )
    }

    private fun showTextWithDebounce() {
        editTextSubject.debounce(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.tvResult.text = it
            }
    }

    private fun threeSubscribers() {

        val oneHundredSource = Observable.range(1, 100)

        oneHundredSource
            .filter { i -> i % 2 == 0 }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "Even number: $it")
                },
                {
                    Log.d("@@@", "Even number: Error")
                },
                {
                    Log.d("@@@", "Parity check: Complete")
                }
            )

        oneHundredSource
            .subscribeOn(Schedulers.computation())
            .takeLast(10)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "Last ten: $it")
                },
                {
                    Log.d("@@@", "Last ten: Error")
                },
                {
                    Log.d("@@@", "Last ten: Complete")
                }
            )

        oneHundredSource
            .filter { i -> i % 3 == 0 && i % 5 == 0 }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "Divider 3 and 5: $it")
                },
                {
                    Log.d("@@@", "Divider 3 and 5r: Error")
                },
                {
                    Log.d("@@@", "Divider 3 and 5: Complete")
                }
            )
    }

    private fun concatAndMerge() {
        val firstFiftySource = Observable.range(1, 50)
        val secondFiftySource = Observable.range(51, 50)

        Observable.concat(firstFiftySource, secondFiftySource)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "Concat: $it")
                },
                {
                    Log.d("@@@", "Concat: Error")
                },
                {
                    Log.d("@@@", "Concat: Complete")
                }
            )

        Observable.merge(
            Observable.interval(1, TimeUnit.SECONDS).map { id -> "A$id" },
            Observable.interval(1, TimeUnit.SECONDS).map { id -> "B$id" })
            .subscribe(System.out::println)

        Observable.merge(firstFiftySource, secondFiftySource)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("@@@", "Merge: $it")
                },
                {
                    Log.d("@@@", "Merge: Error")
                },
                {
                    Log.d("@@@", "Merge: Complete")
                }
            )
    }

    private fun divisionAndZip(dividend: Int, divisor: Int) {
        val dividendObservable = Observable.just(dividend.toLong())
        val divisorObservable = Observable.just(divisor.toLong())
        val zipper = BiFunction<Long, Long, Long> { dividend, divisor -> dividend.div(divisor) }
        Observable.zip(dividendObservable, divisorObservable, zipper)
            .subscribe {
                Log.d("@@@", "Division result: $dividend/$divisor = $it")
            }
    }
}