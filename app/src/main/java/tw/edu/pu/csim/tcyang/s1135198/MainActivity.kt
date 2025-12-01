package tw.edu.pu.csim.tcyang.s1135198

import android.os.Bundle
import android.content.pm.ActivityInfo // 導入 ActivityInfo
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import tw.edu.pu.csim.tcyang.s1135198.ui.theme.S1135198Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. *** 强制螢幕為直式 (PORTRAIT) ***
        // 雖然推薦在 Manifest 中設定，但如果要在程式碼中設定，就放在這裡
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // 2. 隱藏上方狀態列及下方巡覽列 (全螢幕)
        hideSystemBars()

        setContent {
            S1135198Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "鄭姿佳", // 這裡將名稱改為「鄭姿佳」以配合 App 名稱主題
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    // 實現全螢幕沉浸模式的函數
    private fun hideSystemBars() {
        // 1. 確保內容延伸到系統列區域
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 2. 取得 Insets Controller
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)

        // 3. 隱藏系統列
        windowInsetsController?.let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            // 設定行為，允許使用者滑動邊緣暫時顯示系統列
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    // 4. 可選：在失去焦點後重新獲得焦點時，再次隱藏系統列，確保持久性
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemBars()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    S1135198Theme {
        Greeting("Android")
    }
}