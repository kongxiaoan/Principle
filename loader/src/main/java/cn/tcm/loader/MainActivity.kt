package cn.tcm.loader

import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SimpleCursorAdapter
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 实现LoaderCallbacks 重写方法
 */
class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {
	private lateinit var mAdapter: SimpleCursorAdapter
	private lateinit var loader: CursorLoader
	/**
	 * 完成对UI控件的更新 如果不在使用 将自动释放Loader的数据 不需要使用close
	 */
	override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
		mAdapter.swapCursor(data)
	}

	override fun onLoaderReset(loader: Loader<Cursor>?) {
		mAdapter.swapCursor(null)
	}

	/**
	 * 首先检查指定的ID是否存在，如果存在才会触发该方法，通过该方法才能常见一个Loader
	 */
	override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
		/**
		 * 查询音乐数据库 获取音乐数据 并排序
		 */
		loader = CursorLoader(this, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
				null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER)
		return loader
	}

	companion object {
		val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: Int = 1000
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		checkPermission()
		initMusic()
	}

	private fun checkPermission() {
		if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
				//请求存储和位置
				ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,
						android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE)
			}
		}

	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if (requestCode == PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
			if (!(!grantResults.isNotEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED)) {

			} else {
				AlertDialog.Builder(this)
						.setTitle("警告")
						.setMessage("缺少该权限不能读取音乐")
						.setPositiveButton("OK") { dialog, which ->
							finish()
						}
						.create()
						.show()
			}

		}
	}

	private fun initMusic() {
		/**
		 * 注意创建的时候不传递数据
		 */
		mAdapter = SimpleCursorAdapter(this, R.layout.item, null, arrayOf(MediaStore.Audio.Media.TITLE,
				MediaStore.Audio.Media.ARTIST), intArrayOf(R.id.music_name, R.id.music_singer), 0)

		mListView.adapter = mAdapter
		//通过异步的方式添加数据
		val loaderManager = loaderManager
		/**
		 * arg1:id
		 * arg2:bundle 数据
		 * arg3:LoaderCallbacks
		 */
		loaderManager.initLoader(0, null, this)
	}
}
