package bp2go.kotlinbasics.model

import io.realm.RealmObject

open class RealmPerson(
        var name: String? = null,
        var age: Int? = null
) : RealmObject()