package ni.edu.uca.pathwayandroidroom

import android.app.Application
import ni.edu.uca.pathwayandroidroom.data.database.WordsRoomDatabase

class WordsApplication: Application() {

    val database: WordsRoomDatabase by lazy { WordsRoomDatabase.getDatabase(this) }
}