package alexf.com.br.techstore.ui.activity

import alexf.com.br.techstore.AppDatabase
import alexf.com.br.techstore.R
import alexf.com.br.techstore.dao.ProductDao
import alexf.com.br.techstore.ui.activity.recyclerview.ProductsListAdapter
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_products_list.*

class ProductsListActivity : AppCompatActivity() {

    private lateinit var adapter: ProductsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_list)

        val database = AppDatabase.getInstance(this)
        val productsLiveData = database.productDao().all()

        productsLiveData.observe(this, Observer { products ->
            products?.let {
                adapter.replaceAllProducts(it)
            }
        })

        configureRecyclerView()
        configureFabAddProduct()
    }

    private fun configureFabAddProduct() {
        product_list_add_product.setOnClickListener {
            val openProductForm = Intent(this, FormProductActivity::class.java)
            startActivity(openProductForm)
        }
    }

    private fun configureRecyclerView() {
        this.adapter = ProductsListAdapter(context = this)
        products_list_recyclerview.adapter = adapter
    }

}
