package alexf.com.br.techstore

import alexf.com.br.techstore.dao.ProductDao
import alexf.com.br.techstore.model.Product
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
                AppDatabase::class.java, "techstore-database")
                .build()
    })
}

/*
object Database {

    @Volatile
    lateinit var database: AppDatabase

    fun instance(context: Context): AppDatabase {
        synchronized(this) {
            if (::database.isInitialized) return database
            database = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "techstore-database")
                    .build()
            return database
        }
    }
}
*/