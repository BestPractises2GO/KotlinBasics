package bp2go.kotlinbasics

import bp2go.kotlinbasics.utils.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerTest : SchedulerProvider{

     override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

     override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

     override fun io(): Scheduler {
        return Schedulers.trampoline()
    }
}