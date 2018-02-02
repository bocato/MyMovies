package br.com.zup.mymovies.view.activities

import android.support.design.widget.Snackbar
import android.view.MenuItem
import br.com.zup.mymovies.R
import br.com.zup.mymovies.view.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {
    override fun getContentLayoutId(): Int {
        return R.layout.activity_movie_detail;
    }

    override fun initInjectors() {
    }

    override fun initBinding() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings ->
                return true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
