package ni.edu.uca.pathwayandroidroom.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ni.edu.uca.pathwayandroidroom.data.database.dao.WordsDao
import ni.edu.uca.pathwayandroidroom.data.database.entities.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordsRoomDatabase: RoomDatabase() {

    abstract fun WordsDao(): WordsDao

    companion object {
        @Volatile
        private var INSTANCE: WordsRoomDatabase? = null

        fun getDatabase(context: Context): WordsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordsRoomDatabase::class.java,
                    "words_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}