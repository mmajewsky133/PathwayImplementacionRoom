package ni.edu.uca.pathwayandroidroom.data.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ni.edu.uca.pathwayandroidroom.data.database.entities.Word

@Dao
interface WordsDao {

    @Query("SELECT * from word ORDER BY word ASC")
    fun getWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Word)

    @Update
    suspend fun update(word: Word)

    @Delete
    suspend fun delete(word: Word)
}