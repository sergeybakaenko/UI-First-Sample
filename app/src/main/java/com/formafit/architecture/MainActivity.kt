package com.formafit.architecture

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.formafit.architecture.core.ActivityOperations
import com.formafit.architecture.core.Core
import com.formafit.architecture.core.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), ActivityOperations, KodeinAware {
    private val core: Core by instance()

    override val kodein: Kodein by kodein()

    override var disableBackButton: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        core.registerActivity(this)
        core.registerRouting(Router(this))
    }

    override fun onBackPressed() {
        if (!disableBackButton) {
            super.onBackPressed()
        }
    }

    override fun showToast(text: String) {
        GlobalScope.launch(Dispatchers.Main) {
            Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
        }
    }
}
