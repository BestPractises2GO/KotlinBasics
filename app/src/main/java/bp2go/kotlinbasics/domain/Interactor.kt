package bp2go.kotlinbasics.domain

import bp2go.kotlinbasics.injection.module.SCHEDULER_IO
import bp2go.kotlinbasics.injection.module.SCHEDULER_MAIN_THREAD
import bp2go.kotlinbasics.model.network.UserWebservice
import bp2go.kotlinbasics.utils.SchedulerProviderImp
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

class Interactor @Inject constructor(val apiService: UserWebservice, @Named(SCHEDULER_IO) val subscribeOnScheduler: Scheduler, @Named(SCHEDULER_MAIN_THREAD) val observeOnScheduler: Scheduler) {

    fun getUser(id: String) {
        apiService.getUserRx(id)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe(
                        { res ->
                            println("ich komme rein")
                            print(res) },
                        { err ->
                            println("ich komme rein2")
                            print(err) }
                )

    }

}