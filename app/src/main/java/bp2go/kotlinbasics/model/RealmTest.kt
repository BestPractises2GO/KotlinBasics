package bp2go.kotlinbasics.model

import io.realm.RealmObject

class RealmPerson(
        var name: String? = null,
        var age: Int? = null
) : RealmObject()