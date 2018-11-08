package alexf.com.br.techstore

import alexf.com.br.techstore.dao.ProductDao
import alexf.com.br.techstore.model.Product
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

}