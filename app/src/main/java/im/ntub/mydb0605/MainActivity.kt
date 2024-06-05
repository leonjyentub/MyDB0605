package im.ntub.mydb0605

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtName = findViewById<TextView>(R.id.edtName)
        val txtScore = findViewById<TextView>(R.id.edtScore)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val db = AppDatabase.getInstance(this)
        btnSave.setOnClickListener {
            val name = txtName.text.toString()
            val score = txtScore.text.let{
                Integer.parseInt(it.toString())
            }
            GlobalScope.launch {
                val row_id = db.userDao().insert(User(name = name, score = score, updatedTime = LocalDateTime.now()))
                if(row_id > 0){
                    Snackbar.make(it, "成功新增-$row_id", Snackbar.LENGTH_LONG).show()
                }else{
                    Snackbar.make(it, "失敗了 !_!", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}