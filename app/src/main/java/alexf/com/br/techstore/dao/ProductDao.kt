package alexf.com.br.techstore.dao

import alexf.com.br.techstore.model.Product
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ProductDao {

    companion object {
        private val products: MutableList<Product> = mutableListOf()
    }

    @Query("SELECT * FROM product")
    fun all (): LiveData<List<Product>>

    @Insert
    fun add(vararg product: Product)

}
