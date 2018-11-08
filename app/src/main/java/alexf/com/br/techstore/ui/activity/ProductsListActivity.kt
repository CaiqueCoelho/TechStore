package alexf.com.br.techstore.ui.activity

import alexf.com.br.techstore.AppDatabase
import alexf.com.br.techstore.R
import alexf.com.br.techstore.dao.ProductDao
import alexf.com.br.techstore.model.Product
import alexf.com.br.techstore.ui.activity.recyclerview.ProductsListAdapter
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_products_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException

class ProductsListActivity : AppCompatActivity() {

    private lateinit var productDao: ProductDao
    private lateinit var adapter: ProductsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)

        val database = Room.databaseBuilder(
                this,
                AppDatabase::class.java,
                "techstore-database")
                .build()
        productDao = database.productDao()

        configureRecyclerView()
        configureFabAddProduct()
    }

    private fun configureFabAddProduct() {
        product_list_add_product.setOnClickListener {
            val openProductForm = Intent(this, FormProductActivity::class.java)
            startActivity(openProductForm)
        }
    }

    override fun onResume() {
        super.onResume()
        runBlocking { getProducts() }
    }

    private suspend fun getProducts(){
        var products : List<Product> = mutableListOf()
        withContext(Dispatchers.IO) {
            try {
                products = productDao.all()
            } catch (e: IOException) {
                Log.e("GET", e.message)
            }
        }

        adapter.replaceAllProducts(products)
    }

    private fun configureRecyclerView() {
        this.adapter = ProductsListAdapter(context = this)
        products_list_recyclerview.adapter = adapter
    }

}
