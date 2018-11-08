package alexf.com.br.techstore

import android.content.Context

class ExampleUsingSingletonHolder private constructor(context: Context) {
    init {
        // Init using context argument
    }

    companion object : SingletonHolder<ExampleUsingSingletonHolder, Context>(::ExampleUsingSingletonHolder)
}